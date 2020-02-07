import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class ServerConnection {

	static ServerConnection sharedInstance = new ServerConnection();
	static Logger logger = LogManager.getLogger(ServerConnection.class);

	private ServerConnection () {}

	public void startEndPoint(ArrayList<Bookmaker> bookmakerList) throws IOException {

		int serverPort = 8000;
		logger.info("Server starting on port: " + serverPort);
		HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
		server.createContext("/api/bookmaker", (exchange -> {

			if ("GET".equals(exchange.getRequestMethod())) {

				Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getRawQuery());

				BookmakerArrayFilter bookmakerArrayFilter = new BookmakerArrayFilter();
				ArrayList<Bookmaker> filteredList = new ArrayList<Bookmaker> ();
				AverageQuota average = new AverageQuota();

				String round = params.getOrDefault("round", List.of("")).stream().findFirst().orElse("");
				String host = params.getOrDefault("host", List.of("")).stream().findFirst().orElse("");
				String guest = params.getOrDefault("guest", List.of("")).stream().findFirst().orElse("");

				logger.debug("Query parameters - round: " + round + " host: " + host + " guest: " + guest);

				if (round.equalsIgnoreCase("") && host.equalsIgnoreCase("") && guest.equalsIgnoreCase("")) {

					filteredList = bookmakerArrayFilter.filter(bookmakerList);
				} else if (host.equalsIgnoreCase("") && guest.equalsIgnoreCase("")) {

					filteredList = bookmakerArrayFilter.filter(bookmakerList, round);
				} else if (round.equalsIgnoreCase("")) {

					filteredList = bookmakerArrayFilter.filter(bookmakerList, host, guest);
				} else {

					filteredList = bookmakerArrayFilter.filter(bookmakerList, round, host, guest);
				}

				average = average.calculateAverages(filteredList);
				BookmakerAverageManager bookmakerManager = new BookmakerAverageManager(filteredList, average);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				logger.debug("Building Json");

				String result = gson.toJson(bookmakerManager, bookmakerManager.getClass());
				exchange.sendResponseHeaders(200, result.getBytes().length);

				OutputStream output = exchange.getResponseBody();
				output.write(result.getBytes());
				output.flush();
				logger.debug("Flushed successfully!");
			} else {
				exchange.sendResponseHeaders(405, -1);
			}
			exchange.close();
		}));
		
		server.setExecutor(null);
		server.start();
	}

	public static Map<String, List<String>> splitQuery(String query) {
		
		if (query == null || "".equals(query)) {
			return Collections.emptyMap();
		}

		return Pattern.compile("&").splitAsStream(query)
				.map(s -> Arrays.copyOf(s.split("="), 2))
				.collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));
	}

	private static String decode(final String encoded) {
		
		try {
			return encoded == null ? null : URLDecoder.decode(encoded, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 is a required encoding", e);
		}
	}
}

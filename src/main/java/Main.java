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
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class Main {

	static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		logger.info("Starting application!");
		
		ArrayList<Bookmaker> bookmakerListFromDatabase = new ArrayList<Bookmaker> ();
		ArrayList<Bookmaker> bookmakerList = new ArrayList<Bookmaker> ();

		List<Thread> threadList = new ArrayList<Thread>();
		
		BookmakerManager bookmakerManager = new BookmakerManager();
		GenerateBookmakerTask task = new GenerateBookmakerTask(bookmakerManager);
			
		for (String path: task.paths) {

			Thread thread = new Thread(task);
			thread.start();
			threadList.add(thread);
		}
		
		for (Thread t : threadList) {
			
			try {
				t.join();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		logger.info("All the threads are completed!");

		bookmakerList.addAll(bookmakerManager.bookmakerList);
		
		DatabaseConnection.sharedInstance.insertIntoDatabase(bookmakerList);
		
		bookmakerListFromDatabase = DatabaseConnection.sharedInstance.retrieveFromDatabase();
		
		ServerConnection.sharedInstance.startEndPoint(bookmakerListFromDatabase);
	}
}


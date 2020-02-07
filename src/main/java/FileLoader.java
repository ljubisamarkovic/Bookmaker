import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileLoader {

	protected ConfigLoader configReader = new ConfigLoader();
	static FileLoader sharedInstance = new FileLoader();
	static Logger logger = LogManager.getLogger(FileLoader.class);

	private FileLoader() {}

	public ArrayList<String> readInputPaths() {

		ArrayList<String> paths = new ArrayList<String>();

		File folder = new File(configReader.getReadLocation());

		if (folder.exists() && folder.isDirectory()) {

			for (File file: folder.listFiles()) {

				paths.add(file.getAbsolutePath());
			}
		}

		logger.info("Numbers of files loaded: " + paths.size());
		return paths;
	}

	private void moveToNewFolder(String fileName) {

		try {
			FileLoader.sharedInstance.moveFilesToNewPath(fileName, configReader.getStoreLocation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moveFilesToNewPath(String oldPath, String newPath) throws Exception {

		File file = new File(oldPath);
		File folder = new File(newPath);

		if (folder.exists() || folder.mkdir()) {
			
			Files.move(Paths.get(oldPath), Paths.get(newPath + "/" + file.getName()), StandardCopyOption.ATOMIC_MOVE);
			logger.debug("File " + file.getName() + " successfully moved");
		} else {

			logger.error("Failed to move file: " + file.getName());
			throw new Exception("Failed!");
		}
	}

	public ArrayList<Bookmaker> readFromFile(String fileName) {

		ArrayList<Bookmaker> bookmakerList = new ArrayList <> ();
		logger.info("Reading files from folder");

		if (fileName.endsWith(".json")) {

			bookmakerList = readFromJSON(fileName);
		} else if (fileName.endsWith(".csv")) {

			bookmakerList = readFromCSV(fileName);
		} else if (fileName.endsWith(".xml")) {

			bookmakerList = readFromXML(fileName);
		} else {

			try {
				moveFilesToNewPath(fileName, configReader.getInvalidLocation());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		logger.info("Numbers of records loaded from file: " + bookmakerList.size());
		return bookmakerList;
	}

	private ArrayList<Bookmaker> readFromJSON(String filePath) {

		ArrayList<Bookmaker> bookmakerList = new ArrayList <> ();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(filePath));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("Bookmaker");

			for (int i = 0; i < jsonArray.size(); i++) {

				JSONObject object = (JSONObject) jsonArray.get(i);

				int bookmakerID = Integer.parseInt(object.get("bookmakerId").toString());
				int round = Integer.parseInt(object.get("round").toString());
				String homeTeam = object.get("homeTeam").toString();
				String awayTeam = object.get("awayTeam").toString();
				Double homeWinQuota = Double.parseDouble(object.get("homeWinQuota").toString());
				Double awayWinQuota = Double.parseDouble(object.get("awayWinQuota").toString());
				Double tieQuota = Double.parseDouble(object.get("tieQuota").toString());

				Bookmaker bookmaker = new Bookmaker(bookmakerID, round, homeTeam, awayTeam, homeWinQuota, awayWinQuota, tieQuota);

				bookmakerList.add(bookmaker);
			}

			moveToNewFolder(filePath);
		} catch (Exception e) {
			logger.error("Failed to load .json file");
			e.printStackTrace();
		}

		return bookmakerList;
	}

	private ArrayList<Bookmaker> readFromXML(String filePath) {

		ArrayList<Bookmaker> bookmakerList = new ArrayList <> ();

		try {

			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("bookmaker");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					int bookmakerID = Integer.parseInt(eElement.getAttribute("bookmakerId"));
					int round = Integer.parseInt(eElement.getElementsByTagName("round").item(0).getTextContent());
					String homeTeam = eElement.getElementsByTagName("homeTeam").item(0).getTextContent();
					String awayTeam = eElement.getElementsByTagName("awayTeam").item(0).getTextContent();
					Double homeWinQuota = Double.parseDouble(eElement.getElementsByTagName("homeWinQuota").item(0).getTextContent());
					Double awayWinQuota = Double.parseDouble(eElement.getElementsByTagName("awayWinQuota").item(0).getTextContent());
					Double tieQuota = Double.parseDouble(eElement.getElementsByTagName("tieQuota").item(0).getTextContent());

					Bookmaker bookmaker = new Bookmaker(bookmakerID, round, homeTeam, awayTeam, homeWinQuota, awayWinQuota, tieQuota);

					bookmakerList.add(bookmaker);
				}
			}

			moveToNewFolder(filePath);
		} catch (Exception e) {
			logger.error("Failed to load .xml file");
			e.printStackTrace();
		}
		
		return bookmakerList;
	}

	private ArrayList<Bookmaker> readFromCSV(String fileName) {

		ArrayList<Bookmaker> list = new ArrayList<>();

		File fXmlFile = new File(fileName);
		Path pathToFile = Paths.get(fXmlFile.getAbsolutePath());

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			String line = br.readLine();

			while (line != null) {

				String[] attributes = line.split(",");
				Bookmaker bookmaker = new Bookmaker(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), attributes[2], attributes[3], 
						Double.parseDouble(attributes[4]), Double.parseDouble(attributes[5]), Double.parseDouble(attributes[6]));

				list.add(bookmaker);

				line = br.readLine();
			}

			moveToNewFolder(fileName);

		} catch (IOException ioe) {
			logger.error("Failed to load .csv file");
			ioe.printStackTrace();
		}

		return list;
	}

}

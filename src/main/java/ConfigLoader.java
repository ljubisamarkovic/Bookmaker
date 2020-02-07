import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConfigLoader {

	static Logger logger = LogManager.getLogger(ConfigLoader.class);

	private String databaseUrl;
	private String username;
	private String password;
	private String readLocation;
	private String storeLocation;
	private String invalidLocation;

	public ConfigLoader() {

		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties properties = new Properties();

			if (inputStream.toString() == null) {
				logger.fatal("Configuration file is empty!");
				System.exit(0);
			} else {
				properties.load(inputStream);

				databaseUrl = properties.getProperty("dbURL");
				username = properties.getProperty("user");
				password = properties.getProperty("pass");
				readLocation = properties.getProperty("basePath");
				storeLocation = properties.getProperty("pathToMove");
				invalidLocation = properties.getProperty("invalidFilesPath");

				inputStream.close();
			}
		} catch (Exception e) {
			logger.fatal("Config.properties file not found!.");
			System.exit(0);
		}
	}

	public String getUsername() {
		return username; 
	}

	public String getPassword() {
		return password; 
	}

	public String getDatabaseUrl() {
		return databaseUrl; 
	}

	public String getReadLocation() {
		return readLocation; 
	}

	public String getStoreLocation() {
		return storeLocation; 
	}
	
	public String getInvalidLocation() {
		return invalidLocation;
	}
}



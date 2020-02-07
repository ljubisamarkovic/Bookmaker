import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DatabaseConnection {
	
	public ConfigLoader configReader = new ConfigLoader();
	static DatabaseConnection sharedInstance = new DatabaseConnection();
	static Logger logger = LogManager.getLogger(DatabaseConnection.class);
	
	private DatabaseConnection() {}

	public ArrayList<Bookmaker> retrieveFromDatabase () {

		Connection connection = null;
		ArrayList<Bookmaker> list = new ArrayList<Bookmaker> ();

		try {

			connection = DriverManager.getConnection(configReader.getDatabaseUrl(), configReader.getUsername(), configReader.getPassword());
			
			if (connection != null) {
				logger.info("Connected to database");

				PreparedStatement pst = connection.prepareStatement("SELECT * FROM bookmaker");
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					Bookmaker bookmaker = new Bookmaker(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7));
					
					list.add(bookmaker);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			logger.fatal("Failed connecting to database." + configReader.getDatabaseUrl());
		}

		logger.info("Records retrieved from database: " + list.size());
		return list;
	}

	public void insertIntoDatabase(ArrayList<Bookmaker> list) {

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(configReader.getDatabaseUrl(), configReader.getUsername(), configReader.getPassword());
			Statement stm = connection.createStatement();

			for (Bookmaker bookmaker : list) {

				String values = bookmaker.getBookmakerID() + "," + bookmaker.getRound() + ",'" + bookmaker.getHomeTeam() + "','" + bookmaker.getAwayTeam() + "'," +
						bookmaker.getHomeWinQuota() + "," + bookmaker.getAwayWinQuota() + "," + bookmaker.getTieQuota();
				stm.executeUpdate("INSERT INTO bookmaker (bookmakerid, round, hometeam, awayteam, homewinquota, awaywinquota, tiequota) VALUES(" + values + ")");
			}

			stm.close();
			connection.close();
		} catch (Exception e) {
			logger.fatal("Insertion to database failed");
			System.out.println(e);
		}
	}
}


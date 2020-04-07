package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DatabaseConnection {

	public final ConfigLoader configReader = new ConfigLoader();
	public static final DatabaseConnection sharedInstance = new DatabaseConnection();
	static Logger logger = LogManager.getLogger(DatabaseConnection.class);

	private DatabaseConnection() {}

	public List<Bookmaker> retrieveFromDatabase () {

		logger.debug("Connecting to postgres database");
		List<Bookmaker> list = new ArrayList <>();
		
		try (Connection connection = DriverManager.getConnection(configReader.getDatabaseUrl(), configReader.getUsername(), configReader.getPassword());
				PreparedStatement pst = connection.prepareStatement("SELECT * FROM bookmaker");
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {

				Bookmaker bookmaker = new Bookmaker(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7));
				list.add(bookmaker);
			}

		} catch (SQLException ex) {
			logger.fatal("Failed connecting to database." + configReader.getDatabaseUrl());
			return new ArrayList <>();
		}

		logger.info("Records retrieved from database: " + list.size());
		return list;
	}

	public void insertIntoDatabase(List<Bookmaker> list) {

		logger.debug("Inserting data into database");
		String query = "INSERT INTO bookmaker (bookmakerid, round, hometeam, awayteam, homewinquota, awaywinquota, tiequota) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(configReader.getDatabaseUrl(), configReader.getUsername(), configReader.getPassword());
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			for (Bookmaker bookmaker : list) {

				preparedStatement.setInt(1, bookmaker.getBookmakerID());
				preparedStatement.setInt(2, bookmaker.getRound());
				preparedStatement.setString(3, bookmaker.getHomeTeam());
				preparedStatement.setString(4, bookmaker.getAwayTeam());
				preparedStatement.setDouble(5, bookmaker.getHomeWinQuota());
				preparedStatement.setDouble(6, bookmaker.getAwayWinQuota());
				preparedStatement.setDouble(7, bookmaker.getTieQuota());

				preparedStatement.executeUpdate();
			}

			logger.debug("Data successfully inserted");

		} catch (Exception e) {
			logger.fatal("Insertion to database failed - " + e.getMessage());
		} 
	}
}

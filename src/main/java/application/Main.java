package application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.Bookmaker;
import model.BookmakerManager;
import model.DatabaseConnection;
import model.GenerateBookmakerTask;
import model.ServerConnection;

public class Main {

	static Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		logger.info("Starting application!");
		
		List<Bookmaker> bookmakerListFromDatabase;
		List<Thread> threadList = new ArrayList <>();
		
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
			    Thread.currentThread().interrupt();
			    logger.debug("Thread has been interrupted!", e);
			  }
		}

		logger.info("All the threads are completed!");

		DatabaseConnection.sharedInstance.insertIntoDatabase(bookmakerManager.getBookmakerList());
		
		bookmakerListFromDatabase = DatabaseConnection.sharedInstance.retrieveFromDatabase();
		
		ServerConnection.sharedInstance.startEndPoint(bookmakerListFromDatabase);
	}
}




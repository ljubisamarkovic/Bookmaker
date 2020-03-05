package model;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookmakerManager {
	
	protected List<Bookmaker> bookmakerList = new CopyOnWriteArrayList <>();

	public List<Bookmaker> getBookmakerList() {
		return bookmakerList;
	}

	public void readFromFile(String path) {
		
		bookmakerList.addAll(FileLoader.sharedInstance.readFromFile(path));
	}
}

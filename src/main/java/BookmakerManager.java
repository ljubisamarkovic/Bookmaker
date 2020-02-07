import java.util.concurrent.CopyOnWriteArrayList;

public class BookmakerManager {
	
	public CopyOnWriteArrayList<Bookmaker> bookmakerList = new CopyOnWriteArrayList<Bookmaker> ();
	public BookmakerManager() {}

	public void readFromFile(String path) {
		
		bookmakerList.addAll(FileLoader.sharedInstance.readFromFile(path));
	}
}

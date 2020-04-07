package model;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GenerateBookmakerTask implements Runnable {
	
	public final BookmakerManager bookmakerManager;
	public final List<String> paths = new CopyOnWriteArrayList <>(FileLoader.sharedInstance.readInputPaths());
	
	public GenerateBookmakerTask(BookmakerManager bm) {
		this.bookmakerManager = bm;
	}
	
	@Override
	public void run() {
	
		bookmakerManager.readFromFile(paths.remove(0));
	}
}
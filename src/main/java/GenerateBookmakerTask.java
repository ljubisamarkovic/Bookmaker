
import java.util.concurrent.CopyOnWriteArrayList;

public class GenerateBookmakerTask implements Runnable {
	
	public BookmakerManager bookmakerManager;
	public CopyOnWriteArrayList<String> paths = new CopyOnWriteArrayList<String>(FileLoader.sharedInstance.readInputPaths());
	
	public GenerateBookmakerTask(BookmakerManager bm) {
		this.bookmakerManager = bm;
	}
	
	@Override
	public void run() {
	
		bookmakerManager.readFromFile(paths.remove(0));
	}
}



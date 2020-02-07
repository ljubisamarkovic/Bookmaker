import java.util.ArrayList;

public class BookmakerAverageManager {

	private ArrayList<Bookmaker> bookmaker;
	private AverageQuota averageQuota;
	
	public BookmakerAverageManager(ArrayList<Bookmaker> bookmaker, AverageQuota averageQuota) {
		super();
		this.bookmaker = bookmaker;
		this.averageQuota = averageQuota;
	}

	public ArrayList<Bookmaker> getBookmaker() {
		return bookmaker;
	}

	public void setBookmaker(ArrayList<Bookmaker> bookmaker) {
		this.bookmaker = bookmaker;
	}

	public AverageQuota getAverageQuota() {
		return averageQuota;
	}

	public void setAverageQuota(AverageQuota averageQuota) {
		this.averageQuota = averageQuota;
	}

	@Override
	public String toString() {
		return "BookmakerAverageManager: bookmaker = " + bookmaker + ", averageQuota = " + averageQuota;
	}
	
}

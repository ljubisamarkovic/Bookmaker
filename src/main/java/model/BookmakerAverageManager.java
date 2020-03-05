package model;
import java.util.List;

public class BookmakerAverageManager {

	private List<Bookmaker> bookmaker;
	private AverageQuota averageQuota;
	
	public BookmakerAverageManager(List<Bookmaker> bookmaker, AverageQuota averageQuota) {
		super();
		this.bookmaker = bookmaker;
		this.averageQuota = averageQuota;
	}

	public List<Bookmaker> getBookmaker() {
		return bookmaker;
	}

	public void setBookmaker(List<Bookmaker> bookmaker) {
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

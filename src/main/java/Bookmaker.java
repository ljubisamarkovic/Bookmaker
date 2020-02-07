
public class Bookmaker {

	private int bookmakerID;
	private int round;
	private String homeTeam;
	private String awayTeam;
	private Double homeWinQuota;
	private Double awayWinQuota;
	private Double tieQuota;
	
	
	public Bookmaker(int bookmakerID, int round, String homeTeam, String awayTeam, Double homeWinQuota,
			Double awayWinQuota, Double tieQuota) {
		
		super();
		this.bookmakerID = bookmakerID;
		this.round = round;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeWinQuota = (Math.round(homeWinQuota * 100.0) / 100.0);
		this.awayWinQuota = (Math.round(awayWinQuota * 100.0) / 100.0);
		this.tieQuota = (Math.round(tieQuota * 100.0) / 100.0);
	}

	public Bookmaker() {
		// TODO Auto-generated constructor stub
	}


	public int getBookmakerID() {
		return bookmakerID;
	}


	public void setBookmakerID(int bookmakerID) {
		this.bookmakerID = bookmakerID;
	}


	public int getRound() {
		return round;
	}


	public void setRound(int round) {
		this.round = round;
	}


	public String getHomeTeam() {
		return homeTeam;
	}


	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}


	public String getAwayTeam() {
		return awayTeam;
	}


	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}


	public Double getHomeWinQuota() {
		return homeWinQuota;
	}


	public void setHomeWinQuota(Double homeWinQuota) {
		this.homeWinQuota = homeWinQuota;
	}


	public Double getAwayWinQuota() {
		return awayWinQuota;
	}


	public void setAwayWinQuota(Double awayWinQuota) {
		this.awayWinQuota = awayWinQuota;
	}


	public Double getTieQuota() {
		return tieQuota;
	}


	public void setTieQuota(Double tieQuota) {
		this.tieQuota = tieQuota;
	}

	@Override
	public String toString() {
		return "ID: " + bookmakerID + ", round: " + round + ", homeTeam: " + homeTeam + ", awayTeam: "
					  + awayTeam + ", homeWinQuota: " + homeWinQuota + ", awayWinQuota: " + awayWinQuota + ", tieQuota: "
					  + tieQuota;
	}
	
}

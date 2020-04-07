package model;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((awayWinQuota == null) ? 0 : awayWinQuota.hashCode());
		result = prime * result + bookmakerID;
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + ((homeWinQuota == null) ? 0 : homeWinQuota.hashCode());
		result = prime * result + round;
		result = prime * result + ((tieQuota == null) ? 0 : tieQuota.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bookmaker other = (Bookmaker) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (awayWinQuota == null) {
			if (other.awayWinQuota != null)
				return false;
		} else if (!awayWinQuota.equals(other.awayWinQuota))
			return false;
		if (bookmakerID != other.bookmakerID)
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (homeWinQuota == null) {
			if (other.homeWinQuota != null)
				return false;
		} else if (!homeWinQuota.equals(other.homeWinQuota))
			return false;
		if (round != other.round)
			return false;
		if (tieQuota == null) {
			if (other.tieQuota != null)
				return false;
		} else if (!tieQuota.equals(other.tieQuota))
			return false;
		return true;
	}

}

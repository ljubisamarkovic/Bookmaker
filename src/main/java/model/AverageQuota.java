package model;
import java.util.List;

public class AverageQuota {

	private Double averageHomeTeamWinQuota;
	private Double averageAwayTeamWinQuota;
	private Double averageTieQuota;

	public AverageQuota() {
	}

	public AverageQuota(Double averageHomeTeamWinQuota, Double averageAwayTeamWinQuota, Double averageTieQuota) {

		this.averageHomeTeamWinQuota = averageHomeTeamWinQuota;
		this.averageAwayTeamWinQuota = averageAwayTeamWinQuota;
		this.averageTieQuota = averageTieQuota;
	}

	public Double getAverageHomeTeamWinQuota() {
		return averageHomeTeamWinQuota;
	}

	public void setAverageHomeTeamWinQuota(Double averageHomeTeamWinQuota) {
		this.averageHomeTeamWinQuota = averageHomeTeamWinQuota;
	}

	public Double getAverageAwayTeamWinQuota() {
		return averageAwayTeamWinQuota;
	}

	public void setAverageAwayTeamWinQuota(Double averageAwayTeamWinQuota) {
		this.averageAwayTeamWinQuota = averageAwayTeamWinQuota;
	}

	public Double getAverageTieQuota() {
		return averageTieQuota;
	}

	public void setAverageTieQuota(Double averageTieQuota) {
		this.averageTieQuota = averageTieQuota;
	}

	@Override
	public String toString() {
		return "Average averageHomeTeamWinQuota = " + averageHomeTeamWinQuota + ", averageAwayTeamWinQuota = "
				+ averageAwayTeamWinQuota + ", averageTieQuota = " + averageTieQuota;
	}

	public AverageQuota calculateAverages(List <Bookmaker> bookmakerList) {

		Double averageHomeTeam = 0d;
		Double averageAwayTeam = 0d;
		Double averageTie = 0d;

		for (Bookmaker b : bookmakerList) {
			averageHomeTeam += b.getHomeWinQuota();
			averageAwayTeam += b.getAwayWinQuota();
			averageTie += b.getTieQuota();
		}

		return new AverageQuota(averageHomeTeam/bookmakerList.size(), averageAwayTeam/bookmakerList.size(), averageTie/bookmakerList.size());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averageAwayTeamWinQuota == null) ? 0 : averageAwayTeamWinQuota.hashCode());
		result = prime * result + ((averageHomeTeamWinQuota == null) ? 0 : averageHomeTeamWinQuota.hashCode());
		result = prime * result + ((averageTieQuota == null) ? 0 : averageTieQuota.hashCode());
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
		AverageQuota other = (AverageQuota) obj;
		if (averageAwayTeamWinQuota == null) {
			if (other.averageAwayTeamWinQuota != null)
				return false;
		} else if (!averageAwayTeamWinQuota.equals(other.averageAwayTeamWinQuota))
			return false;
		if (averageHomeTeamWinQuota == null) {
			if (other.averageHomeTeamWinQuota != null)
				return false;
		} else if (!averageHomeTeamWinQuota.equals(other.averageHomeTeamWinQuota))
			return false;
		if (averageTieQuota == null) {
			if (other.averageTieQuota != null)
				return false;
		} else if (!averageTieQuota.equals(other.averageTieQuota))
			return false;
		return true;
	}
}

import java.util.ArrayList;

public class AverageQuota {

	private Double averageHomeTeamWinQuota;
	private Double averageAwayTeamWinQuota;
	private Double averageTieQuota;
	
	public AverageQuota() {
		// TODO Auto-generated constructor stub
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
	
	public AverageQuota calculateAverages(ArrayList<Bookmaker> bookmakerList) {
		
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
	
}

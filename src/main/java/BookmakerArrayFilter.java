import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BookmakerArrayFilter {
	
	public ArrayList<Bookmaker> filter (ArrayList<Bookmaker> filteredList) {
		
		String max = Integer.toString(Collections.max(filteredList, Comparator.comparing(s -> s.getRound())).getRound());
		filteredList = (ArrayList<Bookmaker>) filteredList.stream().filter(match -> max.equals(Integer.toString(match.getRound()))).collect(Collectors.toList());
		return filteredList;
	}

	public ArrayList<Bookmaker> filter (ArrayList<Bookmaker> filteredList, String round) {
		
		filteredList = (ArrayList<Bookmaker>) filteredList.stream().filter(match -> round.equals(Integer.toString(match.getRound()))).collect(Collectors.toList());
		return filteredList;
		
	}
	
	public ArrayList<Bookmaker> filter (ArrayList<Bookmaker> filteredList, String homeTeam, String awayTeam) {
		
		String max = Integer.toString(Collections.max(filteredList, Comparator.comparing(s -> s.getRound())).getRound());
		filteredList = (ArrayList<Bookmaker>) filteredList.stream().filter(match -> max.equals(Integer.toString(match.getRound())) &&
				homeTeam.equals(match.getHomeTeam()) && awayTeam.equals(match.getAwayTeam())).collect(Collectors.toList());
		return filteredList;
		
	}
	
	public ArrayList<Bookmaker> filter (ArrayList<Bookmaker> filterdList, String round, String homeTeam, String awayTeam) {
		
		filterdList = (ArrayList<Bookmaker>) filterdList.stream().filter(match -> round.equals(Integer.toString(match.getRound())) &&
				homeTeam.equals(match.getHomeTeam()) && awayTeam.equals(match.getAwayTeam())).collect(Collectors.toList());
		return filterdList;
	}
}

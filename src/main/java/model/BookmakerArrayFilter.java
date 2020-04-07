package model;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookmakerArrayFilter {
	
	public List<Bookmaker> filter (List<Bookmaker> filteredList) {
		
		String max = Integer.toString(Collections.max(filteredList, Comparator.comparing(s -> s.getRound())).getRound());
		filteredList = filteredList.stream().filter(match -> max.equals(Integer.toString(match.getRound()))).collect(Collectors.toList());
		return filteredList;
	}

	public List<Bookmaker> filter (List<Bookmaker> filteredList, String round) {
		
		filteredList = filteredList.stream().filter(match -> round.equals(Integer.toString(match.getRound()))).collect(Collectors.toList());
		return filteredList;
		
	}
	
	public List<Bookmaker> filter (List<Bookmaker> filteredList, String homeTeam, String awayTeam) {
		
		String max = Integer.toString(Collections.max(filteredList, Comparator.comparing(s -> s.getRound())).getRound());
		filteredList = filteredList.stream().filter(match -> max.equals(Integer.toString(match.getRound())) &&
				homeTeam.equals(match.getHomeTeam()) && awayTeam.equals(match.getAwayTeam())).collect(Collectors.toList());
		return filteredList;
		
	}
	
	public List<Bookmaker> filter (List<Bookmaker> filterdList, String round, String homeTeam, String awayTeam) {
		
		filterdList = filterdList.stream().filter(match -> round.equals(Integer.toString(match.getRound())) &&
				homeTeam.equals(match.getHomeTeam()) && awayTeam.equals(match.getAwayTeam())).collect(Collectors.toList());
		return filterdList;
	}
}

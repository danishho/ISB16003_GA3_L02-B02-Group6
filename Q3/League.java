import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class League {
	private final String name;
    private final int winPoints;
    private final int drawPoints;
	private final List<Team> teams;
	
	public League(LeagueConfig config) {
		this.name = config.getLeagueName();
        this.winPoints = config.getWinPoints();
        this.drawPoints = config.getDrawPoints();
		this.teams = new ArrayList<Team>();
    }
	
	
	/**
     * Records a parsed match by updating both the home and away team statistics.
     */
    public void addMatch(Match match) {
        Team home = getOrCreateTeam(match.getHomeTeam());
        Team away = getOrCreateTeam(match.getAwayTeam());

        home.recordMatch(match.getHomeGoals(), match.getAwayGoals());
        away.recordMatch(match.getAwayGoals(), match.getHomeGoals());
    }
    
    private Team getOrCreateTeam(String teamName) {
    	for (int i = 0; i < teams.size(); i++) {
            Team t = teams.get(i);
            if (t.getName().equals(teamName)) {
                return t;
            }
        }
        
        Team newTeam = new Team(teamName);
        teams.add(newTeam);
        return newTeam;
        
    }
    
    /**
     * Builds the complete formatted standings table string including the title header.
     */
    public String formatTable() {
        StringBuilder sb = new StringBuilder();
        String lineSep = System.lineSeparator();

        sb.append("=== ").append(name).append(" ===").append(lineSep);
        sb.append(String.format("%-3s %-15s %3s %3s %3s %3s %3s %3s %4s %4s",
                "Pos", "Team", "P", "W", "D", "L", "GF", "GA", "GD", "Pts")).append(lineSep);

        List<Team> ranked = getStandings();
        for (int i = 0; i < ranked.size(); i++) {
            Team t = ranked.get(i);
            int gd = t.getGoalDifference();
            String gdStr = (gd >= 0 ? "+" : "") + gd;
            int pts = t.getPoints(winPoints, drawPoints);

            sb.append(String.format("%-3d %-15s %3d %3d %3d %3d %3d %3d %4s %4d",
                    (i + 1), t.getName(), t.getPlayed(), t.getWon(), t.getDrawn(),
                    t.getLost(), t.getGoalsFor(), t.getGoalsAgainst(), gdStr, pts)).append(lineSep);
        }
        return sb.toString();
    }
    
    /**
     * Returns teams sorted by Pts (desc), GD (desc), GF (desc), then name (A-Z).
     */
    public List<Team> getStandings() {
        List<Team> sorted = new ArrayList<Team>(teams);
        Collections.sort(sorted, new Comparator<Team>() {
            @Override
            public int compare(Team a, Team b) {
            	// First priority: Points (descending)
                int ptsDiff = b.getPoints(winPoints, drawPoints) - a.getPoints(winPoints, drawPoints);
                if (ptsDiff != 0) {
                    return ptsDiff;
                }
                // Second priority: Goal Difference (descending)
                int gdDiff = b.getGoalDifference() - a.getGoalDifference();
                if (gdDiff != 0) {
                    return gdDiff;
                }
                
                // Third priority: Goals For (descending)
                int gfDiff = b.getGoalsFor() - a.getGoalsFor();
                if (gfDiff != 0) {
                    return gfDiff;
                }
                
                // Fourth priority: Team Name (ascending)
                return a.getName().compareTo(b.getName());
            }
        });
        return sorted;
    }
	
}

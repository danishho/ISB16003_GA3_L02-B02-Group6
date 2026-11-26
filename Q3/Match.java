
public class Match {
	
	private static final int EXPECTED_FIELDS = 4;
    private static final String DELIMITER = ",";

    private final String homeTeam;
    private final int homeGoals;
    private final String awayTeam;
    private final int awayGoals;
    
	public Match(String homeTeam, int homeGoals, String awayTeam, int awayGoals) {
        this.homeTeam = homeTeam;
        this.homeGoals = homeGoals;
        this.awayTeam = awayTeam;
        this.awayGoals = awayGoals;
    }
	
	
	/**
     * Parses a CSV line (home,homeGoals,away,awayGoals) into a Match object.
     * Throws IllegalArgumentException if the format, team names, or goal counts are invalid.
     */
    public static Match parse(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Null line");
        }
        String[] parts = line.split(DELIMITER, -1);
        if (parts.length != EXPECTED_FIELDS) {
            throw new IllegalArgumentException("Expected 4 fields");
        }

        String home = parts[0].trim();
        String away = parts[2].trim();
        if (home.isEmpty() || away.isEmpty() || home.equals(away)) {
            throw new IllegalArgumentException("Invalid team names");
        }

        int hg;
        int ag;
        try {
            hg = Integer.parseInt(parts[1].trim());
            ag = Integer.parseInt(parts[3].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Goals must be integers");
        }

        if (hg < 0 || ag < 0) {
            throw new IllegalArgumentException("Goals cannot be negative");
        }

        return new Match(home, hg, away, ag);
    }


	public String getHomeTeam() {
		return homeTeam;
	}


	public int getHomeGoals() {
		return homeGoals;
	}


	public String getAwayTeam() {
		return awayTeam;
	}


	public int getAwayGoals() {
		return awayGoals;
	}
	
	
}

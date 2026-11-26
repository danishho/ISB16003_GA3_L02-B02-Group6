/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members:
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL (5222610009),
 * MUHAMAD ALIF HAKIM BIN HASHIM (5222610019),
 * NUR SYAHDINA BINTI SHAHRUL NIZA (5222610658),
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI (5222610200)
 */

/**
 * Immutable representation of a single match result between a home team and an away team.
 */
public class Match {
	
	private static final int EXPECTED_FIELDS = 4;
    private static final String DELIMITER = ",";
    private static final String ERR_NULL_LINE = "Null line";
    private static final String ERR_EXPECTED_FIELDS = "Expected 4 fields";
    private static final String ERR_INVALID_TEAMS = "Invalid team names";
    private static final String ERR_INVALID_GOALS= "Goals must be integers";

    private final String homeTeam;
    private final int homeGoals;
    private final String awayTeam;
    private final int awayGoals;
    
    /**
     * Constructs an immutable Match record.
     */
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
            throw new IllegalArgumentException(ERR_NULL_LINE);
        }
        String[] parts = line.split(DELIMITER, -1);
        if (parts.length != EXPECTED_FIELDS) {
            throw new IllegalArgumentException(ERR_EXPECTED_FIELDS);
        }

        String home = parts[0].trim();
        String away = parts[2].trim();
        if (home.isEmpty() || away.isEmpty() || home.equals(away)) {
            throw new IllegalArgumentException(ERR_INVALID_TEAMS);
        }

        int hg;
        int ag;
        try {
            hg = Integer.parseInt(parts[1].trim());
            ag = Integer.parseInt(parts[3].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERR_INVALID_GOALS);
        }

        if (hg < 0 || ag < 0) {
            throw new IllegalArgumentException(ERR_INVALID_GOALS);
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

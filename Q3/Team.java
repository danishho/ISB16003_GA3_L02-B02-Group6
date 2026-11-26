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
 * Tracks match statistics for a single team in the league.
 */

public class Team {
    private final String name;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;

    /**
     * Creates a Team entry with zeroed statistics.
     */
    public Team(String name) {
        this.name = name;
    }

    /**
     * Updates team statistics based on the goals scored and conceded in one match.
     */
    public void recordMatch(int scored, int conceded) {
        played++;
        goalsFor += scored;
        goalsAgainst += conceded;

        if (scored > conceded) {
            won++;
        } else if (scored == conceded) {
            drawn++;
        } else {
            lost++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public int getDrawn() {
        return drawn;
    }

    public int getLost() {
        return lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * Returns goal difference (goals scored minus goals conceded).
     */
    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    /**
     * Calculates total points earned from wins and draws.
     */
    public int getPoints(int winPoints, int drawPoints) {
        return (won * winPoints) + (drawn * drawPoints);
    }
}
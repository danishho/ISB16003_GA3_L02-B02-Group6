
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

    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    public int getPoints(int winPoints, int drawPoints) {
        return (won * winPoints) + (drawn * drawPoints);
    }
}
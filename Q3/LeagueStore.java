import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeagueStore {

	private final String dataFilePath;
	private final String ReportFileName;
	
	
	
	public LeagueStore(LeagueConfig config) {
		this.dataFilePath = config.getDataFile();
		this.ReportFileName = config.getReportFile();
    }
	
	/**
     * Reads matches line by line, skipping blank lines and logging warnings for malformed lines.
     */
    public List<Match> loadMatches() throws IOException {
        List<Match> matches = new ArrayList<Match>();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(dataFilePath));
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    matches.add(Match.parse(line));
                } catch (IllegalArgumentException e) {
                    System.out.println(String.format("Warning: skipped line %d (invalid format)", lineNumber));
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(dataFilePath);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {}
            }
        }
        
        return matches;
    }
	
	/**
     * Appends a single match result to the configured data file in txt format
     */
    public void appendMatch(Match match) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(dataFilePath, true));
            writer.write(match.getHomeTeam() + "," + match.getHomeGoals() + ","+ match.getAwayTeam() + "," + match.getAwayGoals());
            writer.newLine();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {}
            }
        }
    }
    
    /**
     * Overwrites standings.txt with the formatted league table.
     */
    public void saveReport(String reportContent) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(ReportFileName, false));
            writer.write(reportContent);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
	
	
	
}

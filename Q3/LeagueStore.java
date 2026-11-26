/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members:
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL (5222610009),
 * MUHAMAD ALIF HAKIM BIN HASHIM (5222610019),
 * NUR SYAHDINA BINTI SHAHRUL NIZA (5222610658),
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI (5222610200)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all file I/O for reading matches, appending new match results and writing standings reports.
 */
public class LeagueStore {

	private static final String CSV_DELIMITER = ",";
	private static final String WARN_SKIPPED_LINE = "Warning: skipped line %d (invalid format)";

	private final String dataFilePath;
	private final String reportFileName;
	
	/**
	 * Constructs a LeagueStore using file paths resolved from LeagueConfig.
	 */
	public LeagueStore(LeagueConfig config) {
		this.dataFilePath = config.getDataFile();
		this.reportFileName = config.getReportFile();
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
                    System.out.println(String.format(WARN_SKIPPED_LINE, lineNumber));
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
            writer.write(match.getHomeTeam() + CSV_DELIMITER + match.getHomeGoals() + CSV_DELIMITER
                    + match.getAwayTeam() + CSV_DELIMITER + match.getAwayGoals());
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
     * Overwrites report file with the formatted league table.
     */
    public void saveReport(String reportContent) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(reportFileName, false));
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

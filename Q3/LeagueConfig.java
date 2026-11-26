/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members:
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL (5222610009),
 * MUHAMAD ALIF HAKIM BIN HASHIM (5222610019),
 * NUR SYAHDINA BINTI SHAHRUL NIZA (5222610658),
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI (5222610200)
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads and exposes league configuration from an external .properties file with default fallbacks.
 *
 * Design Note - Advantages of using a .properties file over hardcoded constants:
 * 1. Configuration changes without recompilation: League name, data file paths, and scoring
 *    points can be modified directly in the .properties file without editing or recompiling Java code.
 * 2. Flexible multi-environment support: Multiple seasons or league setups can be managed using
 *    separate config files selected at runtime via -Dleague.config=<file>.
 */
public class LeagueConfig {

	private static final String CONFIG_PROPERTY = "league.config";
    private static final String DEFAULT_CONFIG_FILE = "league.properties";
    
    private static final String KEY_LEAGUE_NAME = "leagueName";
    private static final String KEY_DATA_FILE = "dataFile";
    private static final String KEY_WIN_POINTS = "winPoints";
    private static final String KEY_DRAW_POINTS = "drawPoints";
    private static final String KEY_REPORT_FILE = "reportFile";

    private static final String DEFAULT_LEAGUE_NAME = "League";
    private static final String DEFAULT_DATA_FILE = "matches.txt";
    private static final int DEFAULT_WIN_POINTS = 3;
    private static final int DEFAULT_DRAW_POINTS = 1;
    private static final String DEFAULT_REPORT_FILE = "standings.txt";
    private static final String ERR_CLOSING_STREAM = "Error closing file input stream :: ";
    
	private final Properties props;
    private final String configPath;

    /**
     * Resolves the configuration file path from the league.config system property and loads it.
     */
	public LeagueConfig() throws FileNotFoundException,IOException {
        this.props = new Properties();
        this.configPath = System.getProperty(CONFIG_PROPERTY, DEFAULT_CONFIG_FILE);
        
        load();
    }
	
	/**
     * Loads configuration properties from the target file path.
     */
    public void load() throws FileNotFoundException,IOException {
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream(configPath);
            props.load(fis);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(configPath);
        } finally {
            if (fis != null) {
            	try {
            		fis.close();
            	} catch (Exception e) {
					 System.out.println(ERR_CLOSING_STREAM + e.getMessage());
            	}
            }
        }
    }
    

    public String getLeagueName() {
        return getStringOrDefault(props.getProperty(KEY_LEAGUE_NAME), DEFAULT_LEAGUE_NAME);
    }

    public String getDataFile() {
       return getStringOrDefault(props.getProperty(KEY_DATA_FILE), DEFAULT_DATA_FILE);
    }

    public int getWinPoints() {
        return getIntOrDefault(props.getProperty(KEY_WIN_POINTS), DEFAULT_WIN_POINTS);
    }

    public int getDrawPoints() {
        return getIntOrDefault(props.getProperty(KEY_DRAW_POINTS), DEFAULT_DRAW_POINTS);
    }
    
    public String getReportFile() {
		return getStringOrDefault(props.getProperty(KEY_REPORT_FILE), DEFAULT_REPORT_FILE);
	}	

    /**
     * Parses an integer property value, returning defaultValue if null or non-numeric.
     */
    public static int getIntOrDefault(String value, int defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Returns a trimmed property string, or defaultValue if null or blank.
     */
    public static String getStringOrDefault(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }
    
    
    
}

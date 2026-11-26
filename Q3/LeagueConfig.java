import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LeagueConfig {

	private static final String CONFIG_PROPERTY = "league.config";
    private static final String DEFAULT_CONFIG_FILE = "league.properties";
    
    private static final String KEY_LEAGUE_NAME = "leagueName";
    private static final String KEY_DATA_FILE = "dataFile";
    private static final String KEY_WIN_POINTS = "winPoints";
    private static final String KEY_DRAW_POINTS = "drawPoints";
    private static final String KEY_REPORT_FILE = "report.txt";

    private static final String DEFAULT_LEAGUE_NAME = "League";
    private static final String DEFAULT_DATA_FILE = "matches.txt";
    private static final int DEFAULT_WIN_POINTS = 3;
    private static final int DEFAULT_DRAW_POINTS = 1;
    private static final String DEFAULT_REPORT_FILE = "report.txt";
    
	private final Properties props;
    private final String configPath;

    
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
        
        fis = loadConfigFile(configPath);
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
					 System.out.println("Error closing file input stream :: " + e.getMessage());
            	}
            }
        }
    }
    
    private FileInputStream loadConfigFile(String configPath) throws FileNotFoundException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(configPath);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(configPath);
		}finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					System.out.println("Error closing file input stream :: " + e.getMessage());
				}
			}
		}
		
		return fis;
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
    
    public static String getStringOrDefault(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }
    
    
    
}

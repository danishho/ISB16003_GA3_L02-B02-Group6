/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members:
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL (5222610009),
 * MUHAMAD ALIF HAKIM BIN HASHIM (5222610019),
 * NUR SYAHDINA BINTI SHAHRUL NIZA (5222610658),
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI (5222610200)
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * CLI entry point for generating league standings reports and adding match results.
 */
public class LeagueApp {

	private static final String CMD_REPORT = "report";
	private static final String CMD_ADD = "add";
	private static final String MSG_USAGE = "Usage: java LeagueApp report | add <home> <hg> <away> <ag>";
	private static final String MSG_ERR_GOALS = "Error: goals must be non-negative integers";
	private static final String MSG_ERR_SAME_TEAM = "Error: a team cannot play itself";
	private static final String MSG_ERR_FILE_NOT_FOUND = "Error: file not found: ";
	private static final String MSG_ERR_PREFIX = "Error: ";
	private static final String MSG_SAVED_FORMAT = "Saved: %s %d - %d %s";

	/**
	 * Dispatches CLI commands (report or add) based on argument count and command name.
	 */
	public static void main(String[] args) {
		
		if(args.length == 1 && args[0].equals(CMD_REPORT)) {
			generateReport();
		}else if(args.length == 5 && args[0].equals(CMD_ADD)) {
			addMatch(args);
		}else {
			System.out.println(MSG_USAGE);
		}
		
	}
	
	/**
	 * Loads configuration and matches, computes standings, prints the table, and saves the report file.
	 */
	private static void generateReport() {
       
        try {
        	LeagueConfig config = new LeagueConfig();
            LeagueStore store = new LeagueStore(config);
            List<Match> matches = store.loadMatches();

            League league = new League(config);
            for (int i = 0; i < matches.size(); i++) {
                league.addMatch(matches.get(i));
            }

            String table = league.formatTable();
            System.out.print(table);
            store.saveReport(table);
        } catch (FileNotFoundException e) {
            System.out.println(MSG_ERR_FILE_NOT_FOUND + e.getMessage());
        } catch (IOException e) {
            System.out.println(MSG_ERR_PREFIX + e.getMessage());
        }
    }
	
	/**
	 * Validates CLI arguments for a new match and appends the match to the configured data file.
	 */
	public static void addMatch(String[] params) {
		String homeTeam;
		int homeGoals;
		String awayTeam;
		int awayGoals;
		
		try {
			homeTeam = getStr(params[1]);
			homeGoals = getInt(params[2]);
			awayTeam = getStr(params[3]);
			awayGoals = getInt(params[4]);
		} catch (NumberFormatException e) {
			System.out.println(MSG_ERR_GOALS);
			return;
		}
		
		
		if(homeGoals < 0 || awayGoals < 0) {
			System.out.println(MSG_ERR_GOALS);
			return;
		}
		
		if(homeTeam.equals(awayTeam)) {
			System.out.println(MSG_ERR_SAME_TEAM);
			return;
		}
		
	
        try {
        	LeagueConfig config = new LeagueConfig();
        	LeagueStore store = new LeagueStore(config);
        	
            Match match = new Match(homeTeam, homeGoals, awayTeam, awayGoals);
            store.appendMatch(match);
            
            System.out.println(String.format(MSG_SAVED_FORMAT,homeTeam,homeGoals,awayGoals,awayTeam));
        } catch (FileNotFoundException e) {
            System.out.println(MSG_ERR_FILE_NOT_FOUND + e.getMessage());
        } catch (IOException e) {
            System.out.println(MSG_ERR_PREFIX + e.getMessage());
        }
		
		
	}
	
	/**
	 * Converts an argument object to a trimmed String, returning an empty string if null.
	 */
	public static String getStr(Object obj) {
		if(obj != null) {
			return obj.toString().trim();
		} else {
			return "";
		}
	}
	
	/**
	 * Parses an integer from an argument object, throwing NumberFormatException for blank or non-numeric input.
	 */
	public static int getInt(Object value) {

	    if (value == null) {
	        return 0;
	    }

	    String str = value.toString().trim();

	    if (str.isEmpty()) {
	        throw new NumberFormatException();
	    }

	    try {
	        return Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	    	throw new NumberFormatException();
	    }
	}
	
	

}

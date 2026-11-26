import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LeagueApp {
	
	public static void main(String[] args) {
		
		if(args.length == 1 && args[0].equals("report")) {
			generateReport();
		}else if(args.length == 5 && args[0].equals("add")) {
			addMatch(args);
		}else {
			System.out.println("Usage: java LeagueApp report | add <home> <hg> <away> <ag>");
		}
		
	}
	
	
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
            System.out.println("Error: file not found:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
	
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
			System.out.println("Error: goals must be non-negative integer");
			return;
		}
		
		
		if(homeGoals < 0 || awayGoals < 0) {
			System.out.println("Error: goals must be non-negative integers");
			return;
		}
		
		if(homeTeam.equals(awayTeam)) {
			System.out.println("Error: home and away teams must be different");
			return;
		}
		
	
        try {
        	LeagueConfig config = new LeagueConfig();
        	LeagueStore store = new LeagueStore(config);
        	
            Match match = new Match(homeTeam, homeGoals, awayTeam, awayGoals);
            store.appendMatch(match);
            
            System.out.println(String.format("Saved: %s %d - %d %s",homeTeam,homeGoals,awayGoals,awayTeam));
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
		
		
	}
	
	public static String getStr(Object obj) {
		if(obj != null) {
			return obj.toString().trim();
		} else {
			return "";
		}
	}
	
	public static int getInt(Object value) {

	    if (value == null) {
	        return 0;
	    }

	    String str = value.toString().trim();

	    if (str.isEmpty()) {
	        return 0;
	    }

	    try {
	        return Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	    	throw new NumberFormatException();
	    }
	}
	
	

}

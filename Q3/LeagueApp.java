
public class LeagueApp {
	
	
	public static void main(String[] args) {
		
		if(args.length == 1 && args[0].equals("report")) {
			System.out.println("report");
		}else if(args.length == 5 && args[0].equals("add")) {
			System.out.println("add");
			
		}else {
			System.out.println("Usage: java LeagueApp report | add <home> <hg> <away> <ag>");
		}
		
		
	}

}

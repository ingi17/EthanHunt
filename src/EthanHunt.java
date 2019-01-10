import java.util.Collection;

public class EthanHunt implements Agent {
	
	public String nextAction(Collection<String> percepts) {
		System.out.print("perceiving:");
		if(firstMove){
			x = 0;
			y = 0;
			firstMove = false;
			return "TURN_ON";	
		}
		 
		for(String percept:percepts) {
			System.out.print("'" + percept + "', ");
			if(percept.equals("DIRT")) {
				return "SUCK";
			} else if(percept.equals("BUMP")) {
				horizontal = !horizontal;
				left = true;
				return "TURN_LEFT";
			}	
		}

		System.out.println("");
		if (horizontal) {
			if (left) {
				x--;
			} else {
				x++;
			}
		} else {
			if (left) {
				y--;
			} else {
				y++;
			}
		}
		return "GO";
		//String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
	}

	private boolean firstMove = true;
	private int x = 0;
	private int y = 0;
	private boolean horizontal;		//Going up or down
	private boolean left; 			//Going left or right			
}

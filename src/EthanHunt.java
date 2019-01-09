import java.util.Collection;
import java.util.Random;

public class EthanHunt implements Agent
{
	private Random random = new Random();
    
    public String nextAction(Collection<String> percepts) {
		System.out.print("perceiving:");
		for(String percept:percepts) {
			System.out.print("'" + percept + "', ");
			System.out.println("");

			if (moves == 0) {
				x = 0;
				y = 0;
				return "TURN_ON";
			}

			coords[moves][0] = x;
			coords[moves][1] = y;
			System.out.println("X: " + x + " Y: " + y);

			moves++;

			switch(percept) {
				case "DIRT":
					return "SUCK";
				case "BUMP":
					horizontal = !horizontal;
					return "TURN_LEFT";
				default:
					break;
			}
			if (horizontal) { if (left) { x--; } else { x++; } } else { if (left) { y--; } else { y++; } }
			return "GO";
		}
	}

	private boolean alreadyVisited() {
		for (int i = 0; i < coords.length; i++) {
			if (horiz) {	// Er næsti punktur til hliðar til?
				if (left) {
					if (x+1 == coords[i][0]) {	// Vinstri?
						return true;
					}
				} else {
					if (x-1 == coords[i][0]) {	// Hægri?
						return true;
					}
				}

			} else {	// Er næsti punktur upp/niður til?
				if (left) {
					if (y+1 == coords[i][1]) {	// Upp?
						return true;
					}
				} else {
					if (y-1 == coords[i][1]) {	// Niður?
						return true;
					}
				}
			}
		}
		return false;
	}

	private int moves = 0, x = 0, y = 0;
	private int coords[][];
	private int bumps[][];
	private boolean horizontal, left;
}

//String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
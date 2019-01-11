import java.util.Collection;

public class EthanHunt implements Agent {
	
	public String nextAction(Collection<String> percepts) {
		System.out.print("perceiving:");
		if(state == 0){
			x = 0;
			y = 0;
			nextState();
			return "TURN_ON";	
		}
		 
		for(String percept:percepts) {
			System.out.print("'" + percept + "', ");
			if(percept.equals("DIRT")) {
				return "SUCK";
			} else if(percept.equals("BUMP") && state < 2) {
				bumps++;
				return TURN_LEFT();
			}	
		}

		if(maxX == 0 && state == 2){
			nextState();
			horizontal = true;
			left = true;
			leftCounter = 0;
		}
		if(state == 1){
			if (bumps == 2) {
				maxX++;
			} else if(bumps == 3) {
				maxY++;
			}
		} else if(state == 2){
			if(moves < maxX){
				moves++;
				updateCoords();
				return "GO";
			} else if(moves == maxX){
				moves = 0;
				leftCounter++;

				if (leftCounter == 1) { maxX--; }
				else if (leftCounter % 2 != 0) { maxX--; }

				return TURN_LEFT();
			}
		}
		if(state == 3){
			return homeHandler();
		}
		if(bumps == 4){
			//System.out.printf("MAX X:%d, MAX Y:%d ", maxX, maxY);
			maxX--;
			nextState();
		}

		

		System.out.println("");
		
		System.out.printf("X:%d, Y:%d ", x, y);
		updateCoords();
		return GO();
		//String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
	}

	private void nextState(){
		state++;
	}

	private string GO(){
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
	}

	private String TURN_LEFT(){
		if(bumps % 2 == 0){
			left = !left;
		}
		horizontal = !horizontal;
		if(direction == 3) {
			direction = 0;
		} else {
			direction++;
		}
		return "TURN_LEFT";
	}

	private String TURN_RIGHT(){
		if(bumps % 2 == 0){
			left = !left;
		}
		if(direction == 0) {
			direction = 3;
		} else {
			direction++;
		}
		horizontal = !horizontal;
		return "TURN_RIGHT";
	}

	private String homeHandler(){
		if(x == 0 && y == 0) { return "TURN_OFF"; } // BASE CASE TURN_OFF
		// CASE 0: Byrjunarátt 0 og Endaátt 0
		if (direction == 0) {
			if (x > 0) {
				if (leftCounter < 2) {
					leftCounter++;
					return TURN_LEFT(); 
				}
				leftCounter = 0;
				return GO();
			} else if (x < 0) {
				return GO();
			}
			if (y > 0) {
				if (direction == 2) {
					return TURN_LEFT();
				} else if (direction == 0) {
					return TURN_RIGHT();
				}
				return GO();
			} else if (y < 0) {
				if (direction == 2) {
					return TURN_RIGHT();
				} else if (direction == 0) {
					return TURN_LEFT();
				}
				return GO();
			}
		} // CASE 0: END

		// CASE 1: Byrjunarátt 0 og endaátt 1
		if (direction == 1) {

		}

		if(x > 0) {
			if(leftCounter < 2){
				leftCounter++;
				return TURN_LEFT();
			}
			if(leftCounter != 0) { leftCounter = 0; }
			return "GO";

		} else if(x < 0){
			if(rightCounter < 2){
				rightCounter++;
				return TURN_RIGHT();
			}
			if(rightCounter != 0) {rightCounter = 0; }
			
		} 

		return "ERROR";
	}
	private int state = 0, x = 0, y = 0, direction = 0, bumps = 0, maxX = 0, maxY = 0, goCounter = 0, moves = 0, leftCounter = 0, rightCounter = 0;
	private int lastX, lastY;
	private boolean horizontal;		//Going up or down
	private boolean left; 			//Going left or right			
}

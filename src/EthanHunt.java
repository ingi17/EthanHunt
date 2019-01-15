import java.util.Collection;

public class EthanHunt implements Agent {
	
	public String nextAction(Collection<String> percepts) {
		
		// STATE 0: TURN ON
		// STATE 1: Locate corner and count width
		// STATE 2: Spiral to middle
		// State 3: Decide direction related to starting direction
		// State 4: Return home with case related to direction

		if(state == 0){
			x = 0;
			y = 0;
            direction = 0;
			nextState();
			return "TURN_ON";	
		}

		System.out.print("perceiving:");
		for(String percept:percepts) {
			System.out.print("'" + percept + "', ");
			if(percept.equals("DIRT")) {
				return "SUCK";
			} else if(percept.equals("BUMP")) {
				bumps++;
				return TURN_LEFT();
			}	
		}

		if (state == 1) {
			if (bumps == 2) {
				width++;
			} else if (bumps == 3) {
				height++;
			} else if (bumps == 4) {
				width--;
				height--;
				nextState();
			}
		} 

		if (state == 2) {
			if (width == 0) {
				nextState();
			} else {
				if (moves < width) {
					moves++;
					return GO();
				} else if (moves == width) {
					moves = 0;
					leftCounter++;
	
					if (leftCounter == 1) { width--; }
					else if (leftCounter % 2 != 0) { width--; }
					
					return TURN_LEFT();
				}
			}
		}

		if(state == 3){
			System.out.println("\nGOING HOME WITH CASE " + direction +"\n");
            homeCase = direction;
			state++;
        }
        if (state == 4) {
            return homeHandler(homeCase);
		}

		return GO();
		//String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
	}

	private void nextState(){
		state++;
		System.out.println("----------State: " + state);
		System.out.println("--------X: " + x);
		System.out.println("--------Y: " + y);
		System.out.println("--------DIR: " + direction);
		System.out.println("--------Width: " + width);
		System.out.println("");
	}

	private String GO(){
		if (direction == 0)  {
			x++;
		} else if (direction == 1)  {
			y--;
		} else if (direction == 2) {
			x--;
		} else if (direction == 3) {
			y++;
		}
	
		System.out.println("\nX: " + x + " Y: " + y + "\n");
		return "GO";
	}

	private String TURN_LEFT(){
        if(direction == 3) {
			direction = 0;
		} else {
			direction++;
		}
		System.out.println("--------DIR: " + direction);
		return "TURN_LEFT";
	}

	private String TURN_RIGHT(){
        if(direction == 0) {
			direction = 3;
		} else {
			direction--;
		}
		System.out.println("--------DIR: " + direction);
		return "TURN_RIGHT";
	}

    private String homeHandler(int hCase){
		
		if(x == 0 && y == 0) { return "TURN_OFF"; } // BASE CASE TURN_OFF

		// CASE 0: Byrjunarátt 0 og Endaátt 0
		if (hCase == 0) {
			if (x > 0) {
				if (leftCounter < 2) {
					leftCounter++;
					return TURN_LEFT(); 
				}
				return GO();
			} else if (x < 0) {
				return GO();
			}
			leftCounter = 0;
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
		if (hCase == 1) {
			if (y < 0) {
				if (leftCounter < 2) {
					leftCounter++;
					return TURN_LEFT(); 
				}
				return GO();
			} else if (y > 0) {
				return GO();
			}
			leftCounter = 0;
			if (x > 0) {
				if (direction == 1) {
					return TURN_LEFT();
				} else if (direction == 3) {
					return TURN_RIGHT();
				}
				return GO();
			} else if (x < 0) {
				if (direction == 3) {
					return TURN_RIGHT();
				} else if (direction == 1) {
					return TURN_LEFT();
				}
				return GO();
			}
	 	// CASE 1: END
		}
		
		if (hCase == 2) {
		// CASE 2: Byrjunarátt 0 og endaátt 2
			if (x < 0) {
				if (leftCounter < 2) {
					leftCounter++;
					return TURN_LEFT(); 
				}
				return GO();
			} else if (x > 0) {
				return GO();
			}
			leftCounter = 0;
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
		} // CASE 2: END

		if (hCase == 3) {
			// CASE 3: Byrjunarátt 0 og endaátt 3
			if (y > 0) {
				if (leftCounter < 2) {
					leftCounter++;
					return TURN_LEFT(); 
				}
				return GO();
			} else if (y < 0) {
				return GO();
			}
			leftCounter = 0;
			if (x < 0) {
				if (direction == 3) {
					return TURN_LEFT();
				} else if (direction == 1) {
					return TURN_RIGHT();
				}
				return GO();
			} else if (x > 0) {
				if (direction == 3) {
					return TURN_RIGHT();
				} else if (direction == 1) {
					return TURN_LEFT();
				}
				return GO();
			} // CASE 3: END
		}
	return "ERROR";
	}
	private int x, y, direction, homeCase;
	private int state = 0, bumps = 0, width = 0, height = 0, moves = 0, leftCounter = 0;		
}
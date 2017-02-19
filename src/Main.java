import java.util.ArrayList;
import java.util.List;

public class Main {
	//This program performs a breadth first search in order to
	//find the goal state for cannibals and missionaries problem.
	
	//It performs the search by generating the possible states given
	//the actual state. 
	
	//If the generated states have been already explored or are not
	//valid then they are not considered. 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		State initial_state = new State(3,3,'L',0,0);
		BFS search = new BFS();
		State sol = search.perform_search(initial_state);
		printOutput(sol);
	}
	
	private static void printOutput(State solution) {
		if (null == solution) {
			System.out.print("\nNo solution found.");
		} else {
			System.out.println("\nSolution: (cannibals_left_side,missionary_left_side,boat_location,cannibal_right_side,missionary_right_side): \n");
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(null!=state) {
				path.add(state);
				state = state.getParent();
			}

			int depth = path.size() - 1;
			int n = 0;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				if (state.isGoal()) {
					System.out.println("Goal State: " + state.toString() + " --> Goal State Reached!\n");
				} else {
					n++;
					if(n == 1){
						System.out.println("State[" + n + "]:" + state.toString() + " --> Initial State");
					}
					System.out.println("State[" + n + "]:" + state.toString());
				}
			}
			System.out.println("There were " + depth + " states before reaching the goal state.");
		}
	}

}

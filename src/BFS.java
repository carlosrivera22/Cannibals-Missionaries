import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
	
	public State perform_search(State initial_state){
		if (initial_state.isGoal()) {
			return initial_state;
		}
		Queue<State> frontier = new LinkedList<State>(); //FIFO
		Set<State> explored = new HashSet<State>();
		frontier.add(initial_state);
		while(true){
			if(frontier.isEmpty()){
				return null;//failure
			}
			State state = frontier.poll();
			explored.add(state);
			ArrayList<State> successors = state.generateSuccessors();
			for(State s:successors){
				//if statement to check if the generated state has been already explored
				if(!explored.contains(s) || !frontier.contains(s)){
					if(s.isGoal()){
						return s;
					}
					frontier.add(s);
				}
			}		
		}
	}
	
}

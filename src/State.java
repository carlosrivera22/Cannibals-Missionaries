import java.awt.List;
import java.util.ArrayList;

public class State {
	private int right_side_missionaries;
	private int left_side_missionaries;
	private int right_side_cannibals;
	private int left_side_cannibals;
	private char boat_location; 
	private State parent_state;
	
	public State(int left_side_cannibals,int left_side_missionaries,char boat_location,int right_side_cannibals,int right_side_missionaries){
		this.right_side_missionaries = right_side_missionaries;
		this.left_side_missionaries = left_side_missionaries;
		this.right_side_cannibals = right_side_cannibals; 
		this.left_side_cannibals = left_side_cannibals;
		this.boat_location = boat_location;
	}

	public int getRight_side_missionaries() {
		return right_side_missionaries;
	}

	public void setRight_side_missionaries(int right_side_missionaries) {
		this.right_side_missionaries = right_side_missionaries;
	}

	public int getLeft_side_missionaries() {
		return left_side_missionaries;
	}

	public void setLeft_side_missionaries(int left_side_missionaries) {
		this.left_side_missionaries = left_side_missionaries;
	}

	public int getRight_side_cannibals() {
		return right_side_cannibals;
	}

	public void setRight_side_cannibals(int right_side_cannibals) {
		this.right_side_cannibals = right_side_cannibals;
	}

	public int getLeft_side_cannibals() {
		return left_side_cannibals;
	}

	public void setLeft_side_cannibals(int left_side_cannibals) {
		this.left_side_cannibals = left_side_cannibals;
	}

	public char getBoat_location() {
		return boat_location;
	}

	public void setBoat_location(char boat_location) {
		this.boat_location = boat_location;
	}
	
	public boolean isGoal(){
		return left_side_cannibals == 0 && left_side_missionaries == 0;
	}
	
	public boolean isValid(){
		if(left_side_missionaries >= 0 && right_side_missionaries >= 0 && left_side_cannibals >= 0 && right_side_cannibals >= 0
				&& (left_side_missionaries == 0 || left_side_missionaries >= left_side_cannibals)
				&& (right_side_missionaries == 0 || right_side_missionaries >= right_side_cannibals)){
			return true;
		}
		return false;
	}
	
	public ArrayList<State> generateSuccessors(){
		ArrayList<State> successors = new ArrayList<State>();
		if(boat_location == 'L'){
			
			expand(successors, new State(left_side_cannibals - 1, left_side_missionaries, 'R',right_side_cannibals + 1, right_side_missionaries)); // One cannibal crosses left to right.
			expand(successors, new State(left_side_cannibals, left_side_missionaries - 1, 'R',right_side_cannibals, right_side_missionaries + 1)); // One missionary crosses left to right.
			expand(successors, new State(left_side_cannibals - 1, left_side_missionaries - 1, 'R',right_side_cannibals + 1, right_side_missionaries + 1)); // One missionary and one cannibal cross left to right.
			
			expand(successors, new State(left_side_cannibals - 2, left_side_missionaries, 'R',right_side_cannibals + 2, right_side_missionaries)); // Two cannibals cross left to right.
			expand(successors, new State(left_side_cannibals,left_side_missionaries-2, 'R', right_side_cannibals, right_side_missionaries + 2));//// Two missionaries cross left to right.
			
		}
		else {
			expand(successors, new State(left_side_cannibals + 1, left_side_missionaries, 'L',right_side_cannibals - 1, right_side_missionaries)); // One cannibal crosses right to left.
			expand(successors, new State(left_side_cannibals, left_side_missionaries + 1, 'L',right_side_cannibals, right_side_missionaries - 1)); // One missionary crosses right to left.
			expand(successors, new State(left_side_cannibals + 1, left_side_missionaries + 1, 'L',right_side_cannibals - 1, right_side_missionaries - 1)); // One missionary and one cannibal cross right to left.
			
			expand(successors, new State(left_side_cannibals + 2, left_side_missionaries, 'L',right_side_cannibals - 2, right_side_missionaries)); // Two cannibals cross right to left.
			expand(successors, new State(left_side_cannibals, left_side_missionaries + 2,'L',right_side_cannibals, right_side_missionaries - 2)); // Two missionaries cross right to left.
		}
		return successors;
	}
	
	private void expand(ArrayList<State> successors, State new_state){
		if(new_state.isValid()){
			new_state.setParent(this);
			successors.add(new_state);
		}
	}
	
	public void setParent(State parent_state){
		this.parent_state = parent_state;
	}
	
	
	public State getParent() {
		return parent_state;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		State s = (State) obj;
        return (s.left_side_cannibals == left_side_cannibals && s.left_side_missionaries == left_side_missionaries
        		&& s.boat_location == boat_location && s.right_side_cannibals == right_side_cannibals
        		&& s.right_side_missionaries == right_side_missionaries);
	}
	
	@Override
	public String toString() {
		if (boat_location == 'L') {
			return "(" + left_side_cannibals + "," + left_side_missionaries + ",L,"
        			+ right_side_cannibals + "," + right_side_missionaries + ")";
		} else {
			return "(" + left_side_cannibals + "," + left_side_missionaries + ",R,"
        			+right_side_cannibals + "," + right_side_missionaries + ")";
		}
     }
}

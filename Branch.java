import java.util.ArrayList;

public class Branch {
	private String color;
	private ArrayList<Branch> childBranches = new ArrayList<Branch>();
	private boolean active = true;
	private static int count = 0;
	private int name;
	private int level;
	private Branch parentBranch;
	/*
	 * @param color 
	 * decides the color of the branch, which will decide who will be able to cut it.
	 * Red, blue, or green. Red and blue can only be cut by their respective players.
	 * Green is cuttable by either.
	 * @param parentBranch
	 * this is used to decide what branch this one is connected to. If the parent is cut this will be cut as well.
	 * Added to parentBranch in the child as well as to the childBranch list in the parent.
	 * 
	 * In order to identify branches all use a static numbering system in order of instantiation
	 */
	public Branch (Branch parentBranch, String color) {
		this.color = color;
		this.parentBranch = parentBranch;
		parentBranch.addChild(this);
		level = parentBranch.getLevel() + 1;
		name = count;
		count++;
	}
	/*
	 * @param color 
	 * decides the color of the branch, which will decide who will be able to cut it.
	 * Red, blue, or green. Red and blue can only be cut by their respective players.
	 * Green is cuttable by either.
	 * 
	 * All branches have a parent branch. If no such branch is given then level = 0 or base branch, and the parent branch is 
	 * a null branch
	 */
	public Branch(String color) {
		this.color = color;
		level = 0;
		name = count;
		count++;
		parentBranch = new Branch(-1);
	}
	
	/*
	 * 
	 * Used by the other constructors to make a null branch. x is set to < 0 so it is ignored by other methods
	 */
	private Branch(int x) {
		this.color = "";
		level = 0;
		name = x;
	}
	
	public void addChild(Branch childBranch) {
		childBranches.add(childBranch);
	}
	
	/*
	 * If a branch is chopped all branches in the child arraylist are also chopped via a for each loop recursively, 
	 * as the child-child are all accessed until no more child branches are present
	 * 
	 * Chopping a branch means that it is no longer active, which means it wont shown in the branches array list of Hackenbush
	 */
	public void chopBranch() {
		for (Branch i: childBranches) {
			i.chopBranch();
		}
		active = false;
	}
	
	public ArrayList<Branch> getChildBranches(){
		return childBranches;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	
	public Branch getParent() {
		return parentBranch;
	}
}

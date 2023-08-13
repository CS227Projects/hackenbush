import java.util.ArrayList;
public class Hackenbush {
	private ArrayList<Branch> branches = new ArrayList<Branch>();
	private int maxLevel = 0;
	
	public Hackenbush() {
	}
	
	/*
	 * Adds a new branch to the larger tree.
	 * The max level is also determined if the new branch has a higher level
	 * This is used in the printed method, so branches that are iterated through from top to bottom, base branches
	 */
	public void addBranch(Branch b) {
		branches.add(b);
		if (b.getLevel() > maxLevel) {
			maxLevel = b.getLevel();
		}
	}
	
	/* 
	 * @param name
	 * inputed number id that is used to represent a branch. Searches list for such number
	 * 
	 * @param color
	 * the color of the player selecting a branch. If a branch with the right name is found
	 * the color is then compared to the branch color to make sure it is the player's color
	 * 
	 * color is ignored if the branch is green
	*/
	public boolean isValidBranch(int name, String color) {
		for (Branch b: branches) {
			if (b.getName() == name && (b.getColor().equals(color) || b.getColor().equals("green"))) {
				if (b.getActive()) {
					b.chopBranch();
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	/*
	 * When chopped branches active state is set to false
	 * Following method finds branches deemed inactive and removes them from the branch list
	 */
	public void trimBranches() {
		for (int i = branches.size()-1; i >= 0; i --) {
			if(!branches.get(i).getActive()) {
				branches.remove(i);
			}
		}
	}
	
	/*
	 * @param color
	 * the color of the player whose turn it is
	 * 
	 * Searches all of the branches to make sure the player of color color has a move that they can actually play.
	 */
	public boolean isFairMove(String color) {
		for (Branch b: branches) {
			if (b.getColor().equals(color) || b.getColor().equals("green")) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Returns all branches in a format of base branches last/bottom, top child branches first/top\
	 * Branches written in the form color first letter + name# + (parentbranch number)
	 */
	public String toString() {
		int level = maxLevel;
		String board = "";
		while (level >= 0) {
			for (Branch b: branches) {
				if (b.getLevel() == level) {
					String c = b.getColor().substring(0, 1);
					Branch p = b.getParent();
					if (p.getName() != -1) {
						board = board + " " + c + b.getName() + "-(" + p.getName() + ")";
					}
					else {
						board = board + " " + c + b.getName();
					}
				}
			}
			board = board + "\n";
			level--;
		}
		return board;
	}
	
	public Branch getBranch(int index) {
		return branches.get(index);
	}
	
	public int branchCount() {
		return branches.size();
	}
}

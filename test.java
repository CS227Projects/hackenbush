import java.util.Random;
import java.util.Scanner;
public class test {
	public static void main(String args[]) {
		playRound();
	}
	/*
	 * Players take turns
	 * The branch layout is printed before each turn,
	 * first the game checks to make sure their is a valid move for that player (loop ends if not and other player wins)
	 * after which the player is prompted to give a number of a corresponding branch
	 * if a valid branch of the player color (or green) and number exist, the branch and its children are removed from play
	 * otherwise the player is reprompted until a valid branch is selected
	 * 
	 * Afterwards other player repeates the same.
	 * This continues until isFairMove determines one of the players no longer has a valid move
	 */
	public static void playRound() {
		Hackenbush game = new Hackenbush();
		Scanner scan = new Scanner(System.in);
		branchRandomizer(10, 3, 3, game);
		String winner = "blue";
		while (game.branchCount() > 0 && game.isFairMove("blue") && game.isFairMove("red")) {
			System.out.println(game);
			System.out.println("B's turn. Select a valid move number:");
			int select;
			boolean valid = false;
			if (!game.isFairMove("blue")) {
				valid = true;
			}
			while (!valid) {
				select = scan.nextInt();
				if (game.isValidBranch(select, "blue")) {
					valid = true;
					game.trimBranches();
					winner = "blue";
				}
				else {
					System.out.println("Invalid move! Try another: ");
				}
			}
			select = -2;
			valid = false;
			System.out.println(game);
			if (!game.isFairMove("red")) {
				valid = true;
			}
			System.out.println("R's turn. Select a valid move number:");
			while (!valid) {
				select = scan.nextInt();
				if (game.isValidBranch(select, "red")) {
					valid = true;
					game.trimBranches();
					winner = "red";
				}
				else {
					System.out.println("Invalid move! Try another: ");
				}
			}
		}
		System.out.print(winner + " has won! ");
		scan.close();
	}
	
	/*
	 * @param count
	 * how many branches are present
	 * @param nodeCount
	 * how many branches are allowed to be childBranches of each branch
	 * @param baseCount
	 * how many starting/base branches everything starts from
	 * @param round
	 * the constructed object that all hackenbush methods originate from
	 * 
	 * goes through each baseBranch, alternating red blue and green
	 * each base branch receive a random number of 1-nodeCount number of branching branches
	 * every time a new branch is created it is tallied until branches present exceed count
	 * 
	 * Program designed to finish an iteration before checking count, so branch count is usually slightly more than count
	 */
	public static void branchRandomizer(int count, int nodeCount, int baseCount, Hackenbush round) {
		int total = count;
		int current = 0;
		int color = 1;
		String c = "blue";
		for (int i = 0; i < baseCount; i++) {
			if (color == 1) {
				c = "blue";
				color --;
			}
			else if (color == 0) {
				c = "green";
				color --;
			}
			else {
				c = "red";
				color += 2;
			}
			Branch b = new Branch(c);
			round.addBranch(b);
			total --;
		}
		Random random = new Random();
		while (total > 0) {
			int len = round.branchCount();
			for (int i = current; i < len; i++) {
				for (int j = 0; j < random.nextInt(nodeCount+1); j++) {
					if (color == 1) {
						c = "blue";
						color --;
					}
					else if (color == 0) {
						c = "green";
						color --;
					}
					else {
						c = "red";
						color += 2;
					}
					Branch b = new Branch(round.getBranch(i), c);
					round.addBranch(b);
					total --;
				}
			}
			current = len;
		}
	}
}

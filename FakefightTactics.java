import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FakefightTactics {

	static int player = 1;
	static LinkedList<String> team1 = new LinkedList<>();
	static LinkedList<String> team2 = new LinkedList<>();
	static LinkedList<String> team1R = new LinkedList<>();

	// MAIN HERE
	public static void main(String[] args) {

		System.out.println("Welcome to Fakefight Tactics!");
		System.out.println("Each player must assemble a team that will face off in a battle.");
		System.out.println("The characters have the following interactions:");
		System.out.println("* Thief backstabs tank and archer.");
		System.out.println("* Soldier outmaneuvers mage and catches thief.");
		System.out.println("* Archer shoots soldier and tank.");
		System.out.println("* Tank pulverizes mage and soldier.");
		System.out.println("* Mage blasts thief and archer.");
		System.out.println();
		System.out.println("Now, each player must pick their team.");
		System.out.println();
		
		makeTeamCall();

	} // end of main

	public static void makeTeamCall() {
		Scanner input = new Scanner(System.in);
		makeTeam(team1, input);

		for (int i = 0; i < team1.size(); i++) {
			String value = team1.get(i);

			team1R.add(value);

		}
		Collections.reverse(team1R);
		System.out.println("The battle begins!");
		while (true) {
			System.out.println(team1R + " vs. " + team2);
			String front1 = team1.getFirst(); // gets front member of team1
			String front2 = team2.getFirst(); // gets front member of team2
			System.out.println(front1 + " fights against " + front2);
			int winner = fight(front1, front2);

			if (winner == 2) {/* need the check, will make team 1 loose front member */
				System.out.println(front2 + " wins!");
				team1.remove();
				team1R.remove(team1R.size() - 1);

			} else if (winner == 1) {/* need the check, will make team 2 loose front member */
				System.out.println(front1 + " wins!");
				team2.remove();
			} else { /* need the check, both the same so remove both */
				team1.remove();
				team2.remove();
				team1R.remove(team1R.size() - 1);
				System.out.println("They kill each other!");
			}
			System.out.println();
			// checks if the end
			if (team1.isEmpty() || team2.isEmpty()) {
				break;
			}

		}

		// check who won
		if (team1.isEmpty() && !team2.isEmpty()) {
			System.out.println("Team 2 wins!");
		} else if (team2.isEmpty() && !team1.isEmpty()) {
			System.out.println("Team 1 wins!");
		} else {
			System.out.println("Both teams are gone, it is a tie!");
		}

	}

	public static Boolean makeTeam(List<String> team, Scanner input) {
		String currentTeam = null;
		if (team == team1) {
			currentTeam = "Team 1 ";
		} else if (team == team2) {
			currentTeam = "Team 2 ";
		}

		if (team.size() == 5) {
			System.out.println(team);
			System.out.println(currentTeam + "Finalize team selection? Y/N");
			String answer = input.nextLine();

			if (answer.equalsIgnoreCase("Y")) {
				System.out.println();
				if (team == team1) {
					makeTeam(team2, input);
				} else if (team == team2) {
					return true;
				}

			}

			else {
				System.out.println("resetting team");
				for (int i = 0; i < 5; i++) {
					team.remove(0);
				}

				return makeTeam(team, input);
			}

		}

		else if (team.size() < 5) {

			System.out.println(currentTeam + "select an available unit to put into your team.");

			if (!team.contains("THIEF")) {
				System.out.print("THIEF ");
			}
			if (!team.contains("SOLDIER")) {
				System.out.print("SOLDIER ");
			}
			if (!team.contains("ARCHER")) {
				System.out.print("ARCHER ");
			}
			if (!team.contains("TANK")) {
				System.out.print("TANK ");
			}
			if (!team.contains("MAGE")) {
				System.out.print("MAGE ");
			}

			System.out.println();
			String unit = input.nextLine();

			if (unit.toUpperCase().equals("THIEF") && !team.contains("THIEF")) {
				team.add("THIEF");
				return makeTeam(team, input);
			} else if (unit.toUpperCase().equals("THIEF") && team.contains("THIEF")) {
				System.out.println("Cannot add duplicate unit");
				return makeTeam(team, input);
			}
			if (unit.toUpperCase().equals("SOLDIER") && !team.contains("SOLDIER")) {
				team.add("SOLDIER");
				return makeTeam(team, input);
			} else if (unit.toUpperCase().equals("SOLDIER") && team.contains("SOLDIER")) {
				System.out.println("Cannot add duplicate unit");
				return makeTeam(team, input);
			}
			if (unit.toUpperCase().equals("ARCHER") && !team.contains("ARCHER")) {
				team.add("ARCHER");
				return makeTeam(team, input);
			} else if (unit.toUpperCase().equals("ARCHER") && team.contains("ARCHER")) {
				System.out.println("Cannot add duplicate unit");
				return makeTeam(team, input);
			}
			if (unit.toUpperCase().equals("TANK") && !team.contains("TANK")) {
				team.add("TANK");
				return makeTeam(team, input);
			} else if (unit.toUpperCase().equals("TANK") && team.contains("TANK")) {
				System.out.println("Cannot add duplicate unit");
				return makeTeam(team, input);
			}
			if (unit.toUpperCase().equals("MAGE") && !team.contains("MAGE")) {
				team.add("MAGE");
				return makeTeam(team, input);
			} else if (unit.toUpperCase().equals("MAGE") && team.contains("MAGE")) {
				System.out.println("Cannot add duplicate unit");
				return makeTeam(team, input);
			} else {
				System.out.println("Invalid unit");
				return makeTeam(team, input);
			}

		}

		return false;

	}

	public static Integer fight(String team1, String team2) {
		int winner = -1;

		if (team1.equalsIgnoreCase("THIEF")) {

			if (team2.equalsIgnoreCase("TANK")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("ARCHER")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("THIEF")) {
				winner = 0;
			}
			if (team2.equalsIgnoreCase("SOLDIER")) {
				winner = 2;
			}
			if (team2.equalsIgnoreCase("MAGE")) {
				winner = 2;
			}
		} // end of team1 THIEF

		if (team1.equalsIgnoreCase("SOLDIER")) {

			if (team2.equalsIgnoreCase("THIEF")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("MAGE")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("SOLDIER")) {
				winner = 0;
			}
			if (team2.equalsIgnoreCase("ARCHER")) {
				winner = 2;
			}
			if (team2.equalsIgnoreCase("TANK")) {
				winner = 2;
			}

		} // end of team1 SOLDIER

		if (team1.equalsIgnoreCase("ARCHER")) {

			if (team2.equalsIgnoreCase("SOLDIER")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("TANK")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("ARCHER")) {
				winner = 0;
			}
			if (team2.equalsIgnoreCase("THIEF")) {
				winner = 2;
			}
			if (team2.equalsIgnoreCase("MAGE")) {
				winner = 2;
			}
		} // end of team1 ARCHER

		if (team1.equalsIgnoreCase("TANK")) {

			if (team2.equalsIgnoreCase("MAGE")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("SOLDIER")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("TANK")) {
				winner = 0;
			}
			if (team2.equalsIgnoreCase("ARCHER")) {
				winner = 2;
			}
			if (team2.equalsIgnoreCase("THIEF")) {
				winner = 2;
			}
		} // end of team1 TANK

		if (team1.equalsIgnoreCase("MAGE")) {

			if (team2.equalsIgnoreCase("THIEF")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("ARCHER")) {
				winner = 1;
			}
			if (team2.equalsIgnoreCase("MAGE")) {
				winner = 0;
			}
			if (team2.equalsIgnoreCase("SOLDIER")) {
				winner = 2;
			}
			if (team2.equalsIgnoreCase("TANK")) {
				winner = 2;
			}
		} // end of team1 MAGE

		return winner;
	}// end of fight

}// final bracket end of class

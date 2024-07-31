import java.util.*;
public class CricketScoreCard {

    static final int overs = 20;
    static final int balls_per_over = 6;
    static final int maxBalls = overs * balls_per_over;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // Accept team names
        System.out.println("Enter name of team1: ");
        String team1 = input.nextLine();
        System.out.println("Enter name of team2: ");
        String team2 = input.nextLine();

        // Conduct a virtual toss
        int toss = random.nextInt(2);
        String tossWinner = (toss == 0) ? team1 : team2;
        String tossLosser = (toss == 0) ? team2 : team1;
        System.out.println(tossWinner + " won the toss and will bat first");
        String battingTeam = tossWinner;
        String bowlingTeam = tossLosser;

        // Hardcoded player names
        String[] team1Players = {"Babar Azam", "Shaheen Afridi", "Hasan Ali", "Mohammad Rizwan", "Fakhar Zaman",
                "Shadab Khan", "Haris Rauf", "Imran Khan", "Sarfaraz Ahmed", "Wahab Riaz", "Asad Shafiq"};
        String[] team2Players = {"Shoaib Malik", "Mohammad Hafeez", "Imad Wasim", "Junaid Khan", "Mohammad Amir",
                "Usman Qadir", "Danish Aziz", "Aamer Yamin", "Saeed Ajmal", "Umar Gul", "Ehsan Adil"};

        // Simulate innings for both teams
        int team1Score = simulateInnings(battingTeam, team1Players, random);
        int team2Score = simulateInnings(bowlingTeam, team2Players, random);

        // Determine and display the winner
        System.out.println(team1 + " Score: " + team1Score);
        System.out.println(team2 + " Score: " + team2Score);

        if (team1Score > team2Score) {
            System.out.println(team1 + " won the match!");
        } else if (team2Score > team1Score) {
            System.out.println(team2 + " won the match!");
        } else {
            System.out.println("The match is a tie!");
        }

        input.close();
    }

    public static int simulateInnings(String battingTeam, String[] players, Random random) {
        System.out.println("\n" + battingTeam + " is batting now.");

        // Initialize scorekeeping variables
        int totalRuns = 0;
        int wickets = 0;
        int oversBowled = 0;
        int ballsBowled = 0;

        // Track each batsman's performance
        int[] runsScored = new int[players.length];
        int[] ballsFaced = new int[players.length];
        boolean[] isOut = new boolean[players.length];

        int currentBatsmanIndex = 0;

        // Simulate innings
        while (ballsBowled < maxBalls && wickets < players.length - 1) {
            int runs = random.nextInt(7);
            boolean out = random.nextInt(10) < 1;

            if (out) {
                isOut[currentBatsmanIndex] = true;
                wickets++;
                System.out.println(players[currentBatsmanIndex] + " is out!");

                // Move to next batsman
                currentBatsmanIndex++;
            } else {
                runsScored[currentBatsmanIndex] += runs;
                totalRuns += runs;
                ballsFaced[currentBatsmanIndex]++;
            }

            ballsBowled++;
            if (ballsBowled % balls_per_over == 0) {
                oversBowled++;
            }

            System.out.println("Ball " + ballsBowled + ": " + runs + " runs scored");
        }

        System.out.println("\nInnings Summary for " + battingTeam + ":");
        System.out.println("Total Runs: " + totalRuns);
        System.out.println("Wickets Fallen: " + wickets);
        System.out.println("Overs Bowled: " + oversBowled + "." + (ballsBowled % balls_per_over));

        System.out.println("\nScore Card : ");
        System.out.println("Name            Runs  Balls  Strike Rate  Status");
        for (int i = 0; i < players.length; i++) {
            String name = String.format("%-20s", players[i]);
            String runs = String.format("%-5d", runsScored[i]);
            String balls = String.format("%-5d", ballsFaced[i]);
            String strikeRate = String.format("%-10.2f", (ballsFaced[i] > 0) ? (runsScored[i] / (double) ballsFaced[i]) * 100 : 0);
            String status = isOut[i] ? "Out" : "Not Out";

            // Print each player's data
            System.out.println(name + runs + balls + strikeRate + status);
        }

        System.out.println("Total score: " + totalRuns + "/" + wickets + " in " + oversBowled + "." + (ballsBowled % balls_per_over) + " overs");
        return totalRuns;
    }
}


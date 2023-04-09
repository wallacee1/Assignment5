import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

/**
 * A simple implementation of Pig dice game.
 * @author Eric Wallace
 */
public class PigDice {
    private List<Player> players;
    private int winningScore;
    private Random random;
    public static void main(String[] args) {
        PigDice game = new PigDice(100);
        String player1Name = getPlayerName(1);
        String player2Name = getPlayerName(2);
        game.addPlayer(new Player(player1Name));
        game.addPlayer(new Player(player2Name));
        game.play();
    }
    public PigDice(int winningScore) {
        this.players = new ArrayList<>();
        this.winningScore = winningScore;
        this.random = new Random();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }

    public String getUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        while (!input.equals("h") && !input.equals("r") && !input.equals("q")) {
            System.out.println("Invalid input. You must either enter 'h' (hold), 'r' (roll again) or 'q' (quit)");
        }
        return input;
    }

    public static String getPlayerName(int playerNumber)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name for Player " + playerNumber + ": ");
        return scanner.nextLine();
    }

    public void play() {
        boolean gameEnded = false;
        while (!gameEnded) {
            for (Player player : players) {
                int turnScore = 0;
                boolean playerTurnEnded = false;
                while (!playerTurnEnded) {
                    int rollResult = roll();
                    if (rollResult == 1) {
                        turnScore = 0;
                        playerTurnEnded = true;
                    } else {
                        turnScore += rollResult;
                        System.out.println(player.getName() + " rolled a " + rollResult + ". Current turn score: " + turnScore);
                    }

                    if (player.getScore() + turnScore >= winningScore) {
                        player.addScore(turnScore);
                        gameEnded = true;
                        break;
                    }

                    if (!playerTurnEnded) {
                        System.out.println("Do you want to hold or roll again? (h/r/q)");
                        Scanner scanner = new Scanner(System.in);
                        String choice = scanner.nextLine().toLowerCase();
                        if (choice.equals("h")) {
                            player.addScore(turnScore);
                            playerTurnEnded = true;
                        } else if (choice.equals("q")) {
                            gameEnded = true;
                            playerTurnEnded = true;
                        }
                    }
                }
                if (gameEnded) {
                    break;
                }
            }
        }

        saveScoresToFile();
        displayScores();
    }

    public void saveScoresToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt", true))) {
            for (Player player : players) {
                writer.write(player.getName() + ": " + player.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing scores to file: " + e.getMessage());
        }
    }

    public void displayScores() {
        System.out.println("Final scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore());
        }
    }
}
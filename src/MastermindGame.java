package src;

import java.util.Scanner;
import java.util.Random;

public class MastermindGame extends GuessingGame {
    public String secretCode;
    public static final char[] COLORS = {'R', 'Y','G', 'B','P' , 'O' };
    public static final int size = 4;

    public MastermindGame() {
        super();
        this.count = 10;
    }

    // Generates hidden code
    public void generateSecretCode() {
        Random rand = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int j = rand.nextInt(COLORS.length);
            code.append(COLORS[j]);
        }
        this.secretCode = code.toString();
    }

    @Override
    public void generateHiddenPhrase() {
        StringBuilder hiddenPhrase = new StringBuilder();
        for (int i = 0; i < size; i++) {
            hiddenPhrase.append('*');
        }
        this.hiddenPhrase = hiddenPhrase;
    }

    //checks partial number of matches
    public int checkPartials(String guess) {
        int partials = 0;
        StringBuilder secretSB = new StringBuilder(secretCode);
        StringBuilder guessSB = new StringBuilder(guess);
        for (int i = 0; i < size; i++) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                secretSB.setCharAt(i, '-');
                guessSB.setCharAt(i, '*');
            }
        }
        for (int i = 0; i < size; i++) {
            if (guessSB.charAt(i) != '*') {
                for (int j = 0; j < size; j++) {
                    if (secretSB.charAt(j) == guessSB.charAt(i) && secretSB.charAt(j) != '-') {
                        partials++;
                        secretSB.setCharAt(j, '-');
                        break;
                    }
                }
            }
        }
        return partials;
    }
    //checks exact number of match
    public int checkExacts(String guess) {
        int exacts = 0;
        StringBuilder secretSB = new StringBuilder(secretCode);
        StringBuilder guessSB = new StringBuilder(guess);
        for (int i = 0; i < size; i++) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                exacts++;
                secretSB.setCharAt(i, '-');
                guessSB.setCharAt(i, '*');}
        }
        return exacts;
    }

    @Override
    public GameRecord play() {
        generateSecretCode();
        count=10;
        Scanner scanner = new Scanner(System.in);
        int exacts=0;

        System.out.println("\nGuesses consist of 4 letters from:'R', 'Y','G', 'B','P' , 'O' \nEach guess consists of four slots filled with any combination of six possible colors.\nThe colors are Red, Green, Blue, Yellow, Orange, and Purple, each specified with a single letter (R, G, B, Y, O, P). \nYou should enter all the 4 letters together, e.g., \"RGRY\" for Red, Green, Red, Yellow.");

        int score=INITIAL_SCORE;
        while (count > 0) {
            System.out.print("\nPlease enter a 4 letter guess(from R, G, B, Y, O, P):");
            String guess = scanner.nextLine().toUpperCase();

            if (guess.length() != size || !guess.matches("[RGBYOP]+")) {
                System.out.println("You have entered invalid number of characters!\n Each guess should contain 4 letters only!\nGuess again with valid input.");
                continue;
            }
            exacts = checkExacts(guess);
            int partials = checkPartials(guess);
            System.out.println("The exact number os matches are : " + exacts);
            System.out.println("The Partial number of matches are : " + partials);
            if (exacts == size) {
                System.out.println("Congratulations!!You guessed right!! your present score is:" + score);
                break;}
            count--;
            score=score-SCORE_DEDUCTION;
            System.out.println("your remaining attempts: " + count+", your present score: " + score);
        }
        if (count == 0 && exacts!=size) {
            System.out.println("Game over! The hidden code is: " + secretCode);
        }
        return new GameRecord(score, "Player1");
    }

    public static void main(String[] args) {
        MastermindGame mastermind = new MastermindGame();
        AllGamesRecord record = mastermind.playAll();
        System.out.println(record);
        // prints out the results
        System.out.println("your scores average is: " + record.averageScore());
        System.out.println("Your highest scores are: ");
        System.out.println("the summary of your game is : ");
        System.out.println(record.highGameList(2));
    }
}

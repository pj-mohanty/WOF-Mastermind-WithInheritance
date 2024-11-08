package src;

import java.util.Scanner;

public abstract class GuessingGame extends Game{
    public int count;
    public String phrase;
    public StringBuilder hiddenPhrase;
    public static final int INITIAL_SCORE = 100;
    public static final int SCORE_DEDUCTION = 10;

    public GuessingGame(){}
    abstract void generateHiddenPhrase();

    @Override
    public boolean playNext(){
        System.out.print("want to continue the game? (enter y/n): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");
    }

    public abstract GameRecord play();
}

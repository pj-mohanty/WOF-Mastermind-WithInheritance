package src;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WOFAIGame extends WheelOfFortune {
    private List<NextAIPlayer> players;
    private int currentPlayerIndex;
    private List<String> phraseList;
    private int phraseIndex = 0;

    public WOFAIGame() {
        this.players = new ArrayList();
        this.phraseList = new ArrayList();
        this.players.add(new NextAIPlayer("DefaultAI"));
    }

    public WOFAIGame(NextAIPlayer var1) {
        this.players = new ArrayList();
        this.players.add(var1);
        this.phraseList = new ArrayList();
    }

    public WOFAIGame(List<NextAIPlayer> var1) {
        this.players = var1;
        this.phraseList = new ArrayList();
    }

    public void getPhrase() {
        try {
            this.phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException var2) {
            System.out.println("Error reading phrases file: " + var2.getMessage());
            return;
        }

        if (this.phraseList != null && !this.phraseList.isEmpty()) {
            this.phrase = (String)this.phraseList.get(this.phraseIndex);
            System.out.println("Selected phrase: " + this.phrase);
        } else {
            System.out.println("The phrases file is empty.");
        }
    }

    public GameRecord play() {
        this.getPhrase();
        System.out.println("Hidden phrase: ");
        this.generateHiddenPhrase();
        this.preGuess = "";
        this.count = 100;
        WheelOfFortunePlayer var1 = (WheelOfFortunePlayer)this.players.get(this.currentPlayerIndex);

        while(!this.phrase.equals(this.hiddenPhrase.toString()) && this.count > 0) {
            char var2 = var1.nextGuess();
            System.out.println("Ai Guess is : " + var2);
            this.processGuess(var2);
            this.preGuess = this.preGuess + var2;
            --this.count;
            System.out.println("Your current score is : " + this.count);
            System.out.println();
        }

        if (this.phrase.equals(this.hiddenPhrase.toString())) {
            PrintStream var10000 = System.out;
            String var10001 = var1.playerId();
            var10000.println("AI Player: " + var10001 + " won the game! your Score is :" + this.count + ".");
            System.out.println();
        } else {
            System.out.println("AI Player: " + var1.playerId() + " lost the game!");
            System.out.println();
        }

        return new GameRecord(this.count > 0 ? this.count : 0, var1.playerId());
    }

    public boolean playNext() {
        ++this.phraseIndex;
        if (this.phraseIndex >= this.phraseList.size()) {
            this.phraseIndex = 0;
            ++this.currentPlayerIndex;
            if (this.currentPlayerIndex >= this.players.size()) {
                return false;
            }
        }

        ((WheelOfFortunePlayer)this.players.get(this.currentPlayerIndex)).reset();
        return true;
    }

    public char getGuess(String var1) {
        WheelOfFortunePlayer var2 = (WheelOfFortunePlayer)this.players.get(this.currentPlayerIndex);
        return var2.nextGuess();
    }

    public static void main(String[] var0) {
        ArrayList var1 = new ArrayList();
        var1.add(new NextAIPlayer("AI1"));
        var1.add(new RandomAIPlayer("AI2"));
        var1.add(new GuessVowel("AI3"));
        WOFAIGame var2 = new WOFAIGame(var1);
        AllGamesRecord var3 = var2.playAll();
        System.out.println();
        System.out.println("Here is AI game performance: ");
        System.out.println("Each player high scores list as follows: ");
        Iterator var4 = var1.iterator();

        while(var4.hasNext()) {
            WheelOfFortunePlayer var5 = (WheelOfFortunePlayer)var4.next();
            System.out.println("High Scores for " + var5.playerId() + ": ");
            ArrayList var6 = var3.highGameList(var5.playerId(), 3);
            Iterator var7 = var6.iterator();

            while(var7.hasNext()) {
                GameRecord var8 = (GameRecord)var7.next();
                System.out.println(var8);
            }

            PrintStream var10000 = System.out;
            String var10001 = var5.playerId();
            var10000.println("Average Score for " + var10001 + ": " + var3.averageScore(var5.playerId()));
            System.out.println();
        }

        System.out.println();
        System.out.println("Total Average Scores: " + var3.averageScore());
        System.out.println();
        System.out.println("All players high scores list as follows: ");
        System.out.println(var3.highGameList(3));
        System.out.println();
    }
}

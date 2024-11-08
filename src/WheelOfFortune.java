package src;

import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class WheelOfFortune extends GuessingGame{
    abstract char getGuess(String previousGuesses);
    protected String preGuess;

    public WheelOfFortune(){
        super();
        this.preGuess="";
        this.count=100;
    }

    public void randomPhrase(){
        List<String> phraseList=null;
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        Random rand = new Random();
        int r= rand.nextInt(3);
        this.phrase = phraseList.get(r);}

    @Override
    public void generateHiddenPhrase(){
        this.hiddenPhrase=new StringBuilder("");
        for(int i=0;i<phrase.length();i++){
            char ch=phrase.charAt(i);
            if(Character.isLetter(ch)){
                hiddenPhrase.append("*");
            }
            else{
                hiddenPhrase.append(ch);
            }
        }
        System.out.println(hiddenPhrase);}


    public void processGuess(char guess) {
        int index=phrase.toLowerCase().indexOf(guess);
        if(index!=-1) {
            while(index!=-1){
                hiddenPhrase.setCharAt(index, phrase.charAt(index));
                index = phrase.indexOf(guess, index + 1);
            }
            System.out.println(hiddenPhrase);}

        else{
            System.out.println("Wrong guess! Guess Again!");
            System.out.println(hiddenPhrase);
        }
    }

    @Override
    public GameRecord play(){
        this.preGuess="";
        this.count=100;
        int score=INITIAL_SCORE;
        randomPhrase();
        generateHiddenPhrase();
        while(!phrase.equals(hiddenPhrase.toString()) && count>0 && score>0) {
            char guess =getGuess(preGuess);
            String originalHiddenPhrase = hiddenPhrase.toString();
            processGuess(guess);
            if (hiddenPhrase.toString().equals(originalHiddenPhrase)) {
                score -= SCORE_DEDUCTION;
                System.out.println("Incorrect guess. \n your score: " + score);
            } else {
                System.out.println("Correct guess! \n Your Present score: " + score);
            }
            count--;
            System.out.println("Attempts left : " + count);
        }
        if (phrase.equals(hiddenPhrase.toString())) {
            System.out.println("Congratulations! You win! \n Your score is " + score + ".");
        } else if (score <= 0) {
            System.out.println("Game over! \nyou lose.");
        } else {
            System.out.println("Please start a new game!");
        }
        return new GameRecord(score,"Player1");
    }
}

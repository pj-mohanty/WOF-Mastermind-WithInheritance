package src;

import java.util.Scanner;

public class WOFUserGame extends WheelOfFortune{
    public char getGuess(String previousGuesses){
        System.out.print("Please guess a letter in phrase: ");
        Scanner in=new Scanner(System.in);
        String input;
        while (true){
            input=in.next().toLowerCase();
            if(input.length()==1 && Character.isLetter(input.charAt(0))){
                char guess=input.charAt(0);
                if(preGuess.indexOf(String.valueOf(guess))!=-1){
                    System.out.println("You have already guess this letter, Please guess a different letter: ");
                }
                else {
                    preGuess=preGuess+guess;
                    return guess;
                }
            }
            else{
                System.out.println("Input is not valid, guess a letter");
            }
        }
    }


    public static void main(String [] args) {
        WOFUserGame wofUserGame = new WOFUserGame();
        AllGamesRecord record = wofUserGame.playAll();
        System.out.println(record);

        // Display records using AllGamesRecord methods
        System.out.println("Game summary: ");
        System.out.println("Average Score: "+record.averageScore());
        System.out.println("Your high scores are: ");
        System.out.println(record.highGameList(2));
    }
}

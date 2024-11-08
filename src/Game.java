package src;

import src.AllGamesRecord;

public abstract class Game {

    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord=new AllGamesRecord();
        int roundNumber=1;
        do {
            System.out.println("Round "+ roundNumber);
            GameRecord gameRecord=play();
            allGamesRecord.add(gameRecord);
            roundNumber++;
        }while(playNext());
        return allGamesRecord;
    }

    abstract GameRecord play();
    abstract boolean playNext();
}

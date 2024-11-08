package src;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AllGamesRecord {
    private List<GameRecord> gameRecords;
    public AllGamesRecord(){
        this.gameRecords=new ArrayList<>();
    }

    public void add(GameRecord gameRecord)
    {
        gameRecords.add(gameRecord);
    }

    // gets the average of the score

    public double averageScore()
    {
        double totalScore=0;
        for(GameRecord gameRecord:gameRecords){
            totalScore=totalScore+gameRecord.score;
        }
        double ave=totalScore/gameRecords.size();
        return Math.round(ave * 100.0) / 100.0;
    }

    // returns the highest game record of 1 player
    public ArrayList<GameRecord> highGameList(String playerId, int n)
    {
        List<GameRecord> playerScore=new ArrayList<>();
        for(GameRecord gameRecord:gameRecords){
            if(gameRecord.playerID.equals(playerId))
            {
                playerScore.add(gameRecord);
            }
        }
        Collections.sort(playerScore);
        return new ArrayList<>(playerScore.subList(0,n));

    }

    public double averageScore(String playerId)
    {
        double totalScoreID=0;
        int count=0;
        for(GameRecord gameRecord:gameRecords){
            if(gameRecord.playerID.equals(playerId))
            {
                totalScoreID=totalScoreID+gameRecord.score;
                count++;
            }
        }
        double aveID=totalScoreID/count;
        return Math.round(aveID * 100.0) / 100.0;
    }

    // List the first n number of highGameRecords
    public ArrayList<GameRecord> highGameList(int n)
    {
        Collections.sort(gameRecords);
        return new ArrayList<>(gameRecords.subList(0,n));
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        AllGamesRecord other = (AllGamesRecord) obj;
        return gameRecords.equals(other.gameRecords);
    }

    @Override

    public int hashCode()
    {
        return gameRecords.hashCode();
    }

    @Override

    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("All Game Records :\n");
        for(GameRecord gameRecord :gameRecords)
        {
            sb.append(gameRecord.toString()).append("\n");
        }
        return sb.toString();
    }
}
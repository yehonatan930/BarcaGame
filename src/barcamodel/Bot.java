package barcamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Bot extends Player {
    public Bot(boolean isWhite){
        super("computer", isWhite);
    }
    
    public void makeMove(BarcaBoard board){        
        ArrayList<Play> bestPlays = getBestPlays(board);        
        Play best = bestPlays.get((int) Math.floor((Math.random()*bestPlays.size())));  
        best.getMove().getDest().placePiece(best.getMove().getSrc().removePiece());
    }
    
    private ArrayList<Play> getBestPlays(BarcaBoard board){
        ArrayList<Play> possiblePlays = generateMoves(board);
        ArrayList<Play> bestPlays = new ArrayList<Play>();
        int bestQ = Collections.max(possiblePlays, new PlayCmp()).getPlayQuality();
        for(Play p : possiblePlays){
            if(p.getPlayQuality() == bestQ)
                bestPlays.add(p);
        }
        return bestPlays;
    }
    
    private ArrayList<Play> generateMoves(BarcaBoard board){
        //generating the moves
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        boolean hasScared = this.hasScaredPiece(board);       
        for(GamePiece p : playerPieces){
            if(p.getIsScared() == hasScared)
                possibleMoves.addAll(p.getPossibleMoves(board));          
        }
        //calculating every move's quality
        Play temp;
        ArrayList<Play> plays = new ArrayList<Play>();
        for(Move m:possibleMoves){
            temp = new Play(m);
            temp.calculateQuality(board, playerPieces);
            plays.add(temp);
        }       
        return plays;
    }
}



class PlayCmp implements Comparator<Play>{
    @Override
    public int compare(Play p1, Play p2) {
        return p1.getPlayQuality()-p2.getPlayQuality();
    }
}
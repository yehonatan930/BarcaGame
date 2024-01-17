/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barcamodel;

/**
 *
 * @author User
 */
public class Play {
    private Move move;
    private int playQuality;
    
    public Play(Move move){
        this.move = move;
        this.playQuality = 0;
    }

    public Move getMove() {
        return move;
    }

    public int getPlayQuality() {
        return playQuality;
    }

    /*
    public void setPlayQuality(int playQuality) {
        this.playQuality = playQuality;
    }
    */
    
    public int calculateQuality(BarcaBoard b, GamePiece[] pieces){
        int cal = 0;
        GamePiece p = move.getSrc().getPiece();
        GameSquare dest = move.getDest();
        
        //on water hole
        
        //dest water hole
        if(((dest.getX()-4+1)*(dest.getY()-4+1)) == 1 ||
                ((dest.getX()-7+1)*(dest.getY()-4+1)) == 1 ||
                ((dest.getX()-4+1)*(dest.getY()-7+1)) == 1 ||
                ((dest.getX()-7+1)*(dest.getY()-7+1)) == 1)
            cal += 12;
        //src water hole
        if(((move.getSrc().getX()-4+1)*(move.getSrc().getY()-4+1)) == 1 ||
                ((move.getSrc().getX()-7+1)*(move.getSrc().getY()-4+1)) == 1 ||
                ((move.getSrc().getX()-4+1)*(move.getSrc().getY()-7+1)) == 1 ||
                ((move.getSrc().getX()-7+1)*(move.getSrc().getY()-7+1)) == 1)
            cal -= 8;
        
        for(GamePiece piece : pieces){          
            if(piece != p){
                if(((piece.getSquare().getX()-4+1)*(piece.getSquare().getY()-4+1)) == 1 ||
                    ((piece.getSquare().getX()-7+1)*(piece.getSquare().getY()-4+1)) == 1 ||
                    ((piece.getSquare().getX()-4+1)*(piece.getSquare().getY()-7+1)) == 1 ||
                    ((piece.getSquare().getX()-7+1)*(piece.getSquare().getY()-7+1)) == 1)
                    cal += 10;
            }
        }
        
        //scares op
        for (int i = -1; i <=1 ; i++)
            for (int j = -1; j <=1 ; j++)
                if(!b.isEmpty(i + dest.getX(), j + dest.getY()))
                    if (b.getPieceInPos(i + dest.getX(), j + dest.getY()).isScared(p))
                        cal += 3;
        
        //access to water hole        
        dest.placePiece(move.getSrc().removePiece());
        for(GamePiece piece : pieces){          
            if (piece.canMove(b.getSquare(3, 3), b))
                cal += 1;
            if (piece.canMove(b.getSquare(6, 3), b))
                cal += 1;
            if (piece.canMove(b.getSquare(3, 6), b))
                cal += 1;
            if (piece.canMove(b.getSquare(6, 6), b))
                cal += 1;           
        }
        move.getSrc().placePiece(dest.removePiece());
        
        
        
        playQuality = cal;
        return cal;
    }
}

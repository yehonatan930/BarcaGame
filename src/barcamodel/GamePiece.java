package barcamodel;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public abstract class GamePiece {
    private final boolean isWhite;
    private boolean isScared;
    protected ImageIcon icon;
    protected GameSquare square;

    public GamePiece(boolean isWhite) {
        this.isWhite = isWhite;
        setIsScared(false);

    }

    public void setIsScared(boolean isScared) {
        this.isScared = isScared;
    }

    public void setSquare(GameSquare s) {
        this.square = s;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public GameSquare getSquare() {
        return square;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }

    public boolean getIsScared() {
        return isScared;
    }

    //isScared:
    //this function will check the piece's neighbors and set scared state accordingly.
    //the function will return the scared state (false/true).
    protected boolean isOtherPieceScary(GamePiece p) {
        return this.isOtherPieceAnEnemy(p);
    }

    protected boolean isOtherPieceAnEnemy(GamePiece p) {
        return p != null && p.getIsWhite() != this.getIsWhite();
    }

    /*
    public GamePiece getScaryNeighbor(){
        for (int i = -1; i <=1 ; i++)
            for (int j = -1; j <=1 ; j++)
                if (isScared (board.getPieceInPos(i+currentX, j+currentY))){
                     setScared(true);
                    return board.getPieceInPos(i+currentX, j+currentY);
                   
                }
        setScared(false);
         return null;       
    }
*/

    public boolean canMove(GameSquare targetSquare, BarcaBoard b) {
        return targetSquare.isEmpty() && targetSquare != this.square;
    }

    public abstract boolean isStuck(BarcaBoard b);

    public abstract ArrayList<Move> getPossibleMoves(BarcaBoard b);
}

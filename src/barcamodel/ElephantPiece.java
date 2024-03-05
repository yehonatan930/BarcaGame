package barcamodel;

import static java.lang.Math.abs;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ElephantPiece extends GamePiece {
    public ElephantPiece(boolean isWhite) {
        super(isWhite);
        if (isWhite)
            this.icon = new ImageIcon("images/white_pieces/elephant.png");
        else
            this.icon = new ImageIcon("images/brown_pieces/elephant.png");
    }

    @Override
    public boolean isOtherPieceScary(GamePiece p) {
        return super.isOtherPieceAnEnemy(p) && p instanceof MousePiece;
    }

    @Override
    public boolean canMove(GameSquare targetSquare, BarcaBoard b) {

        //on another piece?
        if (!super.canMove(targetSquare, b))
            return false;

        int x, y, xDiff, yDiff;
        x = this.square.getX();
        y = this.square.getY();
        xDiff = abs(x - targetSquare.getX());
        yDiff = abs(y - targetSquare.getY());

       // if not moved diagonally or horizontally or vertically
        if (xDiff != yDiff && xDiff != 0 && yDiff != 0)
            return false;


        //will be scared?
        GameSquare temp = this.square;
        targetSquare.placePiece(this);
        if(targetSquare.getScaryNeighborAndSetCurrentPieceIsScared() != null){
            targetSquare.removePiece();
            temp.placePiece(this);
            square.getScaryNeighborAndSetCurrentPieceIsScared();
            return false;
        }
        targetSquare.removePiece();
        temp.placePiece(this);
        square.getScaryNeighborAndSetCurrentPieceIsScared();


        //jumps above another piece?
        int factor; // +1 or -1
        // moved vertically
        if (xDiff != 0 && yDiff == 0) {
            factor = xDiff / (x - targetSquare.getX());
            for (int i = targetSquare.getX() + factor; i != x; i += factor) {
                if (!b.getSquare(i - 1, y - 1).isEmpty()) {
                    return false;
                }
            }
        // moved horizontally
        } else if (yDiff != 0 && xDiff == 0) {
            factor = yDiff / (y - targetSquare.getY());
            for (int i = targetSquare.getY() + factor; i != y; i += factor) {
                if (!b.getSquare(x - 1, i - 1).isEmpty()) {
                    return false;
                }
            }
        // moved diagonally
        } else {
            int factorX, factorY;
            factorX = xDiff / (x - targetSquare.getX());
            factorY = yDiff / (y - targetSquare.getY());
            x -= 1;
            y -= 1;
            for (int i = 1; i < xDiff; i++) {
                if (!b.getSquare(x - i * factorX, y - i * factorY).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(BarcaBoard b) {
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        GameSquare target;
        int i, j, len = BarcaBoard.BOARDLEN - 2;

        for (i = this.square.getX() - 1; i < len; i++) {
            target = b.getSquare(i, this.square.getY() - 1);
            if (this.canMove(target, b)) {
                possibleMoves.add(new Move(this.square, target));
            }
        }

        for (i = this.square.getX() - 1; i >= 0; i--) {
            target = b.getSquare(i, this.square.getY() - 1);
            if (this.canMove(target, b)) {
                possibleMoves.add(new Move(this.square, target));
            }
        }

        for (i = this.square.getY() - 1; i < len; i++) {
            target = b.getSquare(this.square.getX() - 1, i);
            if (this.canMove(target, b)) {
                possibleMoves.add(new Move(this.square, target));
            }
        }

        for (i = this.square.getY() - 1; i >= 0; i--) {
            target = b.getSquare(this.square.getX() - 1, i);
            if (this.canMove(target, b)) {
                possibleMoves.add(new Move(this.square, target));
            }
        }

        for (i = this.square.getX() - 1, j = this.square.getY() - 1; i < len && j < len; i++, j++) {
            target = b.getSquare(i, j);
            if (this.canMove(target, b)) {
                possibleMoves.add(new Move(this.square, target));
            }
        }

        for (i = this.square.getX() - 1, j = this.square.getY() - 1; i < len && j >= 0; i++, j--) {
            target = b.getSquare(i, j);
            if (this.canMove(target, b)) {
                possibleMoves.add(new Move(this.square, target));
            }
        }

        for (i = this.square.getX() - 1, j = this.square.getY() - 1; i >= 0 && j >= 0; i--, j--) {
            target = b.getSquare(i, j);
            if (this.canMove(target, b)) {
                possibleMoves.add(new Move(this.square, target));
            }
        }

        for (i = this.square.getX() - 1, j = this.square.getY() - 1; i >= 0 && j < len; i--, j++) {
            target = b.getSquare(i, j);
            if (this.canMove(target, b)) {
                possibleMoves.add(new Move(this.square, target));
            }
        }

        return possibleMoves;
    }

    @Override
    public boolean isStuck(BarcaBoard b) {
        return getPossibleMoves(b).isEmpty();
    }
}

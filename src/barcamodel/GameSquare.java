package barcamodel;

import barcaview.SquarePanel;

public class GameSquare {
    private final int x;
    private final int y;
    private GamePiece currentPiece;
    private static BarcaBoard board;
    private SquarePanel myPanel;


    public GameSquare(BarcaBoard b, int x, int y) {
        board = b;
        this.x = x;
        this.y = y;
        this.currentPiece = null;
    }

    public void setPanel(SquarePanel s) {
        this.myPanel = s;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public GamePiece getPiece() {
        return this.currentPiece;
    }


    public GamePiece removePiece() {
        GamePiece p = this.currentPiece;
        this.currentPiece = null;
        myPanel.removePiece();
        p.setSquare(null);
        return p;
    }

    public void placePiece(GamePiece p) {
        if (p != null) {
            this.currentPiece = p;
            this.myPanel.placePiece(p.getIcon());
            p.setSquare(this);
            p.setIsScared(false);
            setScaredNeighbors();
        }
    }

    public boolean isEmpty() {
        return currentPiece == null;
    }

    public GamePiece getScaryNeighborAndSetCurrentPieceIsScared() {
        if (this.isEmpty()) {
            this.currentPiece.setIsScared(false);
            return null;
        }
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                GamePiece surroundingPiece = board.getPieceInPos(i + x, j + y);
                if (this.currentPiece.isOtherPieceScary(surroundingPiece)) {
                    this.currentPiece.setIsScared(true);
                    return surroundingPiece;
                }
            }
        this.currentPiece.setIsScared(false);
        return null;
    }

    public void setScaredNeighbors() {//NEEDS IMPROVEMENT!!!!!!
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (board.placeIsNotEmpty(i + x, j + y)) {
                    GamePiece surroundingPiece = board.getPieceInPos(i + x, j + y);
                    if (surroundingPiece.isOtherPieceScary(this.currentPiece))
                        surroundingPiece.setIsScared(true);
                }
            }
    }
}

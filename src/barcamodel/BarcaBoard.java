package barcamodel;

public class BarcaBoard {
    static final int BOARDLEN = 12;
    private final GameSquare[][] boardMat;

    public BarcaBoard() {
        boardMat = new GameSquare[BOARDLEN][BOARDLEN];
        for (int i = 0; i < BOARDLEN; i++)
            for (int j = 0; j < BOARDLEN; j++)
                boardMat[i][j] = new GameSquare(this, i, j);
    }

    public GamePiece getPieceInPos(int x, int y) {
        return this.boardMat[x][y].getPiece();
    }

    public boolean placeIsNotEmpty(int x, int y) {
        return !this.boardMat[x][y].isEmpty();
    }

    public GameSquare getSquare(int x, int y) {
        return boardMat[x + 1][y + 1];
    }

    @Override
    public String toString() {
        return "";
    }
}

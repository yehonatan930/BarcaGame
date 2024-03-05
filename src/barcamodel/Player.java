package barcamodel;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private int playerScore;
    protected final GamePiece[] playerPieces;
    private final boolean isWhite;
    private final int[][] BLACKPLACES = {{0, 4}, {0, 5}, {1, 3}, {1, 6}, {1, 4}, {1, 5}};
    private final int[][] WHITEPLACES = {{9, 4}, {9, 5}, {8, 3}, {8, 6}, {8, 4}, {8, 5}};

    public Player(String name, boolean isWhite) {
        this.isWhite = isWhite;
        setPlayerName(name);
        setPlayerScore(0);
        playerPieces = new GamePiece[]{new ElephantPiece(isWhite), new ElephantPiece(isWhite), new LionPiece(isWhite), new LionPiece(isWhite), new MousePiece(isWhite), new MousePiece(isWhite)};
    }

    public void placeOnBoard(BarcaBoard barcaBoard) {
        int x = 0;
        int[][] placesToUse = (isWhite) ? WHITEPLACES : BLACKPLACES;
        for (int[] p : placesToUse) {
            barcaBoard.getSquare(p[0], p[1]).placePiece(this.playerPieces[x]);
            this.playerPieces[x++].setSquare(barcaBoard.getSquare(p[0], p[1]));
        }
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getPlayerScore() {
        return this.playerScore;
    }

    public boolean hasScaredPiece(BarcaBoard b) {
        ArrayList<GamePiece> scaredPieces = new ArrayList<GamePiece>();
        for (GamePiece p : playerPieces) {
            p.square.getScaryNeighborAndSetCurrentPieceIsScared();
            if (p.getIsScared() && !p.isStuck(b)) {
                scaredPieces.add(p);
            }
        }
        return !scaredPieces.isEmpty();
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}

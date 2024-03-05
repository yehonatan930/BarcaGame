package barcacontroller;

import barcamodel.BarcaBoard;
import barcamodel.Bot;
import barcamodel.Move;
import barcamodel.GameSquare;
import barcamodel.Player;
import barcaview.BoardPanel;
import barcaview.GameFrame;
import barcaview.SquarePanel;

public class GameController {
    private GameFrame frame;
    private BarcaBoard board;
    private Move currentMove;
    private final Player[] players = new Player[2];
    private boolean click;
    private boolean isWhiteTurn;
    private boolean isGameRunning;

    public GameController() {
        click = false;
    }

    public void move(SquarePanel s) {
        int offset = (this.isWhiteTurn) ? 0 : 1;
        boolean isBotTurn = players[offset] instanceof Bot;
        if (isGameRunning && !isBotTurn)
            isBotTurn = turn(s, offset);
        offset = offset == 0 ? 1 : 0;
        if (isGameRunning && isBotTurn && players[offset] instanceof Bot) {
            ((Bot) players[offset]).makeMove(board);
            win(offset);
            this.isWhiteTurn = !this.isWhiteTurn;
            printTurn();
        }
    }

    public boolean turn(SquarePanel s, int offset) {
        GameSquare currentGameSquare = board.getSquare(s.getXPos(), s.getYPos());
        if (!this.click && !currentGameSquare.isEmpty() && currentGameSquare.getPiece().getIsWhite() == this.isWhiteTurn && players[offset].hasScaredPiece(board) == currentGameSquare.getPiece().getIsScared()) {
            this.currentMove = new Move(currentGameSquare);
            this.click = true;
        } else if (this.click && currentMove.getSrc().getPiece().canMove(currentGameSquare, board)) {
            this.currentMove.setDest(currentGameSquare);
            this.currentMove.getDest().placePiece(this.currentMove.getSrc().removePiece());
            this.click = false;
            win(offset);
            this.isWhiteTurn = !this.isWhiteTurn;
            printTurn();
            return true;
        } else {
            this.click = false;
            this.currentMove = null;
        }
        return false;
    }

    public void initGame() {
        this.frame = new GameFrame();
        this.board = new BarcaBoard();
        BoardPanel guiBoard = new BoardPanel(this);
        frame.add(guiBoard);
        for (int x = 0; x < BoardPanel.GBOARDLEN; x++) {
            for (int y = 0; y < BoardPanel.GBOARDLEN; y++) {
                this.board.getSquare(x, y).setPanel(guiBoard.getSquarePanel(x, y));
            }
        }

        players[0] = new Player("you", true);
        players[1] = new Bot(false);

        for (Player p : players) {
            p.placeOnBoard(board);
        }
        frame.setVisible(true);
        gameRunning();
    }

    public void gameRunning() {
        isWhiteTurn = true;
        printTurn();
        isGameRunning = true;
        int offset;
        while (isGameRunning) {
            /*
            offset = (this.whiteTurn)? 0:1;
            if(players[offset] instanceof Bot){
                System.out.println("BOT");
                ((Bot)players[offset]).makeMove(board);
                win(offset);
                printTurn();
                this.whiteTurn = !this.whiteTurn;
            }
            */
        }
    }

    public void printTurn() {
        if (isWhiteTurn)
            System.out.println("white's turn");
        else
            System.out.println("brown's turn");
    }

    public void win(int offset) {
        int count = 0;
        if (!board.getSquare(3, 3).isEmpty())
            count += (board.getSquare(3, 3).getPiece().getIsWhite() == isWhiteTurn) ? 1 : 0;
        if (!board.getSquare(3, 6).isEmpty())
            count += (board.getSquare(3, 6).getPiece().getIsWhite() == isWhiteTurn) ? 1 : 0;
        if (!board.getSquare(6, 3).isEmpty())
            count += (board.getSquare(6, 3).getPiece().getIsWhite() == isWhiteTurn) ? 1 : 0;
        if (!board.getSquare(6, 6).isEmpty())
            count += (board.getSquare(6, 6).getPiece().getIsWhite() == isWhiteTurn) ? 1 : 0;
        if (count > 2) {
            String str = players[offset].getPlayerName() + " won the game!";
            System.out.println(str);
            isGameRunning = false;
            frame.win(str);
        }
    }
}

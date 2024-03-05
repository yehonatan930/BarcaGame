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
    private Player[] players;
    private boolean click;
    private boolean whiteTurn;
    private boolean gameRunning;

    public GameController() {
        click = false;
    }

    public void move(SquarePanel s) {
        int offset = (this.whiteTurn) ? 0 : 1;
        boolean turn = players[offset] instanceof Bot;
        //System.out.println("MOV");
        if (gameRunning && !turn)
            turn = turn(s, offset);
        offset = Math.abs(offset - 1);
        if (gameRunning && turn && players[offset] instanceof Bot) {
            //System.out.println("BOT");
            ((Bot) players[offset]).makeMove(board);
            win(offset);
            this.whiteTurn = !this.whiteTurn;
            printTurn();
        }
    }

    public boolean turn(SquarePanel s, int offset) {
        GameSquare current = board.getSquare(s.getXPos(), s.getYPos());
        //System.out.println(s.getXPos() + " " + s.getYPos());
        if (!this.click && !current.isEmpty() && current.getPiece().getIsWhite() == this.whiteTurn && players[offset].hasScaredPiece(board) == current.getPiece().getIsScared()) {
            //System.out.println("1 click");
            this.currentMove = new Move(current);
            this.click = true;
        } else if (this.click && currentMove.getSrc().getPiece().canMove(current, board)) {
            //System.out.println("2 click");
            this.currentMove.addDest(current);
            this.currentMove.getDest().placePiece(this.currentMove.getSrc().removePiece());
            this.click = false;
            win(offset);
            this.whiteTurn = !this.whiteTurn;
            printTurn();
            //System.out.println(players[offset].getPlayerName());
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

        boolean amIWhite = true;
        players = new Player[2];
        players[0] = new Player("you", amIWhite);
        players[1] = new Bot(!amIWhite);

        for (Player p : players) {
            p.placeOnBoard(board);
        }
        frame.setVisible(true);
        gameRunning();
    }

    public void gameRunning() {
        whiteTurn = true;
        printTurn();
        gameRunning = true;
        int offset;
        while (gameRunning) {
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
        if (whiteTurn)
            System.out.println("white's turn");
        else
            System.out.println("brown's turn");
    }

    public void win(int offset) {
        int count = 0;
        if (!board.getSquare(3, 3).isEmpty())
            count += (board.getSquare(3, 3).getPiece().getIsWhite() == whiteTurn) ? 1 : 0;
        if (!board.getSquare(3, 6).isEmpty())
            count += (board.getSquare(3, 6).getPiece().getIsWhite() == whiteTurn) ? 1 : 0;
        if (!board.getSquare(6, 3).isEmpty())
            count += (board.getSquare(6, 3).getPiece().getIsWhite() == whiteTurn) ? 1 : 0;
        if (!board.getSquare(6, 6).isEmpty())
            count += (board.getSquare(6, 6).getPiece().getIsWhite() == whiteTurn) ? 1 : 0;
        if (count > 2) {
            String str = players[offset].getPlayerName() + " won the game!";
            System.out.println(str);
            gameRunning = false;
            frame.win(str);
        }
    }
}

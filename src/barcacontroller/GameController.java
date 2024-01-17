/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcacontroller;

import barcamodel.BarcaBoard;
import barcamodel.Bot;
import barcamodel.Move;
import barcamodel.GameSquare;
import barcamodel.GamePiece;
import barcamodel.Player;
import barcaview.BoardPanel;
import barcaview.GameFrame;
import barcaview.SquarePanel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class GameController {
    private GameFrame frame;
    private BarcaBoard board;
    private BoardPanel guiBoard;
    private Move currentMove;
    private Player players[];
    private ArrayList<Move> allmoves;
    private boolean click;
    private boolean whiteTurn;
    private boolean gameRunning;
    
    public GameController (){
        click = false;
    }
    
    public void move(SquarePanel s){
        int offset = (this.whiteTurn)? 0:1;
        boolean turn = players[offset] instanceof Bot;
        //System.out.println("MOV");
        if(gameRunning && !turn)
            turn = turn(s, offset);
        offset = Math.abs(offset-1);
        if(gameRunning && turn && players[offset] instanceof Bot){
            //System.out.println("BOT");
            ((Bot)players[offset]).makeMove(board);
            win(offset);
            this.whiteTurn = !this.whiteTurn;
            printTurn();
        }
    }
    
    public boolean turn(SquarePanel s, int offset){
        GameSquare current = board.getSquare(s.getXPos(), s.getYPos());
        //System.out.println(s.getXPos() + " " + s.getYPos());
        if(!this.click && !current.isEmpty() && current.getPiece().isWhite() == this.whiteTurn && players[offset].hasScaredPiece(board) == current.getPiece().getScared()){
            //System.out.println("1 click");
            this.currentMove = new Move(current);
            this.click = true;
        }
        else if(this.click && currentMove.getSrc().getPiece().canMove(current, board)){
            //System.out.println("2 click");
            this.currentMove.addDest(current);
            this.currentMove.getDest().placePiece(this.currentMove.getSrc().removePiece());           
            this.click = false;
            win(offset);
            this.whiteTurn = !this.whiteTurn;
            printTurn();
            //System.out.println(players[offset].getPlayerName());
            return true;
        }
        else{
            this.click = false;
            this.currentMove = null;
        }
        return false;
    }
    
    public void initGame(){
        //board setting
        frame = new GameFrame();
        board = new BarcaBoard();
        guiBoard = new BoardPanel(this);
        frame.add(guiBoard);
        for(int i = 0; i < BoardPanel.GBOARDLEN; i++)
            for(int j = 0; j < BoardPanel.GBOARDLEN; j++){
                board.getSquare(i, j).setPanel(guiBoard.getSquarePanel(i, j));
            }
        //
        players = new Player[2];
        players[0] = new Player("tom", true);
        //players[1] = new Player("ben", false);
        //players[0] = new Bot(true);
        players[1] = new Bot(false);
        
        for(Player p:players){
            p.placeOnBoard(board);
        }
        frame.setVisible(true);
        gameRunning();
    }
    
    public void gameRunning(){
        whiteTurn = true;
        printTurn();
        gameRunning = true;
        int offset;
        while(gameRunning){
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
    
    public void printTurn(){
        if(whiteTurn)
            System.out.println("white's turn");
        else
            System.out.println("brown's turn");
    }
    
    public void win(int offset){
        int count = 0;
        if(!board.getSquare(3, 3).isEmpty())
            count += (board.getSquare(3, 3).getPiece().isWhite() == whiteTurn)? 1:0;       
        if(!board.getSquare(3, 6).isEmpty())
            count += (board.getSquare(3, 6).getPiece().isWhite() == whiteTurn)? 1:0;      
        if(!board.getSquare(6, 3).isEmpty())
            count += (board.getSquare(6, 3).getPiece().isWhite() == whiteTurn)? 1:0;       
        if(!board.getSquare(6, 6).isEmpty())
            count += (board.getSquare(6, 6).getPiece().isWhite() == whiteTurn)? 1:0;        
        if(count>2){
            String str = players[offset].getPlayerName() + " won the game!";
            System.out.println(str);           
            gameRunning = false;    
            frame.win(str);
        }
    }
}

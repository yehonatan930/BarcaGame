/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcamodel;

import barcaview.SquarePanel;

/**
 *
 * @author student
 */
public class GameSquare {
    private int x;
    private int y;
    private GamePiece currentPiece;
    private static BarcaBoard board;
    private SquarePanel myPanel;
    
    
    public GameSquare (BarcaBoard b, int x, int y){
        this.board = b;
        this.x = x;
        this.y = y;
        this.currentPiece = null;
    }
    
    public void setPanel(SquarePanel s){
        this.myPanel = s;
    }
    
    public int getX(){return this.x;}
    
    public int getY(){return this.y;}
    
    public GamePiece getPiece(){return this.currentPiece;}
    
    
    public GamePiece removePiece (){
        GamePiece p = this.currentPiece;
        this.currentPiece = null;
        myPanel.removePiece();
        p.setSquare(null);
        return p;
    }
    
    public void placePiece (GamePiece p){
        if(p != null){
            this.currentPiece = p;
            this.myPanel.placePiece(p.getIcon());
            p.setSquare(this);
            p.setScared(false);
            setScaredNeighbors();
        }
    }    
    
    public boolean isEmpty(){return currentPiece == null;}
    
    public GamePiece getScaryNeighbor(){
        if(this.isEmpty()){
            this.currentPiece.setScared(false);
            return null;
        }
        for (int i = -1; i <=1 ; i++)
            for (int j = -1; j <=1 ; j++)
                if (this.currentPiece.isScared(board.getPieceInPos(i + x, j + y))){
                    this.currentPiece.setScared(true);
                    return board.getPieceInPos(i + x, j + y);                  
                }
        this.currentPiece.setScared(false);
        return null;
    }
    
    public void setScaredNeighbors(){//NEEDS IMPROVEMENT!!!!!!
        for (int i = -1; i <=1 ; i++)
            for (int j = -1; j <=1 ; j++)
                if(!board.isEmpty(i + x, j + y))
                    if (board.getPieceInPos(i + x, j + y).isScared(this.currentPiece))
                        board.getPieceInPos(i + x, j + y).setScared(true);                            
    }
}

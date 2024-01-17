/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barcamodel;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public abstract class GamePiece {
    //variables:
    private boolean white = false;
    private boolean scared;
    protected ImageIcon icon;
    protected GameSquare square;
        
    
      
    //constructors:  
    public GamePiece(boolean white){
        this.white = white;
        setScared(false);
        
    }
    public void setSquare(GameSquare s){
       this.square = s;
    }
    
    public ImageIcon getIcon() {return icon;}

    public GameSquare getSquare() {
        return square;
    }
    
    
    
  
    //methods:
    //setters
    public void setScared(boolean isScared) {
        this.scared = isScared;
    }  
    //getters
    public boolean isWhite() {
        return this.white;
    }
    //isScared:
    //this function will check the piece's neighbors and set scared state accordingly.
    //the function will return the scared state (false/true).
    protected  boolean isScared(GamePiece p){
        return p!=null && p.isWhite()!= this.isWhite();
    }
    
    public boolean getScared(){
        return scared;
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
    
    public boolean canMove(GameSquare targetSquare, BarcaBoard b){
        return targetSquare.isEmpty() && targetSquare != this.square;
    }
    
    public abstract boolean isStuck(BarcaBoard b);
    
    public abstract ArrayList<Move> getPossibleMoves(BarcaBoard b);
}

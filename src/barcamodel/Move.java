/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barcamodel;

import barcaview.SquarePanel;

/**
 *
 * @author User
 */
public class Move {
    private GameSquare src;
    private GameSquare dest;
    
    
    //array of squares of possible moves.
    
    //constructors:
    public Move(GameSquare src){
        this.src = src;
    }
    
    public Move(GameSquare src, GameSquare dest){
        this.src = src;
        addDest(dest);
    }
    
    public boolean addDest(GameSquare dest){ //checks if move is valid!
        this.dest = dest;
        return true;
    }
    

    //methods:
    //getters
    public GameSquare getSrc(){return this.src;}
    
    public GameSquare getDest(){return this.dest;}
    
    //setters

    @Override
    public String toString() {
        return "src: (" + this.src.getX() + ", " + this.src.getY();// + ")/ntarget: (" + this.dest.getX() + ", " + this.dest.getY() + ")/n"; 
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcaview;

import barcamodel.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author student
 */
public class SquarePanel extends JPanel implements MouseListener{
    //variables:
    private static Color BLUE = new Color(1,102,255,255);
    private static Color WHITE = new Color(207,226,250, 255);
    private static Color WATERPOOL = new Color(0,255,216);
    private int xPos;
    private int yPos;
    private boolean white;
    private boolean isWaterPool = false;
    private JLabel piece;
    //private JLabel waterPool;
    private BoardPanel board;
    
    //constructors:
    public SquarePanel(boolean white, int x, int y, BoardPanel b){
        super();
        this.white = white;
        if(white)
            this.setBackground(WHITE);
        else
            this.setBackground(BLUE);
        this.addMouseListener(this);
        setXPos(x);
        setYPos(y);
        this.board = b;
        piece = new JLabel();  
    }
    
    /*
    public void setWaterPool(ImageIcon ic){
        waterPool = new JLabel();
        waterPool.setIcon(ic);
        add(waterPool);
    }
    */
    
    public void setWaterPool(){
        isWaterPool = true;
        this.setBackground(WATERPOOL);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
        this.setBorder(border);
    }
    
    //methods:
    public int getXPos(){
        return this.xPos;
    }
    
    public int getYPos(){
        return this.yPos;
    }
    
    public void setXPos(int x){
        this.xPos = x;
    }
    
    public void setYPos(int y){
        this.yPos = y;
    }
    
    public void placePiece(ImageIcon ic){
        piece.setIcon(ic);
        add(piece);
        repaint();
    }
    
    public void removePiece(){
       try{
           piece.setIcon(null);
           remove(piece);
           repaint();
       }
       catch (Exception ex){}
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(Color.BLACK);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(isWaterPool)
            this.setBackground(WATERPOOL);
        else if(white)
            this.setBackground(WHITE);
        else
            this.setBackground(BLUE);  
        board.getClick(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}      

    
}

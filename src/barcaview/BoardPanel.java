/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barcaview;

import barcacontroller.GameController;
import barcamodel.BarcaBoard;
import barcamodel.Move;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 *
 * @author User
 */
public class BoardPanel extends JPanel{
    //variables:
    public static final int GBOARDLEN = 10;
    private SquarePanel[][] boardSquares;
    private boolean click = false;
    private GameController controller;
   
    
    //images:
    private ImageIcon whiteWaterTile = new ImageIcon("images/white_water.png");
    private ImageIcon blueWaterTile = new ImageIcon("images/blue_water.png");
    
    //constructors:
    public BoardPanel(GameController c){
        this.setLayout(new GridLayout(GBOARDLEN,GBOARDLEN));
        this.boardSquares = new SquarePanel[GBOARDLEN][GBOARDLEN];
        this.controller = c;
        initViewBoard();
    }
    
    public SquarePanel getSquarePanel(int x, int y){return boardSquares[x][y];}
    
    private void initViewBoard(){
        for (int ii = 0; ii < GBOARDLEN; ii++) {
            for (int jj = 0; jj < GBOARDLEN; jj++) {
                //b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                //ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                //b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    boardSquares[ii][jj] = new SquarePanel(true, ii, jj, this);
                } else {                   
                    boardSquares[ii][jj] = new SquarePanel(false, ii, jj, this);
                }
            }
        }
        
        for (int ii = 0; ii < GBOARDLEN; ii++) {
            for (int jj = 0; jj < GBOARDLEN; jj++) {
                this.add(boardSquares[ii][jj]);
            }
        }
        
                
        //water_pools
        /*
        this.boardSquares[3][3].setWaterPool(this.whiteWaterTile);
        this.boardSquares[6][6].setWaterPool(this.whiteWaterTile);
        this.boardSquares[3][6].setWaterPool(this.blueWaterTile);
        this.boardSquares[6][3].setWaterPool(this.blueWaterTile);
        */
        
        
        this.boardSquares[3][3].setWaterPool();
        this.boardSquares[6][6].setWaterPool();
        this.boardSquares[3][6].setWaterPool();
        this.boardSquares[6][3].setWaterPool();
    }


    public void getClick(SquarePanel s){
       controller.move(s);
    }
    
    /*
    //----------------------------------------------------------
    //needs improvment!!!!!!!!
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        for (int ii = 0; ii < GBOARDLEN; ii++) {
            for (int jj = 0; jj < GBOARDLEN; jj++) {
                if (b == this.boardSquares[ii][jj]){
                    if (click)
                        setMoveTarget(ii,jj);
                    else
                        setMoveSrc(ii,jj);
                    System.out.print(ii + " " + jj + "\n");
                }
            }
        }
        this.click = !this.click;
    }  
    //-----------------------------------------------------------
    
    public void updateViewBoard(BarcaBoard b){
        for (int ii = 0; ii < GBOARDLEN; ii++) {
            for (int jj = 0; jj < GBOARDLEN; jj++) {
                
            }
        }
    }
*/
    
    
}

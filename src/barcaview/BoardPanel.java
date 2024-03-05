package barcaview;

import barcacontroller.GameController;
import barcamodel.BarcaBoard;
import barcamodel.Move;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class BoardPanel extends JPanel {
    public static final int GBOARDLEN = 10;
    private final SquarePanel[][] boardSquares;
    private final GameController controller;

    public BoardPanel(GameController c) {
        this.setLayout(new GridLayout(GBOARDLEN, GBOARDLEN));
        this.boardSquares = new SquarePanel[GBOARDLEN][GBOARDLEN];
        this.controller = c;
        initViewBoard();
    }

    public SquarePanel getSquarePanel(int x, int y) {
        return this.boardSquares[x][y];
    }

    private void initViewBoard() {
        for (int x = 0; x < GBOARDLEN; x++) {
            for (int y = 0; y < GBOARDLEN; y++) {
                if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0)) {
                    boardSquares[x][y] = new SquarePanel(true, x, y, this);
                } else {
                    boardSquares[x][y] = new SquarePanel(false, x, y, this);
                }
            }
        }

        for (int ii = 0; ii < GBOARDLEN; ii++) {
            for (int jj = 0; jj < GBOARDLEN; jj++) {
                this.add(boardSquares[ii][jj]);
            }
        }

        this.boardSquares[3][3].setWaterPool();
        this.boardSquares[6][6].setWaterPool();
        this.boardSquares[3][6].setWaterPool();
        this.boardSquares[6][3].setWaterPool();
    }


    public void getClick(SquarePanel s) {
        controller.move(s);
    }
}

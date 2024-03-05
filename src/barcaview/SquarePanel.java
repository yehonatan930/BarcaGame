package barcaview;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;

public class SquarePanel extends JPanel implements MouseListener {
    //variables:
    private static final Color BLUE = new Color(1, 102, 255, 255);
    private static final Color WHITE = new Color(207, 226, 250, 255);
    private static final Color WATERPOOL = new Color(0, 255, 216);
    private int xPos;
    private int yPos;
    private final boolean isWhite;
    private boolean isWaterPool = false;
    private final JLabel piece;
    private final BoardPanel board;

    public SquarePanel(boolean isWhite, int x, int y, BoardPanel boardPanel) {
        super();
        this.isWhite = isWhite;
        if (isWhite)
            this.setBackground(WHITE);
        else
            this.setBackground(BLUE);
        this.addMouseListener(this);
        this.setXPos(x);
        this.setYPos(y);
        this.board = boardPanel;
        this.piece = new JLabel();
    }

    public void setWaterPool() {
        isWaterPool = true;
        this.setBackground(WATERPOOL);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
        this.setBorder(border);
    }

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public void setXPos(int x) {
        this.xPos = x;
    }

    public void setYPos(int y) {
        this.yPos = y;
    }

    public void placePiece(ImageIcon ic) {
        piece.setIcon(ic);
        add(piece);
        repaint();
    }

    public void removePiece() {
        try {
            piece.setIcon(null);
            remove(piece);
            repaint();
        } catch (Exception ignored) {
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(Color.BLACK);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isWaterPool)
            this.setBackground(WATERPOOL);
        else if (isWhite)
            this.setBackground(WHITE);
        else
            this.setBackground(BLUE);
        board.getClick(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


}

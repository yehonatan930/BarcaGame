package barcaview;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*;

/**
 * @author User
 */
public class GameFrame extends JFrame {
    static final int LENGTH = 750;
    static final int HEIGHT = 800;

    public GameFrame() {
        setSize(LENGTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Barca Game");
        this.setResizable(false);
    }

    public void win(String str) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GAME OVER");
        frame.setSize(200, 200);
        frame.setLocation(this.getLocationOnScreen());
        JLabel label = new JLabel();
        label.setText(str);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        frame.add(label);
        frame.setVisible(true);
    }
}

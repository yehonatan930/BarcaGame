/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barcaview;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author User
 */
public class GameFrame extends JFrame {
    //variables:
    static final int LENGTH = 750;
    static final int HEIGHT = 800;
    private JLabel gameTitle;
    private BoardPanel board;
    //constructors:
    public GameFrame(){
        //screen size setting
        setSize(LENGTH,HEIGHT);
        //close op
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //title setting
        this.setTitle("Barca Game");
        //resize?
        this.setResizable(false);        
    }
    
    public void win(String str){
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

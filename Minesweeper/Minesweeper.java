import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
/**
 * Write a description of class Minesweeper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Minesweeper {
    int tileSize = 70;
    int pocetRiadkov = 8;
    int pocetStlpcov = pocetRiadkov;
    int sirkaPlatna = pocetStlpcov * tileSize;
    int vyskaPlatna = pocetRiadkov * tileSize;
    
    JFrame  frame =  new JFrame("Minesweeper");
    JLabel textLable = new JLabel();
    JPanel textPanel = new JPanel();
    
    Minesweeper(){
        frame.setVisible(true);
        frame.setSize(sirkaPlatna, vyskaPlatna);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        textLable.setFont(new Font("Ariel", Font.BOLD, 25));
        textLable.setHorizontalAlignment(JLabel.CENTER);
        textLable.setText("Minesweeper");
        textLable.setOpaque(true);
    }
}

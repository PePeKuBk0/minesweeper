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
    private class MineTile extends JButton{
        int r;
        int s;
        
        public MineTile(int r, int s){
            this.r = r;
            this.s = s;
        }
    }
    
    int tileSize = 70;
    int pocetRiadkov = 8;
    int pocetStlpcov = pocetRiadkov;
    int sirkaPlatna = pocetStlpcov * tileSize;
    int vyskaPlatna = pocetRiadkov * tileSize;
    
    JFrame  frame =  new JFrame("Minesweeper");
    JLabel textLable = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    MineTile[][] board = new MineTile [pocetRiadkov][pocetStlpcov];
    ArrayList<MineTile> mineList;
    Minesweeper(){
        // frame.setVisible(true);
        frame.setSize(sirkaPlatna, vyskaPlatna);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        textLable.setFont(new Font("Ariel", Font.BOLD, 25));
        textLable.setHorizontalAlignment(JLabel.CENTER);
        textLable.setText("Minesweeper");
        textLable.setOpaque(true);
        
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLable);
        frame.add(textPanel, BorderLayout.NORTH);
        
        boardPanel.setLayout(new GridLayout(pocetRiadkov, pocetStlpcov));
        // boardPanel.setBackground(Color.gray);
        frame.add(boardPanel);
        for(int r = 0; r  < pocetRiadkov; r++){
            for(int s = 0; s <pocetStlpcov; s++){
                MineTile tile = new MineTile(r,s);
                board[r][s] = tile;
                
                tile.setFocusable(false);
                tile.setMargin(new Insets(0,0,0,0));
                tile.setFont(new Font("Ariel Unicode MS", Font.PLAIN, 45));
                // tile.setText("💣");
                tile.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent e){
                    MineTile tile = (MineTile) e.getSource();
                    //left click
                    if(e.getButton() == MouseEvent.BUTTON1){
                        if(tile.getText() == ""){
                            if(mineList.contains(tile)){
                                revealMines();
                            }
                        }
                    }
                }
                });
                
                boardPanel.add(tile);
            }
        }
        frame.setVisible(true);
        setMines();
    }
    void setMines(){
        mineList = new ArrayList<MineTile>();
        mineList.add(board[2][2]);
        mineList.add(board[2][3]);
        mineList.add(board[5][6]);
        mineList.add(board[3][4]);
        mineList.add(board[1][1]);
    }
    void revealMines(){
        for(int i = 0; i < mineList.size(); i++){
            MineTile tile = mineList.get(i);
            tile.setText("💣");
        }
    }
}

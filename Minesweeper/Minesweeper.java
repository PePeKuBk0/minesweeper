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
    
    int mineCount = 10;
    MineTile[][] board = new MineTile [pocetRiadkov][pocetStlpcov];
    ArrayList<MineTile> mineList;
    Random random = new Random();
    
    int tilesClicked = 0;
    boolean gameOver = false;
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
                    if(gameOver){
                        return;
                    }
                    MineTile tile = (MineTile) e.getSource();
                    //left click
                    if(e.getButton() == MouseEvent.BUTTON1){
                        if(tile.getText() == ""){
                            if(mineList.contains(tile)){
                                revealMines();
                            }
                            else{
                                checkMine(tile.r, tile.s);
                            }
                        }
                        //right click
                    } else if(e.getButton() == MouseEvent.BUTTON3){
                        if(tile.getText() == "" && tile.isEnabled()){
                            tile.setText("🚩");
                        }else if(tile.getText() == "🚩"){
                            tile.setText("");
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
        int mineLeft = mineCount;
        while(mineLeft > 0){
            int r = random.nextInt(pocetRiadkov);
            int s = random.nextInt(pocetStlpcov);
            
            MineTile tile = board[r][s];
            if(!mineList.contains(tile)){
                mineList.add(tile);
                mineLeft -=1;
            }
        }
    }
    void revealMines(){
        for(int i = 0; i < mineList.size(); i++){
            MineTile tile = mineList.get(i);
            tile.setText("💣");
        }
        gameOver = true;
        textLable.setText("Game Over!");
    }
    void checkMine(int r, int s){
        if(r < 0 || r >= pocetRiadkov || s < 0 || s >= pocetStlpcov){
            return;
        } 
        MineTile tile = board[r][s];
        if(!tile.isEnabled()){
            return;
        }
        tile.setEnabled(false);
        tilesClicked += 1;
        int minesFound = 0;
        
        minesFound += countMines(r-1, s-1); //top left
        minesFound += countMines(r-1, s); //top
        minesFound += countMines(r-1, s+1); //top right
        minesFound += countMines(r, s-1); //left
        minesFound += countMines(r, s+1); //right
        minesFound += countMines(r+1, s-1); //bottom left
        minesFound += countMines(r+1, s); // bottom 
        minesFound += countMines(r+1, s+1); //bottom right
        
        if(minesFound > 0){
            tile.setText(Integer.toString(minesFound));
        }else{
            tile.setText("");
            checkMine(r-1, s-1);
            checkMine(r-1, s);
            checkMine(r-1, s+1);
            checkMine(r, s-1);
            checkMine(r, s+1);
            checkMine(r+1, s-1);
            checkMine(r+1, s);
            checkMine(r+1, s+1);
            
        }
        if(tilesClicked == pocetStlpcov * pocetRiadkov - mineList.size()){
            gameOver = true;
            textLable.setText("U WON!");
        }
    }
    int countMines(int r, int s){
        if(r < 0 || r >= pocetRiadkov || s < 0 || s >= pocetStlpcov){
            return 0;
        } 
        if(mineList.contains(board[r][s])){
            return 1;
        }
        return 0;
    }
}
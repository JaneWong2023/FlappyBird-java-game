//This is the file""FlappyBird.java"" where the game is created. 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class FlappyBird extends JPanel {
    int boardwidth = 360;
    int boardheight = 640;

    //images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //Bird
    // int birdX=boardwidth/8;
    // int birdY=boardheight/2;
    

    FlappyBird() {
        setPreferredSize(new Dimension(boardwidth, boardheight));
        // setBackground(Color.blue);

        //load images
        backgroundImg = new ImageIcon(getClass().getResource("./images/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./images/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./images/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./images/bottompipe.png")).getImage();

    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }   

    public void draw(Graphics g){
        //background
        g.drawImage(backgroundImg,0,0,boardwidth,boardheight,null);
    }
}

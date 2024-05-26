//This is the file""FlappyBird.java"" where the game is created. 
import java.awt.*; // 导入AWT中的所有类，用于创建GUI和绘制图形
import java.awt.event.*; // 导入AWT事件处理包，用于处理事件（如按键、鼠标点击）
import java.util.ArrayList; // 导入ArrayList类，用于创建动态数组
import java.util.Random; // 导入Random类，用于生成随机数
import javax.swing.*; // 导入Swing中的所有类，用于创建更现代化的GUI


public class FlappyBird extends JPanel implements ActionListener,KeyListener{// 继承JPanel类，自定义一个游戏面板
    int boardwidth = 360;
    int boardheight = 640;

    //images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //bird class
    int birdX = boardwidth/8;
    int birdY = boardheight/2;
    int birdWidth =34;
    int birdHeight =24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird (Image img){
            this.img =img;
        }
    }
    
    //game logic
    Bird bird;
    int velocityY = 0; //向上的一个速度，每秒往上飞x个像素；
    int gravity = 1;//模拟重力；

    Timer gameLoop;
    

    FlappyBird() {
        setPreferredSize(new Dimension(boardwidth, boardheight));
        // setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);

        //load images
        backgroundImg = new ImageIcon(getClass().getResource("./images/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./images/flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./images/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./images/bottompipe.png")).getImage();
        //bird
        bird = new Bird (birdImg);

        //game timer
        gameLoop = new Timer(1000/60,this);//60 times a second;
        gameLoop.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }   

    public void draw(Graphics g){
        System.out.println("draw");
        //background
        g.drawImage(backgroundImg,0,0,boardwidth,boardheight,null);

        //bird
        g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);
    }

public void move() {
    //bird
    velocityY += gravity;//速度受重力影响
    bird.y += velocityY ;//小鸟位移
    bird.y = Math.max(bird.y,0);//不要飞出屏幕
}

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
         velocityY = -9;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

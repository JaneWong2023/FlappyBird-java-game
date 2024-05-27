//This is the file""FlappyBird.java"" where the game is created. 
import java.awt.*; // 导入AWT中的所有类，用于创建GUI和绘制图形
import java.awt.event.*; // 导入AWT事件处理包，用于处理事件（如按键、鼠标点击）
import java.nio.channels.Pipe;
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
    
    //pipe
    int pipeX = boardwidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight= 512;

    class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width =pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;


        Pipe(Image img){
            this.img = img;
        }
    }


    //game logic
    Bird bird;
    int velocityX = -4;//管子向左移（simulate the bird moving right）；
    int velocityY = 0; //向上的一个速度，每秒往上飞x个像素；
    int gravity = 1;//模拟重力；

    ArrayList<Pipe> pipes;
    Random random = new Random();
    Timer gameLoop;
    Timer placePipesTimer;

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
        pipes = new ArrayList<Pipe>();
        //place pipes timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                placePipes();
            }
        });
        placePipesTimer.start();

        //game timer
        gameLoop = new Timer(1000/60,this);//60 times a second;
        gameLoop.start();
    }
        
    public void placePipes(){
        //(random是0到1)-->(0 - 128 -(0~1)*256)-->管子往上移1/4到3/4之间
        int randomPipeY = (int)(pipeY - pipeHeight/4-Math.random()*(pipeHeight/2));
        int openingSpace =boardheight/4;
        
        Pipe topPipe =new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);
        
        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y =topPipe.y +pipeHeight +openingSpace;
        pipes.add(bottomPipe);
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
        
        //pipes
        for (int i = 0;i< pipes.size();i++){
            Pipe pipe = pipes. get(i);
            g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
        }
    }

public void move() {
    //bird
    velocityY += gravity;//速度受重力影响
    bird.y += velocityY ;//小鸟位移
    bird.y = Math.max(bird.y,0);//不要飞出屏幕

    //pipes
    for (int i =0;i < pipes.size();i++){
        Pipe pipe = pipes.get(i);
        pipe.x += velocityX;
    }
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

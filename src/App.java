//This is the file""App.java"" where the main method is written. This is the file where the game is run. 
import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
     int boardwidth = 360;
     int boardheight =640;
     
     JFrame frame = new JFrame("Flappy Bird");
    //  frame.setVisible(true);//to make the frame visible
     frame.setSize(boardwidth,boardheight);//set the size of the frame
     frame.setLocationRelativeTo(null);//frame appear in the center of the screen
     frame.setResizable(false);//frame not resizable
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close when the close button is clicked

    FlappyBird flappyBird = new FlappyBird();//create an object of the game
    frame.add(flappyBird);//add the game to the frame
    frame.pack();//pack the frame
    frame.setVisible(true);//to make the frame visible

    }
}

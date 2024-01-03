import java.util.*;
import java.awt.Color;
import javax.swing.*;

public class SquashBallGame
{
    public static void main(String[] args)
    {
        new SquashBallGame();
    }
    
    public SquashBallGame()
    {
    	Random rand = new Random();
    	
    	GeometricObject[] g = new GeometricObject[2];
    	
    	// Squashball points -----------------------
    	Point[] squashBallList = new Point[8];
		
    	squashBallList[0] = new Point(100, 100);
    	squashBallList[1] = new Point(110, 105);
    	squashBallList[2] = new Point(115, 115);
    	squashBallList[3] = new Point(110, 125);
    	squashBallList[4] = new Point(100, 130);
    	squashBallList[5] = new Point(90, 125);
    	squashBallList[6] = new Point(85, 115);
    	squashBallList[7] = new Point(90, 105);
        
    	
        Polygon squashBall = new Polygon(squashBallList);
        // Randomly translate the initial start of the polygon for
        // a different start of the squash ball for every game.
        squashBall.translate(new Vector(rand.nextInt(Globals.WIDTH-150), 0));
        squashBall.setInteriorColor(Color.CYAN);
        g[0] = squashBall;
        
        // --------------------------------------------
        
        // Player represented as a rectangle ----------
        Point begin = new Point(Globals.WIDTH-200, Globals.HEIGHT-10);
        Point end = new Point(Globals.WIDTH-300, Globals.HEIGHT);
        
        Rectangle player = new Rectangle(begin, end);
        player.setInteriorColor(Color.red);
        g[1] = player;
        // --------------------------------------------
        
        //graphing
        FrameDisplay frame = new FrameDisplay(squashBall, player);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // This while loop executes the game until it ends
        // when it touches the bottom part of the game.
        while(!frame.getGameEnd()) {
        	frame.repaint();
        	frame.updateCurrentState(); // This is used to update the state of the game.
        }
        
        // When the game is over, this throws a message dialog printing the score.
        JOptionPane.showMessageDialog(null, "Final score was: " + frame.getCounter(), "Game Over!", JOptionPane.INFORMATION_MESSAGE);
    }
}

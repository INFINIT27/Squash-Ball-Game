import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphDisplay extends JPanel implements KeyListener {
	
	private SquashBall ball;
	private Polygon squashBall;
	private Rectangle player;
	private boolean finish = false;
	private int counter = 0;
	
	/**
	 * This constructor requires the values for the squash ball and the player.
	 * @param frameWidth - Width of the panel.
	 * @param frameHeight - Height of the panel.
	 * @param squashBall - Squash ball implemented through a Polygon object.
	 * @param player - Player implemented through a Rectangle object.
	 */
	public GraphDisplay(int frameWidth, int frameHeight, Polygon squashBall, Rectangle player)
    {
        this.squashBall = squashBall;
        this.player = player;
        
        // The ball is passed to a thread which starts executing.
        ball = new SquashBall(squashBall);
        Thread thread = new Thread(ball);
        thread.start();
        
        Dimension d = new Dimension(frameWidth, frameHeight);
        setPreferredSize(d); //sets the preferred size of this panel
		setFocusable(true);
        addKeyListener(this);
    }
	
	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * This method updates the position of the player in the game.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int size = 50; // This sets the speed with which the player can move.
		Vector v;
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			// Sets the limit that the player can move to the left.
			if(player.smallestX() <= 0) {
				// Do nothing
			}
			else if(!finish){
				// If the game has not finished and has not hit the edge
				// this allows the player to move left
				v = new Vector(-size, 0);
				player.translate(v);
			}
			break;
		case KeyEvent.VK_RIGHT:
			// Sets the limit that the player can move to the right.
			if(player.greatestX() >= Globals.WIDTH) {
				// Do nothing
			}
			else if (!finish){
				// If the game has not finished and has not hit the edge
				// this allows the player to move right
				v = new Vector(size, 0);
				player.translate(v);
			}
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	/**
	 * Draw both the player and the squash ball.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		player.draw(g);
		squashBall.draw(g);
	}
	/**
	 * Update current state.
	 */
	public void updateCurrentState() {
		squashBall = ball.getSquashBall();
		if(ball.isOver()) { // If the ball reports that the game is over, update finish.
			finish = true;
		}
		else if(player.intersects(squashBall)) { // If there is an intersection 
			if(ball.getSpeed() > 1) {
				// Every time the player catches the ball,
				// the ball gets faster to make the game harder.
				ball.setSpeed(ball.getSpeed()-1); 
			}
			// This vector updates the direction of the ball after the intersection
			// by moving it in a way that it is predictable.
			Vector v = new Vector(ball.getDirection().getX(), -ball.getDirection().getY());
			ball.setDirection(v);
			Globals.delay(20); // We delay the calculation of the score to ensure that
							   // the ball is not inside the player frame.
			counter++;
		}
	}
	
	/**
	 * Gets the current state of the running game.
	 * @return
	 */
	public boolean getFinish() {
		return finish;
	}
	
	/**
	 * Gets the number of times the player pushed the squash ball away.
	 * @return
	 */
	public int getCounter() {
		return counter;
	}
}
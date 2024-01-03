import java.awt.Graphics;
import java.util.*;

public class SquashBall implements Runnable
{
    private Polygon squashBall; //squash ball represented as a regular polygon
    private boolean playing = true; //represents whether the game is still being played
                             //or has stopped
    private int speed = 10;
    private Vector direction; //array of directions the ball can move along
    Random rand = new Random();
    /**
     * Parameterized constructor.
     */
    public SquashBall(Polygon b)
    {
    	squashBall = b;
    	direction = startVector(); // We set a random direction to make every game
    							   // a little more different.
    }
    
    /**
     * Parametrised constructor if you want to set a different initial vector.
     */
    public SquashBall(Polygon b, Vector v)
    {
    	squashBall = b;
    	direction = v;
    }

    /**
     * Change current vector direction. The change is based on the change of impulse
     * of the squash ball. This offers a level of predictability to the player and 
     * how the ball will interact with the environment.
     */
    public void changeDirection()
    {
    	if(squashBall.smallestX() <= 0 || squashBall.greatestX() >= Globals.WIDTH) {
    		direction = new Vector(-direction.getX(), direction.getY());
    	}
    	else if(squashBall.smallestY() <= 0) {
    		direction = new Vector(direction.getX(), -direction.getY());
    	}
    	else if(squashBall.greatestY() >= Globals.HEIGHT) {
    		stopGame(); // If the ball hits the bottom part of the panel, end the game.
    	}
    	else {
    		// Nothing going on
    	}
    }

    @Override
    /**
     * Life loop of this ball.
     */
    public void run()
    {
        while (playing)
        {
            Globals.delay(speed);
            squashBall.translate(direction);
            changeDirection();
        }
    }

    /**
     * Squash game stops.
     */
    public void stopGame()
    {
        playing = false;
    }

    /**
     * Draws the squash ball.
     */
    public void draw(Graphics g)
    {
        squashBall.draw(g);
    }
    
    /*
     * Randomly picks a start vector.
     */
    public Vector startVector() {
    	switch(rand.nextInt(4)) {
    		case 0:
    			return new Vector(1, 1);
    		case 1:
    			return new Vector(1, -1);
    		case 2:
    			return new Vector(-1, 1);
    		case 3:
    			return new Vector(-1, -1);
    		default:
    			return new Vector(1, 1);
    	}
    }
    
    /**
     * Returns the current position of the squash ball.
     * @return
     */
    public Polygon getSquashBall() {
    	return squashBall;
    }
    
    /**
     * Returns true if the game is over.
     * @return
     */
    public boolean isOver() {
    	return !playing;
    }
    
    /**
     * Manually set the direction of the squash ball.
     * @param v
     */
    public void setDirection(Vector v) {
    	direction = v;
    }
    
    /**
     * Returns current direction.
     * @return
     */
    public Vector getDirection() {
    	return direction;
    }
    
    /**
     * Sets the speed of the ball, the smaller the delay the faster the ball moves.
     * @param speed
     */
    public void setSpeed(int speed) {
    	this.speed = speed;
    }
    
    /**
     * Gets the current speed of the ball.
     * @return - (int) speed
     */
    public int getSpeed() {
    	return speed;
    }
}

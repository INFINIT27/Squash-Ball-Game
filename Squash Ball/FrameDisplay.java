import javax.swing.*;

public class FrameDisplay extends JFrame
{
    private int WIDTH = Globals.WIDTH;
    private int HEIGHT = Globals.HEIGHT;
    
    private GraphDisplay panel;
    
    public FrameDisplay(Polygon squashBall, Rectangle player)
    {
        setTitle("Squash Ball Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        panel = new GraphDisplay(WIDTH, HEIGHT, squashBall, player);        
        add(panel);

        pack(); //sizes the frame so that the panel is at, or above,
                //the preferred size
    }
    
    /**
     * Update the current state of the panel.
     */
    public void updateCurrentState() {
    	panel.updateCurrentState();
    }

    /**
     * @return - Returns a boolean value checking if the game ended.
     */
    public boolean getGameEnd() {
    	return panel.getFinish();
    }
    
    /**
     * @return - Gets the counter for how many times the player pushed the ball back.
     */
    public int getCounter() {
    	return panel.getCounter();
    }
}

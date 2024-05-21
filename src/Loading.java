import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.io.*; 

public class Loading extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -215196891130568350L;
	JPanel currentScreen;
	Thread load;
	public Loading() {
		currentScreen = this;
		load = new Thread(new Runnable() {
			@Override
			public void run() {
				Assets.loadImages();
				Entity.loadImages();
				//Assets.loadSounds();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
				SpaceshipWars.navigate(currentScreen, new Start());
			}
		});
		load.start();
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
	    g.fillRect(0, 0, getWidth(), getHeight());
	  //paints the fps number at the top left corner
	    g.setColor(Player.getBarColor(Assets.loaded, Assets.totalImages));
        g.fillRect(X(250), Y(600), X(500 * (double) Assets.loaded/Assets.totalImages), Y(150));
        g.setColor(Color.white);
        g.setFont(new Font("", Font.ROMAN_BASELINE, 10));
        g.drawString("fps: " + Math.round(SpaceshipWars.fps * 10) / 10.0,  X(5), Y(15));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 150));
        g.drawString("Loading Game...", X(150), Y(380));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 50));
        g.drawString(Math.round((double) Assets.loaded * 1000/(Assets.totalImages + 1))/10.0f + "%", X(470), Y(680));
        
	}
	public static int X(double x) {
    	return (int) (SpaceshipWars.width / 1000.0 * x);
    }
    public static float aX(double x) {
    	return (float) (SpaceshipWars.width / 1000.0 * x);
    }
    public static int Y(double y) {
    	return (int) (SpaceshipWars.height / 1000.0 * y);
    }
    public static float aY(double y) {
    	return (float) (SpaceshipWars.height / 1000.0 * y);
    }
}

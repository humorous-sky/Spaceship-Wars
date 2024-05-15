import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Buff {
	private long spawnTime = System.currentTimeMillis();
	private int duration;
	private long diffSpawn = 0;
	private long lastProcess = System.currentTimeMillis();
	private long diffProcess = 0;
	private Class[] refs = {};
	private BufferedImage img;
	public static BufferedImage[] imgs;
	/**
	 * Set the duration parameter and image parameter to the instance variables
	 * @param duration
	 * @param img
	 */
	public Buff(int duration, BufferedImage img) {
		
	}
	/**
	 * Paints the image at the location of the entity
	 * @param g
	 * @param p
	 */
	public void paint(Graphics g, Entity p) {
		//use the static drawImage method
	}
	public void process(Entity p) {
		//nothing here ;) 
	}
	 public static void loadImages() {
   	  System.out.println("Loading Effects...");
   	  imgs = new BufferedImage[]{null};
     }
	 public static void drawImage(double x, double y, double width, double height, float direction, BufferedImage image, Graphics g) {
   	  AffineTransform at = new AffineTransform();
   	  at.translate(x, y);
   	  at.rotate(Math.toRadians(-direction));
   	  at.scale(width/image.getWidth(), height/image.getHeight());
   	  //at.translate(-image.getWidth(null) / 2, -image.getHeight(null) / 2);
   	  Graphics2D g2d = (Graphics2D) g;
   	  g2d.drawImage(image, at, null);
 	}
}

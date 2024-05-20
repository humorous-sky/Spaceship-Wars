import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Freeze extends Buff{

	/**
	 * Set the duration parameter and image parameter to the instance variables
	 * @param duration
	 */
	public Freeze(int duration) {
		super(duration, imgs[0]);
	}
	/**
	 * Sets the entity's currentSpeed variable to 0f
	 * @param p
	 */
	@Override
	public void process(Entity p) {;
		super.process(p); //leave this line as is
		p.currentSpeed = 0f;
	}
}

import java.awt.Graphics;

public class MultiSniper extends Entity {
	public static final float SPEED = 0.3f;
	public static final int DMG = 100;
	public static final int HP = 65;
	public static final int AMMOS = 2;
	public static final int FIRERATE = 6000;
	public static final String DESC = "2 hits will surely take you this time. ";
	public static final int WIDTH = 110;
	public static final int HEIGHT = 88;
	public MultiSniper(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[1][3], 6);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void fire() {
		if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() + rect.getWidth()/3), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() - rect.getWidth()/3), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	lastFire = System.currentTimeMillis();
  	  	}
    }
}
import java.awt.Graphics;

public class SnipeLead extends Entity {
	public static final float SPEED = 0.17f;
	public static final int DMG = 80;
	public static final int HP = 60;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 3800;
	public static final String DESC = "All ships onboard will fire under its command!";
	public static final int WIDTH = 150;
	public static final int HEIGHT = 100;
	public SnipeLead(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[1][4], 8);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void fire() {
  	  	if (System.currentTimeMillis() >= lastFire + fireRate) {
  	  		Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  	  		for (Entity e: Screen.entities) {
  	  			if (!(e instanceof Player) && !(e instanceof Ammos) && !(e instanceof SnipeLead)) {
  	  				e.lastFire = System.currentTimeMillis() - e.fireRate;
  	  			}
  	  		}
  	  		Assets.playSound(Assets.gunFire, dmg);
  	  		lastFire = System.currentTimeMillis();
  	  	}
    }
}
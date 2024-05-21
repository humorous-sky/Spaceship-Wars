import java.awt.Graphics;

public class SplitShooter extends Entity {
	public static final float SPEED = 0.5f;
	public static final int DMG = 20;
	public static final int HP = 45;
	public static final int AMMOS = 8;
	public static final int FIRERATE = 2450;
	public static final String DESC = "Shoots in 8 directions."; 
	public static final int WIDTH = 110;
	public static final int HEIGHT = 66;
	public SplitShooter(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[3][3], 4);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
		
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
	  		for (double theta = 0.0; theta < 2 * Math.PI; theta += Math.PI/4) {
				Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 5 * (float) Math.cos(theta), 5 * (float) Math.sin(theta), dmg, team));
			}
	  		Assets.playSound(Assets.gunFire, dmg * 8);
	  		lastFire = System.currentTimeMillis();
  	  }
    }
}

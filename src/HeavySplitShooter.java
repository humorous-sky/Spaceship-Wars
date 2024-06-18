import java.awt.Graphics;

public class HeavySplitShooter extends Entity {
	public static final float SPEED = 0.35f;
	public static final int DMG = 30;
	public static final int HP = 55;
	public static final int AMMOS = 12;
	public static final int FIRERATE = 2400;
	public static final String DESC = "Shoots in 12 directions."; 
	public static final int WIDTH = 180;
	public static final int HEIGHT = 105;
	public HeavySplitShooter(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, null, 8);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
	  		for (double theta = 0.0; theta < 2 * Math.PI; theta += Math.PI/6) {
				Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 5 * (float) Math.cos(theta), 5 * (float) Math.sin(theta), dmg, team));
			}
	  		Assets.playSound(Assets.gunFire, dmg * 12);
  			lastFire = System.currentTimeMillis();
  	  }
    }
}

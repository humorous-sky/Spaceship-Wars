import java.awt.Graphics;

public class MiniSplitShooter extends Entity {
	public static final float SPEED = 0.88f;
	public static final int DMG = 20;
	public static final int HP = 25;
	public static final int AMMOS = 4;
	public static final int FIRERATE = 2750;
	public static final String DESC = "Shoots in 4 directions."; 
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public MiniSplitShooter(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[3][0], 4);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
	  		for (double theta = 0.0; theta < 2 * Math.PI; theta += Math.PI/2) {
				Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getCenterY(), 5 * (float) Math.cos(theta), 5 * (float) Math.sin(theta), dmg, team));
			}
	  		Assets.playSound(Assets.gunFire, dmg * 4);
  			lastFire = System.currentTimeMillis();
  	  }
    }
}

import java.awt.Graphics;

public class Minigunner extends Entity {
	public static final float SPEED = 0.45f;
	public static final int DMG = 25;
	public static final int HP = 40;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 375;
	public static final String DESC = "Shoots faster and faster from 3 sec/fire until 0.375 sec/fire. "; 
	public static final int WIDTH = 118;
	public static final int HEIGHT = 80;
	private long lastSpeedUp = System.currentTimeMillis();
	//1500, 750, 375
	public Minigunner(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, 3000, false, imgs[3][2], 8);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		Assets.playSound(Assets.gunFire, dmg);
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() >= lastSpeedUp + 3800 && fireRate > 375) {
  		  fireRate /= 2;
		  lastSpeedUp = System.currentTimeMillis();
	  }
    }
}

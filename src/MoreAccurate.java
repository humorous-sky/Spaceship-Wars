import java.awt.Graphics;

public class MoreAccurate extends Entity {
	public static final float SPEED = 0.3f;
	public static final int DMG = 80;
	public static final int HP = 50;
	public static final int AMMOS = 2;
	public static final int FIRERATE = 5000;
	public static final String DESC = "Another sniper that only shoots when it sees you. ";
	public static final int WIDTH = 110;
	public static final int HEIGHT = 80;
	public MoreAccurate(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, null, 10);
	}
	@Override
	public void fire() {
		if (System.currentTimeMillis() >= lastFire + fireRate && Screen.plr.rect.getCenterX() >= x && Screen.plr.rect.getCenterX() <= rect.getMaxX()) {
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() + rect.getWidth()/5), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() - rect.getWidth()/5), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	Assets.playSound(Assets.gunFire, dmg * 2);
  		  	lastFire = System.currentTimeMillis();
  	  	}
    }
}
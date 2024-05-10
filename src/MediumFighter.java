import java.awt.Graphics;

public class MediumFighter extends Entity {
	public static final float SPEED = 0.5f;
	public static final int DMG = 15;
	public static final int HP = 50;
	public static final int AMMOS = 2;
	public static final int FIRERATE = 1130;
	public static final String DESC = "Shoots 2 bullets that converges."; 
	public static final int WIDTH = 110;
	public static final int HEIGHT = 80;
	public MediumFighter(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[0][2], 6);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() - rect.width/10, (int) rect.getMaxY(), 0.15f, 5f, dmg, team));
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/10, (int) rect.getMaxY(), -0.15f, 5f, dmg, team));
  		  lastFire = System.currentTimeMillis();
  	  }
    }
}

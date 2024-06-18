import java.awt.Graphics;

public class Spawner extends Entity {
	public static final float SPEED = 0.26f;
	public static final int DMG = 0;
	public static final int HP = 60;
	public static final int AMMOS = 0;
	public static final int FIRERATE = 6500;
	public static final String DESC = "Slowly spawns an army of fighter ships."; 
	public static final int WIDTH = 180;
	public static final int HEIGHT = 110;
	public Spawner(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, null, 8);
	}

	
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Entity e = new Fighter((int) rect.getCenterX() - Screen.X(30), (int) rect.getCenterY());
  		  e.s = 0;
  		  Screen.entitiesToAdd.add(e);
  		  lastFire = System.currentTimeMillis();
  	  }
    }
}

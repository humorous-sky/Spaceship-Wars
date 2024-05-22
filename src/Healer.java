import java.awt.Graphics;

public class Healer extends Entity {
	public static final float SPEED = 0.15f;
	public static final int DMG = 0;
	public static final int HP = 110;
	public static final int AMMOS = 0;
	public static final int FIRERATE = 3000;
	public static final String DESC = "Every 3 seconds, heals all enemy ships and amplifies enemy bullet damage by 15. ";
	public static final int WIDTH = 170;
	public static final int HEIGHT = 105;
	public Healer(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[2][4], 8);
	}
	@Override
	public void fire() {
  	  	if (System.currentTimeMillis() >= lastFire + fireRate) {
  	  		for (Entity e: Screen.entities) {
  	  			if (!e.team) {
  	  				e.heal(15);
  	  			}
  	  		}
  	  		lastFire = System.currentTimeMillis();
  	  	}
    }
}
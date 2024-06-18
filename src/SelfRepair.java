import java.awt.Graphics;

public class SelfRepair extends Entity {
	public static final float SPEED = 0.3f;
	public static final int DMG = 30;
	public static final int HP = 85;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 1500;
	public static final String DESC = "Heals 5 Hp on every fire. "; 
	public static final int WIDTH = 118;
	public static final int HEIGHT = 68;
	public SelfRepair(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, null, 4);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  Assets.playSound(Assets.gunFire, dmg);
  		  heal(15);
  		  lastFire = System.currentTimeMillis();
  	  }
    }
}

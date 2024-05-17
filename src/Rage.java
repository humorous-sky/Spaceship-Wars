import java.awt.Graphics;

public class Rage extends Entity {
	public static final float SPEED = 0.8f;
	public static final int DMG = 6;
	public static final int HP = 20;
	public static final int AMMOS = 3;
	public static final int FIRERATE = 1150;
	public static final String DESC = "This looks familier..."; 
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public Rage(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[3][1], 5);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
		
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() - rect.width/6, (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/6, (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  Assets.playSound(Assets.newSound("gun.wav"), dmg * 3);
  		  lastFire = System.currentTimeMillis();
  	  }
    }
}

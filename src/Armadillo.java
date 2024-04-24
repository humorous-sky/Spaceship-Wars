import java.awt.Graphics;

public class Armadillo extends Entity {
	public static final float SPEED = 0.3f;
	public static final int DMG = 8;
	public static final int HP = 50;
	public static final int AMMOS = 3;
	public static final int FIRERATE = 800;
	public static final String DESC = "Starts rage shooting after its shell falls off (less than or equal to 10 Hp)."; 
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public Armadillo(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[2][1], 2);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
		
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && maxHp == 10) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() - rect.width/6, (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/6, (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (hp <= 10 && maxHp != 10) {
		  maxHp = 10;
		  img = Assets.misc[1];
		  speed = 1f;
	  }
    }
}

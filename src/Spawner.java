import java.awt.Graphics;

public class Spawner extends Entity {
	public static final float SPEED = 0.26f;
	public static final int DMG = 0;
	public static final int HP = 60;
	public static final int AMMOS = 0;
	public static final int FIRERATE = 6500;
	public static final String DESC = "Slowly spawns an army of fighter ships."; 
	public Spawner(int x, int y) {
		super(x, y);
		img = imgs[0][4];
		this.rect.width = Screen.X(180);
        this.rect.height = Screen.Y(110);
        hp = HP;
        maxHp = hp;
        dmg = DMG;
        fireRate = FIRERATE;
        speed = SPEED;
        s = 8;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
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

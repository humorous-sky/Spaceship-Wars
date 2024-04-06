import java.awt.Graphics;

public class Spawner extends Entity {

	public Spawner(int x, int y) {
		super(x, y);
		img = imgs[0][4];
		this.rect.width = Screen.X(180);
        this.rect.height = Screen.Y(110);
        hp = 60;
        maxHp = hp;
        dmg = 0;
        fireRate = 6500;
        speed = 0.26f;
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

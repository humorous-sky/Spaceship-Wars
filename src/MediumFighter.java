import java.awt.Graphics;

public class MediumFighter extends Entity {

	public MediumFighter(int x, int y) {
		super(x, y);
		img = imgs[0][2];
		this.rect.width = Screen.X(110);
        this.rect.height = Screen.Y(80);
        hp = 50;
        maxHp = hp;
        dmg = 15;
        fireRate = 1500;
        speed = 0.5f;
        s = 6;
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

import java.awt.Graphics;

public class MoreAccurate extends Entity {
	public MoreAccurate(int x, int y) {
		super(x, y);
		img = imgs[1][2];
		this.rect.width = Screen.X(110);
        this.rect.height = Screen.Y(80);
        hp = 30;
        maxHp = hp;
        dmg = 32;
        s = 10;
        speed = 0.23f;
        fireRate = 15000;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void fire() {
		if (System.currentTimeMillis() >= lastFire + fireRate && Screen.plr.rect.getCenterX() >= x && Screen.plr.rect.getCenterX() <= rect.getMaxX()) {
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() + rect.getWidth()/3), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() - rect.getWidth()/3), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	lastFire = System.currentTimeMillis();
  	  	}
    }
}
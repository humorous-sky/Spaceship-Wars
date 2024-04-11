import java.awt.Graphics;

public class MultiSniper extends Entity {
	public MultiSniper(int x, int y) {
		super(x, y);
		img = imgs[1][3];
		this.rect.width = Screen.X(110);
        this.rect.height = Screen.Y(88);
        hp = 50;
        maxHp = hp;
        dmg = 80;
        s = 6;
        speed = 0.17f;
        fireRate = 17000;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void fire() {
		if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() + rect.getWidth()/3), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() - rect.getWidth()/3), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	lastFire = System.currentTimeMillis();
  	  	}
    }
}
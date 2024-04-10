import java.awt.Graphics;

public class SnipeLead extends Entity {
	public SnipeLead(int x, int y) {
		super(x, y);
		img = imgs[1][4];
		this.rect.width = Screen.X(180);
        this.rect.height = Screen.Y(110);
        hp = 60;
        maxHp = hp;
        dmg = 32;
        s = 8;
        speed = 0.17f;
        fireRate = 5000;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void fire() {
  	  	if (System.currentTimeMillis() >= lastFire + fireRate) {
  	  		Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  	  		for (Entity e: Screen.entities) {
  	  			if (!(e instanceof Player) && !(e instanceof Ammos)) {
  	  				e.lastFire = System.currentTimeMillis() - e.fireRate;
  	  			}
  	  		}
  	  		lastFire = System.currentTimeMillis();
  	  	}
    }
}
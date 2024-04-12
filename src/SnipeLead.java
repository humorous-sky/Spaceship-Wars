import java.awt.Graphics;

public class SnipeLead extends Entity {
	public static final float SPEED = 0.17f;
	public static final int DMG = 32;
	public static final int HP = 60;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 3300;
	public static final String DESC = "All ships onboard will fire under its command!";
	public SnipeLead(int x, int y) {
		super(x, y);
		img = imgs[1][4];
		this.rect.width = Screen.X(150);
        this.rect.height = Screen.Y(100);
        hp = HP;
        maxHp = hp;
        dmg = DMG;
        s = 8;
        speed = SPEED;
        fireRate = FIRERATE;
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
  	  			if (!(e instanceof Player) && !(e instanceof Ammos) && !(e instanceof SnipeLead)) {
  	  				e.lastFire = System.currentTimeMillis() - e.fireRate;
  	  			}
  	  		}
  	  		lastFire = System.currentTimeMillis();
  	  	}
    }
}
import java.awt.Graphics;

public class MoreAccurate extends Entity {
	public static final float SPEED = 0.23f;
	public static final int DMG = 32;
	public static final int HP = 30;
	public static final int AMMOS = 2;
	public static final int FIRERATE = 8000;
	public static final String DESC = "Another sniper that only shoots when it sees you. ";
	public MoreAccurate(int x, int y) {
		super(x, y);
		img = imgs[1][2];
		this.rect.width = Screen.X(110);
        this.rect.height = Screen.Y(80);
        hp = HP;
        maxHp = hp;
        dmg = DMG;
        s = 10;
        speed = SPEED;
        fireRate = FIRERATE;
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
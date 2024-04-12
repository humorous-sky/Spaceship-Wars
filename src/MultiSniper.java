import java.awt.Graphics;

public class MultiSniper extends Entity {
	public static final float SPEED = 0.17f;
	public static final int DMG = 80;
	public static final int HP = 50;
	public static final int AMMOS = 2;
	public static final int FIRERATE = 17000;
	public static final String DESC = "2 hits will surely take you this time. ";
	public MultiSniper(int x, int y) {
		super(x, y);
		img = imgs[1][3];
		this.rect.width = Screen.X(110);
        this.rect.height = Screen.Y(88);
        hp = HP;
        maxHp = hp;
        dmg = DMG;
        s = 6;
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
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() + rect.getWidth()/3), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	Screen.entitiesToAdd.add(new Ammos((int) (rect.getCenterX() - rect.getWidth()/3), (int) rect.getMaxY(), 0f, 5f, dmg, team));
  		  	lastFire = System.currentTimeMillis();
  	  	}
    }
}
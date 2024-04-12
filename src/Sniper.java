import java.awt.Graphics;

public class Sniper extends Entity {
	public static final float SPEED = 0.15f;
	public static final int DMG = 128;
	public static final int HP = 15;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 10000;
	public static final String DESC = "1 hit is enough to take you. If not, then 2. ";
	public Sniper(int x, int y) {
		super(x, y);
		img = imgs[1][0];
		this.rect.width = Screen.X(60);
        this.rect.height = Screen.Y(50);
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
	
}
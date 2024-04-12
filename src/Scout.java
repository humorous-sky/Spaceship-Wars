import java.awt.Graphics;

public class Scout extends Entity {
	public static final float SPEED = 1.3f;
	public static final int DMG = 10;
	public static final int HP = 15;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 1500;
	public static final String DESC = "Fast and tiny, but only weak."; 
	public Scout(int x, int y) {
		super(x, y);
		img = imgs[0][1];
		this.rect.width = Screen.X(30);
        this.rect.height = Screen.Y(26);
        hp = HP;
        maxHp = hp;
        dmg = DMG;
        speed = SPEED;
        fireRate = FIRERATE;
        s = 6;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
}

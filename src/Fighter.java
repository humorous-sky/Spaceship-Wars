import java.awt.Graphics;

public class Fighter extends Entity {
	public static final float SPEED = 0.8f;
	public static final int DMG = 30;
	public static final int HP = 30;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 3000;
	public static final String DESC = "A basic fighter ship. "; 
	public Fighter(int x, int y) {
		super(x, y);
		img = imgs[0][0];
		this.rect.width = Screen.X(60);
        this.rect.height = Screen.Y(50);
        hp = HP;
        maxHp = hp;
        dmg = DMG;
        s = 3;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	
}

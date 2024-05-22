import java.awt.Graphics;

public class Sniper extends Entity {
	public static final float SPEED = 0.5f;
	public static final int DMG = 128;
	public static final int HP = 30;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 8000;
	public static final String DESC = "1 hit is enough to take you. If not, then 2. ";
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public Sniper(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[1][0], 3);
	}
	
}
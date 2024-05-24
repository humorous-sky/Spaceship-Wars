import java.awt.Graphics;

public class Fighter extends Entity {
	public static final float SPEED = 0.8f;
	public static final int DMG = 30;
	public static final int HP = 30;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 3000;
	public static final String DESC = "A basic fighter ship. "; 
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public Fighter(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[0][0], 3);
	}
}

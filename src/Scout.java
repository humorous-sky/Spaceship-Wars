import java.awt.Graphics;

public class Scout extends Entity {
	public static final float SPEED = 1.3f;
	public static final int DMG = 10;
	public static final int HP = 15;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 1500;
	public static final String DESC = "Fast and tiny, but only weak."; 
	public static final int WIDTH = 30;
	public static final int HEIGHT = 26;
	public Scout(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[0][1], 6);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
}

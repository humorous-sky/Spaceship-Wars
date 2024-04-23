import java.awt.Graphics;

public class Recycler extends Entity {
	public static final float SPEED = 0.6f;
	public static final int DMG = 15;
	public static final int HP = 50;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 3000;
	public static final String DESC = "Gains full HP every time you take damage."; 
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public Recycler(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[2][0], 3);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	
}

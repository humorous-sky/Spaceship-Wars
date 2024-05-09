import java.awt.Graphics;

public class Accurate extends Entity {
	public static final float SPEED = 0.5f;
	public static final int DMG = 64;
	public static final int HP = 30;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 8000;
	public static final String DESC = "A sniper that only shoots when it sees you. ";
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public Accurate(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[1][1], 3);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void fire() {
  	  if (Screen.plr.rect.getCenterX() >= x && Screen.plr.rect.getCenterX() <= rect.getMaxX()) {
  		  super.fire();
  	  }
    }
}
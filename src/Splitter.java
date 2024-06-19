import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Splitter extends Entity {
	public static final float SPEED = 0.5f;
	public static final int DMG = 24;
	public static final int HP = 24;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 2500;
	public static final String DESC = "Splits into 2 smaller ships when destroyed. Each smaller ship will split into 2 more."; 
	public static final int WIDTH = 110;
	public static final int HEIGHT = 80;
	private int size = 1;
	public Splitter(int x, int y) {
		this(x, y, 1, null);
	}
	public Splitter(int x, int y, int size, BufferedImage img) {
		super(x, y, WIDTH/size, HEIGHT/size, HP/size, DMG/size, SPEED * size, FIRERATE, false, img, 12);
		this.size = size;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintSmall(x, y + rect.height/4f, size + 1, img, g);
		paintSmall((float) rect.getCenterX(), y + rect.height/4f, size + 1, img, g);
	}
	public void paintSmall(float x, float y, int size, BufferedImage img, Graphics g) {
		if (size > 3) {
			return;
		}
		int multi = (int) Math.pow(2, (size - this.size));
		drawImage(x, y, rect.width/multi, rect.height/multi, 0f, img, g);
		paintSmall(x, y + rect.height/(4 * multi), size + 1, img, g);
		paintSmall(x + rect.width/(2 * multi), y + rect.height/(4 * multi), size + 1, img, g);
	}
	@Override
	public void onOof() {
		Screen.plr.a.increment(1, size == 1 ? 1 : 0);
   	  	Screen.entitiesToRemove.add(this);
		if (size < 3) {
			Screen.entitiesToAdd.add(new Splitter(rect.x, rect.y, size + 1, img));
			Screen.entitiesToAdd.add(new Splitter((int) rect.getCenterX(), rect.y, size + 1, img));
		}
	}
}

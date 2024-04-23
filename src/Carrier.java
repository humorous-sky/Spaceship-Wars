import java.awt.Graphics;

public class Carrier extends Entity {
	public static final float SPEED = 0.38f;
	public static final int DMG = 0;
	public static final int HP = 65;
	public static final int AMMOS = 0;
	public static final int FIRERATE = 3000;
	public static final String DESC = "Spawns 2 fighters and 1 scout upon destruction."; 
	public static final int WIDTH = 110;
	public static final int HEIGHT = 80;
	public Carrier(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[0][3], 4);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
		
	}
	@Override
	public void fire() {
  	  
    }
	@Override
	public void onOof() {
		Entity e = new Fighter((int) rect.getCenterX() - rect.width/6, (int) rect.getCenterY() - rect.height/6);
		e.s = 0;
		Screen.entitiesToAdd.add(e);
		e = new Fighter((int) rect.getCenterX() + rect.width/6, (int) rect.getCenterY() - rect.height/6);
		e.s = 0;
		Screen.entitiesToAdd.add(e);
		e = new Scout((int) rect.getCenterX(), (int) rect.getCenterY() + rect.height/6);
		e.s = 0;
		Screen.entitiesToAdd.add(e);
		super.onOof();
	}
}

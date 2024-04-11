import java.awt.Graphics;

public class Carrier extends Entity {

	public Carrier(int x, int y) {
		super(x, y);
		img = imgs[0][3];
		this.rect.width = Screen.X(110);
        this.rect.height = Screen.Y(80);
        hp = 65;
        maxHp = hp;
        dmg = 0;
        speed = 0.38f;
        s = 4;
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

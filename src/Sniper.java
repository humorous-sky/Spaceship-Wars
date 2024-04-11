import java.awt.Graphics;

public class Sniper extends Entity {
	public Sniper(int x, int y) {
		super(x, y);
		img = imgs[1][0];
		this.rect.width = Screen.X(60);
        this.rect.height = Screen.Y(50);
        hp = 15;
        maxHp = hp;
        dmg = 128;
        s = 6;
        speed = 0.15f;
        fireRate = 10000;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	
}
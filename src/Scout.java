import java.awt.Graphics;

public class Scout extends Entity {

	public Scout(int x, int y) {
		super(x, y);
		img = imgs[0][1];
		this.rect.width = Screen.X(30);
        this.rect.height = Screen.Y(26);
        hp = 15;
        maxHp = hp;
        dmg = 10;
        speed = 1.3f;
        fireRate = 1500;
        s = 6;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
}

import java.awt.Graphics;

public class Fighter extends Entity {
	public Fighter(int x, int y) {
		super(x, y);
		img = imgs[0][0];
		this.rect.width = Screen.X(60);
        this.rect.height = Screen.Y(50);
        hp = 30;
        maxHp = hp;
        dmg = 30;
        s = 3;
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	
}

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BossEntity extends Entity {

	private int Y;
	public BossEntity(int x, int y, int width, int height, int hp, int dmg, float speed, int fireRate, boolean team, BufferedImage img, int s) {
		super(x, y, width, height, hp, dmg, speed, fireRate, team, img, s);
		dir = (float) ((Math.random() * speed * 1.6) - (speed * 0.8));
		this.Y = y;
	}
	@Override
	public void move() {
		rect.y = Y;
		y = Y;
		super.move();	
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Player.getBarColor(hp, maxHp));
		g.fillRect(rect.x, (int) rect.getMaxY() + 10,(int) (rect.width * ((double) hp/maxHp)), 5);
	}
}

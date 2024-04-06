import java.awt.Color;
import java.awt.Graphics;

public class Stars {
	Color color;
	int x;
	int y;
	private long lastScroll = System.currentTimeMillis();
	public Stars (int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, Screen.X(8), Screen.Y(13));
		if (System.currentTimeMillis() >= lastScroll + 26) {
			y += Screen.Y(16);
			if (y > Screen.Y(1000)) {
				Screen.starsToRemove.add(this);
			}
			lastScroll = System.currentTimeMillis();
		}
	}
}

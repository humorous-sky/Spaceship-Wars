import java.awt.Point;

public class BlackHole extends Ability {
	private final static int OPTION = 3;
	private final static int VALUE = 15000;
	private boolean confirm = false;
	public BlackHole() {
		super(OPTION, VALUE);
	}
	@Override
	public void activate(Player p) {
		//if (confirm) {
			if (Math.abs(p.xA) + Math.abs(p.yA) == 0) {
				if (Math.abs(p.lastX) + Math.abs(p.lastY) == 2) {
					p.x += p.lastX * 260f * Screen.aX(0.3978);
					p.y += p.lastY * 260f * Screen.aY(0.7071);
				} else {
					p.x += p.lastX * 260f * Screen.aX(0.5625);
					p.y += p.lastY * 260f * Screen.aY(1.0000);
				}
			} else if (Math.abs(p.xA) + Math.abs(p.yA) == 2) {
				p.x += p.xA * 260f * Screen.aX(0.3978);
				p.y += p.yA * 260f * Screen.aY(0.7071);
			} else {
				p.x += p.xA * 260f * Screen.aX(0.5625);
				p.y += p.yA * 260f * Screen.aY(1.0000);
			}
		//} else {
		//	aim(p);
			//confirm = true;
		//}
	}
	@Override
	public Point aim(Player p) {
		//return new Point(100, 100);
		p.gainShields(20);
		if (Math.abs(p.xA) + Math.abs(p.yA) == 0) {
			if (Math.abs(p.lastX) + Math.abs(p.lastY) == 2) {
				return new Point((int) (p.rect.getCenterX() + p.lastX * 260f * Screen.aX(0.3978)), (int) (p.rect.getCenterY() + p.lastY * 380f * Screen.aY(0.7071)));
			} else {
				return new Point((int) (p.rect.getCenterX() + p.lastX * 260f * Screen.aX(0.5625)), (int) (p.rect.getCenterY() + p.lastY * 380f * Screen.aY(1.0000)));
			}
		} else if (Math.abs(p.xA) + Math.abs(p.yA) == 2) {
			return new Point((int) (p.rect.getCenterX() + p.xA * 260f * Screen.aX(0.3978)), (int) (p.rect.getCenterY() + p.yA * 380f * Screen.aY(0.7071)));
		} else {
			return new Point((int) (p.rect.getCenterX() + p.xA * 260f * Screen.aX(0.5625)), (int) (p.rect.getCenterY() + p.yA * 380f * Screen.aY(1.0000)));
		}
	}
}

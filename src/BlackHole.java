
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
				p.y += -1 * 170f * Screen.aY(1.0000);
			} else if (Math.abs(p.xA) + Math.abs(p.yA) == 2) {
				p.x += p.xA * 170f * Screen.aX(0.3978);
				p.y += p.yA * 170f * Screen.aY(0.7071);
			} else {
				p.x += p.xA * 170f * Screen.aX(0.5625);
				p.y += p.yA * 170f * Screen.aY(1.0000);
			}
		//} else {
		//	aim(p);
			//confirm = true;
		//}
	}
	@Override
	public void aim(Player p) {
		if (Math.abs(p.xA) + Math.abs(p.yA) == 0) {
			Screen.x = (int) p.rect.getCenterX();
			Screen.y = (int) (p.rect.getCenterX() + 1 * 380f * Screen.aY(1.0000));
		} else if (Math.abs(p.xA) + Math.abs(p.yA) == 2) {
			Screen.x = (int) (p.rect.getCenterX() + p.xA * 380f * Screen.aX(0.3978));
			Screen.y = (int) (p.rect.getCenterX() + p.yA * 380f * Screen.aY(0.7071));
		} else {
			Screen.x = (int) (p.rect.getCenterX() + p.xA * 380f * Screen.aX(0.5625));
			Screen.y = (int) (p.rect.getCenterX() + p.yA * 380f * Screen.aY(1.0000));
		}
	}
}

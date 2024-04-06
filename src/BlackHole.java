
public class BlackHole extends Ability {
	private final static int OPTION = 3;
	private final static int VALUE = 15000;
	public BlackHole() {
		super(OPTION, VALUE);
	}
	@Override
	public void activateIfCharged(Player p) {
		if (Math.abs(p.xA) + Math.abs(p.yA) != 0) {
			super.activateIfCharged(p);
		}
	}
	@Override
	public void activate(Player p) {
		if (Math.abs(p.xA) + Math.abs(p.yA) == 2) {
			  p.x += p.xA * 380f * Screen.aX(0.3978);
			  p.y += p.yA * 380f * Screen.aY(0.7071);
		  } else {
			  p.x += p.xA * 380f * Screen.aX(0.5625);
			  p.y += p.yA * 380f * Screen.aY(1.0000);
		  }
	}
}

import java.awt.Point;

public class ShieldAbility extends Ability {
	private final static int OPTION = 2;
	private final static int VALUE = 500;
	private boolean confirm = false;
	public ShieldAbility() {
		super(OPTION, VALUE);
	}
	@Override
	public void activate(Player p) {
		p.gainShields(50);
	}
}

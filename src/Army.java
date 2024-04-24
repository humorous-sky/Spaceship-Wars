
public class Army extends Ability {
	private final static int OPTION = 2;
	private final static int VALUE = 800;
	public Army() {
		super(OPTION, VALUE);
	}
	@Override
	public void activate(Player p) {
		for (int i = -2; i <= 2; i ++) {
			Screen.entitiesToAdd.add(new Support((int) Screen.plr.x + i * Screen.X(50), (int) Screen.plr.y - Screen.Y(50)));
		}
	}
}

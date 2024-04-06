
public class Firework extends Ability {
	private final static int OPTION = 1;
	private final static int VALUE = 15;
	public Firework() {
		super(OPTION, VALUE);
	}
	@Override
	public void activate(Player p) {
		//shoots in a circle
		for (double theta = 0.0; theta < 2 * Math.PI; theta += Math.PI/36) {
			Screen.entitiesToAdd.add(new Ammos((int) p.rect.getCenterX(), (int) p.rect.getCenterY(), 26 * (float) Math.cos(theta), 26 * (float) Math.sin(theta), 5, p.team));
		}
	}
}

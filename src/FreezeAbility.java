
public class FreezeAbility extends Ability {
	private final static int OPTION = 1;
	private final static int VALUE = 25;
	public FreezeAbility() {
		super(OPTION, VALUE);
	}
	@Override
	public void activate(Player p) {
		//traverse the Screen.entities ArrayList and add a freeze buff lasting for a 
		//random of 10000-15000 milliseconds to each non-player entity 
	}
}

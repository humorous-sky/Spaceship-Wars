
public class FreezeAbility extends Ability {
	private final static int OPTION = 1;
	private final static int VALUE = 15;
	public FreezeAbility() {
		super(OPTION, VALUE);
	}
	@Override
	public void activate(Player p) {
		//traverse the Screen.entities ArrayList and add a freeze buff lasting for a 
		//random of 5000-10000 milliseconds to each non-player entity 
		for(Entity a: Screen.entities) {
			if(! (a instanceof Player)) {
				a.buffs.add(new Freeze((int)(Math.random()*5001 + 5000)));
			}
		}
	}
}

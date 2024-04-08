
public class Ability {
	private int option;
	private int value;
	private int current;
	private long lastIncrement = System.currentTimeMillis();
	public static final Class[] refs = {BlackHole.class, Firework.class};
	public static final String[] descriptions = {"Makes you instantly move a distance in your direction.",
			"Shoots a circle of bullets around you."};
	public Ability(int option, int value) {
		this.option = option;
		this.value = value;
	}
	public void increment(int n, int k) {
		if (option == k && option == 3) {
			current += (int) (System.currentTimeMillis() - lastIncrement);
		} else if (option == k){		
			current += n;
		}
		current = current > value ? value : current;
		lastIncrement = System.currentTimeMillis();
	}
	public int getValue() {
		return value;
	}
	public int getCurrent() {
		return current;
	}
	public double getProgress() {
		return current/(double) value;
	}
	public void activateIfCharged(Player p) {
		if (current == value) {
			activate(p);
			current = 0;
		}
	}
	public void activate(Player p) {
		
	}
	public static Ability createAbility(int type) {
		try {
			return (Ability) refs[type].getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 		
	}
}

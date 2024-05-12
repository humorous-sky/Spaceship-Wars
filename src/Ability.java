import java.awt.Color;
import java.awt.Point;

public class Ability {
	private int option;
	private int value;
	private int current;
	public static final Class[] refs = {BlackHole.class, Firework.class, Army.class};
	public static final String[] descriptions = {"Instantly moves you a distance in your direction. Recharges every 15 seconds. ",
			"Shoots a circle of bullets around you. Recharges every 15 ships destroyed. ", "Instantly spawns 5 robot ships that fight for you. Recharges every 800 damage dealt. "};
	public Ability(int option, int value) {
		this.option = option;
		this.value = value;
	}
	public void increment(int n, int k) {
		if (option != k) {
			return;
		}
		current += n; 	
		current = current > value ? value : current;
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
	public Point aim(Player p) {
		return null;	
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

import java.awt.Graphics;

public class Teleporter extends Entity {
	public static final float SPEED = 0.88f;
	public static final int DMG = 18;
	public static final int HP = 30;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 3600;
	public static final String DESC = "Teleports itself once hit after reaching 20% hp."; 
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public Teleporter(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, null, 3);
	}
	public void takeDamage(int amount) {
		super.takeDamage(amount);
		if (hp <= 6 && hp > 0 && currentSpeed > 0f) {
			x = Screen.X(Math.random() * 541 + 200);
		}
	}
}

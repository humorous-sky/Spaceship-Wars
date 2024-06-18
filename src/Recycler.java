import java.awt.Graphics;

public class Recycler extends Entity {
	public static final float SPEED = 0.6f;
	public static final int DMG = 15;
	public static final int HP = 50;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 3000;
	public static final String DESC = "Regains full Hp every time you take damage."; 
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	private int php;
	public Recycler(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, null, 2);
		try {
			php = Screen.plr.hp;
		} catch (Exception e) {
			
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (Screen.plr.hp < php) {
			heal(HP);
		}
		php = Screen.plr.hp;
	}
	
}

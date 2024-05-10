import java.awt.Graphics;

public class ShieldShip extends Entity {
	public static final float SPEED = 0.28f;
	public static final int DMG = 0;
	public static final int HP = 85;
	public static final int AMMOS = 0;
	public static final int FIRERATE = 10000;
	public static final String DESC = "Generates a 100 Hp shield every 10 seconds"; 
	public static final int WIDTH = 110;
	public static final int HEIGHT = 80;
	private Shield shield;
	public ShieldShip(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, imgs[2][3], 4);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
		if (shield != null) {
			shield.paint(g);
		}
	}
	@Override
	public void move() {
		super.move();
		if (shield != null) {
			shield.move();
			shield.rect.x = rect.x - Screen.X(60);
			shield.rect.y = rect.y - Screen.Y(75);
			shield.x = x - Screen.X(60);
			shield.y = y - Screen.Y(75);
			if (shield.frame >= 2.85) {
				shield = null;
			}
		}
	}
	@Override
	public void drawAnim(Graphics g) {
		super.drawAnim(g);
		if (shield != null) {
			shield.drawAnim(g);
		}
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  if (shield == null) {
  			  shield = new Shield(rect.x - Screen.X(60), rect.y - Screen.Y(75));
  		  } else if (shield.hp > 0) {
  			  shield.heal(100);
  		  }
  		  lastFire = System.currentTimeMillis();
  	  }
   }
	@Override
	public void heal(int amount) {
		super.heal(amount);
		if (shield != null) {
			shield.heal(amount);
		}	
	}
}

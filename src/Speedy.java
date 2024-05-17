
public class Speedy extends Player {
	public static final float SPEED = 8.88f;
	public static final int DMG = 3;
	public static final int HP = 64;
	public static final int AMMOS = 32;
	public static final int FIRERATE = 60;
	public static final int RELOAD = 1730;
	public static final int WIDTH = 38;
	public static final int HEIGHT = 55;
	public Speedy(int x, int y, Ability a) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, AMMOS, RELOAD, a, Assets.ships[1]);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && fire && ammos > 0 && !reloading) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/10 * getBulletDir(), (int) rect.y, 0f, 26f, dmg, team));
  		  ammos--;
  		  Assets.playSound(Assets.gunFire, dmg * 8);
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > lastTurn && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	
}
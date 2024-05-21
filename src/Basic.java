
public class Basic extends Player {
	public static final float SPEED = 6.6f;
	public static final int DMG = 5;
	public static final int HP = 128;
	public static final int AMMOS = 64;
	public static final int FIRERATE = 100;
	public static final int RELOAD = 4700;
	public static final int WIDTH = 50;
	public static final int HEIGHT = 68;
	public Basic(int x, int y, Ability a) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, AMMOS, RELOAD, a, Assets.ships[0]);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && fire && ammos > 0 && !reloading) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/3 * getBulletDir(), (int) rect.y, 0f, 26f, dmg, team));
  		  Assets.playSound(Assets.gunFire, dmg * 8);
  		  ammos--;
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > lastTurn && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	
}


public class MultiDirection extends Player {
	public static final float SPEED = 5.8f;
	public static final int DMG = 5;
	public static final int HP = 144;
	public static final int AMMOS = 56;
	public static final int FIRERATE = 110;
	public static final int RELOAD = 4700;
	public static final int WIDTH = 55;
	public static final int HEIGHT = 74;
	public MultiDirection(int x, int y, Ability a) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, AMMOS, RELOAD, a, Assets.ships[4]);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && fire && ammos > 0 && !reloading) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/3 * getBulletDir(), (int) rect.y, 0f, 26f, dmg, team));
  		  getBulletDir();
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getCenterY(), 26f * getBulletDir(), (float) Math.random() * 10 - 5, dmg, team));
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


public class Tank extends Player {
	public static final float SPEED = 5f;
	public static final int DMG = 8;
	public static final int HP = 160;
	public static final int AMMOS = 80;
	public static final int FIRERATE = 160;
	public static final int RELOAD = 5000;
	public static final int WIDTH = 68;
	public static final int HEIGHT = 88;
	public Tank(int x, int y, Ability a) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, AMMOS, RELOAD, a, Assets.ships[2]);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && fire && ammos > 0 && !reloading) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/4 * (Math.random() > 0.5? 1 : -1) * (Math.random() > 0.5? 1 : 2), (int) rect.y, 0f, 26f, dmg, team));
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), (float) ((Math.random() > 0.5 ? 1 : -1) * (Math.random() * 5f)), -23f, dmg, team));
  		  ammos--;
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > lastTurn && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	
}

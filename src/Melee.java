
public class Melee extends Player {
	public static final float SPEED = 8f;
	public static final int DMG = 4;
	public static final int HP = 100;
	public static final int AMMOS = 48;
	public static final int FIRERATE = 80;
	public static final int RELOAD = 3650;
	public static final int WIDTH = 45;
	public static final int HEIGHT = 60;
	public Melee(int x, int y, Ability a) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, AMMOS, RELOAD, a, Assets.ships[3]);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && fire && ammos > 0 && !reloading) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.y, 0f, 26f, 4, team, 11));
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.y, 5f * getBulletDir(), 21f, 3, team, 11));
  		  Assets.playSound(Assets.gunFire, dmg * 16);
  		  ammos--;
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > lastTurn && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	
}

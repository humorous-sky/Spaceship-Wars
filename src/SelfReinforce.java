
public class SelfReinforce extends Player {
	public static final float SPEED = 6.88f;
	public static final int DMG = 3;
	public static final int HP = 64;
	public static final int AMMOS = 32;
	public static final int FIRERATE = 68;
	public static final int RELOAD = 3460;
	public static final int WIDTH = 50;
	public static final int HEIGHT = 80;
	private boolean stop = true;
	public SelfReinforce(int x, int y, Ability a) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, AMMOS, RELOAD, a, Assets.ships[4]);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && fire && ammos > 0 && !reloading) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/3 * getBulletDir(), (int) rect.y, 0f, 26f, dmg, team));
  		  ammos--;
  		  Assets.playSound(Assets.gunFire, dmg * 8);
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > lastTurn && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	@Override
	public void move(){
		stop = isStopped() && stop;
		super.move();
	}
	@Override
	public void gainShields(int amount){
		super.gainShields(amount < 0 && stop ? -amount : amount);
		stop = stop || amount < 0;
	}
	
}
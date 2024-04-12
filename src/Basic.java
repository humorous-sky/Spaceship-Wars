
public class Basic extends Player {
	public static final float SPEED = 6.6f;
	public static final int DMG = 5;
	public static final int HP = 128;
	public static final int AMMOS = 64;
	public static final int FIRERATE = 100;
	public static final int RELOAD = 3000;
	public Basic(int x, int y, Ability a) {
		super(x, y, a);
		this.rect.width = Screen.X(50);
        this.rect.height = Screen.Y(68);
        this.team = true;
        img = Assets.ships[0];
        speed = SPEED;
        dmg = DMG;
        hp = HP;
        maxHp = hp;
        maxAmmos = AMMOS;
        ammos = maxAmmos;
        fireRate = FIRERATE;
        reloadTime = RELOAD;
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && fire && ammos > 0 && !reloading) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/3 * (Math.random() > 0.5? 1 : -1), (int) rect.y, 0f, 26f, dmg, team));
  		  ammos--;
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > lastTurn && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	
}

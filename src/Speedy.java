
public class Speedy extends Player {
	public static final float SPEED = 8.88f;
	public static final int DMG = 3;
	public static final int HP = 64;
	public static final int AMMOS = 32;
	public static final int FIRERATE = 60;
	public static final int RELOAD = 1800;
	public Speedy(int x, int y, Ability a) {
		super(x, y, a);
		this.rect.width = Screen.X(38);
        this.rect.height = Screen.Y(55);
        this.team = true;
        img = Assets.ships[1];
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
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/10 * (Math.random() > 0.5? 1 : -1), (int) rect.y, 0f, 26f, dmg, team));
  		  ammos--;
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > reload && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	
}
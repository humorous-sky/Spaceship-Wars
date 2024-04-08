
public class Tank extends Player {
	public static final float SPEED = 5f;
	public static final int DMG = 8;
	public static final int HP = 160;
	public static final int AMMOS = 80;
	public static final int FIRERATE = 160;
	public static final int RELOAD = 5000;
	public Tank(int x, int y, Ability a) {
		super(x, y, a);
		this.rect.width = Screen.X(68);
        this.rect.height = Screen.Y(88);
        this.team = true;
        img = Assets.ships[2];
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
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/4 * (Math.random() > 0.5? 1 : -1) * (Math.random() > 0.5? 1 : 2), (int) rect.y, 0f, 26f, dmg, team));
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), (float) ((Math.random() > 0.5 ? 1 : -1) * (Math.random() * 5f)), -23f, dmg, team));
  		  ammos-=2;
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > reload && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	
}

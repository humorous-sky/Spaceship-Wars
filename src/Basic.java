
public class Basic extends Player {

	public Basic(int x, int y, Ability a) {
		super(x, y, a);
		this.rect.width = Screen.X(50);
        this.rect.height = Screen.Y(68);
        this.team = true;
        img = Assets.ships[0];
        speed = 6.6f;
        dmg = 5;
        hp = 128;
        maxHp = hp;
        maxAmmos = 64;
        ammos = maxAmmos;
        fireRate = 100;
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate && fire && ammos > 0 && !reloading) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/3 * (Math.random() > 0.5? 1 : -1), (int) rect.y, 0f, 26f, dmg, team));
  		  ammos--;
  		  lastFire = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() > reload && reloading) {
  		  ammos = maxAmmos;
  		  reloading = false;
  	  }
    }
	
}

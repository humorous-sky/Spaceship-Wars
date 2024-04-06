
public class Speedy extends Player {

	public Speedy(int x, int y, Ability a) {
		super(x, y, a);
		this.rect.width = Screen.X(38);
        this.rect.height = Screen.Y(55);
        this.team = true;
        img = Assets.ships[1];
        speed = 8.88f;
        dmg = 3;
        hp = 64;
        maxHp = hp;
        maxAmmos = 32;
        ammos = maxAmmos;
        fireRate = 60;
        reloadTime = 1800;
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
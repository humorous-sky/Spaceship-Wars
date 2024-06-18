import java.awt.Graphics;

public class World2Boss extends BossEntity {


	public World2Boss(int x, int y) {
		super(x, y, 230, 180, 1800, 128, 0.8f, 3800, false, null, 15);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Entity e = Entity.createEntity(2, (int) (Math.random() * 5), (int) rect.getCenterX() - rect.width/10, (int) rect.getCenterY());
  		  e.s = 0;
  		  Screen.entitiesToAdd.add(e);
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() - rect.width/5, (int) rect.getMaxY(), 0f, 5f, dmg, team));
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/5, (int) rect.getMaxY(), 0f, 5f, dmg, team));
		  Assets.playSound(Assets.gunFire, dmg * 2);
		  lastFire = System.currentTimeMillis();
  	  }
    }
}

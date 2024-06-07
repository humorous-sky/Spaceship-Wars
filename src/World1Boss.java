import java.awt.Graphics;

public class World1Boss extends BossEntity {

	public World1Boss(int x, int y) {
		super(x, y, 230, 180, 2300, 30, 0.8f, 8500, false, imgs[0][5], 10);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Entity e = Entity.createEntity(1, (int) (Math.random() * 5), (int) rect.getCenterX() - rect.width/3, (int) rect.getCenterY());
  		  e.s = 0;
  		  Screen.entitiesToAdd.add(e);
  		  e = Entity.createEntity(1, (int) (Math.random() * 5), (int) rect.getCenterX() + rect.width/6, (int) rect.getCenterY());
		  e.s = 0;
		  Screen.entitiesToAdd.add(e);
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() - rect.width/2, (int) rect.getMaxY(), 0f, 5f, dmg * 2, team));
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/2, (int) rect.getMaxY(), 0f, 5f, dmg * 2, team));
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 1f, 5f, dmg, team));
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 3f, 5f, dmg, team));
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), -3f, 5f, dmg, team));
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), -1f, 5f, dmg, team));
		  Assets.playSound(Assets.gunFire, dmg * 8);
		  lastFire = System.currentTimeMillis();
  	  }
    }
}

import java.awt.Graphics;

public class World3Boss extends BossEntity {


	public World3Boss(int x, int y) {
		super(x, y, 260, 210, 4600, 25, 0.6f, 8800, false, imgs[2][5], 5);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Entity e = Entity.createEntity(3, (int) (Math.random() * 5), (int) rect.getCenterX() - rect.width/10, (int) rect.getCenterY());
  		  e.s = 0;
  		  Screen.entitiesToAdd.add(e);
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() - rect.width/5, (int) rect.getMaxY(), 0f, 5f, dmg, team));
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/5, (int) rect.getMaxY(), 0f, 5f, dmg, team));
		  for (Entity E: Screen.entities) {
	  			if (E.team == team && E != this) {
	  				E.heal(E.maxHp);
	  			} else if (E == this) {
	  				E.heal(50);
	  			}
	  		}
		  Assets.playSound(Assets.gunFire, dmg * 2);
		  lastFire = System.currentTimeMillis();
  	  }
    }
}

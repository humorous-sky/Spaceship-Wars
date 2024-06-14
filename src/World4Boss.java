import java.awt.Graphics;

public class World4Boss extends BossEntity {

	public World4Boss(int x, int y) {
		super(x, y, 230, 180, 1380, 15, 0.8f, 3500, false, imgs[3][5], 17);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Entity e = Entity.createEntity(4, (int) (Math.random() * 5), (int) rect.getCenterX() - rect.width/10, (int) rect.getCenterY());
  		  e.s = 0;
  		  Screen.entitiesToAdd.add(e);
	  	  for (double theta = -Math.PI/4; theta < 5 * Math.PI/4; theta += Math.PI/20) {
			  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 5 * (float) Math.cos(theta), 5 * (float) Math.sin(theta), dmg, team));
		  }
	  	  Assets.playSound(Assets.gunFire, dmg * 12);
		  lastFire = System.currentTimeMillis();
  	  }
    }
	@Override
	public void onOof() {
		super.onOof();
		 for (double theta = 0.0; theta < 2 * Math.PI; theta += Math.PI/50) {
			  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 10 * (float) Math.cos(theta), 10 * (float) Math.sin(theta), dmg, team));
		  }
	}
}

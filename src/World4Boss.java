import java.awt.Graphics;

public class World4Boss extends BossEntity {
	private double theta = -Math.PI/4;
	private int multi = 1;
	public World4Boss(int x, int y) {
		super(x, y, 230, 180, 1380, 15, 0.8f, 116, false, null, 17);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  if (theta == Math.PI/2) {
  			  Entity e = Entity.createEntity(4, (int) (Math.random() * 5), (int) rect.getCenterX() - rect.width/10, (int) rect.getCenterY());
  			  e.s = 0;
  			  Screen.entitiesToAdd.add(e);
  		  }
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 5 * (float) Math.cos(theta), 5 * (float) Math.sin(theta), dmg, team));
  		  theta += multi * Math.PI/20;
  		  if (theta > 5 * Math.PI/4) {
	  		  theta -= Math.PI/20;
	  		  multi = -1;
	  	  }
	  	  if (theta < -Math.PI/4) {
	  		  theta += Math.PI/20;
	  		  multi = 1;
	  	  }
  		  Assets.playSound(Assets.gunFire, dmg);
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

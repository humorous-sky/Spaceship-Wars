import java.awt.Graphics;

public class World1Boss extends Entity {

	public World1Boss(int x, int y) {
		super(x, y, 230, 180, 2300, 30, 0.8f, 8500, false, imgs[0][5], 10);
		dir = (float) ((Math.random() * speed * 1.6) - (speed * 0.8));
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void move() {
  	  for (Entity e: Screen.entities) {
  		  if (hp > 0 && e.hp > 0&& e instanceof Ammos && e.team != this.team && e.rect.intersects(this.rect)) {
  			  Screen.score += hp > e.hp ? e.hp * s : hp * s;
  			  Screen.plr.a.increment(hp > e.hp ? e.hp : hp, 2);
  			  hp -= e.hp;
  			  e.hp = 0;
  			  Screen.entitiesToRemove.add(e);
  			  lastDamaged = System.currentTimeMillis() + 50;
  		  }
  	  }
  	  if (System.currentTimeMillis() >= lastTurn + 2600) {
  		  dir = (float) ((Math.random() * speed * 1.6) - (speed * 0.8));
  		  lastTurn = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() >= lastMove + 26) {
  		  //y += speed * Screen.aY(1);
		  x += dir * Screen.aX(1);
		  if (x < Screen.min) {
			  x  = Screen.min;
		  }
		  if (x + rect.width > Screen.max) {
			  x = Screen.max - rect.width;
		  }
		  if (y > Screen.aY(1000)) {
			  y = Screen.aY(-15);
		  }
  		  rect.x = (int) x;
  		  rect.y = (int) y;
  		  lastMove = System.currentTimeMillis();
  		  if (hp <= 0 && frame < 2.85) {
      		  frame += 0.15;
      	  } else if (frame >= 2.85) {
      		  onOof();
      	  }
  	  }
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
		  lastFire = System.currentTimeMillis();
  	  }
    }
}

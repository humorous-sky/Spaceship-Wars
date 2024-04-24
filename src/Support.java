import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Support extends Entity {
	public Support(int x, int y) {
		super(x, y, 38, 50, 15, 3, 0.8f, 1500, true, Assets.misc[0], 0);
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void move() {
		for (Entity e: Screen.entities) {
			if (hp > 0 && e.hp > 0 && e.team != this.team && e.rect.intersects(this.rect)) {
				Screen.score += hp > e.hp ? e.hp * e.s : hp * e.s;
  			  	int php = hp;
  			  	hp -= hp > e.hp ? e.hp : hp;
  			  	e.hp = hp > e.hp ? 0 : e.hp - php;
  			  	if (e instanceof Ammos && e.hp <= 0) {
  			  		Screen.entitiesToRemove.add(e);
  			  	}
  			  	lastDamaged = System.currentTimeMillis() + 50;
  		  	}
  	  }
  	  if (System.currentTimeMillis() >= lastTurn + 2600) {
  		  dir = (float) ((Math.random() * speed * 1.6) - (speed * 0.8));
  		  dir *= dir < 0 && Screen.plr.x > x || dir > 0 && Screen.plr.x < x ? -1 : 1;
  		  lastTurn = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() >= lastMove + 26) {
  		  y += speed * Screen.aY(1) * (team ? -1 : 1);
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
  		  if (y < 0 - rect.height) {
			  y = Screen.aY(1000);
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
		fireRate = hp * 100 < 1000 ? 1000 : hp * 100;
		if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.y, 0f, 5f, dmg, team));
  		  lastFire = System.currentTimeMillis();
  	  	}
	}
}

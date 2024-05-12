import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
	public int maxAmmos = 5;
	public int ammos;
	public boolean fire = false;
	public boolean reloading = false;
	private boolean last = false;
	public int reloadTime = 3000; 
	public int xA = 0;
	public int yA = 0;
	public Ability a;
	public static final Class[] refs = {Basic.class, Speedy.class, Tank.class, Melee.class};
	public static final String[] descriptions = {"All purpose ship for anything!", 
			"Small, fast, and versatile.", "Big and powerful. Slow but has a gun in the back. ", "Has a limited bullet range. Better fight up close!"};
	public Player(int x, int y, int width, int height, int hp, int dmg, float speed, int fireRate, int ammos, int reloadTime, Ability a, BufferedImage img) {
		super(x, y, width, height, hp, dmg, speed, fireRate, true, img, 1);
		this.ammos = ammos;
		this.maxAmmos = ammos;
		this.reloadTime = reloadTime;
        this.a = a;
	}
	public static Player createPlayer(int type, int x, int y, Ability a) {
		try {
			return (Player) refs[type].getDeclaredConstructor(int.class, int.class, Ability.class).newInstance(x, y, a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 		
	}
	@Override
	public void paint(Graphics g) {
		drawImage(x, y, rect.width, rect.height, 0f, img, g);
	}
	@Override
	public void move() {
		diffFire = System.currentTimeMillis() - lastFire;
  	  	diffTurn = System.currentTimeMillis() - lastTurn;
  	  	diffMove = System.currentTimeMillis() - lastMove;
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
  	  if (System.currentTimeMillis() >= lastMove + 26) {
  		  xA = 0;
  		  yA = 0;
  		  if (Keys.up) {
  			 yA --;
  		  }
  		  if (Keys.left) {
			 xA --;
		  }
  		  if (Keys.down) {
			 yA ++;
		  } 
  		  if (Keys.right) {
			 xA ++;
		  }
  		  if (Keys.activate) {
  			  a.activateIfCharged(this);
  		  } 
  		  a.increment((int) (System.currentTimeMillis() - lastMove), 3);
  		  if (Math.abs(xA) + Math.abs(yA) == 2) {
  			  x += xA * speed * Screen.aX(0.3978);
			  y += yA * speed * Screen.aY(0.7071);
  		  } else {
  			  x += xA * speed * Screen.aX(0.5625);
  			  y += yA * speed * Screen.aY(1.0000);
  		  }
  		  if (x < Screen.min) {
  			  x = Screen.min;
  		  }
  		  if (x + rect.width > Screen.max) {
  			  x = Screen.max - rect.width;
  		  }
  		  if (y < Screen.aY(0)) {
			  y = Screen.aY(0);
		  }
  		  if (y + rect.height > Screen.aY(1000)) {
  			  y = Screen.aY(1000) - rect.height;
  		  }
  		  rect.x = (int) x;
  		  rect.y = (int) y;
  		  lastMove = System.currentTimeMillis();
  		  if (hp <= 0 && frame < 2.85) {
      		  frame += 0.15;
      	  } 
  		  if (hp < 0) {
  			  hp = 0;
  		  }
  	  }
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/3 * (Math.random() > 0.5? 1 : -1), (int) rect.y, 0f, 26f, dmg, team));
  		  lastFire = System.currentTimeMillis();
  	  }
    }
	public void reload() {
		if (!reloading && ammos != maxAmmos) {
			reloading = true;
			lastTurn = System.currentTimeMillis() + reloadTime;
		}
	}
	public static Color getBarColor(float cur, double max) {
		return cur > max/2 ? new Color((int) ((-255.0/(max/2)) * cur + 510), 255, 0) : cur > 0 ? new Color(255, (int) ((255.0/(max/2)) * cur), 0) : new Color(255, 0, 0);
	}	
	/*(max/2, 255)
	(0, 0)*/
}

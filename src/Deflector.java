import java.awt.Graphics;

public class Deflector extends Entity {
	public static final float SPEED = 0.8f;
	public static final int DMG = 0;
	public static final int HP = 15;
	public static final int AMMOS = 1;
	public static final int FIRERATE = 334;
	public static final String DESC = "Deflects the player's bullets with 6x damage: the faster and lighter the bullets, the faster the deflection!"; 
	public static final int WIDTH = 60;
	public static final int HEIGHT = 50;
	public Deflector(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, null, 6);
	}
	public void move() {
  	  currentSpeed = speed;
  	  for (Buff b: buffs) {
  		  b.process(this);
  	  }
  	  for (Buff b: buffsToRemove) {
  		  buffs.remove(b);
  	  }
  	  buffsToRemove.clear();
  	  if (currentSpeed > 0f) {
  		  diffFire = System.currentTimeMillis() - lastFire;
  		  diffTurn = System.currentTimeMillis() - lastTurn;
  		  diffMove = System.currentTimeMillis() - lastMove;
  	  } else {
  		  lastFire = System.currentTimeMillis() - diffFire;
    		  lastTurn = System.currentTimeMillis() - diffTurn;
  	  }  
  	  for (Entity e: Screen.entities) {
  		  if (System.currentTimeMillis() > spawnTime + 50 && hp > 0 && e.hp > 0 && e instanceof Ammos && e.team != this.team && e.rect.intersects(this.rect)) {
  			  Screen.score += hp <= thres ? (hp > e.hp ? e.hp * s : hp * s) : 0;
  			  Screen.plr.a.increment(hp > e.hp ? e.hp : hp, 2);
  			  takeDamage(e.hp);
  			  if (currentSpeed >  0f ) {
  				  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), -3 * ((Ammos) e).sX/e.hp, -3 * ((Ammos) e).sY/e.hp, 6 * e.hp, team));
  			  }
  			  e.hp = 0;
  			  Screen.entitiesToRemove.add(e);
  			  lastDamaged = System.currentTimeMillis() + 50;
  		  }
  	  }
  	  if (System.currentTimeMillis() >= lastTurn + 2600) {
  		  dir = (float) ((Math.random() * currentSpeed * 1.6) - (currentSpeed * 0.8));
  		  lastTurn = System.currentTimeMillis();
  	  }
  	  if (System.currentTimeMillis() >= lastMove + 26) {
  		  y += currentSpeed * Screen.aY(1) * (team ? -1 : 1);
  		  if (currentSpeed > 0f) {
  			  x += dir * Screen.aX(1);
  		  }
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
  		  if (hp <= 0 && frame < 2.86) {
      		  frame += 0.15;
      	  } else if (frame >= 2.86) {
      		  onOof();
      	  }
  	  }
    }
    public void fire() {
  	 
    }
}

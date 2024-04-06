
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Entity {
	  public int hp = 10;
	  public int s = 0;
	  public int maxHp = 10;
	  public float speed = 0.8f;
	  public int dmg = 1;
	  public float x;
	  public float y;
	  public float dir = 0.0f;
	  public float frame = 0.0f;
	  public boolean team;
      public Rectangle rect = new Rectangle();
      public long lastMove = System.currentTimeMillis();
      public long lastFire = System.currentTimeMillis();
      public int fireRate = 3000;
      public long lastTurn = System.currentTimeMillis();
      public long lastDamaged = System.currentTimeMillis();
      public BufferedImage img = null;
      public static BufferedImage[][] imgs = {{Assets.newImage("Fighter.png"), Assets.newImage("Scout.png"),
    		  								Assets.newImage("MediumFighter.png"), Assets.newImage("Carrier.png"),
    		  								Assets.newImage("Spawn.png"), Assets.newImage("World1Boss.png")}};
      public static BufferedImage[] exp = {Assets.newImage("exp1.png"), Assets.newImage("exp2.png"), Assets.newImage("exp3.png")};
      public Entity (int x, int y) {
          this.x = x;
          this.y = y;
          this.rect.width = Screen.X(10);
          this.rect.height = Screen.Y(10);
          maxHp = hp;
          this.team = false;
      }
   
      public void paint (Graphics g) {
            //drawImage(x, y, rect.width, rect.height, 0f, img, g);
            g.setColor(Color.white);
            g.drawRect((int) x, (int) y, rect.width, rect.height);
      }
      public void move() {
    	  for (Entity e: Screen.entities) {
    		  if (hp > 0 && e.hp > 0&& e instanceof Ammos && e.team != this.team && e.rect.intersects(this.rect)) {
    			  Screen.score += hp > e.hp ? e.hp * s : hp * s;
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
    		  y += speed * Screen.aY(1);
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
      public void fire() {
    	  if (System.currentTimeMillis() >= lastFire + fireRate) {
    		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX(), (int) rect.getMaxY(), 0f, 5f, dmg, team));
    		  lastFire = System.currentTimeMillis();
    	  }
      }
      public static Entity createEntity(int world, int type, int x, int y) {
    	  switch (world) {
    	  		case 1:
    	  			return (type == 0 ? new Fighter(x, y) : type == 1 ? new Scout(x, y) : type == 2 ? new MediumFighter(x, y) : type == 3 ? new Carrier(x, y) : type == 4 ? new Spawner(x, y) : new World1Boss(x, Screen.Y(0)));
    	  }
    		  
    	  return null;
      }
      public void drawAnim(Graphics g) {
    	  if (System.currentTimeMillis() <= lastDamaged)  {
    		  g.setColor(Color.red);
    		  g.fillOval((int) x, (int) y, rect.width, rect.height);
    	  }
    	  if (hp <= 0) {
    		  drawImage(x, y, rect.width, rect.height, 0f, exp[(int) frame], g);
    		  
    	  }
      }
      public void onOof() {
    	  Screen.plr.a.increment(1, 1);
    	  Screen.entitiesToRemove.add(this);
      }
      public static void drawImage(double x, double y, double width, double height, float direction, BufferedImage image, Graphics g) {
    	  AffineTransform at = new AffineTransform();
    	  at.translate(x, y);
    	  at.rotate(Math.toRadians(-direction));
    	  at.scale(width/image.getWidth(), height/image.getHeight());
    	  //at.translate(-image.getWidth(null) / 2, -image.getHeight(null) / 2);
    	  Graphics2D g2d = (Graphics2D) g;
    	  g2d.drawImage(image, at, null);
  	}
}


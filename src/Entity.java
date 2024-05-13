
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.awt.geom.AffineTransform;

public class Entity {
	  public int hp = 10;
	  public int s = 0;
	  public int maxHp = 10;
	  public final int bullets = 1;
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
      public long lastHealed = System.currentTimeMillis();
      public long diffTurn = 0;
      public long diffFire = 0;
      public long diffMove = 0;
      private int thres;
      public BufferedImage img = null;
      public static BufferedImage[][] imgs;
      public static BufferedImage[] exp;
      public static final Class[][] refs = {
    		  							{Fighter.class, Scout.class, MediumFighter.class, Carrier.class, Spawner.class, World1Boss.class},
      									{Sniper.class, Accurate.class, MoreAccurate.class, MultiSniper.class, SnipeLead.class, World2Boss.class},
    		  							{Recycler.class, Armadillo.class, SelfRepair.class, ShieldShip.class, Healer.class, null},
    		  							{MiniSplitShooter.class, Rage.class, Minigunner.class, SplitShooter.class, HeavySplitShooter.class, null}};
      public Entity(int x, int y, int width, int height, int hp, int dmg, float speed, int fireRate, boolean team, BufferedImage img, int s) {
          this.x = x;
          this.y = y;
          this.rect.width = Screen.X(width);
          this.rect.height = Screen.Y(height);
          this.hp = hp;
          this.maxHp = hp;
          this.thres = maxHp;
          this.dmg = dmg;
          this.speed = speed;
          this.fireRate = fireRate;
          this.team = team;
          this.img = img;
          this.s = s;
      }
   
      public void paint (Graphics g) {
            //drawImage(x, y, rect.width, rect.height, 0f, img, g);
            g.setColor(Color.white);
            g.drawRect((int) x, (int) y, rect.width, rect.height);
      }
      public void move() {
    	  diffFire = System.currentTimeMillis() - lastFire;
    	  diffTurn = System.currentTimeMillis() - lastTurn;
    	  diffMove = System.currentTimeMillis() - lastMove;
    	  for (Entity e: Screen.entities) {
    		  if (hp > 0 && e.hp > 0 && e instanceof Ammos && e.team != this.team && e.rect.intersects(this.rect)) {
    			  Screen.score += hp <= thres ? (hp > e.hp ? e.hp * s : hp * s) : 0;
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
			try {
				return (Entity) refs[world - 1][type].getDeclaredConstructor(int.class, int.class).newInstance(x, y);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} 			
      }
      public void drawAnim(Graphics g) {
    	  if (System.currentTimeMillis() <= lastHealed)  {
    		  g.setColor(Color.green);
    		  g.fillOval((int) x, (int) y, rect.width, rect.height);
    	  }
    	  if (System.currentTimeMillis() <= lastDamaged)  {
    		  g.setColor(Color.red);
    		  g.fillOval((int) x, (int) y, rect.width, rect.height);
    	  }
    	  if (hp <= 0) {
    		  drawImage(x, y, rect.width, rect.height, 0f, exp[(int) frame], g);  
    	  } else {
    		  frame = 0.0f;
    	  }
      }
      public void onOof() {
    	  Screen.plr.a.increment(1, 1);
    	  Screen.entitiesToRemove.add(this);
      }
      public void heal(int amount) {
    	  if (amount <= 0 || hp <= 0) {
    		  return;
    	  }
    	  thres = hp;
    	  hp = hp + amount > maxHp? maxHp : hp + amount;
    	  lastHealed = System.currentTimeMillis() + 80;
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
      public static void loadImages() {
    	  System.out.println("Loading Entities...");
    	  imgs = new BufferedImage[][]{{Assets.newImage("Fighter.png"), Assets.newImage("Scout.png"),
				Assets.newImage("MediumFighter.png"), Assets.newImage("Carrier.png"),
				Assets.newImage("Spawn.png"), Assets.newImage("World1Boss.png")},
				{Assets.newImage("Sniper.png"), Assets.newImage("Accurate.png"),
				Assets.newImage("MoreAccurate.png"), Assets.newImage("MultiSniper.png"),
				Assets.newImage("SnipeLead.png"), Assets.newImage("World2Boss.png")},
				{Assets.newImage("Recycler.png"), Assets.newImage("Armadillo.png"),
				Assets.newImage("SelfRepair.png"), Assets.newImage("ShieldShip.png"),
				Assets.newImage("Healer.png"), null}, 
				{Assets.newImage("MiniSplit.png"), Assets.newImage("Rage.png"),
				Assets.newImage("Minigun.png"), Assets.newImage("Split.png"),
				Assets.newImage("HeavySplit.png"), null}};
    	  exp = new BufferedImage[]{Assets.newImage("exp1.png"), Assets.newImage("exp2.png"), Assets.newImage("exp3.png")};
      }
}


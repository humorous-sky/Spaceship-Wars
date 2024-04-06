
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Screen extends JPanel{

    /**
     * 
     */
    private static final long serialVersionUID = 5241834608422998589L;
    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public static ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
    public static ArrayList<Entity> entitiesToAdd = new ArrayList<Entity>();
    public static ArrayList<Stars> stars = new ArrayList<Stars>();
    public static ArrayList<Stars> starsToRemove = new ArrayList<Stars>();
    public static float[] ys = {Y(0), Y(1000)};
    public static Player plr;
    public static final int min = X(200);
    public static final int max = X(800);
    public static int wave = 0;
    public static int spawned = 0;
    public static int onField = 0;
    public int world;
    public int level;
    public static boolean status = false;
    private long lastSpawn = System.currentTimeMillis();
    private long lastStar = System.currentTimeMillis();
    public static long score = 0;
    public Screen(int world, int level, Player p) {
    	plr = p;
    	entities.add(plr);
    	this.world = world;
    	this.level = level;
    }
    @Override
    public void paint(Graphics g) {
    	onField = 0;
    	g.setColor(Color.black);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	for (Stars s: stars) {
        	s.paint(g);
        }
        for (Stars s: starsToRemove) {
        	stars.remove(s);
        }
        starsToRemove.clear();
    	g.setColor(Player.getBarColor(plr.hp, plr.maxHp));
    	g.fillRect(X(805), Y(800), X(170.0 * (plr.hp/(double) plr.maxHp)), Y(33));
    	g.setColor(Player.getBarColor(plr.ammos, plr.maxAmmos));
    	g.fillRect(X(805), Y(838), X(170.0 * (plr.ammos/(double) plr.maxAmmos)), Y(33));
    	g.setColor(Player.getBarColor(plr.a.getCurrent(), plr.a.getValue()));
    	g.fillRect(X(15), Y(850), X(170.0 * plr.a.getProgress()), Y(33));
    	g.setColor(Color.white);
    	g.drawLine(min, Y(0), min, Y(1000));
    	g.drawLine(max, 0, max, Y(1000));
    	g.setFont(new Font("", Font.ROMAN_BASELINE, 10));
        g.drawString("fps: " + Math.round(SpaceshipWars.fps * 10) / 10.0,  X(5), Y(15));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 26));
        g.drawString("Health: " + plr.hp, X(810), Y(827));
        g.drawString("Bullets: " + plr.ammos + "/" + plr.maxAmmos, X(810), Y(864));
        g.drawString("Ability: " + (Math.round(plr.a.getProgress() * 1000)/10.0) + "%", X(15), Y(876));
        g.drawString(plr.reloading ? "(Reloading " + Math.round((plr.reload - System.currentTimeMillis())/100.0)/10.0 + "s)" :"", X(810), Y(898));
        g.drawString(wave > 0 ? "Wave " + wave : level == 25 ? "Boss Fight" : "Warmup Wave", X(5), Y(55));
        g.drawString("Score: " + score, X(5), Y(88));
        g.drawString("Level " + world + "-" + level, X(5), Y(121));
        if (System.currentTimeMillis() >= lastStar + 150) {
        	stars.add(new Stars((int) (Math.random() * X(600)) + X(200), Y(-15), Color.white));
        	lastStar = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() >= lastSpawn + 1700 && spawned < (level <= 24 ? wave * 2 + (level - 1) % 6 + 1 : level == 25 ? 1  : wave + 3)) {
        	entitiesToAdd.add(Entity.createEntity(world, level <= 24 ? (int)(Math.random() * (wave > 0 ? 2 + (level - 1) / 6 : 2)) : level == 25 ? 5 : (int)(Math.random() * (wave > 0 ? 5 : 2)), X(Math.random() * 600 + 200), Y(-15)));
        	lastSpawn = System.currentTimeMillis();
        	spawned ++;
        }
        if (spawned >= (level <= 24 ? wave * 2 + (level - 1) % 6 + 1 : level == 25 ? 1 : wave + 3) && !status) {
        	status = true;
        }
        for (Entity e: entitiesToRemove) {
        	entities.remove(e); 	
        }
        entitiesToRemove.clear();
        for (Entity e: entitiesToAdd) {
        	entities.add(e); 	
        }
        entitiesToAdd.clear();
        for (Entity e: entities) {
        	e.move();
        	if (e.frame < 1.6) {
        		e.paint(g);
        	}
        	if (e.hp > 0) {
        		e.fire();
        	}
        	e.drawAnim(g);
        	if (!(e instanceof Ammos || e instanceof Player)) {
        		onField++;
        	}
        }
        if (onField == 0 && status) {
        	status = false;
        	spawned = 0;
        	score += (wave + 1) * 600;
        	wave ++;
        	if (wave > 5 && level <= 24 || wave > 0 && level == 25) {
        		System.exit(0);
        	}
        	lastSpawn = System.currentTimeMillis();
        }
        for (int i = 0; i < this.getComponentCount(); i ++) {
        	if (this.getComponent(i) instanceof CustomButton) {
        		this.getComponent(i).paint(g);
        	}
        }
        //disposes the paintComponent
        g.dispose();
    }
    public static int X(double x) {
    	return (int) (SpaceshipWars.width / 1000.0 * x);
    }
    public static float aX(double x) {
    	return (float) (SpaceshipWars.width / 1000.0 * x);
    }
    public static int Y(double y) {
    	return (int) (SpaceshipWars.height / 1000.0 * y);
    }
    public static float aY(double y) {
    	return (float) (SpaceshipWars.height / 1000.0 * y);
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


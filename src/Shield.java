import java.awt.Color;
import java.awt.Graphics;

public class Shield extends Entity {
	public static final float SPEED = 0f;
	public static final int DMG = 0;
	public static final int HP = 100;
	public static final int AMMOS = 0;
	public static final int FIRERATE = 3000;
	public static final String DESC = ""; 
	public static final int WIDTH = 230;
	public static final int HEIGHT = 230;
	public Shield(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HP, DMG, SPEED, FIRERATE, false, Assets.misc[2], 0);
	}
	public Shield(int x, int y, int width, int height) {
		super(x, y, width, height, HP, DMG, SPEED, FIRERATE, false, Assets.misc[2], 0);
	}
	@Override
	public void fire() {}
	@Override
	public void drawAnim(Graphics g) {
  	  if (System.currentTimeMillis() <= lastHealed)  {
  		  g.setColor(Color.green);
  		  g.drawOval((int) x, (int) y, rect.width, rect.height);
  	  }
  	  if (System.currentTimeMillis() <= lastDamaged)  {
  		  g.setColor(Color.red);
  		  g.drawOval((int) x, (int) y, rect.width, rect.height);
  	  }
  	  if (hp <= 0) {
  		  drawImage(x, y, rect.width, rect.height, 0f, exp[(int) frame], g);
  		  
  	  }
   }
	@Override
	public void onOof() {}
	@Override
	 public void move() {
   	  diffFire = System.currentTimeMillis() - lastFire;
   	  diffMove = System.currentTimeMillis() - lastMove;
   	  for (Entity e: Screen.entities) {
   		  if (hp > 0 && e.hp > 0 && e instanceof Ammos && e.team != this.team && e.rect.intersects(this.rect)) {
   			  //Screen.plr.a.increment(hp > e.hp ? e.hp : hp, 2);
   			  hp -= e.hp;
   			  e.hp = 0;
   			  Screen.entitiesToRemove.add(e);
   			  lastDamaged = System.currentTimeMillis() + 50;
   		  } else if (hp > 0 && e.hp > 0 && e instanceof Player && e.team != this.team && e.rect.intersects(this.rect)) {
   			  int php = e.hp;
   			  e.hp -= this.hp > e.hp ? e.hp : this.hp;
   			  this.hp -= php;
   			lastDamaged = System.currentTimeMillis() + 50;
   		  }
   	  }
   	  if (System.currentTimeMillis() >= lastMove + 26) {
   		  lastMove = System.currentTimeMillis();
   		  if (hp <= 0 && frame < 2.85) {
       		  frame += 0.15;
       	  } else if (frame >= 2.85) {
       		  onOof();
       	  }
   	  }
     }
}

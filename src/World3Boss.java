import java.awt.Graphics;

public class World3Boss extends BossEntity {

	private Shield shield;
	public World3Boss(int x, int y) {
		super(x, y, 260, 210, 4600, 25, 0.6f, 8500, false, imgs[2][5], 5);
	}
	@Override
	public void fire() {
  	  if (System.currentTimeMillis() >= lastFire + fireRate) {
  		  Entity e = Entity.createEntity(3, (int) (Math.random() * 5), (int) rect.getCenterX() - rect.width/10, (int) rect.getCenterY());
  		  e.s = 0;
  		  Screen.entitiesToAdd.add(e);
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() - rect.width/5, (int) rect.getMaxY(), 0f, 5f, dmg, team));
		  Screen.entitiesToAdd.add(new Ammos((int) rect.getCenterX() + rect.width/5, (int) rect.getMaxY(), 0f, 5f, dmg, team));
		  for (Entity E: Screen.entities) {
	  			if (E.team == team && !(E instanceof BossEntity)) {
	  				E.heal(15);
	  			} else if (E instanceof BossEntity) {
	  				E.heal(5);
	  			}
	  		}
		  Assets.playSound(Assets.gunFire, dmg * 2);
		  if (shield == null) {
  			  shield = new Shield(rect.x - Screen.X(25), rect.y - Screen.Y(25), 310, 260);
  		  } else if (shield.hp > 0) {
  			  shield.heal(100);
  		  }
		  lastFire = System.currentTimeMillis();
  	  }
    }
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (shield != null) {
			shield.paint(g);
		}
	}
	@Override
	public void move() {
		super.move();
		if (shield != null) {
			shield.move();
			shield.rect.x = rect.x - Screen.X(25);
			shield.rect.y = rect.y - Screen.Y(25);
			shield.x = x - Screen.X(25);
			shield.y = y - Screen.Y(25);
			if (shield.frame >= 2.85) {
				shield = null;
			}
		}
	}
	@Override
	public void drawAnim(Graphics g) {
		super.drawAnim(g);
		if (shield != null) {
			shield.drawAnim(g);
		}
	}
	@Override
	public void heal(int amount) {
		super.heal(amount);
		if (shield != null) {
			shield.heal(amount);
		}	
	}
}

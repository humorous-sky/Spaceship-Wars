import java.awt.Color;
import java.awt.Graphics;

public class Ammos extends Entity {
	float sX;
	float sY;
	int steps;
	int moved = 0;
	public Ammos(int x, int y, float sX, float sY, int dmg,  boolean team) {
		this(x, y, sX, sY, dmg, team, -1);
	}
	public Ammos(int x, int y, float sX, float sY, int dmg,  boolean team, int steps) {
		super(x, y, 1, (int) (dmg / (team ? 0.38 : 2.3)), dmg, 0, 0, 0, team, null, 1);
		this.sX = sX;
        this.sY = sY * (team ? -1 : 1);
		this.steps = steps;
	}
	@Override
	public void paint(Graphics g) {
		super.rect.height = Screen.Y(super.hp / (team ? 0.38 : 2.3));
		g.setColor(team ? Color.green : Color.red);
		if (Math.abs(sY) > Math.abs(sX)) {
			for (int i = 0; i < super.rect.height; i ++) {
				g.drawRect((int) (super.x + i * (sX/sY) - 0.5), (int) super.y + i, super.rect.width + 1, 1);
			}	
		} else {
			for (int i = 0; i < super.rect.height; i ++) {
				g.drawRect((int) super.x + i, (int) (super.y + i * (sY/sX) - 0.5), 1, super.rect.width + 1);
			}
		}
	}
	@Override
	public void heal(int amount) {
		this.hp += amount;
		this.maxHp = hp;
	}
	@Override
	public void move() {
		if (System.currentTimeMillis() >= super.lastMove + 26) {
	   		super.y += sY * Screen.aY(1);
	   		super.x += sX * Screen.aX(1);
	   		if (super.x < Screen.min) {
	   			Screen.entitiesToRemove.add(this);
	   		}
	   		if (super.x + super.rect.width > Screen.max) {
	   			 Screen.entitiesToRemove.add(this);
	   		}
	   		if (super.y > Screen.aY(1000)) {
	   			 Screen.entitiesToRemove.add(this);
	   		}
	   		if (super.y < 0) {
	   			 Screen.entitiesToRemove.add(this);
	   		}
	   		super.rect.x = (int) x;
	   		super.rect.y = (int) y;
	   		lastMove = System.currentTimeMillis();
	   		moved ++;
	   		if (steps > 0 && moved > steps) {
	   			Screen.entitiesToRemove.add(this);
	   		}
   	  }
	}
	@Override
	public void fire() {
		
	}
}

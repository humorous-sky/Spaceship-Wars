import java.awt.Color;
import java.awt.Graphics;

public class Ammos extends Entity {
	float sX;
	float sY;
	public Ammos(int x, int y, float sX, float sY, int dmg,  boolean team) {
		super(x, y, 1, (int) (dmg / (team ? 0.38 : 2.3)), dmg, 0, 0, 0, team, null, 1);
        this.sX = sX;
        this.sY = sY * (team ? -1 : 1);
	}
	@Override
	public void paint(Graphics g) {
		super.rect.height = Screen.Y(super.hp / (team ? 0.38 : 2.3));
		g.setColor(team ? Color.green : Color.red);
		if (Math.abs(sY) > Math.abs(sX)) {
			for (int i = 0; i < super.rect.height; i ++) {
				g.drawRect((int) (super.x + i * (sX/sY)), (int) super.y + i, super.rect.width, 1);
			}	
		} else {
			for (int i = 0; i < super.rect.height; i ++) {
				g.drawRect((int) super.x + i, (int) (super.y + i * (sY/sX)), 1, super.rect.width);
			}
		}
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
   	  }
	}
	@Override
	public void fire() {
		
	}
}

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;

public class Info extends JPanel{
	
	/**
	 * 
	 */
	public static int ship = 0;
	public static int world = 0;
	//private int level;
	private HashMap<String, Component> binding = new HashMap<String, Component>();
	private static final long serialVersionUID = -215196891130568350L;
	JPanel currentScreen;
	public Info() {
		currentScreen = this;
		addButton("HomeButton", "Return Home", X(380), Y(800), X(240), Y(80));
		binding.get("HomeButton").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				SpaceshipWars.navigate(currentScreen, new Start());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
	
			});
		binding.get("HomeButton").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		addButton("PreviousShip", "←", X(50), Y(680), X(100), Y(50));
		binding.get("PreviousShip").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ship--;
				ship = ship < 0 ? Entity.refs[world].length - 2 : ship;		
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
	
			});
		binding.get("PreviousShip").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		addButton("NextShip", "→", X(340), Y(680), X(100), Y(50));
		binding.get("NextShip").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ship ++;
				ship %= Entity.refs[world].length - 1;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
	
			});
		binding.get("NextShip").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		addButton("PreviousAbility", "←", X(560), Y(680), X(100), Y(50));
		binding.get("PreviousAbility").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				world--;
				world = world < 0 ? Entity.refs.length - 1 : world;
				ship = ship > Entity.refs[world].length - 2 ? Entity.refs[world].length - 2 : ship;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
	
			});
		binding.get("PreviousAbility").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		addButton("NextAbility", "→", X(850), Y(680), X(100), Y(50));
		binding.get("NextAbility").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				world ++;
				world %= Entity.refs.length;
				ship = ship > Entity.refs[world].length - 2 ? Entity.refs[world].length - 2 : ship;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
	
			});
		binding.get("NextAbility").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
	    g.fillRect(0, 0, getWidth(), getHeight());
	  //paints the fps number at the top left corner
        g.setColor(Color.white);
        g.setFont(new Font("", Font.ROMAN_BASELINE, 10));
        g.drawString("fps: " + Math.round(SpaceshipWars.fps * 10) / 10.0,  X(5), Y(15));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 66));
        g.drawString("Enemy Profiles", X(330), Y(110));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 38));
        g.drawString(Entity.refs[world][ship].getName(), (X(1000) - g.getFontMetrics().stringWidth(Entity.refs[world][ship].getName()))/2, Y(330));
        g.drawString("Enemy #" + (ship + 1), X(180), Y(715));
        g.drawString("World #" + (world + 1), X(700), Y(715));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
        g.drawImage(Entity.imgs[world][ship], X(380), Y(380), X(240), Y(180), null);
        try {
        	g.drawString((String) Entity.refs[world][ship].getField("DESC").get(null), (X(1000) - g.getFontMetrics().stringWidth((String) Entity.refs[world][ship].getField("DESC").get(null)))/2, Y(650));
        	float speed = Entity.refs[world][ship].getField("SPEED").getFloat(null);
			g.setColor(Player.getBarColor(speed - 0.1f, 1.4f));
			g.fillRect(X(50), Y(380), X(100 * ((speed - 0.1)/1.4)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Speed: " + speed, X(50), Y(405));
			int dmg = Entity.refs[world][ship].getField("DMG").getInt(null);
			g.setColor(Player.getBarColor(dmg - 5, 123));
			g.fillRect(X(50), Y(430), X(100 * ((dmg - 5)/123.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Dmg: " + dmg, X(50), Y(455));
			int hp = Entity.refs[world][ship].getField("HP").getInt(null);
			g.setColor(Player.getBarColor(hp - 10, 90));
			g.fillRect(X(50), Y(480), X(100 * ((hp - 10)/90.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Hp: " + hp, X(50), Y(505));
			int ammos = Entity.refs[world][ship].getField("AMMOS").getInt(null);
			g.setColor(Player.getBarColor(ammos, 12));
			g.fillRect(X(50), Y(530), X(100 * (ammos/12.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Bullets: " + ammos + " per fire", X(50), Y(555));
			float fire = Math.round(10000f/Entity.refs[world][ship].getField("FIRERATE").getInt(null))/10f;
			g.setColor(Player.getBarColor(fire, 3f));
			g.fillRect(X(50), Y(580), X(100 * (fire/3.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Fire Rate: " + (float) Entity.refs[world][ship].getField("FIRERATE").getInt(null)/1000 + " sec/fire", X(50), Y(605));
			/*float reload = Math.round(100000f/Entity.refs[world][ship].getField("RELOAD").getInt(null))/10f;
			g.setColor(Player.getBarColor(reload - 1.7f, 5.3f));
			g.fillRect(X(50), Y(630), X(100 * ((reload - 1.7f)/5.3f)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Reload: " + (Player.refs[ship].getField("RELOAD").getInt(null)/1000.0) + "sec", X(50), Y(655));*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (int i = 0; i < this.getComponentCount(); i ++) {
        	if (this.getComponent(i) instanceof CustomButton) {
        		this.getComponent(i).paint(g);
        	}
        }
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
    private void addButton(String key, String text, int x, int y, int width, int height) {
    	CustomButton b = new CustomButton(text);
    	this.add(b, this.getComponentCount() - 1);
    	binding.put(key, b);
    	b.setButtonLocation(x, y);
		b.setSize(width, height);
		b.setPreferredSize(new Dimension(width, height));
    }
}

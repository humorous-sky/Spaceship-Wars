import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;

public class Inventory extends JPanel{
	
	/**
	 * 
	 */
	private static int ship = Assets.prefs[0];
	private static int ability = Assets.prefs[1];
	//private int level;
	private HashMap<String, Component> binding = new HashMap<String, Component>();
	private static final long serialVersionUID = -215196891130568350L;
	JPanel currentScreen;
	public Inventory() {
		currentScreen = this;
		addButton("ShipSelectButton", "Equip", X(170), Y(680), X(150), Y(50));
		binding.get("ShipSelectButton").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Assets.prefs[0] = ship;
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
		binding.get("ShipSelectButton").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		((CustomButton) binding.get("ShipSelectButton")).setColor(Color.GREEN);
		((CustomButton) binding.get("ShipSelectButton")).setTextColor(Color.WHITE);
		addButton("AbilitySelectButton", "Equip", X(680), Y(680), X(150), Y(50));
		binding.get("AbilitySelectButton").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Assets.prefs[1] = ability;
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
		binding.get("AbilitySelectButton").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		((CustomButton) binding.get("AbilitySelectButton")).setColor(Color.GREEN);
		((CustomButton) binding.get("AbilitySelectButton")).setTextColor(Color.WHITE);
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
				ship = ship < 0 ? Player.refs.length - 1 : ship;		
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
				ship %= Player.refs.length;
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
				ability--;
				ability = ability < 0 ? Ability.refs.length - 1 : ability;		
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
				ability ++;
				ability %= Ability.refs.length;
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
        g.drawString("Select a Ship and Ability", X(260), Y(110));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 38));
        g.drawString(putSpaces(Player.refs[ship].getName()).substring(1), (X(500) - g.getFontMetrics().stringWidth(putSpaces(Player.refs[ship].getName()).substring(1)))/2, Y(330));
        g.drawString(putSpaces(Ability.refs[ability].getName()).substring(1), (X(500) - g.getFontMetrics().stringWidth(putSpaces(Ability.refs[ability].getName()).substring(1)))/2 + X(500), Y(330));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
        g.drawString(Player.descriptions[ship], (X(500) - g.getFontMetrics().stringWidth(Player.descriptions[ship]))/2, Y(650));
        g.drawString(Ability.descriptions[ability], (X(500) - g.getFontMetrics().stringWidth(Ability.descriptions[ability]))/2 + X(500), Y(650));
        g.drawImage(Assets.ships[ship], X(170), Y(380), X(150), Y(210), null);
        try {
        	float speed = Player.refs[ship].getField("SPEED").getFloat(null);
			g.setColor(Player.getBarColor(speed - 4, 6.0));
			g.fillRect(X(50), Y(380), X(100 * ((speed - 4)/6.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Speed: " + speed, X(50), Y(405));
			int dmg = Player.refs[ship].getField("DMG").getInt(null);
			g.setColor(Player.getBarColor(dmg, 8.0));
			g.fillRect(X(50), Y(430), X(100 * (dmg/8.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Dmg: " + dmg, X(50), Y(455));
			int hp = Player.refs[ship].getField("HP").getInt(null);
			g.setColor(Player.getBarColor(hp - 32, 128));
			g.fillRect(X(50), Y(480), X(100 * ((hp - 32)/128.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Hp: " + hp, X(50), Y(505));
			int ammos = Player.refs[ship].getField("AMMOS").getInt(null);
			g.setColor(Player.getBarColor(ammos - 16, 64));
			g.fillRect(X(50), Y(530), X(100 * ((ammos - 16)/64.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Bullets: " + ammos, X(50), Y(555));
			float fire = Math.round(10000.0f/Player.refs[ship].getField("FIRERATE").getInt(null))/10f;
			g.setColor(Player.getBarColor(fire - 6, 14f));
			g.fillRect(X(50), Y(580), X(100 * ((fire - 6)/14.0)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Fire Rate: " + fire + "/sec", X(50), Y(605));
			float reload = Math.round(100000.0f/Player.refs[ship].getField("RELOAD").getInt(null))/10f;
			g.setColor(Player.getBarColor(reload - 1.4f, 5.3f));
			g.fillRect(X(50), Y(630), X(100 * ((reload - 1.4f)/5.3f)), Y(38));
			g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
			g.setColor(Color.WHITE);
			g.drawString("Reload: " + (Player.refs[ship].getField("RELOAD").getInt(null)/1000.0) + "sec", X(50), Y(655));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (int i = 0; i < this.getComponentCount(); i ++) {
        	if (this.getComponent(i) instanceof CustomButton) {
        		this.getComponent(i).paint(g);
        	}
        }
        ((CustomButton) binding.get("ShipSelectButton")).setColor(ship == Assets.prefs[0] ? Color.RED : Color.GREEN);
        ((CustomButton) binding.get("ShipSelectButton")).setText(ship == Assets.prefs[0] ? "Equipped" : "Equip");
        ((CustomButton) binding.get("AbilitySelectButton")).setColor(ability == Assets.prefs[1] ? Color.RED : Color.GREEN);
        ((CustomButton) binding.get("AbilitySelectButton")).setText(ability == Assets.prefs[1] ? "Equipped" : "Equip");
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
    public static String putSpaces(String s) {
    	try {
    		if (Character.toUpperCase(s.charAt(0)) == s.charAt(0)) {
    			return " " + s.charAt(0) + putSpaces(s.substring(1));
    		} else {
    			return s.charAt(0) + putSpaces(s.substring(1));
    		}
    	} catch (Exception e) {
    		return s;
    	}
    }
}

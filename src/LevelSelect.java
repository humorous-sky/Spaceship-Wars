import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;

public class LevelSelect extends JPanel{
	
	/**
	 * 
	 */
	private int world;
	//private int level;
	private HashMap<String, Component> binding = new HashMap<String, Component>();
	private static final long serialVersionUID = -215196891130568350L;
	JPanel currentScreen;
	public LevelSelect(int world) {
		currentScreen = this;
		this.world = world;
		for (int row = 0; row < 4; row ++) {
			for (int col = 1; col <= 6; col ++) {
				int level = 6 * row + col;
				addButton(level + "", level + "", X(150 * col - 50), Y(100 * row + 300), X(50), Y(50));
				binding.get(level + "").addMouseListener(new MouseListener() {
		
					@Override
					public void mouseClicked(MouseEvent e) {
						SpaceshipWars.navigate(currentScreen, new Screen(world, level, Player.createPlayer(Assets.prefs[0], X(500), Y(880), Ability.createAbility(Assets.prefs[1]))));
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
				binding.get(level + "").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
			}
		}
		addButton(25 + "", 25 + "", X(150 * 3 - 50), Y(100 * 4 + 300), X(50), Y(50));
		binding.get(25 + "").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				SpaceshipWars.navigate(currentScreen, new Screen(world, 25, Player.createPlayer(Assets.prefs[0], X(500), Y(880), Ability.createAbility(Assets.prefs[1]))));
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
		binding.get(25 + "").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		addButton(26 + "", 26 + "", X(150 * 4 - 50), Y(100 * 4 + 300), X(50), Y(50));
		binding.get(26 + "").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				SpaceshipWars.navigate(currentScreen, new Screen(world, 26, Player.createPlayer(Assets.prefs[0], X(500), Y(880), Ability.createAbility(Assets.prefs[1]))));
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
		binding.get(26 + "").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		addButton("ReturnButton", "Return", X(380), Y(800), X(240), Y(50));
		binding.get("ReturnButton").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				SpaceshipWars.navigate(currentScreen, new WorldSelect());
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
		binding.get("ReturnButton").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
	    g.fillRect(0, 0, getWidth(), getHeight());
	  //paints the fps number at the top left corner
        g.setColor(Color.white);
        g.setFont(new Font("", Font.ROMAN_BASELINE, 10));
        g.drawString("fps: " + Math.round(SpaceshipWars.fps * 10) / 10.0,  X(5), Y(15));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 100));
        g.drawString("Select a Level", X(280), Y(170));
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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;

public class Settings extends JPanel{
	
	/**
	 * 
	 */
	//private int level;
	private HashMap<String, Component> binding = new HashMap<String, Component>();
	private static final long serialVersionUID = -215196891130568350L;
	JPanel currentScreen;
	public Settings() {
		currentScreen = this;
		addButton("HomeButton", "Return Home", X(380), Y(800), X(240), Y(80));
		binding.get("HomeButton").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Keys.query = -1;
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
		addButton("PreviousFps", "-", X(260), Y(680), X(100), Y(50));
		binding.get("PreviousFps").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Assets.prefs[2] = Assets.prefs[2] >= 44 ? 44 : Assets.prefs[2] + 1;
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
		binding.get("PreviousFps").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		addButton("NextFps", "+", X(640), Y(680), X(100), Y(50));
		binding.get("NextFps").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Assets.prefs[2] = Assets.prefs[2] < -5 ? -6 : Assets.prefs[2] - 1;
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
		binding.get("NextFps").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		for (int i = 0; i <= 6; i ++) {
			int number = i;
			addButton("Key" + i, "Change", X(800), Y(145 + 50 * i), X(100), Y(38));
			binding.get("Key" + i).addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					Keys.query = number;
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
			binding.get("Key" + i).setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 26));
		}
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
        g.drawString("Game Settings", X(380), Y(110));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 38));
        g.drawString(KeyEvent.getKeyText(Assets.keyBinds[0]) + " - Up ", X(260), Y(180));
        g.drawString(KeyEvent.getKeyText(Assets.keyBinds[1]) + " - Left ", X(260), Y(230));
        g.drawString(KeyEvent.getKeyText(Assets.keyBinds[2]) + " - Down ", X(260), Y(280));
        g.drawString(KeyEvent.getKeyText(Assets.keyBinds[3]) + " - Right ", X(260), Y(330));
        g.drawString(KeyEvent.getKeyText(Assets.keyBinds[4]) + " - Toggle Fire ", X(260), Y(380));
        g.drawString(KeyEvent.getKeyText(Assets.keyBinds[5]) + " - Reload Bullets ", X(260), Y(430));
        g.drawString(KeyEvent.getKeyText(Assets.keyBinds[6]) + " - Activate Ability ", X(260), Y(480));
        g.drawString("Fps Limit: " + (Assets.prefs[2] != -6 ? Math.round(10000.0/(Assets.prefs[2] + 6))/10.0 : "No Limit"), (X(1000) - g.getFontMetrics().stringWidth("Fps Limit: " + (Assets.prefs[2] != -6 ? Math.round(10000.0/(Assets.prefs[2] + 6))/10.0 : "No Limit")))/2, Y(690));
        for (int i = 0; i < this.getComponentCount(); i ++) {
        	if (this.getComponent(i) instanceof CustomButton) {
        		this.getComponent(i).paint(g);
        		if (i - 2 == Keys.query && ((CustomButton) this.getComponent(i)).getText().equals("Change")) {
        			((CustomButton) this.getComponent(i)).setText("Type...");
        		} else if (i - 2 != Keys.query &&((CustomButton) this.getComponent(i)).getText().equals("Type...")) {
        			((CustomButton) this.getComponent(i)).setText("Change");
        		}
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

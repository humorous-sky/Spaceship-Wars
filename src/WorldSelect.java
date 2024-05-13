import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;

public class WorldSelect extends JPanel{
	
	/**
	 * 
	 */
	private static int world = 1;
	private static final int maxWorlds = 4;
	private static final String[] names = {"/", "World 1", "World 2", "World 3 (Boss Fight Unavailable)","World 4 (Temporarily Unavailable)"};
	private static final String[] descriptions = {"/", "What a basic world...get them before they swarm you!", "One wrong move and you're doomed...",
	"The best shipmakers in the universe...only if they had better weapons...", "Look! It's a meteor shower! Wait...those are bullets..."};
	//private int level;
	private HashMap<String, Component> binding = new HashMap<String, Component>();
	private static final long serialVersionUID = -215196891130568350L;
	JPanel currentScreen;
	public WorldSelect() {
		currentScreen = this;
		addButton("SelectButton", "Let's Go!", X(330), Y(680), X(340), Y(100));
		binding.get("SelectButton").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				SpaceshipWars.navigate(currentScreen, new LevelSelect(world));
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
		binding.get("SelectButton").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		((CustomButton) binding.get("SelectButton")).setColor(Color.GREEN);
		((CustomButton) binding.get("SelectButton")).setTextColor(Color.WHITE);
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
		addButton("Previous", "←", X(50), Y(260), X(150), Y(330));
		binding.get("Previous").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				world--;
				world = world < 1 ? maxWorlds : world;		
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
		binding.get("Previous").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		addButton("Next", "→", X(800), Y(260), X(150), Y(330));
		binding.get("Next").addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				world ++;
				world = world > maxWorlds ? 1 : world;
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
		binding.get("Next").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
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
        g.drawString("Select a Planet to Conquer", X(230), Y(110));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 38));
        g.drawString(names[world], (X(1000) - g.getFontMetrics().stringWidth(names[world]))/2, Y(330));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 15));
        g.drawString(descriptions[world], (X(1000) - g.getFontMetrics().stringWidth(descriptions[world]))/2, Y(650));
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

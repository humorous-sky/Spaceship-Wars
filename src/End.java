import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;

public class End extends JPanel{
	
	/**
	 * 
	 */
	private HashMap<String, Component> binding = new HashMap<String, Component>();
	private static final long serialVersionUID = -215196891130568350L;
	JPanel currentScreen;
	int stars;
	long score;
	String str = "";
	public End(int world, int level, int stars, long score) {
		currentScreen = this;
		this.stars = stars;
		for (int i = 0; i < stars; i ++) {
			str += "★";
		}
		this.score = score;
		if (!SpaceshipWars.devMode) {
			try {
				Assets.progress[world - 1][level] = stars > 0 && Assets.progress[world - 1][level] == 0 ? 1 : Assets.progress[world - 1][level]; 
			} catch (Exception e) {}
			Assets.progress[world - 1][level - 1] = stars + 1 > Assets.progress[world - 1][level - 1] ? stars + 1 : Assets.progress[world - 1][level - 1];
		}
		addButton("ReturnButton", "Return", X(380), Y(680), X(260), Y(110));
		binding.get("ReturnButton").addMouseListener(new MouseListener() {

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
		binding.get("ReturnButton").setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 38));
		SpaceshipWars.saveData();
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
	    g.fillRect(0, 0, getWidth(), getHeight());
	  //paints the fps number at the top left corner
        g.setColor(Color.white);
        g.setFont(new Font("", Font.ROMAN_BASELINE, 10));
        g.drawString("fps: " + Math.round(SpaceshipWars.fps * 10) / 10.0,  X(5), Y(15));
        g.setFont(new Font("", Font.ROMAN_BASELINE, 130));
        g.drawString(stars == 0 ? "Mission Failed" : "Mission Completed!", (getWidth() - g.getFontMetrics().stringWidth(stars == 0 ? "Mission Failed" : "Mission Completed!"))/2, Y(380));      
        g.setFont(new Font("", Font.ROMAN_BASELINE, 88));
        g.drawString(stars == 0 ? "" : str, (getWidth() - g.getFontMetrics().stringWidth(stars == 0 ? "" : str))/2, Y(500)); 
        g.drawString(stars == 0 ? "" : "Score: " + score, (getWidth() - g.getFontMetrics().stringWidth(stars == 0 ? "" : "Score " + score))/2, Y(580));
        //g.drawImage(SpaceshipWars.img, X(400), Y(430), null);
        for (int i = 0; i < this.getComponentCount(); i ++) {
        	if (this.getComponent(i) instanceof CustomButton) {
        		this.getComponent(i).paint(g);
        	}
        }
        /*try {
			System.out.println(Player.refs[0].getField("SPEED").getFloat(null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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

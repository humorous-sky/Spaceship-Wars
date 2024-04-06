import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class CustomButton extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3588010048527930413L;
	private boolean hover = false;
	private boolean clicked = false;
	private boolean access = false;
	private String text = "";
	private Color color = new Color(255, 255, 255);
	private Color focusColor = new Color(235, 235, 235);
	private Color textColor = new Color(0, 0, 0);
	public CustomButton(String s) {
		super();
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				clicked = true;
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				clicked = false;
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				hover = true;
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hover = false;
				
			}
	
		});
		this.text = s;
	}	
	@Override
	public void setLocation(int x, int y) {
		if (access) {
			super.setLocation(x, y);
			access = false;
		}
	}
	public void setButtonLocation(int x, int y) {
		access = true;
		setLocation(x, y);
	}
	@Override
	public void paint(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(getX(), getY(), getWidth(), getHeight());
		if (hover) {
			g.setColor(focusColor);
			g.fillRect(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2);
		} 
		g.setColor(color);
		g.fill3DRect(getX(), getY(), getWidth(), getHeight(), !clicked);
		g.setColor(textColor);
		g.setFont(getFont());
		g.drawString(text, getX() + (getWidth() - g.getFontMetrics().stringWidth(text))/2, getY() + (getHeight() + g.getFontMetrics().getHeight()/2)/2);
	}
	public String getText() {
		return text;
	}
	public void setText(String s) {
		this.text = s;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getTextColor() {
		return textColor;
	}
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	public Color getFocusColor() {
		return focusColor;
	}
	public void setFocusColor(Color focusColor) {
		this.focusColor = focusColor;
	}
}

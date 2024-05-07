
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener{
      //last key pressed
      public static boolean up;
      public static boolean left;
      public static boolean down;
      public static boolean right;
      public static boolean activate;
      public static int query = -1;
    @Override
      public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
    		//System.out.println(e.getKeyCode());
    		//System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
  	  		if (e.getKeyCode() == Assets.keyBinds[0]) {
  	  			up = true;
			} else if (e.getKeyCode() == Assets.keyBinds[1]) {
  	  			left = true;
			} else if (e.getKeyCode() == Assets.keyBinds[2]) {
  	  			down = true;
			} else if (e.getKeyCode() == Assets.keyBinds[3]) {
  	  			right = true;
			} else if (e.getKeyCode() == Assets.keyBinds[4]) {
  	  			if (SpaceshipWars.s instanceof Screen) {
  	  				Screen.plr.fire = !Screen.plr.fire;
  	  			}
			} else if (e.getKeyCode() == Assets.keyBinds[5]) {
  	  			if (SpaceshipWars.s instanceof Screen) {
  	  				Screen.plr.reload();
  	  			}
			} else if (e.getKeyCode() == Assets.keyBinds[6]) {
  	  			activate = true;
			}
    		if (query >= 0) {
    			Assets.keyBinds[query] = e.getKeyCode();
    			query = -1;
    		}
      }

      @Override
      public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
    	  if (e.getKeyCode() == Assets.keyBinds[0]) {
	  			up = false;
			} else if (e.getKeyCode() == Assets.keyBinds[1]) {
	  			left = false;
			} else if (e.getKeyCode() == Assets.keyBinds[2]) {
	  			down = false;
			} else if (e.getKeyCode() == Assets.keyBinds[3]) {
	  			right = false;
			} else if (e.getKeyCode() == Assets.keyBinds[4]) {
	  			activate = false;
			} 
      }

      @Override
      public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

      }

}

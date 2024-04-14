
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener{
      //last key pressed
      public static boolean up;
      public static boolean left;
      public static boolean down;
      public static boolean right;
      public static boolean activate;
    @Override
      public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
    		//System.out.println(e.getKeyCode());
    		switch(e.getKeyCode()) {
  	  	  		case 38: case 87:
  	  	  			up = true;
  	  	  			break;
  	  	  		case 37: case 65:
  	  	  			left = true;
  	  	  			break;
  	  	  		case 40: case 83:
  	  	  			down = true;
  	  	  			break;
  	  	  		case 39: case 68:
  	  	  			right = true;
  	  	  			break;
  	  	  		case 10: case 88:
  	  	  			activate = true;
  	  	  			break;
  	  	  		case 47: case 82:
  	  	  			Screen.plr.reload();
  	  	  			break;
  	  	  		case 32:
  	  	  			Screen.plr.fire = !Screen.plr.fire;
  	  	  			break;
    		}	  
      }

      @Override
      public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
    	  switch(e.getKeyCode()) {
    	  	  case 38: case 87:
    	  		  up = false;
    	  		  break;
    	  	  case 37: case 65:
    	  		  left = false;
    	  		  break;
    	  	  case 40: case 83:
    	  		  down = false;
    	  		  break;
    	  	  case 39: case 68:
    	  		  right = false;
    	  		  break;
    	  	  case 10: case 88:
    	  		  activate = false;
    	  		  break;
    	  } 
      }

      @Override
      public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

      }

}

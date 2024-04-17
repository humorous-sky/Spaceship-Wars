

import java.awt.Dimension; 
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpaceshipWars {
    //detection of user screen resolution 
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static final double width = screenSize.getWidth();
    static final double height = screenSize.getHeight();
    static int limit = 6; 
    //sets up fps calculation 
    static int frames = 0;
    static long lastUpdate = System.currentTimeMillis();
    static long startTime = System.currentTimeMillis();
    private static long lastNavigate = System.currentTimeMillis() - 300;
    static double fps = 15.0;
    static BufferedImage img;
    static JPanel s;// = new Screen(1, 26, new Basic(Screen.X(500), Screen.Y(880)));
    static JPanel queue;
    //initalizes the frame
    static JFrame frame = new JFrame();
    public static void main(String[] args) {
    	s = new Start();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) width, (int) height);
        frame.setLocation(0, 0);
        frame.setName("Spaceship Wars");
        frame.setTitle("Spaceship Wars");
        new Assets();
        Entity.run();
        URL resourceURL = SpaceshipWars.class.getResource("images/Fighter.png"); 
        System.out.println(resourceURL);
        try {
            //img = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Birb.png"));
            img = ImageIO.read(resourceURL);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        frame.setIconImage(img);
        frame.addKeyListener(new Keys());
        //frame.addMouseListener(new Mouse());
        frame.setVisible(true);
        //initalizes the screen
        frame.add(s);
        //main game loop
        while (true) { 
              //updates fps every 166 milliseconds
              if (System.currentTimeMillis() >= lastUpdate + 166) {
                      //calculates fps
                      fps = frames/((System.currentTimeMillis() - lastUpdate)/1000.0);
                      lastUpdate = System.currentTimeMillis(); 
                      frames = 0;
              }
              //paints the screen
              s.repaint();
              frames ++;
              
              //fps limit of 30 fps (waits 33 milliseconds before painting next screen)
              try {
            	  /*if (s instanceof Start) {
            		  Thread.sleep(100);
            	  } else {*/
            		  Thread.sleep(limit);
            	  //}
              } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
              } 
              if (queue != null) {
            	  frame.add(queue);
            	  //updates screen
                  frame.setVisible(true);
            	  SpaceshipWars.s = queue;
            	  queue = null;
              }
              //System.out.println(frame.getComponentCount());
         }
    }
    public static void navigate(JPanel from, JPanel to) {
    	if (System.currentTimeMillis() - 300 >= lastNavigate) {
    		lastNavigate = System.currentTimeMillis();
    		from.removeAll();
    		queue = queue == null ? to : queue;
    	}
    }
}


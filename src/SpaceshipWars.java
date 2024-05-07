

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
    static double width = screenSize.getWidth();
    static double height = screenSize.getHeight();
    //sets up fps calculation 
    static int frames = 0;
    static long lastUpdate = System.currentTimeMillis();
    static long startTime = System.currentTimeMillis();
    private static long lastNavigate = System.currentTimeMillis() - 1000;
    static double fps = 15.0;
    static BufferedImage img;
    static JPanel s;// = new Screen(1, 26, new Basic(Screen.X(500), Screen.Y(880)));
    static JPanel queue;
    //initalizes the frame
    static JFrame frame = new JFrame();
    public static void main(String[] args) {
    	s = new Loading();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) width, (int) height);
        frame.setLocation(0, 0);
        frame.setName("Spaceship Wars");
        frame.setTitle("Spaceship Wars");
        System.out.println(System.getProperty("user.name"));
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
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Saving Data...");
                Assets.writeInts("Key Binds", Assets.keyBinds);
                Assets.writeInts("prefs", Assets.prefs);
                for (int i = 0; i < Assets.progress.length; i ++) {
        			Assets.writeInts("World" + (i + 1), Assets.progress[i]);
        			System.out.println("World " + (i + 1) + " Status:");
        			for (int n : Assets.progress[i]) {
        				System.out.print(n + " ");
        			}
        			System.out.println();
        		}
                System.out.println("Data Successfully Saved.");
                System.exit(0);
            }
        });
        //main game loop
        while (true) { 
        	//width = frame.getWidth();
        	//height = frame.getHeight();
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
            		  Thread.sleep(s instanceof Loading ? 100 : Assets.prefs[2] + 6);
            	  //}
              } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
              } 
              if (queue != null) {
            	  frame.getContentPane().removeAll();
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
    	if (System.currentTimeMillis() - 1000 >= lastNavigate) {
    		lastNavigate = System.currentTimeMillis();
    		from.removeAll();
    		queue = queue == null ? to : queue;
    	}
    }
}


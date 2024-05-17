

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Assets {
	public static BufferedImage[] ships;
	public static BufferedImage[] misc;
	public static final int totalImages = Entity.refs.length * Entity.refs[0].length + 3 + Player.refs.length + 3 /*miscellaneous*/;
	public static int loaded = 0;
	public static int[] prefs;
	public static int[][] progress;
	public static int[] keyBinds;
	public static final Clip gunFire = newSound("gun.wav");
	public static final Clip bulletHit = newSound("hit.wav");
	public static final Clip reload = newSound("reload.wav");
	public static final Clip explode = newSound("explode.wav");
	public Assets() {
		
	}
	public static BufferedImage newImage(String name) {
		try {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			URL url = SpaceshipWars.class.getResource("images/" + name);
			BufferedImage img = ImageIO.read(url);
			System.out.println(url + " successfully loaded.");
			loaded += loaded < totalImages ? 1 : 0;
			System.out.println(loaded + " images loaded.");
			playSound(gunFire, 100);
			return img;
		} catch (Exception e) {
			if (name.equals("MissingTexture.png")) {
				System.out.println("Cannot find " + name);
				System.exit(0);
			}
			return newImage("MissingTexture.png");
		}
	}
	public static Clip newSound(String name) {
		try {
			URL url = SpaceshipWars.class.getResource("sounds/" + name);
			AudioInputStream in = AudioSystem.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip(); 
			clip.open(in);
			return clip;
		} catch (NullPointerException e) {
			return newSound("gun.wav");
		} catch (UnsupportedAudioFileException e) {
			System.out.println(name + " is unsupported");
			System.exit(0);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot find " + name);
			System.exit(0);
			return null;
		} catch (LineUnavailableException e) {
			System.out.println(name + "'s line is unavailable");
			System.exit(0);
			return null;
		}
	}
	public static void loadImages() {
		System.out.println("Loading Data..."); 
		progress = new int[Entity.refs.length][25];
		for (int i = 0; i < progress.length; i ++) {
			progress[i] = readInts("World" + (i + 1), 25);
			progress[i][0] = progress[i][0] == 0 ? 1 : progress[i][0];
			System.out.println("World " + (i + 1) + " Status:");
			for (int n : progress[i]) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
		prefs = readInts("prefs", 4);
		if ((prefs[3] - 60) % 80 != 0) {
			prefs[3] = 220;
		}
		keyBinds = readInts("Key Binds", 7);
		System.out.println("Loading Players...");
		ships = new BufferedImage[]{newImage("Basic.png"), newImage("Speedy.png"), newImage("Tank.png"), newImage("Melee.png")};
		System.out.println("Loading Miscellaneous...");
		misc = new BufferedImage[]{newImage("Support.png"), newImage("Rage.png"), newImage("ShieldOrb.png")};
	}
	public static void loadSounds() {
		System.out.println("Loading Sound FX...");
	}
	public static void playSound(Clip clip, int volume) {
		clip.stop();
		volume += 2;
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = 0;
		if ((range/3 * volume) + gainControl.getMinimum() <= gainControl.getMaximum()) {
			gain = (range/5f * volume) + gainControl.getMinimum();
		} else {
			gain = gainControl.getMaximum();
		}
		gainControl.setValue(gain);
		clip.setFramePosition(0);
		clip.setMicrosecondPosition(0);
		for (int i = 0; !clip.isActive(); i ++) {
			if (i > Assets.prefs[3]) {
				System.err.println("Unable to play sound");
				break;
			}
			clip.start();
		}
	}
	public static long[] readLongs(String fileName, int length) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data\\" + fileName + ".bin"));
		} catch (FileNotFoundException e) {
			writeLongs(fileName, new long[length]);
			return new long[length];
		}
		long[] res = new long[length];
		try {
		    while (length > 0) {
		        res[length - 1] = in.readLong();
		        length--;
		    }
		    in.close();
		    return flip(res);
		} catch (EOFException ignored) {
			System.out.println("[EOF]");
			DataOutputStream out = null;
			try {
				out = new DataOutputStream(new FileOutputStream("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data\\" + fileName + ".bin"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (length > 0) {
				try {
					out.writeLong(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        length--;
		    }
		    return flip(res);
		} catch (IOException e) {
			return flip(res);
		}

	}
	public static void writeLongs(String fileName, long[] values) {
		new File("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data\\" + fileName + ".bin").delete();
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data\\" + fileName + ".bin"));
			for (int i = 0; i < values.length; i ++) {
				try {
					out.writeLong(values[i]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			File f1 = new File("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data");  
		      //Creating a folder using mkdir() method  
		      boolean bool = f1.mkdir();  
		      if(bool){  
		         System.out.println("Folder is created successfully");  
		         writeLongs(fileName, values);
		      }else{  
		         System.out.println("Error Found!");  
		         System.exit(0);
		      }  
		}
	}	
	public static int[] readInts(String fileName, int length) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data\\" + fileName + ".bin"));
		} catch (FileNotFoundException e) {
			writeInts(fileName, new int[length]);
			return new int[length];
		}
		int[] res = new int[length];
		try {
		    while (length > 0) {
		        res[length - 1] = in.readInt();
		        length--;
		    }
		    in.close();
		    return flip(res);
		} catch (EOFException ignored) {
			System.out.println("[EOF]");
			DataOutputStream out = null;
			try {
				out = new DataOutputStream(new FileOutputStream("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data\\" + fileName + ".bin"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (length > 0) {
				try {
					out.writeInt(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        length--;
		    }
		    return flip(res);
		} catch (IOException e) {
			return flip(res);
		}
	}
	public static void writeInts(String fileName, int[] values) {
		new File("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data\\" + fileName + ".bin").delete();
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data\\" + fileName + ".bin"));
			for (int i = 0; i < values.length; i ++) {
				try {
					out.writeInt(values[i]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			File f1 = new File("C:\\Users\\" + System.getProperty("user.name") + "\\SpaceshipWars Data");  
		      //Creating a folder using mkdir() method  
		      boolean bool = f1.mkdir();  
		      if(bool){  
		         System.out.println("Folder is created successfully");  
		         writeInts(fileName, values);
		      }else{  
		         System.out.println("Error Found!");  
		         System.exit(0);
		      }  
		}
	}	
	public static int[] flip(int[] values) {
		int[] res = new int[values.length];
		for (int i = 0; i < values.length; i ++) {
			res[values.length - i - 1] = values[i];
		}
		return res;
	}
	public static long[] flip(long[] values) {
		long[] res = new long[values.length];
		for (int i = 0; i < values.length; i ++) {
			res[values.length - i - 1] = values[i];
		}
		return res;
	}
}


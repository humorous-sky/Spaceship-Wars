

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
	public static final int totalImages = Entity.refs.length * Entity.refs[0].length + 3 + Player.refs.length + 3;
	public static int loaded = 0;
	public static int[] prefs = readInts("prefs", 2);
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
			return img;
		} catch (Exception e) {
			System.out.println("Cannot find " + name);
			System.exit(0);
			return null;
		}
	}
	public static Clip newSound(String name) {
		try {
			AudioInputStream in = AudioSystem.getAudioInputStream(new File(SpaceshipWars.class.getResource("sounds/" + name).getPath()).getAbsoluteFile());
			Clip clip = AudioSystem.getClip(); 
			clip.open(in); 
			return clip;
		} catch (UnsupportedAudioFileException e) {
			System.out.println(name + " is unsupported");
			System.exit(0);
			return null;
		} catch (IOException e) {
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
		System.out.println("Loading Players...");
		ships = new BufferedImage[]{newImage("Basic.png"), newImage("Speedy.png"), newImage("Tank.png")};
		System.out.println("Loading Miscellaneous...");
		misc = new BufferedImage[]{newImage("Support.png"), newImage("Rage.png"), newImage("ShieldOrb.png")};
	}
	public static void loadSounds() {
		System.out.println("Loading Sound FX...");
		ships = new BufferedImage[]{newImage("Basic.png"), newImage("Speedy.png"), newImage("Tank.png")};
		System.out.println("Loading Miscellaneous...");
		misc = new BufferedImage[]{newImage("Support.png"), newImage("Rage.png"), newImage("ShieldOrb.png")};
	}
	public static void playSound(Clip clip, int volume) {
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
		clip.stop();
		clip.setMicrosecondPosition(0);
		clip.start();
	}
	public static long[] readLongs(String fileName, int length) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(fileName + ".bin"));
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
				out = new DataOutputStream(new FileOutputStream(fileName + ".bin"));
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
		new File(fileName + ".bin").delete();
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream(fileName + ".bin"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = values.length - 1; i >= 0; i --) {
			try {
				out.writeLong(values[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	public static int[] readInts(String fileName, int length) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(fileName + ".bin"));
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
				out = new DataOutputStream(new FileOutputStream(fileName + ".bin"));
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
		new File(fileName + ".bin").delete();
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream(fileName + ".bin"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < values.length; i ++) {
			try {
				out.writeInt(values[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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


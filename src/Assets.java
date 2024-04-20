

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Assets {
	public static BufferedImage[] ships;
	public static final int totalImages = Entity.refs.length * Entity.refs[0].length + 3 + Player.refs.length;
	public static int loaded = 0;
	public Assets() {
		
	}
	public static BufferedImage newImage(String name) {
		try {
			try {
				Thread.sleep(300);
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
			AudioInputStream in = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "\\sounds\\" + name).getAbsoluteFile());
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
}


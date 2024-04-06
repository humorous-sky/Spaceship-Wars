

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Assets {
	public static final BufferedImage[] ships = {newImage("Basic.png"), newImage("Speedy.png")};
	public Assets() {

	}
	public static BufferedImage newImage(String name) {
		try {
			return ImageIO.read(SpaceshipWars.class.getResource("images/" + name));
		} catch (IOException e) {
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


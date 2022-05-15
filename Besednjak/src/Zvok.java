import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Zvok {

	Clip clip;
	URL zvokURL[] = new URL[3];
	
	public Zvok() {
		zvokURL[0] = getClass().getResource("zmaga.wav");
		zvokURL[1] = getClass().getResource("poraz.wav");
		zvokURL[2] = getClass().getResource("novo_okno.wav");
	}
	
	public void setFile(int i) throws UnsupportedAudioFileException, IOException {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(zvokURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} 
		catch (LineUnavailableException e) {
		}	
	}
	
	
	public void play() {
		clip.start();
	}
	
	
	
}

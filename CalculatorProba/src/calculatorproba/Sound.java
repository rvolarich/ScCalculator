package calculatorproba;

import java.io.File;
import java.util.Objects;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	Clip clip;
	File file;
	float floatVolume;
	int [] tempIntArray = new int[3];
	
	
	private void setClipVolume(int volume) {
		
		if(volume > 80 && volume <= 100) {
		floatVolume = ((float) volume - 80) * 0.3f;
		}
		else if(volume == 80) {
			
			floatVolume = 0f;
		}
		else if(volume < 80 && volume > 0) {
			
			floatVolume = ((float) volume - 80) * 0.633f;
		}
		else if(volume == 0) {
			
			floatVolume = -80f;
		}
		
		
	}
	
	Sound(String sample, int volume){
		
		try {
			
		file = new File(sample);
		setClipVolume(volume);
		AudioInputStream sound = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(sound);
		clip.setFramePosition(0);
		setVolume(clip, floatVolume);
		clip.start();
		
		}
		catch(Exception e) {
		}
	}
	
	public static void setVolume(Clip clip, float levelTemp) {
	    
	    FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    float level = levelTemp;
	        volume.setValue(level);     
	   
	}
}

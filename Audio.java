
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;




public class Audio {
	Clip bonk;
	String filePath;
	File soundfile;
	AudioInputStream ais;
	
	public Audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		filePath = "C:\\Users\\omer2\\Dropbox\\Java\\My Game\\src\\bonk.wav";
		bonk = AudioSystem.getClip();
		soundfile = new File(filePath);
	    ais = AudioSystem.getAudioInputStream(soundfile);
	    bonk.open(ais);
	    bonk.start();
	}
	
	public void bonk()
	{
	     bonk.start();
	}
	
	public void reset() throws LineUnavailableException, IOException, UnsupportedAudioFileException
	{
		bonk.close();
		ais = AudioSystem.getAudioInputStream(soundfile);
	    bonk.open(ais);
        bonk.setMicrosecondPosition(0); 
	}

}

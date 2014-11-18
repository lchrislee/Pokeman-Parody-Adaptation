import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Test {

	public static void run(){
		File url = new File(
	            "Forget_You.wav");
	        Clip clip;
			try {
				clip = AudioSystem.getClip();
				 AudioInputStream ais = AudioSystem.
				            getAudioInputStream( url );
				        clip.open(ais);
				        clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // getAudioInputStream() also accepts a File or InputStream
	}
	
	public static void main(String[] args) throws Exception {
        Test.run();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });
    }

}
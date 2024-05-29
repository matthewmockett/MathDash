import javax.sound.sampled.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;

/**
 * AudioPlayer allows for playing, pausing, and stopping an audio file.
 * It also provides the functionality to adjust the volume of the playback.
 * 
 * @author Vincent
 */

public class AudioPlayer {
    private Clip clip; // Clip object to control audio playback
    private boolean isPlaying = false;

    
    /**
     * Constructs an AudioPlayer by loading an audio file.
     *
     * @param filePath The path to the audio file that will be played.
     */
    
    public AudioPlayer(String filePath) {
        try {
            // Create an AudioInputStream from the provided file path

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the audio is currently playing
     *
     * @return true if the audio is playing, false otherwise
     */
    
    public boolean isPlaying() {
        return isPlaying;
    }
    
    /**
     * Toggles the playback of the audio. If audio is playing, it pauses.
     * If its paused, start playing.
     */
    
    public void togglePlayPause() {
        if (isPlaying) {
            clip.stop(); // Stop the audio if it is currently playing
        } else {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Play the audio continuously if its not playing
        }
        isPlaying = !isPlaying;
    }

    /**
     * Stops the audio playback and resets the audio clip to beginning
     */
    
    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
        isPlaying = false;
    }
    
    /**
     * Sets the volume of the audio playback
     *
     * @param volume The volume level to set 
     */
    
    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // Reduce volume by a number of decibels.
        }
    }
    
}

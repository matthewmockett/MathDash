import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * The {@code SoundUtils} class provides utility methods for playing sound files.
 * This class utilizes the {@code javax.sound.sampled} package to open and play audio files.
 * <p>
 * It currently includes a method to play sound from a file, which supports various audio formats
 * recognized by the {@link javax.sound.sampled.AudioSystem}.
 * </p>
 * 
 * Example usage:
 * <pre>
 *     SoundUtils.playSound("notification.wav");
 * </pre>
 * 
 * Note: This class handles basic audio playback functionality and does not provide
 * controls for stopping or pausing the playback.
 * 
 * @author team42
 */
public class SoundUtils {

      /**
     * Plays an audio file specified by the {@code soundFileName} parameter. The method attempts to open
     * the audio file as an {@link AudioInputStream}, obtain a {@link Clip} for playing back the audio,
     * and then starts playback.
     * <p>
     * In case the specified audio file format is not supported, an error occurs during file reading,
     * or if the audio line is unavailable, an error message is printed to {@code System.err}.
     * </p>
     * 
     * @param soundFileName the path and name of the audio file to be played
     * 
     * @throws UnsupportedAudioFileException if the specified audio file format is not supported.
     *         This exception is caught and handled within the method, with an error message printed
     *         to {@code System.err}.
     * @throws IOException if an I/O error occurs during reading of the audio file.
     *         This exception is caught and handled within the method, with an error message printed
     *         to {@code System.err}.
     * @throws LineUnavailableException if a line matching the {@link Clip} object's requirements
     *         is not available due to resource restrictions. This exception is caught and handled
     *         within the method, with an error message printed to {@code System.err}.
     */
    public static void playSound(String soundFileName) {
        try {
            // Open an audio input stream.
            File soundFile = new File(soundFileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();

       
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Audio file format not supported: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading audio file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Audio line unavailable: " + e.getMessage());
        }
    }
    
}

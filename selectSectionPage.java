import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The {@code selectSectionPage} class extends {@code JFrame} to create a window where
 * the user can select the section of the game they want to play. This window includes
 * buttons for different game sections like Addition, Subtraction, Multiplication, and Division,
 * along with a background image and shortcuts for pausing, saving, and muting the game.
 * 
 * This class manages user interaction with the game's main menu, allowing for navigation
 * between game states and the control of background music playback.
 * 
 * @author team42
 */
public class selectSectionPage extends JFrame {
    private JFrame mainPage;
    private ImageIcon backgroundImage;
    private Player player;
    private AudioPlayer backgroundMusicPlayer; // Assume AudioPlayer is accessible

    /**
     * Constructs a new {@code selectSectionPage} frame with a specified main page, player,
     * and background music player. It initializes the frame with a background image, section
     * buttons, and additional functionality buttons like back, pause, save, and mute.
     * 
     * @param mainPage The main JFrame that should be visible when navigating back from this frame.
     * @param player The player instance representing the current user.
     * @param backgroundMusicPlayer The AudioPlayer instance to control background music.
     */
    public selectSectionPage(JFrame mainPage, Player player, AudioPlayer backgroundMusicPlayer) {
        // Load the background image
        this.mainPage = mainPage;
        this.player = player;
        this.backgroundMusicPlayer = backgroundMusicPlayer; // Pass the AudioPlayer instance

        backgroundImage = new ImageIcon(getClass().getResource("selectmodefinal.png")); // Adjust path as necessary

        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // We'll place components manually
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        setLocationRelativeTo(null);


        // Add section buttons
        addSectionButton("Addition",60 , 380, 150, 50); // Adjust x, y, width, height to fit your UI
        addSectionButton("Subtraction", 250, 380, 150, 50);
        addSectionButton("Multiplication", 440, 380, 150, 50);
        addSectionButton("Division", 615, 380, 150, 50);
        addBackButton();
        setupPauseShortcut();
        setUpSaveShortCut();
        setupMuteShortcut();
        
        revalidate();
        repaint();

    }
    
  	 /**
     * Adds a section button to the frame. Each button is associated with a game section
     * and, when clicked, navigates to the corresponding difficulty selection page.
     * 
     * @param text The text to display on the button.
     * @param x The x coordinate of the button.
     * @param y The y coordinate of the button.
     * @param width The width of the button.
     * @param height The height of the button.
     */
    private void addSectionButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSectionPage.this.setVisible(false);
                new selectDifficultyPage(selectSectionPage.this, text, player, backgroundMusicPlayer).setVisible(true);
            }
        });
        add(button);
    }

    /**
     * Adds a "Back" button to the frame. When clicked, this button makes the current
     * frame invisible and shows the main page frame.
     */
    private void addBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30); // Adjust as needed
        
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSectionPage.this.setVisible(false); // Hide the select section page
                mainPage.setVisible(true); // Show the main page again
            }
        });
        add(backButton);
    }

    /**
     * Sets up a keyboard shortcut for pausing the game. When the pause key is pressed,
     * the background music is toggled between play and pause, and a dialog is displayed
     * to the user indicating the paused state.
     */
    private void setupPauseShortcut() {
        KeyStroke pauseKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_P, 0);
        Action pauseAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePause();
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pauseKeyStroke, "pauseGame");
        getRootPane().getActionMap().put("pauseGame", pauseAction);
    }

    /**
     * Sets up a keyboard shortcut for saving the game. When the save key is pressed,
     * the game's current state is saved, and a dialog is shown to the user indicating
     * the save action.
     */
    private void setUpSaveShortCut() {
        KeyStroke pauseKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        Action pauseAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	saveGame();
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pauseKeyStroke, "saveGame");
        getRootPane().getActionMap().put("saveGame", pauseAction);
    }

    /**
     * Sets up a keyboard shortcut for muting the game sound. When the mute key is pressed,
     * the background music is toggled between play and pause, and a dialog is displayed
     * to the user indicating the mute state.
     */
    private void setupMuteShortcut() {
        KeyStroke pauseKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_M, 0);
        Action pauseAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 muteSound();
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pauseKeyStroke, "mute Sound");
        getRootPane().getActionMap().put("mute Sound", pauseAction);
    }

    /**
     * Saves the game state. This method is called when the save keyboard shortcut is activated.
     * It shows a non-modal dialog to the user indicating that the game is saved.
     */
    private void saveGame() {
	    // Toggle play/pause state of the background music
		JOptionPane pane = new JOptionPane("The game is saved", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = pane.createDialog(this, "Save Game");
        dialog.setModal(false); // Non-modal dialog allows interaction with other parts of the application

        JButton unpauseButton = new JButton("OK");
        unpauseButton.addActionListener(e -> {
            dialog.dispose();
        });

        pane.add(unpauseButton);
        dialog.setVisible(true);
	}

    /**
     * Toggles the pause state of the game. This method is called when the pause keyboard shortcut
     * is activated. It pauses or unpauses the background music and shows a non-modal dialog
     * to the user indicating the game's paused state.
     */
    private void togglePause() {
	    // Toggle play/pause state of the background music
	    backgroundMusicPlayer.togglePlayPause();
	    
	    if (!backgroundMusicPlayer.isPlaying()) {
	        JOptionPane pane = new JOptionPane("The game is paused", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
	        JDialog dialog = pane.createDialog(this, "Game Paused");
	        dialog.setModal(false); // Non-modal dialog allows interaction with other parts of the application

	        JButton unpauseButton = new JButton("Unpause");
	        unpauseButton.addActionListener(e -> {
	            dialog.dispose();
	            togglePause(); // Toggle the pause state back when Unpause is clicked
	        });

	        pane.add(unpauseButton);
	        dialog.setVisible(true);
	    }
	}

    /**
     * Toggles the mute state of the game sound. This method is called when the mute keyboard shortcut
     * is activated. It mutes or unmutes the background music and shows a non-modal dialog
     * to the user indicating the mute state.
     */
    private void muteSound() {
	    backgroundMusicPlayer.togglePlayPause();
	    // Toggle play/pause state of the background music
	    String mute = (backgroundMusicPlayer.isPlaying() ? "Unmuted" : "Muted");
		JOptionPane pane = new JOptionPane(mute + " the music", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = pane.createDialog(this, "Muted Sound");
        dialog.setModal(false); // Non-modal dialog allows interaction with other parts of the application

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dialog.dispose();
        });

        pane.add(okButton);
        dialog.setVisible(true);
	}
    
    /**
     * Overrides the {@code paint} method to draw the background image on the frame.
     * 
     * @param g The {@code Graphics} object to be used for drawing operations.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this);
    }

}

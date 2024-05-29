import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The {@code selectDifficultyPage} class extends {@code JFrame} to create a window where
 * the user can select the difficulty level for the game. This window includes buttons for
 * different difficulty levels, along with a background image and shortcuts for pausing,
 * saving, and muting the game.
 * <p>
 * This class facilitates navigation between different game states, allowing the user to
 * choose a game difficulty and controlling the playback of background music.
 * </p>
 * 
 * @author team42
 */
public class selectDifficultyPage extends JFrame {
    private Frame previousPage;
    private ImageIcon backgroundImage;
    private String selectedSection;
    private Player player;
    private AudioPlayer backgroundMusicPlayer; // Assume AudioPlayer is accessible

    /**
     * Constructs a new {@code selectDifficultyPage} frame with the specified previous page,
     * selected section, player, and background music player. It initializes the frame with a
     * background image and difficulty level buttons.
     * 
     * @param previousPage The previous Frame that should be visible when navigating back.
     * @param selectedSection The game section that has been selected.
     * @param player The player instance representing the current user.
     * @param backgroundMusicPlayer The AudioPlayer instance for controlling background music.
     */
    public selectDifficultyPage(Frame previousPage, String selectedSection, Player player, AudioPlayer backgroundMusicPlayer) {
        this.previousPage = previousPage;
        this.selectedSection = selectedSection;
        this.player = player;
        this.backgroundMusicPlayer = backgroundMusicPlayer; // Pass the AudioPlayer instance
        
        backgroundImage = new ImageIcon(getClass().getResource("selectlevelfinal.png")); // Adjust path as necessary
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        setLocationRelativeTo(null);

       
        addLevelButton("Level 1 ", 210, 200, 100, 20);
        addLevelButton("Level 2 ", 337, 200, 100, 20);
        addLevelButton("Level 3 ", 464, 200, 100, 20);
        addLevelButton("Level 4 ", 591, 200, 100, 20);
        addLevelButton("Level 5 ", 718, 200, 100, 20);        
        addLevelButton("Level 6 ", 95, 410, 100, 20);
        addLevelButton("Level 7 ", 225, 410, 100, 20);
        addLevelButton("Level 8 ", 355, 410, 100, 20);
        addLevelButton("Level 9", 485, 410, 100, 20);
        addLevelButton("Level 10 ", 625, 410, 100, 20);
       
        setupPauseShortcut();
        setUpSaveShortCut();
        setupMuteShortcut();
        
        addBackButton();
        
    }

    /**
     * Adds a button for each difficulty level to the frame. Each button, when clicked, checks if the
     * level is unlocked and either navigates to the game page for the selected level or shows a message
     * dialog indicating the level is locked.
     * 
     * @param text The text to display on the button, indicating the level.
     * @param x The x coordinate of the button.
     * @param y The y coordinate of the button.
     * @param width The width of the button.
     * @param height The height of the button.
     */
    private void addLevelButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text.trim());
        int level = Integer.parseInt(text.trim().split(" ")[1]); // Extract level number from button text
        

        button.setBounds(x, y, width, height);

        if ((player.isLevelUnlocked(selectedSection, level)) == false) {
        	button.setOpaque(false);
        	button.setContentAreaFilled(false);
        	button.setBorderPainted(false);
        	button.setFocusPainted(false); // Ensure the focus border is not painted
        }
        
        button.addActionListener(e -> {
            // Check if the level is unlocked before proceeding
            if (player.isLevelUnlocked(selectedSection, level)) {
                selectDifficultyPage.this.setVisible(false);
                // Proceed to the game page with the selected level
                new GamePage(selectDifficultyPage.this, selectedSection, level, player, backgroundMusicPlayer).setVisible(true);
            } else {
                // Show message dialog if the level is not yet unlocked
            	
                JOptionPane.showMessageDialog(selectDifficultyPage.this, "Please complete the previous level to proceed.");
            }
        });

        add(button); // Add the button to the frame
    }

    /**
     * Adds a "Back" button to the frame. When clicked, this button makes the current frame invisible
     * and shows the previous page frame.
     */
    private void addBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDifficultyPage.this.setVisible(false);
                previousPage.setVisible(true);
            }
        });
        add(backButton);
    }

    /**
     * Sets up a keyboard shortcut for pausing the game. When the pause key is pressed, it toggles the
     * pause state and displays a dialog indicating the game is paused.
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
     * Sets up a keyboard shortcut for saving the game. When the save key is pressed, it saves the
     * current game state and displays a dialog indicating the game is saved.
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
     * Sets up a keyboard shortcut for muting the game sound. When the mute key is pressed, it toggles
     * the mute state of the background music and displays a dialog indicating the music is muted or unmuted.
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
     * Saves the game state. This method is called when the save keyboard shortcut is activated,
     * showing a dialog to the user that the game has been saved.
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
     * is activated, pausing or unpausing the background music and showing a dialog to the user.
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
     * Mutes or unmutes the game sound. This method is called when the mute keyboard shortcut
     * is activated, toggling the play/pause state of the background music and showing a dialog.
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
     * @param g The {@code Graphics} object used for drawing operations.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this);
    }
}

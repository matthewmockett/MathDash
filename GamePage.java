import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The {@code GamePage} class extends {@code JFrame} and represents the game play screen where
 * players can interact with the game by answering questions, viewing their score, and racing against an AI opponent.
 * It handles the display of questions, scoring, and progression of the player and AI cars along the race track.
 *
 *
 * @ author team42
 */
public class GamePage extends JFrame {
    private Frame previousPage;
    private ImageIcon backgroundImage;
    private MathQuestionGenerator questionGenerator;
    private Question currentQuestion;
    private JButton backButton, displayQuestionButton ;
    static int correctAnswers = 0;
    static int incorrectAnswers = 0;
    private JTextArea scoreArea;
    private PlayerRaceTrack playerinfo;
    private CpuRaceTrack cpuinfo;
    private Player player;
    private String playerSection;
    private int playerCarXPosition;
    private int aiCarXPosition;
    private static final int Y_POSITION = 118; 
    private ImageIcon playerCarImage;
    private ImageIcon aiCarImage;
    private AudioPlayer backgroundMusicPlayer; 
    private Timer gameTimer;
    
    private int remainingTimeInSeconds; // 3 minutes
    private JLabel timerLabel;
    private boolean isPaused = false; // Add this as a class member to track the pause state


    /**
     * Constructs a new {@code GamePage} with specific game settings.
     * 
     * @param previousPage The frame to return to when exiting the game.
     * @param section The math section selected for the game.
     * @param level The difficulty level selected for the game.
     * @param Player The current player.
     * @param backgroundMusicPlayer The audio player for background music.
     */
    public GamePage(Frame previousPage, String section, int level, Player Player, AudioPlayer backgroundMusicPlayer) {
        this.previousPage = previousPage;
        this.backgroundImage = new ImageIcon(getClass().getResource("racetrackbackgroundfinal.png")); // Adjust path as necessary
        this.backgroundMusicPlayer = backgroundMusicPlayer; 

        remainingTimeInSeconds = 3*60;
        this.questionGenerator = new MathQuestionGenerator();
        this.questionGenerator.setLevel(level);
        this.questionGenerator.setSection(section);
        this.currentQuestion = questionGenerator.generateQuestion();
        playerSection = section;
        player = Player;
        
        playerCarImage = new ImageIcon(getClass().getResource("playerCarFinal.png"));
        aiCarImage = new ImageIcon(getClass().getResource("aiCarFinal.png"));
        playerCarXPosition = 10; // Initial X position for player car
        aiCarXPosition = 10; // Initial X position for AI car
        
        playerinfo = new PlayerRaceTrack(10, player, section);
        cpuinfo = new CpuRaceTrack(10);

        boolean paused;
        GameState gameState = new GameState(level, true);
        paused = gameState.isPaused(); 
        
        setupPauseShortcut();
        setUpSaveShortCut();
        setupMuteShortcut();
        initComponents(paused);
        
        
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTimeInSeconds--;
                if (remainingTimeInSeconds >= 0) {
                    // Update the timer label
                    timerLabel.setText("Time Remaining: " + formatTime(remainingTimeInSeconds));
                } else {
                    // Time's up, stop the timer and show game over
                    gameTimer.stop();
                    cpuWinsGame();
                }
            }
        });
        

        gameTimer.start(); // Start the timer as soon as the game page is initialized

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Use null layout for absolute positioning
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        setLocationRelativeTo(null);


    }

    /**
     * Initializes components of the game page including timer, buttons, and listeners.
     * 
     * @param isPaused Initial pause state of the game.
     */
    private void initComponents(boolean isPaused) {
        
    	timerLabel = new JLabel("Time Remaining: " + formatTime(remainingTimeInSeconds));
        timerLabel.setBounds(10, 50, 200, 30); // Adjust the size and positioning as necessary
    	
        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a confirmation dialog
                gameTimer.stop();
                int response = JOptionPane.showConfirmDialog(GamePage.this, 
                        "All progress will be discarded. Are you sure you want to go back?", 
                        "Confirm Exit", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    // User confirms to go back, discard progress
                    GamePage.this.setVisible(false);
                    previousPage.setVisible(true);
                } else {
                    // User decides not to go back, do nothing
                }
            }
        });

        
        add(timerLabel);
        add(backButton);
        
     // Display Question Button
        displayQuestionButton = new JButton("Display Question");
        displayQuestionButton.setBounds(315, 370, 200, 50); // Adjust position and size as needed
        displayQuestionButton.addActionListener(e -> {
            currentQuestion = questionGenerator.generateQuestion();
            QuestionFrame questionFrame = new QuestionFrame(currentQuestion, playerinfo, cpuinfo, this, playerSection, player, backgroundMusicPlayer);
            //SoundUtils.playSound("displayQuestionSound.wav");
            questionFrame.setVisible(true);
        });
        add(displayQuestionButton);
        
        
    }
    
    /**
     * Handles the logic when CPU wins the game, typically called when the timer runs out.
     */
    private void cpuWinsGame() {
      // This method is called when the 5-minute timer expires
      JOptionPane.showMessageDialog(this, "Time's up! CPU wins the game.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
      
      // You can add any additional actions here, such as closing the game window
      // and returning to the main menu or another appropriate screen.
      this.setVisible(false);
      previousPage.setVisible(true);
      // Stop the background music if needed
      if (backgroundMusicPlayer != null) {
          backgroundMusicPlayer.stop();
      }
      gameTimer.stop();

  }

    /**
     * Formats the remaining time from seconds into a mm:ss format.
     * 
     * @param totalSeconds The total remaining seconds.
     * @return A string representing the formatted time.
     */
    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Updates the player car's position on the race track.
     */
    public void updatePlayerCarPosition() {
    	playerCarXPosition  = playerCarXPosition + 70; // Adjust starting point and multiplier as needed
        repaint(); // Repaint to show the updated position
    }

    /**
     * Updates the CPU car's position on the race track.
     */
    public void updateCpuCarPosition() {
    	aiCarXPosition  = aiCarXPosition + 70; // Adjust starting point and multiplier as needed
        repaint(); // Repaint to show the updated position
    }

    /**
     * Gets the frame that was displayed before the game page.
     * 
     * @return The previous {@code Frame}.
     */
    public Frame getPreviousPage() {
        return previousPage;
    }

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


    private void togglePause() {
	    // Toggle play/pause state of the background music
        isPaused = !isPaused; // Toggle the pause state
        
        if (isPaused) {
            // If the game is now paused
            gameTimer.stop(); // Stop the timer
        } else {
            // If the game is now resumed
            gameTimer.start(); // Start the timer again
        }
        
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
     * Overrides the {@code paint} method to draw the game's background image and cars.
     * 
     * @param g The {@code Graphics} object used for drawing.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

        // Draw player and AI cars
        if (playerCarImage != null && aiCarImage != null) {
            g.drawImage(playerCarImage.getImage(), playerCarXPosition, Y_POSITION, this);
            g.drawImage(aiCarImage.getImage(), aiCarXPosition, Y_POSITION + 167, this); // Adjust Y as needed
        }
    }


}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import javax.swing.border.Border;

/**
 * The {@code GUI5} class extends {@code JFrame} and serves as the main graphical user interface
 * for a game application. It initializes the game's main menu, including buttons for starting the game,
 * viewing the scoreboard, accessing the daily bonus, entering feedback, and muting the game sound.
 * Additionally, it handles the game's audio playback and provides methods for pausing the game, saving
 * game data, and toggling the mute state.
 *
 * @author team42
 */
public class GUI5 extends JFrame{

	Scanner input = new Scanner(System.in);
	String playeranamecomp = "";
	int playeraagecomp = 0;
	String filenametxt = "";
	String feedback_message_data = "";
	String feedback_subject_data = "";

	private AudioPlayer backgroundMusicPlayer;

    /**
     * Constructs a new GUI5 frame and initializes all components including background images, buttons,
     * text fields, and action listeners for the game's main menu and other functionalities.
     *
     * @throws IOException If there is an error reading the background images or setting custom fonts.
     */
	public GUI5() throws IOException
	{
		 backgroundMusicPlayer = new AudioPlayer("generalBackGroundMusic.wav");
		 backgroundMusicPlayer.setVolume(-30.0f); // Example to lower the volume
	     backgroundMusicPlayer.togglePlayPause(); // Start playing music by default   
	     //backgroundMusicPlayer.setVolume(-30.0f); // Example to lower the volume
	    
	     
	    BufferedImage start_background = ImageIO.read(new File("finalmenu1.png"));
		BufferedImage pickmode_background = ImageIO.read(new File("dailybonusenterbackground.png"));
		BufferedImage scoreboard_background3 = ImageIO.read(new File("scoreboard_background3.png"));
		BufferedImage feedbackbackgroundimage = ImageIO.read(new File("feedbackbackgroundfinal.png"));
		BufferedImage instructordashboardimage = ImageIO.read(new File("instructordashboardfinal.png"));

		JPanel panel = new JPanel();
		
		JLabel start_game_background = new JLabel(new ImageIcon(start_background));
		JLabel dailybonus = new JLabel(new ImageIcon(pickmode_background));
		JLabel dailybonus_enter = new JLabel(new ImageIcon(pickmode_background));
		JLabel selectMode = new JLabel(new ImageIcon(pickmode_background));
		JLabel feedback_input = new JLabel(new ImageIcon(feedbackbackgroundimage));
		JLabel scoreboard_background = new JLabel(new ImageIcon(scoreboard_background3));
		JLabel instructordashboard = new JLabel(new ImageIcon(instructordashboardimage));

		panel.setBackground(new Color(204,204,204));

		panel.add(start_game_background);
		panel.add(dailybonus_enter);
		panel.add(dailybonus);
		panel.add(selectMode);
		panel.add(feedback_input);
		panel.add(scoreboard_background);
		panel.add(instructordashboard);
		
		ImageIcon startplayimage = new ImageIcon("mathdash_playbutton.png");
		ImageIcon loadgameimage = new ImageIcon("mathdash_loadgame_button.png");
		ImageIcon scoreboard_button = new ImageIcon("mathdashscoreboard_button.png");
		ImageIcon dailybonus_button = new ImageIcon("dailybonus_buttonfinal1.png");
		ImageIcon feedback_button = new ImageIcon("mathdashfeedback_button.png");
		ImageIcon tutorialButton = new ImageIcon("mathdash_tutorialbutton.png");
		ImageIcon tutorial_button = new ImageIcon("instructordashboardfinal211.png");

		ImageIcon back = new ImageIcon("backbuttonfinal1.png");
		ImageIcon next = new ImageIcon("sendbuttonfinal.png");
		ImageIcon smallback = new ImageIcon("backbuttonfinal1.png");
		ImageIcon audioicon = new ImageIcon("soundbuttonfinal.png");
		
		JButton start_play = new JButton(startplayimage);
		JButton scoreboard_button_main = new JButton(scoreboard_button);
		JButton dailybonus_button_main = new JButton(dailybonus_button);
		JButton tutorial_button_main = new JButton(tutorial_button);
		JButton feedback_button_main = new JButton(feedback_button);
		JButton dashboard_button_main = new JButton(tutorialButton);
		JButton claimBonusButton = new JButton("Claim Bonus");
		JButton feedbackbutton_back = new JButton(back);
		JButton feedbackbutton_submit = new JButton(next);
		JButton scoreboard_back = new JButton(smallback);
		JButton instructordashboard_back = new JButton(smallback);
		JButton dailybonus_enter_back = new JButton(back);
		JButton dailybonus_enter_next = new JButton(next);
		JButton dailybonus_claim_back = new JButton(smallback);
		JButton mutebutton = new JButton(audioicon);
		JButton debug_button_main = new JButton();
		
		mutebutton.setOpaque(false);
        mutebutton.setContentAreaFilled(false);
        mutebutton.setBorderPainted(false);
		
		JTextField gamefilename = new JTextField(10);
		gamefilename.setBounds(660, 330, 700, 60);
		
		JTextField feedback_message = new JTextField(10);
		feedback_message.setBounds(650, 292, 700, 40);
		
		JTextArea feedback_subject = new JTextArea();
		feedback_subject.setBounds(650, 390, 700, 200);
		feedback_subject.setLineWrap(true);
		feedback_subject.setWrapStyleWord(true);

		JLabel usernameLabel = new JLabel("Username:");
		
		JLabel player1username = new JLabel("Username:");
		JLabel player2username = new JLabel("Username:");
		JLabel player3username = new JLabel("Username:");
		JLabel player4username = new JLabel("Username:");
		JLabel player5username = new JLabel("Username:");
		
		JLabel player1points = new JLabel("PTS");
		JLabel player2points = new JLabel("PTS");
		JLabel player3points = new JLabel("PTS");
		JLabel player4points = new JLabel("PTS");
		JLabel player5points = new JLabel("PTS");
		
		
		player1username.setBounds(460, 270, 300, 60);
		player2username.setBounds(460, 370, 300, 60);
		player3username.setBounds(460, 470, 300, 60);
		player4username.setBounds(460, 570, 300, 60);
		player5username.setBounds(460, 660, 300, 60);
		
		
		player1points.setBounds(800, 270, 300, 60);
		player2points.setBounds(800, 370, 300, 60);
		player3points.setBounds(800, 470, 300, 60);
		player4points.setBounds(800, 560, 300, 60);
		player5points.setBounds(800, 660, 300, 60);
		
		
		try {
			//create the font to use. Specify the size!
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("coffee.ttf")).deriveFont(29f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(customFont);
			
			player1username.setFont(customFont);
			player2username.setFont(customFont);
			player3username.setFont(customFont);
			player4username.setFont(customFont);
			player5username.setFont(customFont);
			
			player1points.setFont(customFont);
			player2points.setFont(customFont);
			player3points.setFont(customFont);
			player4points.setFont(customFont);
			player5points.setFont(customFont);


		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}
		
		
		Font labelFont = new Font("Arial", Font.BOLD, 60);
		usernameLabel.setFont(labelFont);
		
		usernameLabel.setBounds(360, 330, 700, 100);
		usernameLabel.setVisible(true);
		usernameLabel.setText("");
		
		
		
		

		feedback_message.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				feedback_message_data=feedback_message.getText();
							}
		});
		
		feedback_subject.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				feedback_subject_data=feedback_subject.getText();
				
			}
		});


		gamefilename.addKeyListener (new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				filenametxt=gamefilename.getText();
			}
		});

		try {
			//create the font to use. Specify the size!
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("coffee.ttf")).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(customFont);
			gamefilename.setFont(customFont);
			feedback_message.setFont(customFont);
			feedback_subject.setFont(customFont);

		} catch (IOException e) {
			e.printStackTrace();
		} catch(FontFormatException e) {
			e.printStackTrace();
		}


		
		gamefilename.setOpaque(false);
		
		feedback_message.setOpaque(false);
		feedback_subject.setOpaque(true);
		

		start_play.setOpaque(false);
		start_play.setContentAreaFilled(false);
		start_play.setBorderPainted(false);
		
		dailybonus_button_main.setOpaque(false);
		dailybonus_button_main.setContentAreaFilled(false);
		dailybonus_button_main.setBorderPainted(false);
		
		scoreboard_button_main.setOpaque(false);
		scoreboard_button_main.setContentAreaFilled(false);
		scoreboard_button_main.setBorderPainted(false);
		
		tutorial_button_main.setOpaque(false);
		tutorial_button_main.setContentAreaFilled(false);
		tutorial_button_main.setBorderPainted(false);
		
		dashboard_button_main.setOpaque(false);
		dashboard_button_main.setContentAreaFilled(false);
		dashboard_button_main.setBorderPainted(false);
		
		feedback_button_main.setOpaque(false);
		feedback_button_main.setContentAreaFilled(false);
		feedback_button_main.setBorderPainted(false);
		
		dailybonus_enter_back.setOpaque(false);
		dailybonus_enter_back.setContentAreaFilled(false);
		dailybonus_enter_back.setBorderPainted(false);
		

		dailybonus_enter_next.setOpaque(false);
		dailybonus_enter_next.setContentAreaFilled(false);
		dailybonus_enter_next.setBorderPainted(false);
		
		dailybonus_claim_back.setOpaque(false);
		dailybonus_claim_back.setContentAreaFilled(false);
		dailybonus_claim_back.setBorderPainted(false);
		

		feedbackbutton_back.setOpaque(false);
		feedbackbutton_back.setContentAreaFilled(false);
		feedbackbutton_back.setBorderPainted(false);
		feedbackbutton_submit.setOpaque(false);
		feedbackbutton_submit.setContentAreaFilled(false);
		feedbackbutton_submit.setBorderPainted(false);
		

		scoreboard_back.setOpaque(false);
		scoreboard_back.setContentAreaFilled(false);
		scoreboard_back.setBorderPainted(false);
		
		instructordashboard_back.setOpaque(false);
		instructordashboard_back.setContentAreaFilled(false);
		instructordashboard_back.setBorderPainted(false);
		
		
		mutebutton.setOpaque(false);
		mutebutton.setContentAreaFilled(false);
		mutebutton.setBorderPainted(false);
		
		debug_button_main.setOpaque(true);
        debug_button_main.setContentAreaFilled(false);
        debug_button_main.setBorderPainted(false);

		start_game_background.add(start_play);	
		start_game_background.add(dailybonus_button_main);
		start_game_background.add(scoreboard_button_main);
		start_game_background.add(tutorial_button_main);
		start_game_background.add(feedback_button_main);
		start_game_background.add(dashboard_button_main);
		start_game_background.add(mutebutton);
		start_game_background.add(debug_button_main);
		
		
		feedback_input.add(feedbackbutton_back);
		feedback_input.add(feedbackbutton_submit);
		feedback_input.add(feedback_message);
		feedback_input.add(feedback_subject);
	
		

		dailybonus.add(dailybonus_claim_back);
		dailybonus.add(usernameLabel);
		dailybonus.add(claimBonusButton);
		
		scoreboard_background.add(player1username);
		scoreboard_background.add(player2username);
		scoreboard_background.add(player3username);
		scoreboard_background.add(player4username);
		scoreboard_background.add(player5username);
		
		scoreboard_background.add(player1points);
		scoreboard_background.add(player2points);
		scoreboard_background.add(player3points);
		scoreboard_background.add(player4points);
		scoreboard_background.add(player5points);
		
		scoreboard_background.add(scoreboard_back);
		
		instructordashboard.add(instructordashboard_back);
		

		dailybonus_enter.add(dailybonus_enter_back);
		dailybonus_enter.add(dailybonus_enter_next);
		dailybonus_enter.add(gamefilename);


		start_play.setSize(210, 90);
		start_play.setLocation(590,240);
		start_play.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent e) {	
				start_game_background.setVisible(true);
				enterUserIDPage enterUserIDPage = new enterUserIDPage(GUI5.this, backgroundMusicPlayer);
                enterUserIDPage.setLocationRelativeTo(GUI5.this);
                enterUserIDPage.setVisible(true);

			}
		});
		
		dailybonus_button_main.setSize(210, 90);
		dailybonus_button_main.setLocation(590,400);
		dailybonus_button_main.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				start_game_background.setVisible(false);
				dailybonus_enter.setVisible(true);

			}
		});	
		
		scoreboard_button_main.setSize(210, 90);
		scoreboard_button_main.setLocation(590,320);
		scoreboard_button_main.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				start_game_background.setVisible(false);
				dailybonus_enter.setVisible(false);
				dailybonus.setVisible(false);
				selectMode.setVisible(false);
				feedback_input.setVisible(false);
				scoreboard_background.setVisible(true);
				instructordashboard.setVisible(false);
				
				
				SaveGameManager gameFile = new SaveGameManager("players.csv");
				List<Player> players = gameFile.readData();
				
				Scoreboard scoreboard = new Scoreboard(players);
				
				player1username.setText(scoreboard.getTopPlayerName(1));
				player2username.setText(scoreboard.getTopPlayerName(2));
				player3username.setText(scoreboard.getTopPlayerName(3));
				player4username.setText(scoreboard.getTopPlayerName(4));
				player5username.setText(scoreboard.getTopPlayerName(5));
				
				player1points.setText(scoreboard.getTopPlayerPoints(1));
				player2points.setText(scoreboard.getTopPlayerPoints(2));
				player3points.setText(scoreboard.getTopPlayerPoints(3));
				player4points.setText(scoreboard.getTopPlayerPoints(4));
				player5points.setText(scoreboard.getTopPlayerPoints(5));
				
			}
		});
		
		
		tutorial_button_main.setSize(210, 90);
		tutorial_button_main.setLocation(590,480);
		
		tutorial_button_main.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Ask for the password
		        String password = JOptionPane.showInputDialog(GUI5.this, "Enter the password to access the Instructor Dashboard:", "Password Required", JOptionPane.PLAIN_MESSAGE);

		        // Check if the password is correct
		        if ("ducklover".equals(password)) {
		            // Password is correct, proceed to the dashboard
		            start_game_background.setVisible(false);
		            dailybonus_enter.setVisible(false);
		            dailybonus.setVisible(false);
		            selectMode.setVisible(false);
		            feedback_input.setVisible(false);
		            scoreboard_background.setVisible(false);
		            instructordashboard.setVisible(true);
		            
		            SaveGameManager gameFile = new SaveGameManager("players.csv");
		            List<Player> players = gameFile.readData();
		            
		            JLabel instructordashboard1 = new JLabel();
		            InstructorDashboard.displayAllPlayersUI(players, instructordashboard1);
		            
		            instructordashboard.add(instructordashboard1);
		            instructordashboard1.setSize(1000,550);
		            instructordashboard1.setLocation(530,140);
		            instructordashboard1.setOpaque(false);
		            
		            instructordashboard_back.setSize(210, 90);
		            instructordashboard_back.setLocation(310,140);
		            instructordashboard_back.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    start_game_background.setVisible(true);
		                    dailybonus_enter.setVisible(false);
		                    dailybonus.setVisible(false);
		                    selectMode.setVisible(false);
		                    feedback_input.setVisible(false);
		                    scoreboard_background.setVisible(false);
		                    instructordashboard.setVisible(false);
		                    
		                    instructordashboard.remove(instructordashboard1);
		                    instructordashboard.revalidate();
		                    instructordashboard.repaint();
		                }
		            });
		        } else if (password != null) {
		            // Password is incorrect and the user did not cancel the dialog
		            JOptionPane.showMessageDialog(GUI5.this, "Wrong password. Access denied.", "Access Denied", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		//tutorial
		dashboard_button_main.setSize(210, 90);
		dashboard_button_main.setLocation(590,560);
		dashboard_button_main.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				TutorialPage tutorialpage = new TutorialPage(GUI5.this);
				tutorialpage.setLocationRelativeTo(GUI5.this);
				tutorialpage.setVisible(true);
		
			}
		});
		
		debug_button_main.setSize(70, 70);
        debug_button_main.setLocation(1220,260);
        debug_button_main.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	start_game_background.setVisible(true);
				enterUserIDPage enterUserIDPage = new enterUserIDPage(GUI5.this, backgroundMusicPlayer);
                enterUserIDPage.setLocationRelativeTo(GUI5.this);
                enterUserIDPage.setVisible(true);

            }
        });
        
		feedback_button_main.setSize(210, 90);
		feedback_button_main.setLocation(590,640);
		feedback_button_main.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
	
				// TODO Auto-generated method stub
				start_game_background.setVisible(false);
				dailybonus_enter.setVisible(false);
				dailybonus.setVisible(false);
				selectMode.setVisible(false);
				feedback_input.setVisible(true);
				scoreboard_background.setVisible(false);
				instructordashboard.setVisible(false);

			}
		});
	
		{
			
			claimBonusButton.setSize(400,150);
			claimBonusButton.setLocation(800,300);
			
			
			dailybonus_claim_back.setSize(230, 125);
			dailybonus_claim_back.setLocation(500,600);
			dailybonus_claim_back.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					
					start_game_background.setVisible(true);


				}
			});
			
			scoreboard_back.setSize(210, 90);
			scoreboard_back.setLocation(30,80);
			scoreboard_back.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					start_game_background.setVisible(true);
					dailybonus_enter.setVisible(false);
					dailybonus.setVisible(false);
					selectMode.setVisible(false);
					feedback_input.setVisible(false);
					scoreboard_background.setVisible(false);
					instructordashboard.setVisible(false);


				}
			});
			
			feedbackbutton_submit.setSize(230, 125);
			feedbackbutton_submit.setLocation(900,600);
			feedbackbutton_submit.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	String message = feedback_subject.getText().trim();
			        String subject = feedback_message.getText().trim();
			        

			        // Only proceed if both subject and message are not empty
			        if (!subject.isEmpty() && !message.isEmpty()) {
			            writeFeedbackToFile(subject, message);
			            JOptionPane.showMessageDialog(GUI5.this, "Feedback submitted successfully.", "Feedback Submitted", JOptionPane.INFORMATION_MESSAGE);
			            
			            // Optionally clear the fields after submission
			            feedback_subject.setText("");
			            feedback_message.setText("");
			        } else {
			            JOptionPane.showMessageDialog(GUI5.this, "Please fill in both subject and message.", "Incomplete Feedback", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			});


			dailybonus_enter_back.setSize(230, 125);
			dailybonus_enter_back.setLocation(500,600);
			dailybonus_enter_back.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					start_game_background.setVisible(true);
				}
			});
			
			
			feedbackbutton_back.setSize(230, 125);
			feedbackbutton_back.setLocation(350,50);
			feedbackbutton_back.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					start_game_background.setVisible(true);
				}
			});	
			
			

			dailybonus_enter_next.setSize(230, 125);
			dailybonus_enter_next.setLocation(1200,600);
			dailybonus_enter_next.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					filenametxt=gamefilename.getText();
					DailyLoginBonus dailyLoginBonus = new DailyLoginBonus(filenametxt);
					
					if(dailyLoginBonus.canClaim()==true) {
						claimBonusButton.setText("CLICK TO CLAIM DAILY BONUS!");
						claimBonusButton.setEnabled(true);
						claimBonusButton.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								claimBonusButton.setEnabled(false);
								
								dailyLoginBonus.claimBonus();
								claimBonusButton.setText("Successfully Claimed 500 Daily Login Bonus Points!");
								// TODO Auto-generated method stub
								
							}
							
						});
						
					}
					else {
						claimBonusButton.setEnabled(false);
						claimBonusButton.setText("Daily Bonus Already Claimed");
					}
					
					start_game_background.setVisible(false);
					dailybonus_enter.setVisible(false);
					dailybonus.setVisible(true);
					selectMode.setVisible(false);
					feedback_input.setVisible(false);
					scoreboard_background.setVisible(false);
					instructordashboard.setVisible(true);
					panel.setVisible(true);

				}
			});		
			
			mutebutton.setSize(210,100);
	        mutebutton.setLocation(20, 0);
	        mutebutton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                backgroundMusicPlayer.togglePlayPause();
	            }
	        });


			Border border = BorderFactory.createLineBorder(Color.white, 3);

			this.setTitle("MathDash Game - Andrew, Vincent, Matthew, Mirna, Moe");
			this.add(panel);
			this.setSize(1300, 800);
			this.setVisible(true);
	        setLocationRelativeTo(null);

			
			// Correcting Key Binding Implementation:
		    JPanel contentPane1 = (JPanel) this.getContentPane();
		    contentPane1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('p'), "pauseGame");
		    contentPane1.getActionMap().put("pauseGame", new AbstractAction() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            togglePause();
		        }
		    });
		    
		    
		 // Correcting Key Binding Implementation:
		    JPanel contentPane2 = (JPanel) this.getContentPane();
		    contentPane2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "saveGame");
		    contentPane2.getActionMap().put("saveGame", new AbstractAction() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            saveGame();
		        }
		    });
		    
		    JPanel contentPane3 = (JPanel) this.getContentPane();
		    contentPane3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('m'), "muteSound");
		    contentPane3.getActionMap().put("muteSound", new AbstractAction() { // This was mistakenly set to "saveGame"
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            muteSound();
		        }
		    });

		    // Handling application exit to stop music
		    this.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent e) {
		            if (backgroundMusicPlayer != null) {
		                backgroundMusicPlayer.stop();
		            }
		            System.exit(0);
		        }
		    });
		}
	}

    /**
     * Toggles the play/pause state of the background music. If the music is playing, it will be paused;
     * if it is paused, it will resume playing.
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
     * Saves the current game state. This placeholder method simulates saving the game and displays
     * a message dialog indicating that the game has been saved.
     */
	private void saveGame() {
	    // Toggle play/pause state of the background music
		JOptionPane pane = new JOptionPane("The game is saved", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = pane.createDialog(this, "Save Game");
        dialog.setModal(false); // Non-modal dialog allows interaction with other parts of the application

        JButton okButton= new JButton("OK");
        okButton.addActionListener(e -> {
            dialog.dispose();
        });

        pane.add(okButton);
        dialog.setVisible(true);
	}
	
    /**
     * Mutes or unmutes the background music of the game. This method toggles the audio playback state
     * and displays a message dialog indicating the current mute state.
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
     * Writes feedback provided by the user to a file. This method captures feedback subject and message
     * from the GUI and appends it to a feedback text file.
     *
     * @param subject The subject of the feedback.
     * @param feedbackMessage The message body of the feedback.
     */
	private void writeFeedbackToFile(String subject, String feedbackMessage) {
	    File file = new File("feedback.txt"); // Name of the file to store feedback
	    try (FileWriter fw = new FileWriter(file, true); // The true parameter tells the FileWriter to append to the file
	         BufferedWriter bw = new BufferedWriter(fw);
	         PrintWriter out = new PrintWriter(bw)) {
	        out.println(subject + ":" + feedbackMessage); // Writes feedback in the specified format
	    } catch (IOException e) {
	        System.err.println("An error occurred while writing feedback to file: " + e.getMessage());
	    }
	}

    /**
     * The main method that creates an instance of {@code GUI5}. This serves as the entry point
     * of the application.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs during the initialization of the GUI.
     */
	public static void main(String[] args) throws IOException {
		new GUI5();

	}
	
}


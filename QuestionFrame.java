import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * The QuestionFrame class provides a graphical user interface to display a question to the player,
 * accept their answer, and give immediate feedback. It is part of a larger game application that involves
 * racing against a CPU opponent. The class handles user input, validates it against the correct answer,
 * and updates the game state based on the outcome.
 *
 * @author Mohammed
 */
public class QuestionFrame extends JFrame {
    private Question currentQuestion;
    private JTextField answerField;
    private JLabel feedbackLabel;
    private JLabel answerMessage;
    private JLabel CpufeedbackLabel;
    private Player player;
    private PlayerRaceTrack playerinfo;
    private CpuRaceTrack cpuinfo;
    private GamePage gamepage;
    private String section;
    private AudioPlayer backgroundMusicPlayer; // Assume AudioPlayer is accessible


    /**
     * Constructs a QuestionFrame with the specified question, player and CPU race track information,
     * the current game page, the game section, the player object, and the background music player.
     * It initializes the UI components including the question display and answer submission interface.
     *
     * @param question the current question to be displayed
     * @param playerinfo the race track information for the player
     * @param cpuinfo the race track information for the CPU
     * @param gamepage the current game page
     * @param section the current section of the game
     * @param player the player object
     * @param backgroundMusicPlayer the background music player
     */
    public QuestionFrame(Question question, PlayerRaceTrack playerinfo, CpuRaceTrack cpuinfo, GamePage gamepage, String section, Player player, AudioPlayer backgroundMusicPlayer) {
        this.currentQuestion = question;
        this.playerinfo = playerinfo;
        this.cpuinfo = cpuinfo;
        this.gamepage = gamepage;
        this.section = section;
        this.player = player;
        this.backgroundMusicPlayer = backgroundMusicPlayer;
        initializeComponents();
    }

    /**
     * Initializes the components of the QuestionFrame. This includes setting up the layout,
     * creating and adding UI elements for displaying the question and receiving the answer, and
     * setting up action listeners for answer submission.
     */
    private void initializeComponents() {
        setLayout(new FlowLayout());
        setSize(400, 200);
        setLocationRelativeTo(null);

        add(new JLabel("Question: " + currentQuestion.toString()));

        // Answer Field
        answerField = new JTextField(20);
        add(answerField);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> checkAnswer());

        add(submitButton);

        // Feedback Label
        feedbackLabel = new JLabel("");
        add(feedbackLabel);

        answerMessage = new JLabel("");
        add(answerMessage);

        CpufeedbackLabel = new JLabel("");
        add(answerMessage);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Checks the answer submitted by the user. If the answer is correct, it updates the game state
     * accordingly and provides positive feedback. If the answer is incorrect, it provides corrective feedback.
     * It also manages the transition to the next question or the conclusion of the game based on the outcome.
     */
    private void checkAnswer() {
        try {
            int userAnswer = Integer.parseInt(answerField.getText());
            if (userAnswer == currentQuestion.getCorrectAnswer()) {
                feedbackLabel.setText("Correct!");
                SoundUtils.playSound("quizCorrectSound.wav");
                JOptionPane.showMessageDialog(this, feedbackLabel.getText());
                gamepage.updatePlayerCarPosition();
                playerinfo.movePlayer(1);
                playerinfo.addTotalCorrectAnswer();
                if (playerinfo.playerWon() == true) {
                    playerinfo.addPoints();
                    answerMessage.setText("Congradulation, you have won the game");
                    SoundUtils.playSound("levelClearSound.wav");
                    JOptionPane.showMessageDialog(this, answerMessage.getText());

                    SwingUtilities.invokeLater(() -> {
                        gamepage.setVisible(false); // Hide or dispose the GamePage
                        selectDifficultyPage selectPage = new selectDifficultyPage(gamepage.getPreviousPage(), section, player, backgroundMusicPlayer);
                        selectPage.setVisible(true);
                    });
                }
            } else {
                feedbackLabel.setText("Incorrect. Try again!");
                playerinfo.addTotalIncorrectAnswer();
                SoundUtils.playSound("quizWrongSound.wav");
                JOptionPane.showMessageDialog(this, feedbackLabel.getText());
                GamePage.incorrectAnswers++; // Increment the incorrect answer count
            }
            // Show feedback in a dialog
            dispose(); // Close the frame
            checkCpuAnswer();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    /**
     * Simulates the CPU's attempt to answer the question, randomly determining if the CPU's answer
     * is correct or incorrect, and updates the game state accordingly. Provides feedback on the CPU's
     * performance to the player.
     */
    private void checkCpuAnswer() {
        int result = (int) (Math.random() * 2);
        if (result == 0) {
            CpufeedbackLabel.setText("CPU has answered correctly!");
            gamepage.updateCpuCarPosition(); // Update car position on the game page.
            cpuinfo.moveCpu(1);
            JOptionPane.showMessageDialog(this, CpufeedbackLabel.getText());
            if (cpuinfo.CpuWon() == true) {
                answerMessage.setText("Cpu won... Good luck next time!");
                JOptionPane.showMessageDialog(this, answerMessage.getText());
            }

        } else {
            CpufeedbackLabel.setText("CPU has answered Wrong");
            JOptionPane.showMessageDialog(this, CpufeedbackLabel.getText());
        }

        dispose(); // Close the frame
    }

}

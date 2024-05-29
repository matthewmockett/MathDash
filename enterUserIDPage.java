import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents a dialog for entering a user ID. It extends JDialog, making it a modal window
 * that pauses interaction with the parent frame until it is closed. The user is prompted to enter their ID,
 * which is then used to load their game data or create a new user profile.
 * <p>
 * This dialog is part of a larger application that manages user sessions and game data.
 * Upon submission, it transitions to a new page where the user can select game sections.
 * </p>
 * 
 * @author team42
 */

public class enterUserIDPage extends JDialog {
    private JTextField userIdField; // Field for user to enter ID
    private JButton submitButton; // Button to submit the ID
    private GUI5 previousFrame; // Reference to the previous frame (parent window)
    private AudioPlayer backgroundMusicPlayer; // Assume AudioPlayer is accessible

     /**
     * Constructs a new dialog where users can enter their ID.
     * 
     * @param owner The frame from which the dialog is displayed
     * @param backgroundMusicPlayer The audio player for background music control
     */

    public enterUserIDPage(GUI5 owner, AudioPlayer backgroundMusicPlayer) {
        super(owner, "Enter User ID", true); // 'true' for a modal dialog
        previousFrame = owner;
        this.backgroundMusicPlayer = backgroundMusicPlayer;
        initComponents();
    }

    /**
     * Initializes the components of this dialog, including layout, fields, and buttons.
     */
    private void initComponents() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        // Label
        JLabel promptLabel = new JLabel("Enter your ID:");
        contentPane.add(promptLabel);

        // Text field for user ID input
        userIdField = new JTextField(15);
        contentPane.add(userIdField);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousFrame.setVisible(false);// Dispose/hide the GUI5 instance
                String userID = userIdField.getText();
                SaveGameManager user = new SaveGameManager("players.csv");
                Player player = user.loadGameData(user.readData(), userID);
                selectSectionPage selectSectionPage = new selectSectionPage(previousFrame, player, backgroundMusicPlayer);
                selectSectionPage.setLocationRelativeTo(null); // Center on screen
                selectSectionPage.setVisible(true); // Show the selectSectionPage
            }
        });
       
        contentPane.add(submitButton);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack(); // Size the dialog to fit the preferred size and layouts of its subcomponents
    }
}

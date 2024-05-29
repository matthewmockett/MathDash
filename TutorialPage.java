import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * TutorialPage provides a GUI window displaying a tutorial for users.
 * It extends JFrame and includes a background image along with a back button
 * to return to the main page.
 * 
 * @author Vincent
 */

public class TutorialPage extends JFrame {
    private JFrame mainPage;
    private ImageIcon backgroundImage;

    /**
     * Constructor for creating the tutorial page.
     *
     * @param mainPage The main JFrame window that this tutorial page is associated with.
     */
    
    public TutorialPage(JFrame mainPage) {
        this.mainPage = mainPage;
        // Load and set the background image
        backgroundImage = new ImageIcon(getClass().getResource("howtoplayfinal.png")); // Adjust path

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        setLocationRelativeTo(null);// Center the window
        addBackButton(); // Add the back button to the frame
    }

    /*
     * Adds a back button to the tutorial page. When pressed, it hides this tutorial page
     * and shows the main page.
     */
    
    private void addBackButton() {
    	JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30); // Adjust as needed
        
        // Define the action to perform when the back button is clicked
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	TutorialPage.this.setVisible(false); // Hide the select section page
                mainPage.setVisible(true); // Show the main page again
            }
        });
        add(backButton);
    }

    /**
     * Paints the background image onto the JFrame.
     *
     * @param g The Graphics object to protect.
     */
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this);
    }
}

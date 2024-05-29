import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The InstructorDashboard class is designed to display a UI panel showing details
 * for all players. It sorts players by name and displays their information, such as points,
 * last login, and levels in various arithmetic operations. The class demonstrates the use
 * of custom fonts, sorting of collections, and UI components in Java Swing.
 *
 * @author Mohammed
 */
public class InstructorDashboard {

    /**
     * Displays a sorted list of players in the instructor's dashboard. This method
     * sorts the players by their names and dynamically creates UI components to show each
     * player's details. A custom font is applied to the text, enhancing the visual presentation.
     * The method encapsulates the entire process of UI generation including sorting, UI creation,
     * and applying custom styles.
     *
     * @param players The list of Player objects to be displayed.
     * @param InstructorDashboard The JLabel container for displaying the player information.
     */

    public static void displayAllPlayersUI(List<Player> players, JLabel instructordashboard) {
        // Sort players by their names
        Collections.sort(players, Comparator.comparing(Player::getPlayerName));

        // Create a panel to hold player details
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3, 15, 15));

        panel.setOpaque(false);

        for (Player player : players) {
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

            playerPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));

            // Create labels for player information
            JLabel nameLabel = new JLabel("Name: " + player.getPlayerName(), JLabel.RIGHT);

            JLabel pointsLabel = new JLabel("Points: " + player.getTotalPlayerPoints());
            JLabel lastLoginLabel = new JLabel("Last Login: " + player.getLastClaimedDailyBonus());
            JLabel additionLevelLabel = new JLabel("Addition Level: " + player.getAdditionLevel());
            JLabel subtractionLevelLabel = new JLabel("Subtraction Level: " + player.getSubtractionLevel());
            JLabel multiplicationLevelLabel = new JLabel("Multiplication Level: " + player.getMultiplicationLevel());
            JLabel divisionLevelLabel = new JLabel("Division Level: " + player.getDivisionLevel());


            // Attempt to set a custom font for the labels
            try {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("coffee.ttf")).deriveFont(20f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);



                nameLabel.setFont(customFont);
                pointsLabel.setFont(customFont);
                lastLoginLabel.setFont(customFont);
                additionLevelLabel.setFont(customFont);
                subtractionLevelLabel.setFont(customFont);
                multiplicationLevelLabel.setFont(customFont);
                divisionLevelLabel.setFont(customFont);

            } catch (IOException e) {
                e.printStackTrace();
            } catch(FontFormatException e) {
                e.printStackTrace();
            }

            playerPanel.add(nameLabel);
            playerPanel.add(pointsLabel);
            playerPanel.add(lastLoginLabel);
            playerPanel.add(additionLevelLabel);
            playerPanel.add(subtractionLevelLabel);
            playerPanel.add(multiplicationLevelLabel);
            playerPanel.add(divisionLevelLabel);

            panel.add(playerPanel);
        }

        // Wrap the main panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        // Set up the instructor dashboard label to contain the scroll pane
        instructordashboard.setLayout(new BorderLayout());
        instructordashboard.add(scrollPane, BorderLayout.CENTER);

        instructordashboard.setOpaque(false);
        instructordashboard.revalidate();
        instructordashboard.repaint();

    }
}

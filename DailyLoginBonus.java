
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.Scanner;


/**
 * DailyLoginBonus allows users to claim daily bonus points.
 * It displays the current point balance, provides a button to claim the bonus,
 * and shows a countdown timer until the next bonus claim is available.
 * The claim resets daily at 11:59 PM. User's last claim time and total points
 * are loaded from and saved to a data file.
 *
 * @author Mohammed
 */

public class DailyLoginBonus extends JFrame {
    private JLabel pointsLabel;
    private JButton claimButton;
    private Timer timer;
    private LocalDateTime lastClaimTime;
    private int points = 0;
    private final int bonusPoints = 500;
    private final LocalTime resetTime = LocalTime.of(23, 59); // Reset time at 11:59 PM
    private final Duration claimInterval = Duration.ofDays(1);
    private final String dataFile = "login_data.csv";
    private final SaveGameManager saveGameManager = new SaveGameManager("players.csv");
    private Player currentPlayer;
    Scanner scanner = new Scanner(System.in);
    public static String usernameFinal;


    /**
     * Constructor for DailyLoginBonus. Initializes the UI components and data management objects.
     *
     * @param username The username of the current player.
     */
    public DailyLoginBonus(String username) {
        usernameFinal = username;

        pointsLabel = new JLabel("Points: " + points);
        //add(pointsLabel);

        claimButton = new JButton("Claim Bonus");
        claimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                claimBonus();
            }
        });
        //add(claimButton);

        loadData();
        updateUI();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateUI();
            }
        }, 0, 1000);
    }

    /**
     * Updates the UI elements as the points display and the status of the claim button
     * Called periodically by the timer.
     */
    public void updateUI() {
        if (canClaim()) {
            claimButton.setEnabled(true);
            pointsLabel.setText("Points: " + points);
        } else {
            claimButton.setEnabled(false);
            Duration timeUntilClaim = getTimeUntilNextClaim();
            pointsLabel.setText("Next claim in: " + formatDuration(timeUntilClaim));
        }
    }

    /**
     * Determines if the user is eligible to claim the daily bonus.
     *
     * @return true if the bonus can be claimed, false otherwise.
     */
    public boolean canClaim() {
        if (lastClaimTime == null) return true;
        LocalDateTime resetDateTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), resetTime);
        LocalDateTime nextClaimDateTime = lastClaimTime.plus(claimInterval);
        return LocalDateTime.now().isAfter(resetDateTime) || LocalDateTime.now().isEqual(nextClaimDateTime) || LocalDateTime.now().isAfter(nextClaimDateTime);
    }

    /**
     * Calculates the time remaining until the next claim is available.
     *
     * @return the duration until the user can claim the next bonus.
     */
    public Duration getTimeUntilNextClaim() {
        LocalDateTime resetDateTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), resetTime);
        if (LocalDateTime.now().isBefore(resetDateTime)) {
            return Duration.between(LocalDateTime.now(), resetDateTime);
        } else {
            return Duration.ofDays(1);
        }
    }

    /**
     * Claims the daily bonus for the user, updates the last claim time and points,
     * and persists the data.
     */
    public void claimBonus() {
        lastClaimTime = LocalDateTime.now();
        points += bonusPoints;

        currentPlayer.setLastClaimedDailyBonus(lastClaimTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), saveGameManager);
        currentPlayer.setTotalPlayerPoints(points, saveGameManager);

        saveData();
        updateUI();
    }

    /**
     * Formats a duration into a string of format HH:mm:ss.
     *
     * @param duration The duration to format.
     * @return a formatted string representing the duration.
     */
    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String formattedDuration = String.format("%02d:%02d:%02d",
                absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
        return seconds < 0 ? "-" + formattedDuration : formattedDuration;
    }

    /**
     * Saves the current player's data to the data file.
     */
    public void saveData() {
        saveGameManager.saveGameData(currentPlayer);
    }

    /**
     * Loads player data from the data file and initializes the current player.
     */
    public void loadData() {
        List<Player> players = saveGameManager.readData();
        String playerName = askForUsername(players);
        currentPlayer = saveGameManager.loadGameData(players, playerName);
        currentPlayer.toString();
        lastClaimTime = LocalDateTime.parse(currentPlayer.getLastClaimedDailyBonus(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        points = currentPlayer.getTotalPlayerPoints();
    }

    /**
     * Prompts the user for their username and performs validation against the list of players.
     *
     * @param players List of all player data to validate against.
     * @return the validated username.
     */
    private String askForUsername(List<Player> players) {
        String playerName = usernameFinal;
        boolean validName = false;
        while (!validName) {
            System.out.print("Enter username: ");

            for (Player player : players) {
                if (player.getPlayerName().equals(playerName)) {
                    validName = true;
                    break;
                }
            }
            break;
        }

        return playerName;
    }

}

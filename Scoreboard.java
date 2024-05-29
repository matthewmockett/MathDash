import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Scoreboard class maintains a list of players and provides
 * functionality to get top players based on their points.
 * 
 * @author Vincent
 */

public class Scoreboard {
    private List<Player> players;

    /**
     * Constructs a Scoreboard with a list of players
     *
     * @param players The list of players to include in the scoreboard
     */
    
    public Scoreboard(List<Player> players) {
        this.players = players;
    }


    /**
     * Retrieves the top five players sorted by their points 
     * If there are fewer than five players, it returns all the players
     *
     * @return A list containing the top players
     */
    
    public List<Player> getTopPlayers() {
        List<Player> topPlayers = new ArrayList<>(players);
        Collections.sort(topPlayers, Comparator.comparingInt(Player::getTotalPlayerPoints).reversed());
        return topPlayers.subList(0, Math.min(5, topPlayers.size()));
    }

    
    /**
     * Retrieves the name of the player based on the rank
     *
     * @param rank The rank of the player to retrieve
     * @return The name of the player at the specified rank
     */

    public String getTopPlayerName(int rank) {
        List<Player> topPlayers = getTopPlayers();
        if (rank >= 1 && rank <= topPlayers.size()) {
            return topPlayers.get(rank - 1).getPlayerName();
        } else {
            return "N/A";
        }
    }

    /**
     * Retrieves the points of the player based on the rank
     *
     * @param rank The rank of the player to retrieve
     * @return The points of the player at the specified rank
     */
    
    public String getTopPlayerPoints(int rank) {
        List<Player> topPlayers = getTopPlayers();
        if (rank >= 1 && rank <= topPlayers.size()) {
            return String.valueOf(topPlayers.get(rank - 1).getTotalPlayerPoints());
        } else {
            return "-1";
        }
    }


    /**
     * Prints the top five players to the standard output
     */
    
    public void printTopPlayers() {
        List<Player> topPlayers = getTopPlayers();
        System.out.println("Top 5 Players:");
        for (int i = 0; i < topPlayers.size(); i++) {
            System.out.println((i + 1) + ". " + topPlayers.get(i).getPlayerName() + ": "
                    + topPlayers.get(i).getTotalPlayerPoints() + " points");
        }
    }
}

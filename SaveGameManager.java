import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * SaveGameManager handles the reading and writing of player data to and from a file.
 * It provides functionality to load, save, and display player information.
 * 
 * @author Andrew
 */

public class SaveGameManager {
    private String filename; // The name of the file to read from and write to
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a SaveGameManager with the specified filename.
     *
     * @param filename The name of the file used for saving and loading data.
     */
    
    public SaveGameManager(String filename) {
        this.filename = filename;
    }

    
    /**
     * Reads player data from a file and constructs a list of Player objects.
     *
     * @return A list of Player objects read from the file.
     */
    
    public List<Player> readData() {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	String line;
        	while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String playerName = data[0];
                String lastLoginTime = data[1];
                String lastClaimedDailyBonus = data[2];
                int totalPlayerPoints = Integer.parseInt(data[3]);
                int totalCorrectAnswers = Integer.parseInt(data[4]);
                int totalIncorrectAnswers = Integer.parseInt(data[5]);
                int currentScore = Integer.parseInt(data[6]);
                int additionLevel = Integer.parseInt(data[7]);
                int subtractionLevel = Integer.parseInt(data[8]);
                int multiplicationLevel = Integer.parseInt(data[9]);
                int divisionLevel = Integer.parseInt(data[10]);
                

                Player player = new Player(playerName, lastLoginTime, lastClaimedDailyBonus,totalPlayerPoints, totalCorrectAnswers,totalIncorrectAnswers, currentScore, additionLevel, subtractionLevel, multiplicationLevel, divisionLevel);           
                players.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    
    /**
     * Loads the game data for a specific player.
     *
     * @param players The list of all players.
     * @param playerName The name of the player whose data is to be loaded.
     * @return The Player object with the loaded data.
     */
    
    public Player loadGameData(List<Player> players, String playerName) { 
        

        for (Player player : players) {
            if (player.getPlayerName().equals(playerName)) {
                SaveGameManager gameFile = new SaveGameManager("players.csv");
                players = gameFile.readData();
                return player;
            }
        }
        // Create a new player if not found and append to the file

        try (FileWriter writer = new FileWriter(filename, true)) {
            Player newPlayer = new Player(playerName, "Last Login", "2024-01-01 16:50:36", 0, 0, 0, 0, 0, 0, 0, 0);
            writer.write(newPlayer.toString() + "\n");
            writer.close();
            players.add(newPlayer);
            return newPlayer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    /**
     * Saves the game data of a specific player.
     *
     * @param player The player whose data is to be saved.
     */
    
    public void saveGameData(Player player) {
        List<Player> players = readData();
        try (FileWriter writer = new FileWriter(filename)) {
            for (Player p : players) {
                if (p.getPlayerName().equals(player.getPlayerName())) {
                    writer.write(player.toString() + "\n");
                } 
                else {
                    writer.write(p.toString() + "\n");
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Displays information about all players in the provided list
     *
     * @param players The list of players to be displayed.
     */
    
    public void displayAllPlayers(List<Player> players) {
    	for (int i = 1; i < players.size(); i++) {
            System.out.println(players.get(i));
        }
    }

}

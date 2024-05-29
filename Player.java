import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in a game, holding various details about the player's
 * progress and achievements.
 * 
 * @Author Andrew and Vincent
 */

public class Player {
    // Player attributes
    private String playerName;
    private String lastLoginTime;
    private String lastClaimedDailyBonus;
    private int totalPlayerPoints;
    private int totalCorrectAnswers;
    private int totalIncorrectAnswers;
    private int currentScore;
    private int additionLevel;
    private int subtractionLevel;
    private int multiplicationLevel;
    private int divisionLevel;
    
    /**
     * Constructs a new Player with the specified details.
     *
     * @param playerName Name of the player
     * @param lastLoginTime The last time the player logged in
     * @param lastClaimedDailyBonus The last time the player claimed a daily bonus
     * @param totalPlayerPoints The total points the player has earned
     * @param totalCorrectAnswers The total number of correct answers given by the player
     * @param totalIncorrectAnswers The total number of incorrect answers given by the player
     * @param currentScore The player's current score
     * @param additionLevel The player's level in addition
     * @param subtractionLevel The player's level in subtraction
     * @param multiplicationLevel The player's level in multiplication
     * @param divisionLevel The player's level in division
     */

    public Player(String playerName, 
    		String lastLoginTime, 
    		String lastClaimedDailyBonus,
            int totalPlayerPoints, 
            int totalCorrectAnswers, 
            int totalIncorrectAnswers, 
            int currentScore,
            int additionLevel, 
            int subtractionLevel, 
            int multiplicationLevel,
            int divisionLevel)
            
    {
        this.playerName = playerName;
        this.lastLoginTime = lastLoginTime;
        this.lastClaimedDailyBonus = lastClaimedDailyBonus;
        this.totalPlayerPoints = totalPlayerPoints;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.totalIncorrectAnswers = totalIncorrectAnswers;
        this.currentScore = currentScore;
        this.additionLevel = additionLevel;
        this.subtractionLevel = subtractionLevel;
        this.multiplicationLevel = multiplicationLevel;
        this.divisionLevel = divisionLevel;
    }

    /**
     * Gets the player's name.
     *
     * @return The player's name.
     */

    public String getPlayerName() {
        return playerName;
    }

    /**
     * Updates the player's name and saves the game data
     *
     * @param playerName The new name of the player
     * @param gameFile The game file manager to handle saving
     */
    
    public void setPlayerName(String playerName, SaveGameManager gameFile) {
        this.playerName = playerName;
        gameFile.saveGameData(this);
    }

    /**
     * Gets the last login time of the player
     *
     * @return The last login time as a String
     */
    
    public String getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * Sets the last login time for the player and saves the updated data
     *
     * @param lastLoginTime The new last login time for the player
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setLastLoginTime(String lastLoginTime, SaveGameManager gameFile) {
        this.lastLoginTime = lastLoginTime;
        gameFile.saveGameData(this);
    }

    /**
     * Gets the last claimed daily bonus time for the player
     *
     * @return The last claimed daily bonus time
     */
    
    public String getLastClaimedDailyBonus() {
        return lastClaimedDailyBonus;
    }

    /**
     * Sets the last time the daily bonus was claimed for the player and saves the updated data
     *
     * @param lastClaimTime The new last claimed time for the daily bonus
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setLastClaimedDailyBonus(String lastClaimTime, SaveGameManager gameFile) {
        this.lastClaimedDailyBonus = lastClaimTime;
        gameFile.saveGameData(this);
    }

    /**
     * Gets the total player points
     *
     * @return The total player points
     */
    
    public int getTotalPlayerPoints() {
        return totalPlayerPoints;
    }

    /**
     * Sets the total player points and saves the updated data
     *
     * @param totalPlayerPoints The new total player points
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setTotalPlayerPoints(int totalPlayerPoints, SaveGameManager gameFile) {
        this.totalPlayerPoints = totalPlayerPoints;
        gameFile.saveGameData(this);
    }
    
    /**
     * Gets the total number of correct answers the player has given
     *
     * @return The total number of correct answers
     */
    
    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    /**
     * Sets the total number of correct answers the player has given and saves the updated data
     *
     * @param totalCorrectAnswers The new total number of correct answers
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setTotalCorrectAnswers(int totalCorrectAnswers, SaveGameManager gameFile) {
        this.totalCorrectAnswers = totalCorrectAnswers;
        gameFile.saveGameData(this);
    }
    
    /**
     * Gets the total number of incorrect answers the player has given
     *
     * @return The total number of incorrect answers
     */
    
    public int getTotalIncorrectAnswers() {
        return totalIncorrectAnswers;
       
    }

    /**
     * Sets the total number of incorrect answers the player has given and saves the updated data
     *
     * @param totalIncorrectAnswers The new total number of incorrect answers
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setTotalIncorrectAnswers(int totalIncorrectAnswers, SaveGameManager gameFile) {
        this.totalIncorrectAnswers = totalIncorrectAnswers;
        gameFile.saveGameData(this);
    }

    /**
     * Gets the current score of the player
     *
     * @return The current score
     */
    
    public int getCurrentScore() {
        return currentScore;
        
    }

    /**
     * Sets the current score of the player and saves the updated data
     *
     * @param currentScore The new current score
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setCurrentScore(int currentScore, SaveGameManager gameFile) {
        this.currentScore = currentScore;
        gameFile.saveGameData(this);
    }

    /**
     * Gets the player's level in addition
     *
     * @return The addition level
     */
    
    public int getAdditionLevel() {
    	System.out.println("test");
        return additionLevel;
    }

    /**
     * Sets the player's level in addition and saves the updated data
     *
     * @param additionLevel The new level in addition
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setAdditionLevel(int additionLevel, SaveGameManager gameFile) {
        this.additionLevel = additionLevel;
        gameFile.saveGameData(this);
    }

    /**
     * Gets the player's level in subtraction
     *
     * @return The subtraction level
     */
    
    public int getSubtractionLevel() {
        return subtractionLevel;
    }

    /**
     * Sets the player's level in subtraction and saves the updated data
     *
     * @param subtractionLevel The new level in subtraction
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setSubtractionLevel(int subtractionLevel, SaveGameManager gameFile) {
        this.subtractionLevel = subtractionLevel;
        gameFile.saveGameData(this);
    }

    /**
     * Gets the player's level in multiplication
     *
     * @return The multiplication level
     */
    
    public int getMultiplicationLevel() {
        return multiplicationLevel;
    }

    /**
     * Sets the player's level in multiplication and saves the updated data
     *
     * @param multiplicationLevel The new level in multiplication
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setMultiplicationLevel(int multiplicationLevel, SaveGameManager gameFile) {
        this.multiplicationLevel = multiplicationLevel;
        gameFile.saveGameData(this);
    }

    /**
     * Gets the player's level in division
     *
     * @return The division level
     */
    
    public int getDivisionLevel() {
        return divisionLevel;
    }

    /**
     * Sets the player's level in division and saves the updated data
     *
     * @param divisionLevel The new level in division
     * @param gameFile The game file manager to handle data saving
     */
    
    public void setDivisionLevel(int divisionLevel, SaveGameManager gameFile) {
        this.divisionLevel = divisionLevel;
        gameFile.saveGameData(this);
    }

    /**
     * Checks if a specified level is unlocked for a given section
     *
     * @param section The game section (ex, Addition, Subtraction)
     * @param level The level to check
     * @return true if the level is unlocked, false otherwise
     */
    
    public boolean isLevelUnlocked(String section, int level) {
        int currentLevel = 0;
        switch (section) {
            case "Addition":
                currentLevel = additionLevel;
                break;
            case "Subtraction":
                currentLevel = subtractionLevel;
                break;
            case "Multiplication":
                currentLevel = multiplicationLevel;
                break;
            case "Division":
                currentLevel = divisionLevel;
                break;
        }
        return level <= currentLevel + 1; // Unlocks the next level
    }


    /**
     * Returns a string representation of the player, which includes all player details
     *
     * @return A comma-separated string of player details
     */
    @Override
    public String toString() {
        return playerName + "," + lastLoginTime + "," + lastClaimedDailyBonus + "," +
                totalPlayerPoints + "," + totalCorrectAnswers + ","+ totalIncorrectAnswers + "," + currentScore + "," +
                additionLevel + "," + subtractionLevel + "," + multiplicationLevel + "," +
                divisionLevel;
    }
}

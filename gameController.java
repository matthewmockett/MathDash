/**
 * The gameController class manages the game's state, including the player, the CPU player,
 * the current level, and the game's active status. It also handles player actions and
 * determines if the game ends based on the score.
 * 
 * @author Matthew
 */

public class gameController {

    private Player player;
    private CPUPlayer cpuPlayer;
    private int currentLevel;
    private boolean isGameActive;
    private final int winScore = 10;  
    private int score;


    /**
     * Constructor for gameController
     * 
     * @param player The human player participating in the game
     * @param cpuPlayer The CPU player participating in the game
     */
    
    public gameController(Player player, CPUPlayer cpuPlayer) {
        this.player = player;
        this.cpuPlayer = cpuPlayer;
    }

    /**
     * Starts a new game at the specified level.
     * 
     * @param level The level at which the game should start.
     */
    
    public void startGame(int level) {
        this.isGameActive = true;
        this.currentLevel = level;
        cpuPlayer.setCurrentLevel(level);
        player.resetScore(); // Reset the human player's score
        cpuPlayer.resetScore(); // Reset the CPU player's score
        
    }

    /**
     * Ends the current game and updates the games active status
     */
    
    public void endGame() {
        this.isGameActive = false;
 
    }

    /**
     * Processes the players answer and updates the game state
     * 
     * @param answer The answer provided by the player
     */
    
    public void handlePlayerAnswer(int answer) {
        if (isGameActive) {
            boolean isCorrect = player.answerQuestion(currentQuestion, answer);

            if (isCorrect) {
                score++;  // Increment score for correct answer
                if (score >= winScore) {
                    endGame();  // End the game if the win condition is met
                    return;  
                }
            }

            
            cpuPlayer.takeTurn();

            
            updateGameStateAfterPlayerMove();
        }
    }

    
    /**
     * Updates the game state after each player move
     */
    private void updateGameStateAfterPlayerMove() {
        
    }

    /**
     * Gets the CPU player instance
     * 
     * @return The CPU player
     */
    
    public CPUPlayer getCpuPlayer() {
        return cpuPlayer;
    }

    /**
     * Sets the CPU player instance
     * 
     * @param cpuPlayer The CPU player to set
     */
    
    public void setCpuPlayer(CPUPlayer cpuPlayer) {
        this.cpuPlayer = cpuPlayer;
    }

    /**
     * Gets the current game level
     * 
     * @return The current level
     */
    
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Sets the current game level
     * 
     * @param currentLevel The level to set as the current level
     */
    
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}


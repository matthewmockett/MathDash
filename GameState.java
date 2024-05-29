
/**
 * The GameState class represents the state of the game, including the current level and pause status.
 * @author team42
 */
class GameState {
    private int level;
    private boolean isPaused;

    /**
     * Constructs a new GameState object with the specified level and pause status.
     * 
     * @param level     The current level of the game.
     * @param isPaused  A boolean indicating whether the game is paused or not.
     */
    public GameState(int level, boolean isPaused) {
        this.level = level;
        this.isPaused = isPaused;
    }

    /**
     * Retrieves the current level of the game.
     * 
     * @return The current level of the game.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the game to the specified value.
     * 
     * @param level The new level of the game.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Checks if the game is currently paused.
     * 
     * @return true if the game is paused, false otherwise.
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Sets the pause status of the game.
     * 
     * @param paused A boolean indicating whether the game should be paused or not.
     */
    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}

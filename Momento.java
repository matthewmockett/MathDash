/**
 * The Memento class represents a snapshot of the game state at a specific point in time.
 * @Author Vincent
 */

class Memento {
    private int playerPos;
    private int cpuPos;
    private GameState gameState;

    /**
     * Constructs a new Memento object with the specified player position, CPU position, and game state.
     * 
     * @param playerPos The position of the player.
     * @param cpuPos    The position of the CPU.
     * @param gameState The state of the game.
     */
    public Memento(int playerPos, int cpuPos, GameState gameState) {
        this.playerPos = playerPos;
        this.cpuPos = cpuPos;
        this.gameState = gameState;
    }

    /**
     * Retrieves the player position stored in this memento.
     * 
     * @return The player position.
     */
    public int getPlayerPos() {
        return playerPos;
    }

    /**
     * Retrieves the CPU position stored in this memento.
     * 
     * @return The CPU position.
     */
    public int getCpuPos() {
        return cpuPos;
    }

    /**
     * Retrieves the game state stored in this memento.
     * 
     * @return The game state.
     */
    public GameState getGameState() {
        return gameState;
    }
}

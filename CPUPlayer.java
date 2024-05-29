import java.util.Random;
/**
 * The CPUPlayer class represents the CPU racing against the Player and how it will navigate to the finish line.
 * 
 * @author Matthew Lee-Mockett
 */

public class CPUPlayer {

    private int currentLevel;
    private int score;
    private Random random;

 /**
  * Constructs a new CPU player with their starting number of points at the beginner of the level being 0.
  * 
  */
    public CPUPlayer() {
        this.score = 0;
        this.random = new Random();
    }

    /**
     * Simulate the CPU taking its turn and moving based on difficulty level
     * The CPU moves forward if their answer is correct and backwards if incorrect
     * However, since it is just the CPU, we can implement this by randomly deciding if it gets the question
     * correct or not by just randomly determining if it should move backwards or forwards where the odds of that
     * is based on the difficulty
     */
 // Inside CPUPlayer class
    public boolean takeTurn(int currentLevel) {
        this.currentLevel = currentLevel; // Ensure the current level is set correctly before deciding
        boolean correct = isAnswerCorrect();
        // Assuming each correct answer moves the car by 20 pixels, and incorrect answers don't move it.
        score += correct ? 20 : 0; // Update this logic as needed for your game
        return correct;
    }

    
    // Determines if the CPU gets the answer correct or not
    private boolean isAnswerCorrect() {
    	int probability = correctAnswerProb();
    	return random.nextInt(100) < probability; // True if random number is less than probability therefore implementing the CPUs difficulty
    }

    // Determine whether the CPU should move forward this turn
    private int correctAnswerProb() {
        switch (currentLevel) {
            case 1:
            case 2:
                return 35; //35% of being correct for levels 1 and 2
            case 3:
            case 4:
            case 5:
                return 55; // 55% chance of being correct for levels 3,4,5
            case 6:
            case 7:
            case 8:
                return 75; // 75% chance of being correct for levels 6,7,8
            case 9:
            case 10:
            	return 90; // 90% chance of being correct for levels 6,7,8
            default:
                return 0; // Default if case is out of range
        }
    }
    
    /**
     * Method that adjusts the CPUs difficulty based on the current level
     * 
     * @param level
     */
    public void setCurrentLevel(int level) {
    	this.currentLevel = level; 
    	/* The CPU's probability of getting the answer correct is based on the level selected 
    	*  by the player and this adjustment will affect the chances the answer is correct
    	*/
    }
    
    /**
     * Retrieves the difficulty of the CPU
     * 
     * @return currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Sets the score if the score needs to be set for any variety of reasons although uncommon
     * 
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }
    
	/**
	 * Retrieves the current score/progress of the CPU while racing
	 * 
	 * @return score
	 */
    
    public int getScore() {
        return score;
    }

}

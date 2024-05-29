import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class CPUPlayerTest {

    // Helper class to mock Random behavior
    private static class MockRandom extends Random {
        private final int fixedValue;

        public MockRandom(int fixedValue) {
            this.fixedValue = fixedValue;
        }

        @Override
        public int nextInt(int bound) {
            return fixedValue;
        }
    }
    @Test
    void testTakeTurnStatistics() {
        CPUPlayer cpuPlayer = new CPUPlayer();
        int levelsToTest = 10; 
        int trialsPerLevel = 10000;

        for (int level = 1; level <= levelsToTest; level++) {
            cpuPlayer.setCurrentLevel(level);
            int successfulTurns = 0;

            for (int i = 0; i < trialsPerLevel; i++) {
                if (cpuPlayer.takeTurn(level)) {
                    successfulTurns++;
                }
            }

            double successRate = (double) successfulTurns / trialsPerLevel * 100;
            
            System.out.println("Level " + level + " Success Rate: " + successRate + "%");
        }
    }
    @Test
    void testScoreIncrement() {
        CPUPlayer cpuPlayer = new CPUPlayer();
        int level = 1; // Using level 1 for this test
        cpuPlayer.setCurrentLevel(level);
        int trials = 10000;
        int scoreIncreasePerCorrectTurn = 20;

        for (int i = 0; i < trials; i++) {
            cpuPlayer.takeTurn(level);
        }

        // Calculate expected score range based on probability
        int expectedMinScore = (int) (trials * 0.30 * scoreIncreasePerCorrectTurn); // 30% success rate
        int expectedMaxScore = (int) (trials * 0.40 * scoreIncreasePerCorrectTurn); // 40% success rate

        int actualScore = cpuPlayer.getScore();

        
        assertTrue(actualScore >= expectedMinScore && actualScore <= expectedMaxScore,
                "Actual score (" + actualScore + ") should be within the expected range (" 
                + expectedMinScore + " to " + expectedMaxScore + ").");
    }
}



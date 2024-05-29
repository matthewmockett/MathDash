import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CpuPlayerAndCpuRaceTrackIntegrationTest {

    private CPUPlayer cpuPlayer;
    private CpuRaceTrack cpuRaceTrack;
    private final int trackLength = 100; // track length of 100 for this test

    @Before
    public void setUp() {
        cpuRaceTrack = new CpuRaceTrack(trackLength);
        cpuPlayer = new CPUPlayer();
        cpuPlayer.setCurrentLevel(5); // level 5 difficulty for the CPU player
    }

    @Test
    public void testCpuMovementOnTrack() {
        // Simulate the CPU player taking a turn
        boolean isCorrect = cpuPlayer.takeTurn(cpuPlayer.getCurrentLevel());

        // Based on whether the CPU player answered correctly, update the track position
        if (isCorrect) {
            cpuRaceTrack.moveCpu(20); // Test using the CPU moving 20 units per correct answer
        }

        // CPUs position on the track is as expected
        if (isCorrect) {
            assertEquals("CPU player's position should be updated on the track", 20, cpuRaceTrack.getCpuPosition());
        } else {
            assertEquals("CPU player's position should remain unchanged", 0, cpuRaceTrack.getCpuPosition());
        }
    }

    @Test
    public void testCpuWinningCondition() {
        //  CPU player getting enough right answers to win
        while (cpuRaceTrack.getCpuPosition() < trackLength) {
            if (cpuPlayer.takeTurn(cpuPlayer.getCurrentLevel())) {
                cpuRaceTrack.moveCpu(20);
            }
        }

        // Check if the CPU has reached/exceeded the track length
        assertTrue("CPU should have reached the end of the track", cpuRaceTrack.getCpuPosition() >= trackLength);
    }
}


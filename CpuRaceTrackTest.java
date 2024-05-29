import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CpuRaceTrackTest {
    private CpuRaceTrack track;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
        track = new CpuRaceTrack(100);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

    @Test
    void moveCpu() {
        // Move CPU by a distance of 50 and check if the position is updated correctly
        track.moveCpu(50);
        assertEquals(50, track.getCpuPosition(), "CPU position should be 50 after moving by 50.");

        // Further move CPU by 50 and check if it reaches the end of the track
        track.moveCpu(50);
        assertEquals(100, track.getCpuPosition(), "CPU should reach the end of the track.");
    }
    
    @Test
    void getCpuPosition() {
        // Initially, CPU position should be 0
        assertEquals(0, track.getCpuPosition(), "Initial CPU position should be 0.");

        // Move CPU and check position again
        track.moveCpu(30);
        assertEquals(30, track.getCpuPosition(), "CPU position should be 30 after moving.");
    }
    
    @Test
    void getTrackLength() {
        // The track length should match the length specified at initialization
        assertEquals(100, track.getTrackLength(), "Track length should be 100.");
    }

}

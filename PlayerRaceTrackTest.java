import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerRaceTrackTest {

    private PlayerRaceTrack track;

    @BeforeEach
    void setUp() {
        // Initialize PlayerRaceTrack with a track length of 100 for each test
        track = new PlayerRaceTrack(100);
    }

    @Test
    void testMovePlayer_ForwardMovement() {
        // Arrange
        int initialPosition = track.getPlayerPosition();
        int moveDistance = 10;

        // Act
        track.movePlayer(moveDistance);

        // Assert
        assertEquals(initialPosition + moveDistance, track.getPlayerPosition(), "Player should move forward by the specified distance.");
    }

    @Test
    void testMovePlayer_ExactWinningCondition() {
        // Arrange
        int moveDistance = 100;

        // Act
        track.movePlayer(moveDistance);

        // Assert
        assertEquals(track.getTrackLength(), track.getPlayerPosition(), "Player should win by reaching the exact end of the track.");
    }

    @Test
    void testMovePlayer_OverWinningCondition() {
        // Arrange
        int moveDistance = 110; // More than the track length

        // Act
        track.movePlayer(moveDistance);

        // Assert
        assertEquals(track.getTrackLength(), track.getPlayerPosition(), "Player position should not exceed the track length.");
    }

    @Test
    void testGetPlayerPosition_InitialPosition() {
        // Assert
        assertEquals(0, track.getPlayerPosition(), "Initial position of the player should be 0.");
    }

    @Test
    void testGetTrackLength() {
        // Assert
        assertEquals(100, track.getTrackLength(), "Track length should be accurately reported.");
    }
}

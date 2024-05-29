import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void testIsLevelUnlocked() {
        Player player = new Player("Homer Simpson", "2024-03-30T12:00:00", "2024-03-29", 100, 50, 30, 20, 3, 2, 4, 1);
        
        // Test for each category
        assertTrue(player.isLevelUnlocked("Addition", 4), "Player should have level 4 unlocked in Addition");
        assertFalse(player.isLevelUnlocked("Addition", 5), "Player should not have level 5 unlocked in Addition");

        assertTrue(player.isLevelUnlocked("Subtraction", 3), "Player should have level 3 unlocked in Subtraction");
        assertFalse(player.isLevelUnlocked("Subtraction", 4), "Player should not have level 4 unlocked in Subtraction");

        assertTrue(player.isLevelUnlocked("Multiplication", 5), "Player should have level 5 unlocked in Multiplication");
        assertFalse(player.isLevelUnlocked("Multiplication", 6), "Player should not have level 6 unlocked in Multiplication");

        assertTrue(player.isLevelUnlocked("Division", 2), "Player should have level 2 unlocked in Division");
        assertFalse(player.isLevelUnlocked("Division", 3), "Player should not have level 3 unlocked in Division");
    }

    @Test
    void testToString() {
        Player player = new Player("Homer Simpson", "2024-03-30T12:00:00", "2024-03-29", 100, 50, 30, 20, 3, 2, 4, 1);
        String expected = "Homer Simpson,2024-03-30T12:00:00,2024-03-29,100,50,30,20,3,2,4,1";
        assertEquals(expected, player.toString(), "The toString method does not format the player's information as expected");
    }
}


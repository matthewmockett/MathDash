import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        // Setup with a known list of players
        List<Player> players = Arrays.asList(
            new Player("Alice", "2022-03-01T14:00", "2022-03-01", 100, 10, 5, 50, 1, 2, 3, 4),
            new Player("Bob", "2022-03-02T15:00", "2022-03-02", 200, 20, 10, 100, 2, 3, 4, 5),
            new Player("Charlie", "2022-03-03T16:00", "2022-03-03", 300, 30, 15, 150, 3, 4, 5, 6),
            new Player("Dave", "2022-03-04T17:00", "2022-03-04", 400, 40, 20, 200, 4, 5, 6, 7),
            new Player("Eve", "2022-03-05T18:00", "2022-03-05", 500, 50, 25, 250, 5, 6, 7, 8),
            new Player("Frank", "2022-03-06T19:00", "2022-03-06", 250, 25, 12, 125, 2, 3, 4, 5)
        );
        scoreboard = new Scoreboard(players);
    }

    @Test
    void testGetTopPlayers() {
        List<Player> topPlayers = scoreboard.getTopPlayers();
        assertEquals(5, topPlayers.size(), "Should return top 5 players");
        assertEquals("Eve", topPlayers.get(0).getPlayerName(), "Top player should be Eve");
    }

    @Test
    void testGetTopPlayerName() {
        assertEquals("Eve", scoreboard.getTopPlayerName(1), "Top player's name should be Eve");
        assertEquals("N/A", scoreboard.getTopPlayerName(6), "Should return 'N/A' for rank out of range");
    }

    @Test
    void testGetTopPlayerPoints() {
        assertEquals("500", scoreboard.getTopPlayerPoints(1), "Top player's points should be 500");
        assertEquals("-1", scoreboard.getTopPlayerPoints(6), "Should return '-1' for rank out of range");
    }
}

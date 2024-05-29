import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDateTime;

class DailyLoginBonusTest {
    private DailyLoginBonus dailyLoginBonus;
    private SaveGameManager mockSaveGameManager;
    private Clock fixedClock;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        // Setup fixed clock
        fixedClock = Clock.fixed(Instant.parse("2024-03-30T10:15:30.00Z"), ZoneId.systemDefault());
        mockSaveGameManager = mock(SaveGameManager.class);
        mockPlayer = mock(Player.class);

        // Initialize DailyLoginBonus with the fixed clock and mocked dependencies
        dailyLoginBonus = new DailyLoginBonus("testUser", fixedClock);
        dailyLoginBonus.setSaveGameManager(mockSaveGameManager);
        dailyLoginBonus.setCurrentPlayer(mockPlayer);

        // Setup mock behavior
        when(mockPlayer.getLastClaimedDailyBonus()).thenReturn(LocalDateTime.now(fixedClock).minusDays(1).toString());
        when(mockPlayer.getTotalPlayerPoints()).thenReturn(1000);
    }

    @Test
    void canClaim_WhenAllowed_ShouldReturnTrue() {
        //act
        boolean canClaim = dailyLoginBonus.canClaim();

        //assert
        assertTrue(canClaim, "canClaim should return true when the user is eligible to claim the bonus.");
    }

    @Test
    void claimBonus_ShouldIncreasePoints() {
        // Arrange
        final int initialPoints = mockPlayer.getTotalPlayerPoints();

        // act
        dailyLoginBonus.claimBonus();

        //assert
        verify(mockPlayer).setTotalPlayerPoints(initialPoints + dailyLoginBonus.getBonusPoints(), mockSaveGameManager);
    }


}


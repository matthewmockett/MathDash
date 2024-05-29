import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

class SaveGameManagerTest {
    private SaveGameManager saveGameManager;
    private final String testFilename = "testPlayers.csv";
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary test file
        testFile = new File(testFilename);
        testFile.createNewFile(); // Actually creates a file for testing
        saveGameManager = new SaveGameManager(testFilename);
    }

    @AfterEach
    void tearDown() {
        // delete the test file after each test
        testFile.delete();
    }

    @Test
    void readData_ReturnsNonEmptyListWhenFileHasData() throws IOException {
        //Given a file with one line of player data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("JohnDoe,2024-01-01 10:00:00,2024-01-01 10:00:00,100,10,5,50,1,1,1,1\n");
        }

        // when readData is called
        List<Player> players = saveGameManager.readData();

        // then the returned list should not be empty
        assertFalse(players.isEmpty(), "List of players should not be empty when file has data");
    }

    @Test
    void saveGameData_WritesDataToFile() throws IOException {
        // given a player and a mocked SaveGameManager with a real file
        Player player = new Player("JohnDoe", "2024-01-01 10:00:00", "2024-01-01 10:00:00",
                100, 10, 5, 50, 1, 1, 1, 1);

        //when saveGameData is called
        saveGameManager.saveGameData(player);

        // then the file should contain the player data
        assertTrue(testFile.length() > 0, "File should contain data after saveGameData is called");

        // read the file content
        List<String> lines = Files.readAllLines(testFile.toPath());
        assertTrue(lines.contains(player.toString()), "The file should contain the saved player data");
    }

}


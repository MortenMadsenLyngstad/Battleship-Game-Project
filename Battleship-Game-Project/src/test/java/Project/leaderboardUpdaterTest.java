package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class leaderboardUpdaterTest {
    private final String testPath1 = "C:/Users/Morte/2. semester/Objekt/Projekt/TDT4100-prosjekt-morteml/testFile1.txt";
    private final String testPath2 = "C:/Users/Morte/2. semester/Objekt/Projekt/TDT4100-prosjekt-morteml/testFile2.txt";
    private final String testPath3 = "C:/Users/Morte/2. semester/Objekt/Projekt/TDT4100-prosjekt-morteml/testFile3.txt";

    private leaderboardUpdater updater;
    private ListView<String> listView;
    
    @BeforeEach
    public void setUp() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // Ignore exception
        }
        updater = new leaderboardUpdater();
        listView = new ListView<>();
        updater.listView = listView;
        updater.items = listView.getItems();

    }

    @Test
    public void testCheckFile1() {
        updater.checkFile(testPath1);
        List<Player> players = updater.getPlayers();
        assertEquals(3, players.size());
        assertEquals("Morten", players.get(0).getName());
        assertEquals("Knut", players.get(1).getName());
        assertEquals("Hans", players.get(2).getName());
        assertEquals(16, players.get(0).getScore());
        assertEquals(17, players.get(1).getScore());
        assertEquals(18, players.get(2).getScore());
    }

    @Test
    public void testCheckFile2() {
        updater.checkFile(testPath2);
        List<Player> players = updater.getPlayers();
        assertEquals(4, players.size());
        assertEquals("Elias", players.get(0).getName());
        assertEquals("Trygve", players.get(1).getName());
        assertEquals("Harald", players.get(2).getName());
        assertEquals("Sofie", players.get(3).getName());
        assertEquals(18, players.get(0).getScore());
        assertEquals(22, players.get(1).getScore());
        assertEquals(27, players.get(2).getScore());
        assertEquals(42, players.get(3).getScore());
    }

    @Test
    public void testCheckFile3() {
        updater.checkFile(testPath3);
        List<Player> players = updater.getPlayers();
        assertEquals(3, players.size());
        assertEquals("Marius", players.get(0).getName());
        assertEquals("Sander", players.get(1).getName());
        assertEquals("Edvard", players.get(2).getName());
        assertEquals(34, players.get(0).getScore());
        assertEquals(62, players.get(1).getScore());
        assertEquals(80, players.get(2).getScore());
    }
}

package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testConstructor() {
        Player player = new Player("Test", 0);
        assertEquals("Test", player.getName());
        assertEquals(0, player.getScore());
    }

    @Test
    public void testCompareTo() {
        Player player1 = new Player("Test1", 0);
        Player player2 = new Player("Test2", 1);
        Player player3 = new Player("Test3", 2);
        assertEquals(0, player1.compareTo(player1));
        assertEquals(-1, player1.compareTo(player2));
        assertEquals(-1, player1.compareTo(player3));
        assertEquals(1, player2.compareTo(player1));
        assertEquals(0, player2.compareTo(player2));
        assertEquals(-1, player2.compareTo(player3));
        assertEquals(1, player3.compareTo(player1));
        assertEquals(1, player3.compareTo(player2));
        assertEquals(0, player3.compareTo(player3));
    }
}

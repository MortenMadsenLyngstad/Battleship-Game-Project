package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShipTest {

    private Ship ship;

    @BeforeEach
    public void setUp() {
        ship = new Ship(5, true);
    }

    @Test
    public void testConstructor() {
        assertEquals(5, ship.getLength());
        assertEquals(5, ship.getHealth());
        assertEquals(true, ship.isVertical());
        assertEquals(true, ship.isAlive());
    }

    @Test
    public void testHit() {
        assertEquals(5, ship.getLength());
        assertEquals(true, ship.isAlive());
        ship.hit();
        assertEquals(4, ship.getHealth());
        ship.hit();
        ship.hit();
        ship.hit();
        ship.hit();
        assertEquals(false, ship.isAlive());
    }
}
package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;
    private Ship ship;

    @BeforeEach
    public void setUp() {
        board = new Board(false, null);
        ship = new Ship(3, false);
    }
    @Test
    public void testConstructor() {
        assertEquals(100, board.getBoardSize());
        
    }
    @Test
    public void testSetShipVertical() {
        Ship ship2 = new Ship(5, true);
        board.setShip(0, 0, ship2);
        assertEquals(5, board.getShip(0, 0).getLength());
        assertEquals(5, board.getShip(0, 1).getLength());
        assertEquals(5, board.getShip(0, 2).getLength());
        assertEquals(5, board.getShip(0, 3).getLength());
        assertEquals(5, board.getShip(0, 4).getLength());
        
        
    }

    @Test
    public void testSetShipHorizontal() {
        board.setShip(5, 5, ship);
        assertEquals(3, board.getShip(5, 5).getLength());
        assertEquals(3, board.getShip(6, 5).getLength());
        assertEquals(3, board.getShip(7, 5).getLength());
    }

    /**
     * Tests the possiblePlacement method.
     * ! This also tests the surroundingCells method.
     * The possiblePlacement method returns true if the ship can be placed at the given coordinates.
     */
    @Test
    public void testPossiblePlacement() {
        Ship ship2 = new Ship(2, false);
        board.setShip(5, 5, ship);
        assertEquals(false, board.possiblePlacement(5, 5, ship2), "");
        assertEquals(false, board.possiblePlacement(4, 5, ship2));
        assertEquals(false, board.possiblePlacement(4, 6, ship2));
        assertEquals(false, board.possiblePlacement(4, 4, ship2));
        assertEquals(false, board.possiblePlacement(8, 6, ship2));
        assertEquals(false, board.possiblePlacement(8, 4, ship2));
        assertEquals(false, board.possiblePlacement(9, 9, ship2));

        assertEquals(true,  board.possiblePlacement(0, 0, ship2));
        assertEquals(true,  board.possiblePlacement(0, 9, ship2));
        assertEquals(true,  board.possiblePlacement(2, 5, ship2));
        assertEquals(true,  board.possiblePlacement(8, 9, ship2));
    }

    @Test
    public void testGetShip() {
        board.setShip(5, 5, ship);
        assertEquals(ship, board.getShip(5, 5));
        assertEquals(ship, board.getShip(6, 5));
        assertEquals(ship, board.getShip(7, 5));
        assertEquals(null, board.getShip(8, 5));
    }

    /**
     * 
     */
    @Test
    public void testGetCell() {
        board.setShip(5, 5, ship);
        assertEquals(true, board.getCell(5, 5).hasShip());
        assertEquals(true, board.getCell(6, 5).hasShip());
        assertEquals(true, board.getCell(7, 5).hasShip());
        assertEquals(false, board.getCell(8, 5).hasShip());
    }

    @Test
    public void testValidPoint() {
        assertEquals(true, board.validPoint(0, 0));
        assertEquals(true, board.validPoint(9, 9));
        assertEquals(true, board.validPoint(5, 5));
        assertEquals(false, board.validPoint(-1, 0));
        assertEquals(false, board.validPoint(0, -1));
        assertEquals(false, board.validPoint(10, 0));
        assertEquals(false, board.validPoint(0, 10));
    }
}

package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

public class CellTest {
    @Test
    public void testConstructor() {
        Board board = new Board(false, null);
        Cell cell = new Cell(3, 7, board);
        assertEquals(30.0, cell.getWidth());
        assertEquals(30.0, cell.getHeight());
        assertEquals(3, cell.cellX());
        assertEquals(7, cell.cellY());
        assertEquals(false, cell.wasShot());
        assertEquals(false, cell.hasShip());
        assertEquals(Color.ALICEBLUE, cell.getFill());
        assertEquals(Color.LIGHTGRAY, cell.getStroke());
    }
    

    @Test
    public void testShot() {
        Board board = new Board(false, null);
        Cell cell = new Cell(3, 7, board);
        assertEquals(false, cell.wasShot());
        assertEquals(Color.ALICEBLUE, cell.getFill());
        assertEquals(Color.LIGHTGRAY, cell.getStroke());

        cell.shot();
        
        assertEquals(true, cell.wasShot());
        assertEquals(Color.DARKBLUE, cell.getFill());
        assertEquals(Color.LIGHTGRAY, cell.getStroke());
        
        Cell cell2 = new Cell(0, 0, board);
        Ship ship = new Ship(3, false);

        cell2.ship = ship;
        cell2.setHasShip(true);

        assertEquals(false, cell2.wasShot());
        assertEquals(true, cell2.hasShip());
        assertEquals(false, cell2.getFill().equals(Color.RED));

        cell2.shot();

        assertEquals(true, cell2.wasShot());
        assertEquals(Color.RED, cell2.getFill());
    }
}

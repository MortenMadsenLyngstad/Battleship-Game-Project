package Project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
  private int x, y;
  private boolean hasShip = false;
  private boolean wasShot = false;
  public Ship ship;

  private Board board;

  /**
   * Creates a new cell with the given coordinates and board.
   * 
   * @param x     the x coordinate
   * @param y     the y coordinate
   * @param board the board
   *              super(30,30) = cell size
   *              Here the super keyword is used to call the constructor of the
   *              parent class Rectangle.
   *              setFill(Color.ALICEBLUE) = cell color
   *              setStroke(Color.LIGHTGRAY) = cell border color
   */
  public Cell(int x, int y, Board board) {
    super(30, 30);
    this.x = x;
    this.y = y;
    this.board = board;
    setFill(Color.ALICEBLUE);
    setStroke(Color.LIGHTGRAY);
  }

  /**
   * Returns the x coordinate of the cell.
   * 
   * @return the x coordinate of the cell
   */
  public int cellX() {
    return this.x;
  }

  /**
   * Returns the y coordinate of the cell.
   * 
   * @return the y coordinate of the cell
   */
  public int cellY() {
    return this.y;
  }

  public boolean hasShip() {
    return hasShip;
  }

  public void setHasShip(boolean hasShip) {
    this.hasShip = hasShip;
  }

  public boolean wasShot() {
    return wasShot;
  }

  public void setWasShot() {
    this.wasShot = true;
  }

  /**
   * Returns true if the cell has a ship.
   * Sets wasShot to true and changes the color of the cell to dark blue.
   * If the cell has a ship the ship is hit and the color of the cell is changed
   * to red.
   * If the ship is dead the shipsAlive variable in the board is decreased by one.
   * The for loops inside the if statement is used to change the color of the
   * cells around the ship to dark blue if the ship is sunk
   * If the ship is vertical the x-coordinate stays the same and thus only the
   * y-coordinate needs to change
   * If the ship is horizontal the y-coordinate stays the same and thus only the
   * x-coordinate needs to change
   * 
   * @return true if the cell has a ship
   */
  public boolean shot() {
    wasShot = true;
    setFill(Color.DARKBLUE);

    if (hasShip) {
      ship.hit();
      setFill(Color.RED);
      if (!ship.isAlive()) {
        board.shipsAlive--;

        if (battleshipGame.enemyMove) {
          if (ship.isVertical()) {
            int startY = battleshipGame.myShipPosY.get(5 - ship.getLength());
            for (int i = startY; i < startY + ship.getLength(); i++) {
              for (Cell c : board.checkSurroundingCells(x, i)) {
                c.setFill(Color.DARKBLUE);
                c.setWasShot();
              }
            }
            for (int i = startY; i < startY + ship.getLength(); i++) {
              Cell c = board.getCell(x, i);
              c.setFill(Color.RED);
            }
          }

          else {
            int startX = battleshipGame.myShipPosX.get(5 - ship.getLength());
            for (int i = startX; i < startX + ship.getLength(); i++) {
              for (Cell c : board.checkSurroundingCells(i, y)) {
                c.setFill(Color.DARKBLUE);
                c.setWasShot();
              }
            }
            for (int i = startX; i < startX + ship.getLength(); i++) {
              Cell c = board.getCell(i, y);
              c.setFill(Color.RED);
            }
          }
        } else {
          if (ship.isVertical()) {
            int startY = battleshipGame.botShipPosY.get(5 - ship.getLength());
            for (int i = startY; i < startY + ship.getLength(); i++) {
              for (Cell c : board.checkSurroundingCells(x, i)) {
                c.setFill(Color.DARKBLUE);
                c.setWasShot();
              }
            }
            for (int i = startY; i < startY + ship.getLength(); i++) {
              Cell c = board.getCell(x, i);
              c.setFill(Color.RED);
            }
          }

          else {
            int startX = battleshipGame.botShipPosX.get(5 - ship.getLength());
            for (int i = startX; i < startX + ship.getLength(); i++) {
              for (Cell c : board.checkSurroundingCells(i, y)) {
                c.setFill(Color.DARKBLUE);
                c.setWasShot();
              }
            }
            for (int i = startX; i < startX + ship.getLength(); i++) {
              Cell c = board.getCell(i, y);
              c.setFill(Color.RED);
            }
          }
        }
        return false;
      }
    }
    return false;
  }
}

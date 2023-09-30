package Project;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
 
public class Board extends Parent {
  private VBox rows = new VBox();
  private boolean enemyBoard = false;
  public int shipsAlive = 5;
  public int numCells = 0;

  /**
   * Constructor for the Board class.
   * @param enemyBoard true if the board is the enemy board
   * @param eventHandler the event handler for the board
   * EventHandler<? super MouseEvent>" represents a generic type that specifies 
   * an event handler that can handle events of type MouseEvent or any of its superclasses.
   * The "?" is a wildcard that represents an unknown type.
   * The "super" keyword is used to specify that the unknown type must be a superclass of MouseEvent.
   * The for loop creates a new Cell object for each cell on the 10x10 board.
   */
  public Board(boolean enemyBoard, EventHandler<? super MouseEvent> eventHandler) {
    this.enemyBoard = enemyBoard;
    for(int y = 0; y < 10; y++) {
      HBox row = new HBox();
      for(int x = 0; x < 10; x++) {
        Cell cell = new Cell(x, y, this);
        cell.setOnMouseClicked(eventHandler);
        row.getChildren().add(cell);
        this.numCells++;
      } 
      rows.getChildren().add(row);
    }
      getChildren().add(rows);
  }

  public int getBoardSize() {
    return this.numCells;
  }

  public Ship getShip(int x, int y) {
    return getCell(x, y).ship;
  }

  /**
   * Returns true if the ship was successfully placed on the board.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param ship the ship to be placed
   * @return true if the ship was successfully placed on the board
   */
  public boolean setShip(int x, int y, Ship ship) {
    if(possiblePlacement(x, y, ship)) {
      if(ship.isVertical()) {
        for (int i = y; i < y + ship.getLength(); i++) {
          Cell cell = getCell(x, i);
          cell.ship = ship;
          cell.setHasShip(true);

          if (!enemyBoard) {
            cell.setFill(Color.DARKCYAN);
            cell.setStroke(Color.DARKGRAY);
          }
        }
      }
      else {
        for (int i = x; i < x + ship.getLength(); i++) {
          Cell cell = getCell(i, y);
          cell.ship = ship;
          cell.setHasShip(true);

          if (!enemyBoard) {
            cell.setFill(Color.DARKCYAN);
            cell.setStroke(Color.DARKGRAY);
        }
      }
    }
    return true;
  }
  return false;
}

  /**
   * Returns true if the given coordinates are valid points on the board.
   * @param x the x coordinate
   * @param y the y coordinate
   * @param ship the ship to be placed
   * @return true if the given coordinates are valid points on the board
   */
  public boolean possiblePlacement(int x, int y, Ship ship) {
    if(ship.isVertical()) {
      for (int i = y; i < y + ship.getLength(); i++) {
        if(!validPoint(x, i)) {
          return false;
        }

        Cell cell = getCell(x, i);
        if(cell.hasShip()) {
          return false;
        }

        for (Cell c : checkSurroundingCells(x, i)) {
          if(!validPoint(x, i) || c.hasShip()){
            return false;
          }
        }
      }
    }
    else {
      for (int i = x; i < x + ship.getLength(); i++) {
        if(!validPoint(i, y)) {
          return false;
        }

        Cell cell = getCell(i, y);
        if(cell.hasShip()) {
          return false;
        }

        for (Cell c : checkSurroundingCells(i, y)) {
          if(!validPoint(i, y) || c.hasShip()){
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * This method returns a list of cells surrounding the given coordinates.
   * @param x the x coordinate
   * @param y the y coordinate
   * @return a list of cells surrounding the given coordinates
   */
  public Cell[] checkSurroundingCells(int x, int y) {
    Point2D[] cells = new Point2D[] {
      new Point2D(x-1, y),
      new Point2D(x+1, y),
      new Point2D(x, y-1),
      new Point2D(x, y+1),
      new Point2D(x+1, y+1),
      new Point2D(x+1, y-1),
      new Point2D(x-1, y+1),
      new Point2D(x-1, y-1)
    };

    List<Cell> surroundingCells = new ArrayList<Cell>();

    for (Point2D cell : cells) {
      if (validPoint(cell)) {
        surroundingCells.add(getCell((int) cell.getX(), (int) cell.getY()));
      }
    }

    return surroundingCells.toArray(new Cell[surroundingCells.size()]);
  }

  /**
   * This method returns the cell at the given coordinates.
   * @param x the x coordinate
   * @param y the y coordinate
   * @return the cell at the given coordinates
   * @throws IndexOutOfBoundsException if the coordinates are out of bounds
   */
  public Cell getCell(int x, int y) {
    if (x < 10 || y < 10){
      return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }
    else {
      throw new IndexOutOfBoundsException("The coordinates are out of bounds.");}
  }

  /**
   * This method returns true if the point is within the board.
   * @param point the point to check
   * @return true if the point is within the board
   */
  public boolean validPoint(Point2D point) {
    return validPoint(point.getX(), point.getY());
  }
  
    /**
   * This method returns true if the point (x,y) is within the board.
   * @param x the x coordinate
   * @param y the y coordinate
   * @return true if the point is within the board
   */
  public boolean validPoint(double x, double y) {
    return x >= 0 && y >= 0 && x < 10 && y < 10;
  }
  // public leaderboardUpdater updater = new leaderboardUpdater();

  /**
   * This method is called when the player has won.
   * It shows a message and exits the program.
   */
  public void gameOverWin() {
    Util.writeFileWin();

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Congratulations!");
    alert.setHeaderText("You Win!");
    alert.showAndWait();
    System.exit(0);
  }

   /**
   * This method is called when the player has lost.
   * It shows a message and exits the program.
   */
  public void gameOverLose() {
    Util.writeFileLose();

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Better luck next time!");
    alert.setHeaderText("You Lose!");
    alert.showAndWait();
    System.exit(0);
  }
}


package Project;

import javafx.scene.Parent;

public class Ship extends Parent {
  private int length;
  private int health;
  private boolean vertical;
  private boolean alive = true;

  /**
   * Constructor for the Ship class.
   * @param length the length of the ship
   * @param vertical true if the ship is vertical
   * The health of the ship is the same as the length of the ship.
   */
  public Ship(int length, boolean vertical) {
    this.length = length;
    this.health = length;
    this.vertical = vertical;
  }

  /**
   * Returns the length of the ship.
   * @return the length of the ship
   */
  public int getLength() {
    return length;
  }

  /**
   * Returns true if the ship is vertical and false if it is horizontal.
   * @return true if the ship is vertical and false if it is horizontal
   */
  public boolean isVertical() {
    return vertical;
  }

  /**
   * Returns the health of the ship.
   * @return the health of the ship
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the vertical variable to the given boolean.
   * @param vertical the boolean to set the vertical variable to
   */
  public void setVertical(boolean vertical) {
    this.vertical = vertical;
  }

  /**
   * Returns true if the ship is alive and false if it is dead.
   * @return true if the ship is alive and false if it is dead
   */
  public boolean isAlive() {
    return alive;
  }

  /**
   * Decreases the health of the ship by one and sets alive to false if the health is zero.
   */
  public void hit() {
    health--;
    if(health == 0) {
      alive = false;
    }
  }
}

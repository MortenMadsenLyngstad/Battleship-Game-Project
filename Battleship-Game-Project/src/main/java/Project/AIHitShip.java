package Project;

public class AIHitShip {
  protected boolean hitLeft = true;
  protected boolean hitRight = true;
  protected boolean hitUp = true;
  protected boolean hitDown = true;

  /**
   * This method resets all values
   */
  private void resetValues() {
    battleshipGame.hit = false;
    hitLeft = true;
    hitRight = true;
    hitUp = true;
    hitDown = true;
  }
  
  /**
   * This method is used to hit a ship if it is hit on the right side
   */
  public void AIHitShipRight() {
    int counter = 0;
    while (battleshipGame.enemyMove) {
      if (counter > 15) {
        AINormalShot();
      }
      counter++;
      int currentShips = battleshipGame.ownBoard.shipsAlive;

      int x = battleshipGame.myShipPosX.get(battleshipGame.myShipPosX.size() - 1) + 1;
      int y = battleshipGame.myShipPosY.get(battleshipGame.myShipPosY.size() - 1);

      Cell cell = battleshipGame.ownBoard.getCell(x, y);
      if (cell.wasShot()) {
        continue;
      }

      if (cell.hasShip()) {
        battleshipGame.myShipPosX.add(x);
        battleshipGame.myShipPosY.add(y);
      }
      if (!cell.hasShip()) {
        hitRight = false;
      }

      battleshipGame.enemyMove = cell.shot();

      if(currentShips > battleshipGame.ownBoard.shipsAlive) {
        resetValues();
      }

      if (battleshipGame.ownBoard.shipsAlive == 0) {
        battleshipGame.ownBoard.gameOverLose();
        
      }
    }
  }

  /**
   * This method is used to hit a ship if it is hit on the left side
   */
  public void AIHitShipLeft() {
    int counter = 0;
    while (battleshipGame.enemyMove) {
      if (counter > 15) {
        AINormalShot();
      }
      counter++;
      int currentShips = battleshipGame.ownBoard.shipsAlive;

      int x = battleshipGame.myShipPosX.get(battleshipGame.myShipPosX.size() - 1) - 1;
      int y = battleshipGame.myShipPosY.get(battleshipGame.myShipPosY.size() - 1);

      Cell cell = battleshipGame.ownBoard.getCell(x, y);
      if (cell.wasShot()) {
        
        continue;
      }

      if (cell.hasShip()) {
        battleshipGame.myShipPosX.add(x);
        battleshipGame.myShipPosY.add(y);
      }

      if (!cell.hasShip()) {
        hitLeft = false;
      }

      battleshipGame.enemyMove = cell.shot();

      if(currentShips > battleshipGame.ownBoard.shipsAlive) {
        resetValues();
      }

      if (battleshipGame.ownBoard.shipsAlive == 0) {
        battleshipGame.ownBoard.gameOverLose();
        
      }
    }
  }

  /**
   * This method is used to hit a ship if it is hit on the top side
   */
  public void AIHitShipUp() {
    int counter = 0;
    while (battleshipGame.enemyMove) {
      if (counter > 15) {
        AINormalShot();
      }
      counter++;
      int currentShips = battleshipGame.ownBoard.shipsAlive;

      int x = battleshipGame.myShipPosX.get(battleshipGame.myShipPosX.size() - 1);
      int y = battleshipGame.myShipPosY.get(battleshipGame.myShipPosY.size() - 1) - 1;

      Cell cell = battleshipGame.ownBoard.getCell(x, y);
      if (cell.wasShot()) {
        continue;
      }

      if (cell.hasShip()) {
        battleshipGame.myShipPosX.add(x);
        battleshipGame.myShipPosY.add(y);
      }

      if(!cell.hasShip()) {
        hitUp = false;
      }

      battleshipGame.enemyMove = cell.shot();

      if(currentShips > battleshipGame.ownBoard.shipsAlive) {
        resetValues();
      }


      if (battleshipGame.ownBoard.shipsAlive == 0) {
        battleshipGame.ownBoard.gameOverLose();
        
      }
    }
  }

  /**
   * This method is used to hit a ship if it is hit on the bottom side
   */
  public void AIHitShipDown() {
    int counter = 0;
    while (battleshipGame.enemyMove) {
      if (counter > 15) {
        AINormalShot();
      }
      counter++;
      int currentShips = battleshipGame.ownBoard.shipsAlive;

      int x = battleshipGame.myShipPosX.get(battleshipGame.myShipPosX.size() - 1);
      int y = battleshipGame.myShipPosY.get(battleshipGame.myShipPosY.size() - 1) + 1;


      Cell cell = battleshipGame.ownBoard.getCell(x, y);
      if (cell.wasShot()) {
        continue;
      }

      if (cell.hasShip()) {
        battleshipGame.myShipPosX.add(x);
        battleshipGame.myShipPosY.add(y);
      }

      if(!cell.hasShip()) {
        hitUp = false;
      }
      
      battleshipGame.enemyMove = cell.shot();
      
      if(currentShips > battleshipGame.ownBoard.shipsAlive) {
        resetValues();
      }


      if (battleshipGame.ownBoard.shipsAlive == 0) {
        battleshipGame.ownBoard.gameOverLose();
        
      }
    }
  }

  /**
   * This method is used when the bot has not hit a ship yet (it is randomized)
   */
  public void AINormalShot() {
    resetValues();

    int currentShips = battleshipGame.ownBoard.shipsAlive;

    int x = battleshipGame.randomMoveGenerator.nextInt(10);
    int y = battleshipGame.randomMoveGenerator.nextInt(10);

    Cell cell = battleshipGame.ownBoard.getCell(x, y);
    if (cell.wasShot()) {
      return;
    }

    if (cell.hasShip()) {
      battleshipGame.hit = true;
      battleshipGame.myShipPosX.add(x);
      battleshipGame.myShipPosY.add(y);
    }

    battleshipGame.enemyMove = cell.shot();

    if(currentShips > battleshipGame.ownBoard.shipsAlive) {
      battleshipGame.hit = false;

    }

    if (battleshipGame.ownBoard.shipsAlive == 0) {
      battleshipGame.ownBoard.gameOverLose();
      
    }
  }
}

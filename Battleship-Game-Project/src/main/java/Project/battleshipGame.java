package Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class battleshipGame extends AIHitShip {
  private int placingShips = 5;
  private boolean finishedPlacing = false;
  public Board opponentBoard;
  public static boolean hit = false;
  public static boolean enemyMove = false;
  public static Board ownBoard;
  public static Random randomMoveGenerator = new Random();
  public static int botShots = 0;
  public static int myShots = 0;

  public static List<Integer> botShipPosX = new ArrayList<>();
  public static List<Integer> botShipPosY = new ArrayList<>();
  public static List<Integer> myShipPosX = new ArrayList<>();
  public static List<Integer> myShipPosY = new ArrayList<>();

  /**
   * Creates a screen for the game.
   * Then creates two different boards, one for the player and one for the bot.
   * If all ships are placed, the game starts.
   * @return the screen for the game
   */
  public Parent createMap() {
    BorderPane root = new BorderPane();
    root.setPrefSize(1000, 700);
    root.setStyle("-fx-background-color: #D3D3D3;");

    Button button = new Button("Exit");
    button.setAlignment(Pos.CENTER);
    button.setPrefSize(100, 50);
    root.setBottom(button);
    button.setOnAction(EventHandler -> {
      System.exit(0);
    });

    opponentBoard = new Board(true, event -> {
      if (!finishedPlacing) {
        return;
      }

      Cell cell = (Cell) event.getSource();
      if (cell.wasShot()) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("You have already shot here!");
        alert.setContentText("Please choose another cell.");
        alert.showAndWait();
        return;
      }
      myShots++;

      enemyMove = !cell.shot();

      if (opponentBoard.shipsAlive == 0) {
        opponentBoard.gameOverWin();
      }

      if (enemyMove) {
        improvedRandomizedMove();
        botShots++;
      }
    });

    ownBoard = new Board(false, event -> {
      if (finishedPlacing) {
        return;
      }

      Cell cell = (Cell) event.getSource();

      if (ownBoard.setShip(cell.cellX(), cell.cellY(), new Ship(placingShips, !event.isControlDown()))) {
        myShipPosX.add(cell.cellX());
        myShipPosY.add(cell.cellY());
        if (--placingShips == 0) {
          startGame();
        }
      }
    });

    Label topLabel = new Label("Battleship");
    topLabel.setStyle("-fx-font-size: 50px; -fx-text-fill: #e88719; -fx-font-weight: bold; -fx-font-family: 'Eras Demi ITC';");
    root.setTop(topLabel);
    
    Label ownBoardLabel = new Label("Your board");
    ownBoardLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #2020ff; -fx-font-weight: bold;");
    ownBoardLabel.setAlignment(Pos.CENTER);
    Label opponentBoardLabel = new Label("Opponent board");
    opponentBoardLabel.setStyle("-fx-font-size: 25px; -fx-text-fill: #ff2020; -fx-font-weight: bold;");
    opponentBoardLabel.setAlignment(Pos.CENTER);
    
    HBox hboxBoards = new HBox(100, ownBoard, opponentBoard);
    HBox hboxLabels = new HBox(405, ownBoardLabel, opponentBoardLabel);
    HBox hboxTopLabel = new HBox(topLabel);
    hboxBoards.setAlignment(Pos.CENTER);
    hboxLabels.setAlignment(Pos.CENTER);
    hboxTopLabel.setAlignment(Pos.TOP_CENTER);

    VBox vbox = new VBox(20, hboxTopLabel, hboxLabels, hboxBoards);
    root.setCenter(vbox);

    return root;
  }

  /**
   * Starts the game.
   * Places the bots ships randomly on the board.
   */
  public void startGame() {
    int ships = 5;
    
    while (ships > 0) {
      int x = randomMoveGenerator.nextInt(10);
      int y = randomMoveGenerator.nextInt(10);

      if (opponentBoard.setShip(x, y, new Ship(ships, Math.random() < 0.5))) {
        ships--;
        botShipPosX.add(x);
        botShipPosY.add(y);

        // System.out.println(botShipPosX + " " + botShipPosY);
      }
    }
    finishedPlacing = true;
  }


  /**
   * Improved randomized move for the bot.
   * setShip() and shot() are  examples of delegation because they delegate the task to the Cell class.
   */
  public void improvedRandomizedMove() {
    while (enemyMove && (opponentBoard.shipsAlive > 0)) {
      if (hit) {
        Integer y = myShipPosY.get(myShipPosY.size() - 1);
        Integer x = myShipPosX.get(myShipPosX.size() - 1);
        if (!hitRight && hitLeft && (x != 0 && ownBoard.getCell(x - 1, y).wasShot() == false)) {
          AIHitShipLeft();

        } else if (!hitLeft && hitUp && (y != 0 && ownBoard.getCell(x, y - 1).wasShot() == false)) {
          AIHitShipUp();
          
        } else if (!hitUp && (y != 9 && ownBoard.getCell(x, y + 1).wasShot() == false)) {
          AIHitShipDown();
          
        } else {
          if (hitRight && (x != 9) && ownBoard.getCell(x + 1, y).wasShot() == false) {
            AIHitShipRight();
            
          } else if (hitLeft && (x != 0) && ownBoard.getCell(x - 1, y).wasShot() == false) {
            AIHitShipLeft();
            
          } else if (hitUp && (y != 0) && ownBoard.getCell(x, y - 1).wasShot() == false) {
            AIHitShipUp();
            
          } else if (hitUp && (y != 9) && ownBoard.getCell(x, y + 1).wasShot() == false) {
            AIHitShipDown();
            
          } else {
            AINormalShot();
          }
        }
        
      } else {
        AINormalShot();
        continue;
      }
    }
  }
  
  /**
   * First opponent movegenerator, which was later improved in the improvedRandomizedMove method.
   * This method is not in use
   */
  public void randomizedMove() {
    while (enemyMove && (opponentBoard.shipsAlive > 0)) {
      int x = randomMoveGenerator.nextInt(10);
      int y = randomMoveGenerator.nextInt(10);

      Cell cell = ownBoard.getCell(x, y);
      if (cell.wasShot()) {
        continue;
      }

      if (cell.hasShip()) {
        hit = true;
        // posValueXHit.add(x);
        // posValueYHit.add(y);
      }

      enemyMove = cell.shot();

      if (ownBoard.shipsAlive == 0) {
        ownBoard.gameOverLose();
        
      }
    }
  }
}

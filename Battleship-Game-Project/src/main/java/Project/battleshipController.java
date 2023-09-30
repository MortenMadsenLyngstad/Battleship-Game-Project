package Project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;

public class battleshipController extends battleshipGame {
  private Scene scene;
  private Stage stage;
  public static Player currentPlayer;
  public static leaderboardUpdater updater = new leaderboardUpdater();

  @FXML
  private TextField nameField;

  @FXML
  private Button enterButton;

  @FXML
  private Button buttonClicked;

  /**
   * Switches to the game screen
   * 
   * @param event the event that triggers the method (button click)
   * @throws IOException if the fxml file is not found
   */
  public void switchToGamescreen(ActionEvent event) throws IOException {
    scene = new Scene(createMap());
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    ;
    stage.show();
  }

  /**
   * Switches to the leaderboard screen
   * 
   * @param event the event that triggers the method (clicking the leaderboard
   *              icon)
   * @throws IOException if the fxml file is not found
   */
  public void switchToLeaderboardscreen(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
    scene = new Scene(root);
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  /**
   * Saves the player's name to a Player object with score 0
   * 
   * @param event the event that triggers the method (clicking the login button)
   *              Saves the name as "Anonymous" if the name field is empty
   */
  public void handleLogin(ActionEvent event) {
    String name = nameField.getText();
    if (name.contains("|")) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Invalid name");
      alert.setContentText("The name can not contain the character '|'");
      alert.showAndWait();
      nameField.setText("");
    } else {
      if (name.equals("") || name.equals("Player")) {
        name = "Anonymous";
      }
      currentPlayer = new Player(name, 0);
    }
  }
}

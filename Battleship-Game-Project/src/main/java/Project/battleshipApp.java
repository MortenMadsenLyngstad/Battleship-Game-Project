package Project;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class battleshipApp extends Application {
  
  /**
   * Starts the application
   * @param primaryStage the stage to be shown
   * @throws IOException
   */
  @Override
  public void start(Stage primaryStage) throws IOException{
    try {
      Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
      Scene scene = new Scene(root);
      primaryStage.setTitle("Battleship Startscreen");
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }   
  }

  /**
   * Launches the application
   * @param args
   */
  public static void main(String[] args) {
    Application.launch(args); 
  } 
}


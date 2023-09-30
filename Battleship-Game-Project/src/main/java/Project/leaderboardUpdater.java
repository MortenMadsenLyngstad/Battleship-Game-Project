package Project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class leaderboardUpdater implements Initializable {
    private Scene scene;
    private Stage stage;
    private List<Player> players = new ArrayList<>();

    @FXML
    ListView<String> listView = new ListView<>();

    ObservableList<String> items = listView.getItems();

    /**
     * Reads the leaderboard file and adds the players to the list
     * 
     * @param file the file to be read
     */
    public void checkFile(String file) {
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String name = parts[0].trim();
                int score = Integer.parseInt(parts[parts.length - 1].trim());
                Player newPlayer = new Player(name, score);
                players.add(newPlayer);
            }
            Collections.sort(players);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Adds the players to the leaderboard
     */
    public void addToLeaderboard() {
        int index = 1;
        for (Player player : players) {
            String name = player.getName();
            int score = player.getScore();
            String item = ((index<=9)?"  ":"")+ String.format("%d.    |    %d                                              %s", index, score, name);
            this.items.add(item);
            index++;
        }
    }

    /**
     * Uses the initialize method from the Initializable interface to initialize the leaderboard
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().clear();
        listView.setStyle("-fx-font: 1.5em \"Eras Bold ITC\"");
        listView.getItems().addAll(items);
        this.items.clear();
        this.players.clear();
    }

    /**
     * Switches to the main screen
     * 
     * @param event the event that triggers the method
     * @throws IOException if the fxml file is not found
     */
    @FXML
    public void switchToMainscreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        ;
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Loads the leaderboard
     * @param event the event that triggers the method
     * @throws Exception if the file is not found
     */
    public void loadLeaderboard(ActionEvent event) {
        checkFile("TDT4100-prosjekt-morteml/leaderboard.txt");
        addToLeaderboard();
        initialize(null, null);
    }

    /**
     * Returns the list of players
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }
}

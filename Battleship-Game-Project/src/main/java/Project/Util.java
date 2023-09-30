package Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Util {
  /**
   * Writes the player's name and score to a file
   */
  public static void writeFileWin() {
    try (
        FileWriter filewriter = new FileWriter("TDT4100-prosjekt-morteml/leaderboard.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
        PrintWriter output = new PrintWriter(bufferedWriter)) {
      output.println(battleshipController.currentPlayer.getName() + " | " + String.valueOf(battleshipGame.myShots));
    } catch (IOException e) {
      System.out.println("Error writing to file");
      System.out.println(e.getStackTrace());
    }
  }

  /**
   * Writes bot (name) and score to a file
   */
  public static void writeFileLose() {
    try (
        FileWriter filewriter = new FileWriter("TDT4100-prosjekt-morteml/leaderboard.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
        PrintWriter output = new PrintWriter(bufferedWriter)) {
      output.println("Bot" + " | " + String.valueOf(battleshipGame.myShots));
    } catch (IOException e) {
      System.out.println("Error writing to file");
      System.out.println(e.getStackTrace());
    }
  }
}

package Project;

public class Player implements Comparable<Player>{
    private String name;
    private int score;

    /**
     * Creates a new player object
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the score of the player
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Compares the score of the player to the score of the opponent
     * @param opponent the opponent to be compared to
     * @return the difference between the scores
     */
    @Override
    public int compareTo(Player opponent) {
        return Integer.compare(this.score, opponent.score);
    }


}

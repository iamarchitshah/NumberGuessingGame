import java.util.Random;

public class GameLogic {

    private int secretNumber;
    private int attempts;
    private int maxAttempts;
    private int maxNumber;

    private Random random = new Random();

    public GameLogic() {
        setDifficulty("Medium");
    }

    public void setDifficulty(String difficulty) {

        switch (difficulty) {

            case "Easy":
                maxNumber = 50;
                maxAttempts = 10;
                break;

            case "Hard":
                maxNumber = 200;
                maxAttempts = 5;
                break;

            default:
                maxNumber = 100;
                maxAttempts = 7;
        }

        startNewGame();
    }

    public void startNewGame() {
        secretNumber = random.nextInt(maxNumber) + 1;
        attempts = 0;
    }

    public String checkGuess(int guess) {

        attempts++;

        if (guess == secretNumber) {
            return "Correct";
        }

        if (attempts >= maxAttempts) {
            return "Game Over";
        }

        if (guess > secretNumber) {
            return "Too High";
        }

        return "Too Low";
    }

    public int getAttempts() {
        return attempts;
    }

    public int getRemainingAttempts() {
        return maxAttempts - attempts;
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}
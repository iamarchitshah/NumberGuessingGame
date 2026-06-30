import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JLabel difficultyLabel;
    private JLabel instructionLabel;
    private JLabel resultLabel;
    private JLabel attemptLabel;
    private JLabel scoreLabel;

    private JTextField guessField;

    private JButton guessButton;
    private JButton playAgainButton;

    private JComboBox<String> difficultyBox;

    private GameLogic game;

    private int rounds = 1;
    private int wins = 0;

    public GameFrame() {

        game = new GameLogic();

        setTitle("Number Guessing Game");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 245, 255));

        // Title
        titleLabel = new JLabel("NUMBER GUESSING GAME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(70, 20, 350, 30);
        add(titleLabel);

        // Difficulty
        difficultyLabel = new JLabel("Difficulty:");
        difficultyLabel.setBounds(50, 80, 100, 25);
        add(difficultyLabel);

        String[] levels = {"Easy", "Medium", "Hard"};
        difficultyBox = new JComboBox<>(levels);
        difficultyBox.setSelectedItem("Medium");
        difficultyBox.setBounds(150, 80, 120, 30);
        add(difficultyBox);

        // Instruction
        instructionLabel = new JLabel("Guess a number between 1 and 100");
        instructionLabel.setBounds(50, 130, 300, 25);
        add(instructionLabel);

        // Guess Field
        guessField = new JTextField();
        guessField.setBounds(50, 170, 150, 35);
        add(guessField);

        // Guess Button
        guessButton = new JButton("Guess");
        guessButton.setBounds(230, 170, 120, 35);
        guessButton.addActionListener(this);
        add(guessButton);

        // Result
        resultLabel = new JLabel("Result will appear here.");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setBounds(50, 220, 350, 30);
        add(resultLabel);

        // Attempts
        attemptLabel = new JLabel("Attempts: 0 / " + game.getMaxAttempts());
        attemptLabel.setBounds(50, 260, 250, 25);
        add(attemptLabel);

        // Score
        scoreLabel = new JLabel("Round: 1    Wins: 0");
        scoreLabel.setBounds(50, 300, 250, 25);
        add(scoreLabel);

        // Play Again Button
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(150, 350, 150, 40);
        playAgainButton.addActionListener(this);
        add(playAgainButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Guess Button
        if (e.getSource() == guessButton) {

            try {

                int guess = Integer.parseInt(guessField.getText());

                String result = game.checkGuess(guess);

                attemptLabel.setText("Attempts: " +
                        game.getAttempts() + " / " +
                        game.getMaxAttempts());

                if (result.equals("Too High")) {

                    resultLabel.setText("Too High!");

                } else if (result.equals("Too Low")) {

                    resultLabel.setText("Too Low!");

                } else if (result.equals("Correct")) {

                    wins++;

                    resultLabel.setText("Correct! You Won!");

                    scoreLabel.setText("Round: " + rounds +
                            "    Wins: " + wins);

                    JOptionPane.showMessageDialog(this,
                            "Congratulations!\nYou guessed correctly in "
                                    + game.getAttempts() + " attempts.");

                    guessButton.setEnabled(false);

                } else {

                    resultLabel.setText("You Lost!");

                    JOptionPane.showMessageDialog(this,
                            "Game Over!\nCorrect Number was "
                                    + game.getSecretNumber());

                    guessButton.setEnabled(false);

                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number.");

            }

        }

        // Play Again Button
        if (e.getSource() == playAgainButton) {

            rounds++;

            String difficulty =
                    difficultyBox.getSelectedItem().toString();

            game.setDifficulty(difficulty);

            instructionLabel.setText(
                    "Guess a number between 1 and " +
                            game.getMaxNumber());

            attemptLabel.setText(
                    "Attempts: 0 / " + game.getMaxAttempts());

            scoreLabel.setText(
                    "Round: " + rounds +
                            "    Wins: " + wins);

            resultLabel.setText("New Game Started!");

            guessField.setText("");

            guessButton.setEnabled(true);
        }
    }
}
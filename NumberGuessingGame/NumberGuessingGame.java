import javax.swing.SwingUtilities;

public class NumberGuessingGame {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame();
            }
        });

    }
}
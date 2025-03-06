import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimonGameGUI {
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JButton redButton;
    private JButton greenButton;
    private JButton blueButton;
    private JButton yellowButton;
    private JButton startButton;
    private JLabel scoreLabel;

    private List<String> pattern = new ArrayList<>();
    private int currentStep = 0;
    private int score = 0;
    private boolean isPlayerTurn = false;

    private final String[] COLORS = {"Red", "Green", "Blue", "Yellow"};
    private Random random = new Random();

    public SimonGameGUI() {
        setupFrame();
        setupButtons();
        setupInfoPanel();
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame = new JFrame("Simon Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        frame.add(buttonPanel, BorderLayout.CENTER);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        frame.add(infoPanel, BorderLayout.NORTH);
    }

    private void setupButtons() {
        redButton = createButton(Color.RED);
        greenButton = createButton(Color.GREEN);
        blueButton = createButton(Color.BLUE);
        yellowButton = createButton(Color.YELLOW);

        buttonPanel.add(redButton);
        buttonPanel.add(greenButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(yellowButton);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(e -> startGame());
        frame.add(startButton, BorderLayout.SOUTH);
    }

    private void setupInfoPanel() {
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        infoPanel.add(scoreLabel, BorderLayout.CENTER);
    }

    private JButton createButton(Color color) {
        JButton button = new JButton();
        button.setBackground(color);
        button.setOpaque(true);
        button.setEnabled(false);
        button.addActionListener(e -> handlePlayerInput(button));
        return button;
    }

    void startGame() {
        pattern.clear();
        score = 0;
        updateScore();
        startButton.setEnabled(false);
        generateNextStep();
    }

    private void generateNextStep() {
        pattern.add(COLORS[random.nextInt(COLORS.length)]);
        playPattern();
    }

    private void playPattern() {
        new Thread(() -> {
            disableButtons();
            for (String color : pattern) {
                highlightButton(color);
                try {
                    Thread.sleep(800); // Timing for visual feedback
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resetButtons();
            }
            isPlayerTurn = true;
            enableButtons();
            currentStep = 0;
        }).start();
    }

    private void handlePlayerInput(JButton button) {
        if (!isPlayerTurn) return;

        String color = getColorFromButton(button);
        if (color.equals(pattern.get(currentStep))) {
            currentStep++;
            if (currentStep == pattern.size()) {
                score++;
                updateScore();
                isPlayerTurn = false;
                disableButtons();
                generateNextStep();
            }
        } else {
            gameOver();
        }
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(frame, "Game Over! Your final score is: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        startButton.setEnabled(true);
        disableButtons();
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }

    private void highlightButton(String color) {
        JButton button = getButtonFromColor(color);
        if (button != null) {
            button.setBackground(button.getBackground().brighter());
            try {
                Thread.sleep(400); // Duration of highlight
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            button.setBackground(button.getBackground().darker());
        }
    }

    private void resetButtons() {
        redButton.setBackground(Color.RED);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);
        yellowButton.setBackground(Color.YELLOW);
    }

    private void enableButtons() {
        redButton.setEnabled(true);
        greenButton.setEnabled(true);
        blueButton.setEnabled(true);
        yellowButton.setEnabled(true);
    }

    private void disableButtons() {
        redButton.setEnabled(false);
        greenButton.setEnabled(false);
        blueButton.setEnabled(false);
        yellowButton.setEnabled(false);
    }

    private String getColorFromButton(JButton button) {
        if (button == redButton) return "Red";
        if (button == greenButton) return "Green";
        if (button == blueButton) return "Blue";
        if (button == yellowButton) return "Yellow";
        return null;
    }

    private JButton getButtonFromColor(String color) {
        return switch (color) {
            case "Red" -> redButton;
            case "Green" -> greenButton;
            case "Blue" -> blueButton;
            case "Yellow" -> yellowButton;
            default -> null;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimonGameGUI::new);
    }
}
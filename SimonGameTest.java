import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SimonGameTest {

    private SimonGameGUI game;

    // This method will be called before each test case to initialize the game object
    @BeforeEach
    public void setUp() {
        game = new SimonGameGUI();
        // We could simulate the game setup and call the startGame method here if necessary
    }

    // Test that the game starts with a score of 0
    @Test
    public void testStartGame() {
        game.startGame();
        assertEquals(0, game.score, "Score should be 0 at the start of the game");
        assertNotNull(game.pattern, "Pattern should not be null when the game starts");
    }

    // Test that the pattern list is being updated properly
    @Test
    public void testGenerateNextStep() {
        int initialPatternSize = game.pattern.size();
        game.generateNextStep();
        assertEquals(initialPatternSize + 1, game.pattern.size(), "Pattern size should increase by 1 after generating a new step");
    }

    // Test the handling of player input (correct input)
    @Test
    public void testHandleCorrectPlayerInput() {
        game.startGame(); // Start the game to initialize the pattern
        String correctColor = game.pattern.get(0); // Get the first color in the pattern
        
        // Simulate correct player input (matching the pattern)
        // We would need to modify the handlePlayerInput method to accept the color directly (currently, it's based on button clicks)
        boolean result = game.handlePlayerInput(correctColor);
        assertTrue(result, "The input should be correct");
        assertEquals(1, game.score, "Score should increase after correct input");
    }

    // Test the handling of player input (incorrect input)
    @Test
    public void testHandleIncorrectPlayerInput() {
        game.startGame(); // Start the game to initialize the pattern
        String correctColor = game.pattern.get(0); // Get the first color in the pattern
        String incorrectColor = correctColor.equals("Red") ? "Green" : "Red"; // Select a different color for incorrect input
        
        // Simulate incorrect player input
        boolean result = game.handlePlayerInput(incorrectColor);
        assertFalse(result, "The input should be incorrect");
        assertEquals(0, game.score, "Score should remain 0 after incorrect input");
    }

    // Test that the score updates correctly when the player makes correct inputs
    @Test
    public void testScoreUpdate() {
        game.startGame(); // Start the game
        int initialScore = game.score;
        
        // Correctly handle player inputs (matching the pattern)
        for (String color : game.pattern) {
            game.handlePlayerInput(color);
        }

        assertTrue(game.score > initialScore, "Score should increase after correct inputs");
    }

    // Test that the game ends after an incorrect input
    @Test
    public void testGameOver() {
        game.startGame();
        String correctColor = game.pattern.get(0);
        String incorrectColor = correctColor.equals("Red") ? "Green" : "Red";
        
        // Simulate incorrect input
        game.handlePlayerInput(incorrectColor);
        
        // Check that the game ended and score is still the same
        assertFalse(game.isPlayerTurn, "Game should end after incorrect input");
        assertEquals(0, game.score, "Score should remain 0 after game over");
    }

    // Test that the game resets properly after a game over
    @Test
    public void testGameReset() {
        game.startGame(); // Start a new game
        game.handlePlayerInput(game.pattern.get(0)); // Correct input for the first color
        
        int currentScore = game.score;
        
        // Simulate a game over
        game.handlePlayerInput("Green"); // Incorrect input
        game.gameOver();
        
        // Reset the game
        game.startGame();
        
        assertEquals(0, game.score, "Score should reset to 0 when a new game starts");
        assertNotEquals(currentScore, game.score, "Score should be different after starting a new game");
    }
}

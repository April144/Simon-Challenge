# Simon-Challenge
Simon-Game-Swing

# Simon Game

A Java-based implementation of the classic Simon Game using Swing for the graphical user interface (GUI). This project includes a GUI-based game where players must repeat an increasing sequence of colors, along with unit tests to ensure game logic correctness.

## Features
- Interactive GUI with colored buttons
- Randomized sequence generation
- Player input validation
- Score tracking
- Game over detection
- Unit tests for core game logic

## Technologies Used
- Java
- Swing (for GUI)
- JUnit 5 (for testing)

## Installation
1. Ensure you have Java installed (JDK 8 or later).
2. Clone this repository:
   ```sh
   git clone https://github.com/your-repo/simon-game.git
   ```
3. Navigate to the project directory:
   ```sh
   cd simon-game
   ```
4. Compile the Java files:
   ```sh
   javac -cp .;junit-5.7.1.jar *.java
   ```

## Usage
1. Run the game:
   ```sh
   java SimonGameGUI
   ```
2. Click the "Start Game" button to begin.
3. Watch the sequence and repeat it by clicking the corresponding colored buttons.
4. Each correct sequence increases the score and adds a new step.
5. A wrong input results in a game over.
6. Restart the game by clicking "Start Game" again.

## Running Tests
1. Ensure JUnit 5 is installed and added to the classpath.
2. Run the test cases:
   ```sh
   java -jar junit-platform-console-standalone-1.7.1.jar --class-path . --select-class SimonGameTest
   ```
3. The test results will be displayed in the terminal.

## Future Enhancements
- Add sound effects to match the classic Simon Game.
- Improve UI with animations and effects.
- Implement difficulty levels.
- Save high scores for competitive play.

## License
This project is open-source and available under the MIT License.

## Author
Thin Zar Nwe (April Oo)


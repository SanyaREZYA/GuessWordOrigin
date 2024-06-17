import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.HangmanGame;
import game.HangmanGameInterface;
import lexicon.HangmanLexicon;

import javax.swing.*;

public class HangManApp {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private static HangmanGameInterface hangmanGame;
	private static HangmanLexicon hangmanLexicon;

	private static JFrame resultFrame;
	private static JLabel resultInfo;
	private static JLabel hangmanLabel;
	private static JLabel attemptLabel;
	private static JLabel usedLetterLabel;

	public static void setup() {
		hangmanLexicon = new HangmanLexicon();
		hangmanGame = new HangmanGame(hangmanLexicon);
	}

	public static void main(String[] args) {
		setup();
		createPlayground();
		createResultFrame();
		startGame();
	}

	public static void createPlayground() {
		JFrame gameFrame = new JFrame("Hangman Game");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(WIDTH, HEIGHT);
		gameFrame.setLocationRelativeTo(null);

		gameFrame.setLayout(new GridLayout(3, 1));

		JPanel gameField = new JPanel();
		gameField.setLayout(new FlowLayout());

		hangmanLabel = new JLabel();
		JTextField inputText = new JTextField(10);
		JButton checkLetter = new JButton("Check Letter");

		gameField.add(inputText);
		gameField.add(checkLetter);

		JPanel gameInfo = new JPanel();
		gameInfo.setLayout(new GridLayout(2, 1));

		attemptLabel = new JLabel();
		usedLetterLabel = new JLabel();

		gameInfo.add(attemptLabel);
		gameInfo.add(usedLetterLabel);

		gameFrame.add(hangmanLabel);
		gameFrame.add(gameField);
		gameFrame.add(gameInfo);

		checkLetter.addActionListener(e -> {
			String text = inputText.getText();
			if (text.length() == 1) {
				checkLetter(hangmanLabel, attemptLabel, usedLetterLabel, text.charAt(0));
			} else {
				JOptionPane.showMessageDialog(gameFrame, "Please enter a single letter.");
			}
			inputText.setText(""); // Clear input field after each guess
		});

		gameFrame.setVisible(true);
	}

	public static void createResultFrame() {
		resultFrame = new JFrame("Game Over!");
		resultInfo = new JLabel("Result:");
		JButton playAgainButton = new JButton("Play Again");

		resultFrame.setLayout(new GridLayout(2, 1));
		resultFrame.setSize(200, 200);
		resultFrame.setLocationRelativeTo(null);

		playAgainButton.addActionListener(e -> {
			startGame();
			resultFrame.setVisible(false);
		});

		resultFrame.add(resultInfo);
		resultFrame.add(playAgainButton);
	}

	public static void startGame() {
//		hangmanGame.getNewWord();
		updateWord(hangmanLabel);
		attemptLabel.setText("Attempts left: " + hangmanGame.getGuessesLeft());
		usedLetterLabel.setText("Used Letters: ");
		resultFrame.setVisible(false);
	}

	public static void checkLetter(JLabel hangmanLabel, JLabel attemptLabel, JLabel usedLetterLabel, char letter) {
		if (hangmanGame.guess(letter)) {
			updateWord(hangmanLabel);
		} else {
			attemptLabel.setText("Attempts left: " + hangmanGame.getGuessesLeft());
		}

		usedLetterLabel.setText("Used Letters: " + hangmanGame.getGuessedLetters());
		gameOverCheck();
	}

	public static void updateWord(JLabel hangmanLabel) {
		hangmanLabel.setText(hangmanGame.getPartlyGuessedWord());
	}

	public static void gameOverCheck() {
		if (hangmanGame.isGameLost()) {
			resultFrame.setVisible(true);
			resultInfo.setText("You lost!");
		} else if (hangmanGame.isGameWon()) {
			resultFrame.setVisible(true);
			resultInfo.setText("You win!");
		}
	}
}

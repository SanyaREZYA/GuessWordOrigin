package game;

import lexicon.LexiconInterface;

import java.util.HashSet;
import java.util.Set;

public class HangmanGame implements HangmanGameInterface {

	public static final int ATTEMPTION = 9;
	private LexiconInterface lexicon;
	private String hangmanWord;
	private String word;
	private Set<Character> guessedLetters;
	private int correctGuesses = 0;
	private int incorrectGuesses = 0;

	public HangmanGame(LexiconInterface l) {
		this.lexicon = l;
		this.word = l.getRandomWord();
		this.hangmanWord = "-".repeat(word.length());
//		this.hangmanWord = "*".repeat(word.length());
		this.guessedLetters = new HashSet<>();
	}

	@Override
	public boolean guess(char letter) {
		if (guessedLetters.contains(letter)) {
			return false;
		}

		if (word.toLowerCase().contains(Character.toString(letter).toLowerCase())) {
			guessedLetters.add(letter);
			StringBuilder newHangmanWord = new StringBuilder(hangmanWord);
			for (int i = 0; i < word.length(); i++) {
				if (Character.toString(word.charAt(i)).equalsIgnoreCase(Character.toString(letter))) {
					newHangmanWord.setCharAt(i, letter);
				}
			}
			correctGuesses++;
			hangmanWord = newHangmanWord.toString();
			return true;
		} else {
			incorrectGuesses++;
			return false;
		}
	}

	@Override
	public String getPartlyGuessedWord() {
		return hangmanWord;
	}

	@Override
	public String getHangmanWord() {
		return word;
	}

	@Override
	public String getGuessedLetters() {
		StringBuilder guessed = new StringBuilder();
		for (char c : guessedLetters) {
			guessed.append(c).append(' ');
		}
		return guessed.toString().trim();
	}

	@Override
	public boolean isGameLost() {
		return incorrectGuesses == ATTEMPTION;
	}

	@Override
	public boolean isGameWon() {
		return !hangmanWord.contains("-");
//		return !hangmanWord.contains("*");
	}

	@Override
	public int getGuessesLeft() {
		return ATTEMPTION - incorrectGuesses;
	}

	@Override
	public int getIncorrectGuesses() {
		return incorrectGuesses;
	}

	@Override
	public int getCorrectGuesses() {
		return correctGuesses;
	}

	@Override
	public String getNewWord() {
		this.word = lexicon.getRandomWord();
		this.hangmanWord = "*".repeat(word.length());
		this.guessedLetters.clear();
		this.correctGuesses = 0;
		this.incorrectGuesses = 0;
		return this.word;
	}
}

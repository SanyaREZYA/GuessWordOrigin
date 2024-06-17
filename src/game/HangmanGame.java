package game;

import lexicon.LexiconInterface;

import java.util.LinkedHashSet;
import java.util.Set;

public class HangmanGame implements HangmanGameInterface {

	private final LexiconInterface lexicon;
	private final String hangmanWord;
	private final Set<Character> guessedLetters;
	private int guessesLeft;
	private int correctGuesses;
	private int incorrectGuesses;

	public HangmanGame(LexiconInterface l) {
		lexicon = l;
		hangmanWord = lexicon.getWord(0).toUpperCase();
		guessedLetters = new LinkedHashSet<>();
		guessesLeft = 9;
		correctGuesses = 0;
		incorrectGuesses = 0;
	}

	@Override
	public boolean guess(char letter) {
		letter = Character.toUpperCase(letter);

		if (guessedLetters.contains(letter)) {
			incorrectGuesses++;
			return false;  // Already guessed this letter
		}

		if (hangmanWord.contains(String.valueOf(letter))) {
			guessedLetters.add(letter);
			correctGuesses++;
		} else {
			incorrectGuesses++;
			guessesLeft--;
		}

		return hangmanWord.contains(String.valueOf(letter));
	}


	@Override
	public String getPartlyGuessedWord() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < hangmanWord.length(); i++) {
			char letter = hangmanWord.charAt(i);
			if (guessedLetters.contains(letter)) {
				result.append(letter);
			} else {
				result.append("-");
			}
		}
		return result.toString();
	}

	@Override
	public String getHangmanWord() {
		return hangmanWord;
	}

	@Override
	public String getGuessedLetters() {
		StringBuilder guessed = new StringBuilder();
		for (char letter : guessedLetters) {
			guessed.append(letter);
		}
		return guessed.toString();
	}

	@Override
	public boolean isGameLost() {
		return guessesLeft <= 0;
	}

	@Override
	public boolean isGameWon() {
		return getPartlyGuessedWord().equals(hangmanWord);
	}

	@Override
	public int getGuessesLeft() {
		return guessesLeft;
	}

	@Override
	public int getIncorrectGuesses() {
		return incorrectGuesses;
	}

	@Override
	public int getCorrectGuesses() {
		return correctGuesses;
	}

}

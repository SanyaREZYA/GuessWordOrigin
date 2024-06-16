package lexicon;

import java.util.Random;

public class HangmanLexicon implements LexiconInterface {

	private String[] words = {
			"apple", "banana", "cherry", "date", "elderberry",
			"fig", "grape", "honeydew", "kiwi", "lemon"
	};
	private Random random;

	public HangmanLexicon() {
		this.random = new Random();
	}

	@Override
	public int getWordCount() {
		return words.length;
	}

	@Override
	public String getWord(int index) {
		if (index >= 0 && index < words.length) {
			return words[index];
		} else {
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
	}

	@Override
	public String getRandomWord() {
		int index = random.nextInt(words.length);
		return words[index];
	}
}

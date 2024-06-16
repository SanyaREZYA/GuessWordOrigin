package junit;

import lexicon.LexiconInterface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestLexicon implements LexiconInterface {

	private String defaultString;
	private String[] words;

	public TestLexicon(String defaultString) {
		this.defaultString = defaultString;
		this.words = defaultString.split(" ");
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
		return defaultString;
	}

	@Test
	public void TestGetWordCount() {
		TestLexicon lexicon = new TestLexicon("apple banana cherry");
		assertEquals(3, lexicon.getWordCount());
	}

	@Test
	public void TestGetWord() {
		TestLexicon lexicon = new TestLexicon("apple banana cherry");
		assertEquals("apple", lexicon.getWord(0));
		assertEquals("banana", lexicon.getWord(1));
		assertEquals("cherry", lexicon.getWord(2));
	}

	@Test
	public void TestGetRandomWord() {
		TestLexicon lexicon = new TestLexicon("apple banana cherry");
		assertEquals("apple banana cherry", lexicon.getRandomWord());
	}

	@Test
	public void TestGetWordOutOfBounds() {
		TestLexicon lexicon = new TestLexicon("apple banana cherry");
		assertThrows(IndexOutOfBoundsException.class, () -> {
			lexicon.getWord(3);
		});
	}
}

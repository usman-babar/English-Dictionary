package englishDictionary;

public class DataSet {

	private String word;
	private String meaning;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public DataSet() {
	}

	public DataSet(String word, String meaning) {
		this.word = word;
		this.meaning = meaning;
	}

	@Override
	public String toString() {
		return "word: " + word + "\t\tMeaning: " + meaning;
	}
}

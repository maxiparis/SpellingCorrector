package spell;

import java.io.IOException;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws IOException {
		
//		String dictionaryFileName = args[0];
//		String inputWord = args[1];

		Trie t = new Trie();
		t.add("b");
		t.add("a");
		t.add("a");
		t.add("c");
		t.add("z");



		Trie t2 = new Trie();
		t2.add("b");
		t2.add("c");
		t2.add("c");


		Boolean test = t.equals(t2);


		System.out.println(t.toString());



		//
        //Create an instance of your corrector here
        //
		ISpellCorrector corrector = null;
		
//		corrector.useDictionary(dictionaryFileName);
//		String suggestion = corrector.suggestSimilarWord(inputWord);
//		if (suggestion == null) {
//		    suggestion = "No similar word found";
//		}
//
//		System.out.println("Suggestion is: " + suggestion);
	}

}

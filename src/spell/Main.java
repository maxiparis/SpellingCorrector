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

		Node n = new Node();
		n.incrementValue();
		System.out.println("n.getValue() " + n.getValue());
		n.getChildren()[4] = new Node();

		Trie t = new Trie();
		t.add("sTaRt");
		t.add("trucK");
		t.add("EATING");


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

package spell;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

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

		Trie t1 = new Trie();
		t1.add("ba");
		t1.add("ba");
		t1.add("a");
		t1.add("a");
		t1.add("c");
		t1.add("z");




		Trie t2 = new Trie();
		t2.add("b");
		t2.add("ba");
		t2.add("c");
		t2.add("c");

		Boolean test = Arrays.equals(t1.getRoot().getChildren()[1].getNodesPosition(), t2.getRoot().getChildren()[1].getNodesPosition());
		//expecting true
		Boolean test2 = t1.equals(t2);


		System.out.println(t1.toString());



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

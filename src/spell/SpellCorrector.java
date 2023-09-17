package spell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class SpellCorrector implements ISpellCorrector{
    private Trie myTrie;

  public SpellCorrector() {
    myTrie = new Trie();
  }

  @Override
  public void useDictionary(String dictionaryFileName) throws IOException {
      try {
          File inputText = new File(dictionaryFileName); //create a text file
          Scanner input = new Scanner(inputText); // craete a scanner using the text
//          while (input.hasNextLine()) {
//              myTrie.add(input.nextLine());
//          }
          while (input.hasNext()) {
              myTrie.add(input.next());
          }
          input.close();
      } catch (FileNotFoundException e){
          System.out.println("An error occurred.");
      }

  }

@Override
  public String suggestSimilarWord(String inputWord) {
        //check if word is in trie
        if (inputWord == "") {
            return null;
        }
        if (myTrie.isWordInDictionary(inputWord)) { //if the word is in the trie, then return that word.
            return inputWord.toLowerCase();
        }

        //word was not in dictionary, generate different words that can be used to test possible mispellings
        HashSet<String> editDistance1 = new HashSet<String>();


    //Edit Distance 1
        editDistance1 = doAll4Modifications(inputWord);

        // check if words in ED1 are in trie
//        TreeMap<String, Integer> words = new TreeMap<>();
        TreeMap<String, Integer> wordsFoundInED1 = lookUpEditDistanceSetInTrie(editDistance1);

        //if there is one winner, return it
        //else if there is tie, use tiebrakers to do so
        //else if is empty (it )

        if (wordsFoundInED1.size() == 1){
            return wordsFoundInED1.firstKey();
        } else if (wordsFoundInED1.size() > 1) { //there is a tie
            return wordsFoundInED1.firstKey();
        } //is smaller than 1, which is at least 0, so there was no match





    //EditDistance2
        //for each word in editDistance1, COMPUTE all 4 of the modifications
        HashSet<String> editDistance2 = new HashSet<String>();

        for (String aWord : editDistance1){
            editDistance2.addAll(doAll4Modifications(aWord));
        }

        //see if the words in ED2 are in the tree (dictionary)
        TreeMap<String, Integer> wordsFoundInED2 = lookUpEditDistanceSetInTrie(editDistance2);


        //If there is one word in that TreeMap, return that one
        //if there are more than 1 word in the TreeMap, return the first one alphabetically
        //if there is not, return null
        if (wordsFoundInED2.size() == 1){
            return wordsFoundInED2.firstKey();
        } else if (wordsFoundInED2.size() > 1) { //there is a tie
            return wordsFoundInED2.firstKey();
        } //is smaller than 1, which is at least 0, so there was no match




        return null;
  }

    private TreeMap<String, Integer> lookUpEditDistanceSetInTrie(HashSet<String> editDistance1) {
        TreeMap<String, Integer> foundWordsInTrie = new TreeMap<>();

        int frequencies = 0;
        for (String aWord : editDistance1){
            Node node = myTrie.find(aWord);
            //if the word.count is higher than frequencies
            //update frequencies
            //clear map
            //add the map
            //
            //if not, do nothing
            if(node != null){ //we actually found a word
                if (node.getValue() > frequencies){ // if is higher
                    frequencies = node.getValue();
                    foundWordsInTrie.clear();
                    foundWordsInTrie.put(aWord, node.getValue());
                } else if (node.getValue() == frequencies) { //if is the same frequency
                    foundWordsInTrie.put(aWord, node.getValue());
                } // if is smaller, do nothing.
            }
        }

        return foundWordsInTrie;
    }

    private HashSet<String> doAll4Modifications(String aWord) {
        HashSet<String> toReturn = new HashSet<>();
        toReturn.addAll(deletion(aWord));
        toReturn.addAll(insertion(aWord));
        toReturn.addAll(transposition(aWord));
        toReturn.addAll(alteration(aWord));
        return toReturn;
    }


    private HashSet<String> insertion(String inputWord) {
        HashSet<String> toReturn= new HashSet<String>();
        for (int i = 0; i <= inputWord.length(); i++) {
            for (int j=0; j < 26; j++) {
                char c = (char)('a'+j);
                StringBuilder modifiedWord = new StringBuilder(inputWord);
                modifiedWord.insert(i, c);
                toReturn.add(modifiedWord.toString());
            }
        }
        return toReturn;
    }

    private HashSet<String> alteration(String inputWord) {
        HashSet<String> toReturn= new HashSet<String>();
        for (int i = 0; i < inputWord.length(); i++) {
            for (int j=0; j < 26; j++) {
                StringBuilder modifiedWord = new StringBuilder(inputWord);
                char alphabetChar =(char)('a' + j);
                modifiedWord.replace(i, i+1, Character.toString(alphabetChar));
                if (modifiedWord.toString() != inputWord) {
                    toReturn.add(modifiedWord.toString());
                }
            }
        }
//        toReturn.remove(inputWord); //should I include the same word? i dont think so... lets wait for the tests.
        return toReturn;
    }

    private HashSet<String> transposition(String inputWord) {
        HashSet<String> toReturn= new HashSet<String>();
        StringBuilder modifiedWord;
        for (int i=0; i < inputWord.length()-1; i++) {
            modifiedWord = new StringBuilder(inputWord); // i = 1 , HOUSE
            StringBuilder get2Chars = new StringBuilder(modifiedWord.subSequence(i, i+2)); //OU
            get2Chars.reverse(); // UO
            modifiedWord.replace(i, i+2, get2Chars.toString()); // HUOSE
            toReturn.add(modifiedWord.toString());
        }
        return toReturn;
    }

    private HashSet<String> deletion(String inputWord) {
        HashSet<String> toReturn= new HashSet<String>();
        if(inputWord.length() == 1){
            return toReturn;
        }
        for (int i = 0; i < inputWord.length(); i++) {
            String modifiedWord = "";
            for (int j=0; j < inputWord.length(); j++) {
                if (i != j) {
                    modifiedWord += inputWord.charAt(j);
                }

            }
            toReturn.add(modifiedWord);
        }
        return toReturn;
    }
}

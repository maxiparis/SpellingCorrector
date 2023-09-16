package spell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class SpellCorrector implements ISpellCorrector{
    private Trie myTrie;
    private HashSet<String> editDistance1;

  public SpellCorrector() {
    myTrie = new Trie();
    editDistance1 = new HashSet<String>();
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
        if (myTrie.isWordInDictionary(inputWord)) { //if the word is in the trie, then return that word.
            return inputWord.toLowerCase();
        } else { //word was not in dictionary, generate different words that can be used to test possible mispellings
            //Edit Distance 1
            editDistance1.addAll(deletion(inputWord));
            editDistance1.addAll(transposition(inputWord));
            editDistance1.addAll(alteration(inputWord));
            editDistance1.addAll(insertion(inputWord));

            // check if words in ED1 are in trie
            int frequencies = 0;
            Map <Integer, String> words = new HashMap<>();

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
                        words.clear();
                        words.put(node.getValue(), aWord);
                    } else if (node.getValue() == frequencies) { //if is the same frequency
                        words.put(node.getValue(), aWord);
                    } // if is smaller, do nothing.
                }
            }

            //if there is one winner, return it
            //else if there is tie, use tiebrakers to do so
            //else if is empty (it )

            if (words.size() == 1){
                return words.get(frequencies);
            } else if (words.size() > 1) { //there is a tie
                
            }


        }


        return null;
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

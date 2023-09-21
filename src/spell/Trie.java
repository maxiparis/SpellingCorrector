package spell;

import java.util.Arrays;

public class Trie implements ITrie {
  private Node root;
  private int wordCount;
  private int nodeCount;

    public Trie() {
        root = new Node();
        wordCount = 0;
        nodeCount = 1; //root is one node
    }

    public Node getRoot() {
        return root;
    }

    @Override
  public void add(String word) {
        String newWord = word.toLowerCase();
        Node currentNode = this.root;
        for (int i = 0; i < newWord.length(); i++){
            char c = newWord.charAt(i);
            int index = c - 'a';


            if (currentNode.getChildren()[index] == null){
                currentNode.getChildren()[index] = new Node();
                currentNode.getNodesPosition()[index] = 1;
                this.incrementNodeCount();
            }
            currentNode = currentNode.getChildren()[index];
        }
        if (currentNode.getValue() == 0 ){
            this.incrementWordCount();
        }

        currentNode.incrementValue();
  }

    private void incrementWordCount() {
        wordCount++;
    }

    private void incrementNodeCount() {
        nodeCount++;
    }

    @Override
  public Node find(String word) {
        String newWord = word.toLowerCase();
        Node currentNode = this.root;
        //traverse through each word, until i get to the last word
        for(int i = 0; i < newWord.length(); i++){
            char c = newWord.charAt(i);
            int index = c - 'a';
            if (currentNode.getChildren()[index] == null) {
                return null;
            } else {
                //if I am in last position
                    //if node.getCount > 0
                        //return node
                    //else
                        //return null, because i came all the way but the count is 0, which means, there is no word there.
                //else (i am not in the last position)
                    //update currentNode to the child


                if (i == newWord.length() - 1) {
                    if (currentNode.getChildren()[index].getValue() > 0){
                        return currentNode.getChildren()[index];
                    } else {
                        return null;
                    }
                } else {
                    currentNode = (Node) currentNode.getChildren()[index];
                }
            }
        }
        return currentNode;
  }
 @Override
  public int getWordCount() {
	return wordCount;
  }

  @Override
  public int getNodeCount() {
	return nodeCount;
  }


  //toString
  @Override
  public String toString(){
	//should return a string of an alphabetized list of all the words in the trie, each word only appear
	// once. Each word in separate line
	StringBuilder curWord = new StringBuilder();
	StringBuilder output = new StringBuilder();

	toStringHelper(root, curWord, output); //start recursion at the root

	return output.toString();
  }

  private void toStringHelper(INode node, StringBuilder curWord, StringBuilder output){ //INode or Node??
	if (node.getValue() > 0) {
	  //append the node's word to output. List?
	  output.append(curWord.toString());
	  output.append("\n");
	}
	//recurse on children
	for (int i = 0; i < node.getChildren().length; i++){
	  INode child = node.getChildren()[i];
	  if (child != null){
		char childLetter = (char) ('a' + i);
		curWord.append(childLetter);
		toStringHelper(child, curWord, output);
        curWord.deleteCharAt(curWord.length() - 1);
	  }
	}
  }

//Equals
  @Override
  public boolean equals(Object o){
    //some cases to check
    //is o == null? return false
    //is o == this? return true
    //do this and o have the same class?
        //no? return false
        //yes? keep going
    //do this and at this point do wordCount and nodeCount are the same for theOtherTrie?

    Trie theOtherTrie = (Trie) o;

    if (o == null) {
        return false;
    } else if (o == this) {
        return true;
    } else if (!o.getClass().equals(this.getClass())) {
        return false;
    } else if (this.getWordCount() != theOtherTrie.getWordCount()) {
        return false;
    } else if (this.getNodeCount() != theOtherTrie.getNodeCount()) {
        return false;
    }

    return equalsHelper(this.root, theOtherTrie.root);
  }

  private boolean equalsHelper(Node n1, Node n2){//to traverse both trees at the same time
    //compare n1 and n2 to see if they are the same
      //do any n1 or n2 is null and the other not? yes -> false
      //do n1 and n2 have the same count? yes continue, no -> false
      //do n1 and n2 have non-null children in the same indexes?
    //recurse on the children and compare the children subtrees


      //PRE CHECK CASES
      if ((n1 == null & n2 != null) || (n1 != null & n2 == null)) {//is it one null and the other not?
          return false;
      } else if (n1.getValue() != n2.getValue()) {//do they have different values?
          return false;
      } else if (!Arrays.equals(n1.getNodesPosition(), n2.getNodesPosition())){ //do both nodes have children in same positions. yes = continue, no = ret false
          return false;
      }

      for (int i = 0 ; i < n1.getChildren().length; i++){ //traverse through all non-null children
          Node childN1=n1.getChildren()[i];
          Node childN2=n2.getChildren()[i];
          if (childN1 != null){ //only one child because i know we have childs in same position
              if(!equalsHelper(childN1, childN2)){
                  return false;
              }
          }
      }

    return true; //if at any point there is any difference, return false
  }

//HashCode
  @Override
    public int hashCode(){
        //Another thing to do, combine the following numbers:
        //1. nodeCount
        //2. wordCount
        //3. the index of each non-null child (combine somehow - add, multiply them, etc)

        //taking the index of the first non-null node after the char #15
         int nonNullIndex = 0;
         for (int i = 0 ; i < 26 ; i++){
             if(this.root.getChildren()[i] != null){
                 nonNullIndex = i;

                 return getNodeCount() * getWordCount() * nonNullIndex;
             }
         }

        // if there is no non-null then just return this one below.
        return getNodeCount() * getWordCount(); //for example
    }

    public boolean isWordInDictionary(String inputWord) {
        if (this.find(inputWord) == null){
            return false;
        }
        return true;
    }
}

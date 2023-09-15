package spell;

public class Trie implements ITrie {
  private Node root;
  private int wordCount;
  private int nodeCount;

    public Trie() {
        root = new Node();
        wordCount = 0;
        nodeCount = 0;
    }

    @Override
  public void add(String word) {
        String newWord = word.toLowerCase();
        Node currentNode = this.root;
        for (int i = 0; i < newWord.length(); i++){
            char c = newWord.charAt(i);
            int index = c - 'a';
            currentNode.getChildren()[index] = new Node();
            this.incrementNodeCount();
            currentNode = (Node) currentNode.getChildren()[index];
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
  public INode find(String word) {
	return null;
  }

  @Override
  public int getWordCount() {
	return 0;
  }

  @Override
  public int getNodeCount() {
	return 0;
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
    Trie theOtherTrie = (Trie) o;
    //do this and at this point do wordCount and nodeCount are the same for theOtherTrie?


    return equalsHelper(this.root, theOtherTrie.root);
  }

  private boolean equalsHelper(Node n1, Node n2){//to traverse both trees at the same time
    //compare n1 and n2 to see if they are the same
      //do n1 and n2 have the same count? yes continue, no -> false
      //do n1 and n2 have non-null children in the same indexes?
    //recurse on the children and compare the children subtrees


    return true; //if at any point there is any difference, return false
  }

//HashCode
  @Override
  public int hashCode(){

    return getNodeCount() * getWordCount(); //for example
    //Another thing to do, combine the following numbers:
      //1. nodeCount
      //2. wordCount
      //3. the index of each non-null child (combine somehow - add, multiply them, etc)
  }
}

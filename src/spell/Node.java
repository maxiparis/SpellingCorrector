package spell;

public class Node implements INode{
    private int count; //frequency count for the word represented by the node
    private Node[] children; //26 children
    private int[] nodesPosition; //if is 1, that means there is a node there. Each time I add a node, I update this index to 1.



  public Node() {
    count = 0;
    children = new Node[26];
    nodesPosition= new int[26];
  }

  public int[] getNodesPosition() {
    return nodesPosition;
  }

  //  public int[] getChildreNodesPosition() {
//    int[] toReturn;
//    int nonNullChildren;
//
//    for(int i = 0; i < children.length; i++){
//
//    }
//
//    return toReturn;
//  }


  @Override
  public int getValue() {

    return count;
  } //returns the count

  @Override
  public void incrementValue() { //increment count

    count++;
  }

  @Override
  public Node[] getChildren() {

    return children;
  }
}

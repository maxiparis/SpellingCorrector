package spell;

import javax.management.NotificationFilter;

public class Node implements INode{
    private int count;
    private Node[] children; //26 children


  public Node() {
    count = 0;
    children = new Node[26];
  }

  @Override
  public int getValue() {

    return count;
  } //returns the count

  @Override
  public void incrementValue() { //increment count

    count++;
  }

  @Override
  public INode[] getChildren() {

    return children;
  }
}

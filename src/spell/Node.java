package spell;

import javax.management.NotificationFilter;

public class Node implements INode{
    private int count;
    private Node[] children; //26 children
  @Override
  public int getValue() {

    return 0;
  } //returns the count

  @Override
  public void incrementValue() { //increment count

  }

  @Override
  public INode[] getChildren() {

    return new INode[0];
  }
}

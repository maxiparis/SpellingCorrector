package spell;

import javax.management.NotificationFilter;

public class Node implements INode{
    private int count;
    private Node[] children;
  @Override
  public int getValue() {
    return 0;
  }

  @Override
  public void incrementValue() {

  }

  @Override
  public INode[] getChildren() {
    return new INode[0];
  }
}

/*
 * @author Seungmin Lee
 * CS 2 - Jiang
 * Assignment 5 Question 2
 * 2015/03/28
 * This circular linkedList contains the QuickSort function. 
 */

import java.util.*;

public class LLwithQS
{
	//Node definition
  public class Node
  {
    public int v;
    public Node next;
    
    public Node( int newItem, Node newNext )
    {
      v = newItem;
      next = newNext;
    }
  }

  	//Linked List Definition
  private Node tail;
  private int nodeCount;
  private static Random generator = new Random();  //this random generator will be used in quickSort.

  
  public LLwithQS()
  {
    tail = null;
    nodeCount = 0;
  }

  public int getSize()
  {
    return nodeCount;
  }

  private void pushBack( Node newNode )
  {
    if ( nodeCount == 0 )
    {
      newNode.next = newNode;
      tail = newNode;
    }
    else
    {
      newNode.next = tail.next;
      tail.next = newNode;
    }
    nodeCount++;
  }

  //insert to the front of the linkedList.
  private void insertValue( int newItem )
  {
    Node newNode = new Node( newItem, null );
    if ( nodeCount == 0 )
    {
      newNode.next = newNode;
      tail = newNode;
    }
    else
    {
      newNode.next = tail.next;
      tail.next = newNode;
    }
    nodeCount++;
  }

  private Node choosePivot( )
  {
    int randomIndex = generator.nextInt( nodeCount );
    Node pivot;
    Node prev;

    // start at the first node (index = 0), and keep grabbing the next node
    pivot = tail.next;
    prev = tail;
    for(int i=0; i<randomIndex; i++)
    {
        prev = pivot;
        pivot = pivot.next;
    }
        
    prev.next = pivot.next;
    nodeCount--;

    return pivot;
  }

  public LLwithQS partition( Node pivot )
  {
    LLwithQS smallSide = new LLwithQS(); //initialize 2 different sides. (small and large)
    LLwithQS largeSide = new LLwithQS();
    Node curr;

    for(int i=0; i<nodeCount; i++)
    {
        curr = tail.next;     
        tail.next = curr.next;        
        if(curr.v < pivot.v) //if the index v is smaller than pivot, put it to the smallSide.
            smallSide.pushBack(curr);
        else
            largeSide.pushBack(curr); //else put it to the largeSide.
    }
    tail = largeSide.tail;
    nodeCount = largeSide.nodeCount;

    return smallSide;
  }

  private void combine( LLwithQS smallSide, Node pivot )
  {
    Node firstBig;

    smallSide.pushBack( pivot ); // insert pivot at front
    smallSide.tail = smallSide.tail.next; // pivot comes after all smallSide

    if ( nodeCount != 0 )
    {

      firstBig = this.tail.next;
      this.tail.next = smallSide.tail.next; 
      pivot.next = firstBig; 
      nodeCount = nodeCount + smallSide.nodeCount;
    }
    else
    {
      // There are no largeSide.

      this.tail = smallSide.tail;
      nodeCount = smallSide.nodeCount;
    }

  }

  public void quickSort( )
  {
    Node pivot;
    LLwithQS smallSide;

    if ( nodeCount > 2 )
    {
      pivot = choosePivot(); // removes a pivot
      smallSide = partition( pivot ); // largeSide stay in original list
      smallSide.quickSort(  );
      quickSort( ); // recursively quick sort the largeSide
      combine( smallSide, pivot ); // combine into one circular list
    }
    if ( nodeCount == 2 )
    {
      if ( tail.v < tail.next.v )
      { 
        // swap them: first becomes tail
        tail = tail.next;
      }
    }
    // do nothing for nodeCount 0,1
  }   
  public void print()
  {
    Node curr;

    if (nodeCount > 0)
    {
      curr = tail.next;
      do
      {
        System.out.print( curr.v + "->");
        curr = curr.next;
      } while ( curr != tail.next );
    }
    System.out.print("Null");
  }

  public static void main( String[] args )
  {
    LLwithQS list = new LLwithQS();
    
    for (int i = 0; i < 15; i++)
    	list.insertValue(generator.nextInt(20)); //randomly generate 15 elements ranging from 0~20.
    
    System.out.print("Unsorted List: ");
    list.print(); 
    System.out.println();
    list.quickSort();
    System.out.print("Sorted List: ");
    list.print();
  }
}
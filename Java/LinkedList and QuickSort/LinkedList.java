/*
 * @author Seungmin Lee
 * CS 2 - Jiang
 * Assignment 5 Question 2
 * 2015/03/28
 * This linkedList contains the QuickSort function. 
 */

import java.util.*;

class Node<T> {
         T v;
         Node<T> next;
         public Node(T v) { this.v = v; next = null;}
         public String toString() { return v+""; }
         
}

public class LinkedList<T> {

      private Node<T> head;
      private Node<T> tail;
      private int nodeCount;
      private static Random generator = new Random(System.nanoTime());
      
      public LinkedList()
      {
    	  nodeCount = 0;
      }
      
      public int getSize()
      {
    	  return nodeCount;
      }
      
/*      
      public void quickSort(int s, int e)
      {
    	  if( s >= e ) return;
    	  int k = partition(a,s,e);
    	  quickSort(a,s,k-1);
    	  quickSort(a,k+1<e);
      }
  */    
      public void pushBack(T v)
      {
          if (head == null)
          {
              head = new Node<T>(v);
              tail = head;
              nodeCount++;
              return;
          }

          Node<T> x = new Node<T>(v);
          tail.next = x;
          tail = x;
          nodeCount++;
      }

      public void pushFront(T v)
      {
          if (head == null)
          {
              head = new Node<T>(v);
              tail = head;
              nodeCount++;
              return;
          }

          Node<T> x = new Node<T>(v);
          x.next = head;
          head = x;
          nodeCount++;
      }

      public void insertAfter(int n, T v)
      {
          if (head == null)
          {
             head = new Node<T>(v);
             tail = head;
             nodeCount++;
             return;
          }

          Node<T> t = head;
          int i = 0;
          // {I: t is ith node}
          while (t != null && i != n)
          {
             t = t.next;
             i = i + 1;
          }

          Node<T> x = new Node<T>(v);
          if (t == null) { 
             tail.next = x;
             tail = x;
             nodeCount++;
          }
          else {
             Node<T> y = t.next;
             t.next = x;
             x.next = y;
             nodeCount++;
          }
       }   
             

      public void remove(int n)
      {
          if (head == null)
          {
             return;
          }

          if (n == 0) {
             head = head.next;
             if (head == null) tail = null;
             return;
          }

          Node<T> a = head;
          Node<T> b = head.next;
          int i = 1;
          // {I: b is ith node, a is the (i-1)th node}
          while (b != null && i != n)
          {
             a = a.next;
             b = b.next;
             i = i + 1;
          }

          if (b != null) { a.next = b.next; }
          if (b == tail) a = tail; 
       }

      public Node<T> get(int n)
      {
          Node<T> t = head;
          int i = 0;
          // {I: t is ith node}
          while (t != null && i != n)
          {
             t = t.next;
             i = i + 1;
          }

          return t;        
      }
      
      
      
      public LinkedList<T> partition( Node<T> pivot )
      {
        LinkedList<T> smalls = new LinkedList<T>();
        LinkedList<T> bigs = new LinkedList<T>();
        Node<T> curr;

        // *** PART 2 ***
        // walk through the list, removing nodes as we go, and adding them
        // to bigs or smalls as necessary
        for(int i=0; i<nodeCount; i++)
        {
            // we'll always work on the first node in the list
            curr = tail.next;
            
            // adjust the pointer coming out from tail
            // so that we skip over curr
            tail.next = tail.next;
            
            // add curr to the proper list
            // this will automatically change curr.next to point to
            // its new neighbor
            if((int) curr.v < (int) pivot.v)
                smalls.pushBack(curr.v);
            else
                bigs.pushBack(curr.v);
        }

        tail = bigs.tail;
        nodeCount = bigs.nodeCount;

        return smalls;
      } // end partition


      /***********************************************
      *
      *  rejoin: Given the sorted smalls list and 
      *  a pointer to the pivot, relink into the
      *  sorted list (which contains the sorted bigs)
      *  into the correct positions.
      *
      ************************************************/

      private void rejoin( LinkedList<T> smalls, Node<T> pivot )
      {
        Node<T> firstBig;
        smalls.pushBack( pivot.v ); // insert pivot at front
        smalls.tail = smalls.tail.next; // pivot comes after all smalls

        // Now add smalls + pivot into the sorted bigs between
        // the tail and first node.
        if ( nodeCount != 0 )
        {
          // There are some bigs.

          firstBig = this.tail.next;
          this.tail.next = smalls.tail.next; // tail big points at 1st small
          pivot.next = firstBig; // pivot at end of smalls points at 1st big
          nodeCount = nodeCount + smalls.nodeCount;
        }
        else
        {
          // There are no bigs.

          this.tail = smalls.tail;
          nodeCount = smalls.nodeCount;
        }

      } // end rejoin
      
      private Node<T> choosePivot( )
      {
        int randomIndex = generator.nextInt( nodeCount );
        Node<T> pivot;
        Node<T> prev;

        // *** PART 1 ***
        // simply use a for-loop to get the node at randomIndex
        // start at the first node (index 0), and keep grabbing the next node
        pivot = tail.next;
        prev = tail;
        for(int i=0; i<randomIndex; i++)
        {
            prev = pivot;
            pivot = pivot.next;
        }
            
        // now we need to unhook pivot from the list
        // simply change prev.next to skip over pivot
        prev.next = pivot.next;
        nodeCount--;

        return pivot;
      } // end choosePivot

      /***********************************
      *
      * quickSort: Recursively quick sort a
      *    a circular linked list with no
      *    dummy nodes.
      *
      *   (Since the partition method
      *   puts the smalls into a new
      *   circular linked list, after the
      *   recursive calls, the smalls must
      *   be joined back into the list.)
      *   
      **************************************/
      public void quickSort( )
      {
        Node<T> pivot;
        LinkedList<T> smalls;

        if ( nodeCount > 2 )
        {
          pivot = choosePivot(); // removes a pivot
          smalls = partition( pivot ); // bigs stay in original list
          smalls.quickSort(  );
          quickSort( ); // recursively quick sort the bigs
          rejoin( smalls, pivot ); // rejoin into one circular list
        }
        if ( nodeCount == 2 )
        {
          if ( (int) tail.v < (int) tail.next.v )
          { 
            // swap them: first becomes tail
            tail = tail.next;
          }
        }
        // else do nothing for nodeCount 0 or 1
      } // end quickSort
      
      public void print()
      {
          Node<T> t = head;
          // {I: nodes before t have heen printed}
          while (t != null)
          {
              System.out.print(t + "->");
              t = t.next;
          }
          System.out.println("null");
      }


      public static void main(String[] args)
      {
          LinkedList<String> list = new LinkedList<String>();
          list.pushBack("1");
          list.pushBack("3");
          list.pushBack("2");
          list.pushBack("4");
          list.pushFront("6");
          //list.insertAfter(0, "p");
          //list.print();
          //list.remove(0);
          list.print();
          System.out.println(list.getSize());
          list.quickSort();
          list.print();
          //System.out.println(list.get(1));
       }
}      
       
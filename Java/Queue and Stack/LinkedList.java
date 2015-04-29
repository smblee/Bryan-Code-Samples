/*
 *Name: Seungmin Lee
 * Date: 03/16/2015
 * CS2 Assignment 4
 * Question Number: 1, 2 (Merge, Palindrome)
*/

public class LinkedList<T> {

      private Node<T> head;
      private Node<T> tail;
      
      /*
       * ***************Question 1*****************************
       * Recursive Merge Function for two merged linked lists.*
       ********************************************************/
      
      public static Node<Integer> merge(Node<Integer> headA, Node<Integer> headB)
      {
        
        //Base cases
        if (headA == null) 
        {
//          System.out.println("List A is empty");
          return headB;
        }
        if (headB == null) 
        {
//          System.out.println("List B is empty");
          return headA;
        }
        
        if (headA.v > headB.v)
        {
          headB.next = merge(headA, headB.next);
          return headB;
        }
        else
        {
          headA.next = merge(headA.next,headB);
          return headA;
        }
      }

      /*
       * ***************Question 2***************************
       * Tell whether the LinkedList is a Palindrome or nah.*
       ******************************************************/

      public static Node<Integer> reverse(Node<Integer> head)
      {
    	Node<Integer> current = head;
    	Node<Integer> newHead = null;
    	while (current != null) {
    		Node<Integer> tmp = current;
    		current = current.next;
    		tmp.next = newHead;
    		newHead = tmp;
    	}
    	return newHead;
    	}

      
      public static boolean isPalindrome(Node<Integer> head)
      {
    	  Node<Integer> reversed = reverse(head); 
    	  while (head != null)
    	  {
    		  if (reversed.v == head.v)
    		  {
    			  reversed = reversed.next;
    			  head = head.next;
    			  continue;
    		  } 
    		  else if (reversed.v != head.v) { return false; }
    	  }
    	  return true;
      }
      
      public void pushBack(T v)
      {
          if (head == null)
          {
              head = new Node<T>(v);
              tail = head;
              return;
          }

          Node<T> x = new Node<T>(v);
          tail.next = x;
          tail = x;
      }

      public void pushFront(T v)
      {
          if (head == null)
          {
              head = new Node<T>(v);
              tail = head;
              return;
          }

          Node<T> x = new Node<T>(v);
          x.next = head;
          head = x;
      }

      public void insertAfter(int n, T v)
      {
          if (head == null)
          {
             head = new Node<T>(v);
             tail = head;
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
          }
          else {
             Node<T> y = t.next;
             t.next = x;
             x.next = y;
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
          LinkedList<Integer> lista = new LinkedList<Integer>();
          lista.pushBack(1);
          lista.pushBack(3);
          lista.pushBack(4);
          lista.pushBack(5);
          lista.pushBack(6);
          System.out.print("LinkedList A is: ");
          lista.print();
          
          LinkedList<Integer> listb = new LinkedList<Integer>();
          listb.pushBack(2);
          listb.pushBack(4);
          listb.pushBack(6);
          listb.pushBack(9);
          listb.pushBack(10);
          System.out.print("LinkedList B is: ");
          listb.print();
          
          Node<Integer> headA = lista.head; //assign starting values
          Node<Integer> headB = listb.head; //assign starting values
          
          merge(headA, headB); //Merge two lists into listA.
          System.out.print("Merged LinkedList of A and B is: ");
          
          LinkedList<Integer> mergedList = headA.v > headB.v ? listb : lista;
          mergedList.print(); //Print the new combined merged list.
          System.out.println(""); //Add spaces so we can test the Palindrome.
          
          LinkedList<Integer> listc = new LinkedList<Integer>();
          listc.pushBack(2);
          listc.pushBack(4);
          listc.pushBack(6);
          listc.pushBack(4);
          listc.pushBack(2);
          System.out.print("LinkedList C is: ");
          listc.print();

          Node<Integer> headC = listc.head; //assign starting values

          boolean result1 = isPalindrome(headC);
          System.out.println("The LinkedList is a Palindrome : " + result1); //This is true.
          
          LinkedList<Integer> listd = new LinkedList<Integer>();
          listd.pushBack(1);
          listd.pushBack(5);
          listd.pushBack(7);
          listd.pushBack(6);
          listd.pushBack(4);
          System.out.print("LinkedList D is: ");
          listd.print();
          
          Node<Integer> headD = listd.head; //assign starting values

          boolean result2 = isPalindrome(headD);
          System.out.println("The LinkedList is a Palindrome : " + result2); //This is false.
          
      
      }      
}      

class Node<T> {
    T v;
    Node<T> next;
    public Node(T v) { this.v = v; next = null;}
    public String toString() { return v+""; }

}
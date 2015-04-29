// Helper class for StackWithMax.java. It must be in the same folder as StackWithMax.java.
public class Stack<T> {

    class Node {
        T val;
        Node next;
        Node(T val) { this.val = val; next = null; }
    }

    public Node top;
    public boolean isEmpty()
    {
       return top == null;
    }

    public void push(T v) 
    {
        if (top == null) 
        {
           top = new Node(v);
           return;
        }

       Node t = new Node(v);
       t.next = top;
       top = t;
    } 

    public T peek()
    {
       if (top == null) return null;
       return top.val;
    }

    public void pop()
    {
       if (top == null) return;
       Node t = top;
       top = top.next;
       t.next = null;
    }
    

    public void show()
    {
       Node t = top;
       while (t != null)
       {
          System.out.print(t.val + " ");  
          t = t.next;
       }
       System.out.println();
       
    }
}


   

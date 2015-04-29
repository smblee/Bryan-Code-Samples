/*
 *Name: Seungmin Lee
 * Date: 03/16/2015
 * CS2 Assignment 4
 * Question Number: 3 (Stack with Max function O(1) )
 * ## This class uses the Stack<T> class to create another stack called maxStack. ##
*/

public class StackWithMax<T> extends Stack<T> {

	public Stack<T> maxStack;
	//Declare StackWithMax class and initialize maxStack variable.

	public StackWithMax () {  
        maxStack = new Stack<T>();    
    }

    public void push(T v) 
    {
        if (this.top == null) 
        {
           this.top = new Node(v);
           maxStack.top = new Node(v);
           return;
        }

        if ((Integer) v >= (Integer) maxStack.peek()) 
        	maxStack.push(v);
        else
        	maxStack.push(maxStack.peek());
        Node t = new Node(v);
        t.next = top;
        this.top = t;
    }
    
    public void pop()
    {
       if (top == null) return;
       
       maxStack.pop();
       Node t = this.top;
       top = this.top.next;
       t.next = null;
    }
    
    public void show() //Shows the Actual Stack and MAX stack.
    {
        Node t = top;
        Node maxt = maxStack.top;
        // Print the stack //
        System.out.print("The Stack: ");
        while (t != null)
        {
           System.out.print(t.val + " ");  
           t = t.next;
        }
        System.out.println();
        // Print max stack //
        System.out.print("The MAX Stack: ");
        while (maxt != null)
        {
     	   System.out.print(maxt.val + " ");
     	   maxt = maxt.next;
        }
        System.out.println();        
    }

    public T max() //get only the max value.
    {
    	return maxStack.peek();
    }
    
    public void getMax() //prints max
    {
    	System.out.println("The MAX is: " + this.max());
    }
    
    
    public static void main(String[] args)
    {
        StackWithMax<Integer> s = new StackWithMax<Integer>();
        s.push(2); s.show(); s.getMax(); //add 2 to stack.
        System.out.println();
        
        s.push(3); s.show(); s.getMax(); //add 3 to stack.
        System.out.println();
        
        s.push(3); s.show(); s.getMax(); // add 3
        System.out.println();
        
        s.push(5); s.show(); s.getMax(); // add 5 ; 5 should have been added to the Max Stack.
        System.out.println();

        s.push(2); s.show(); s.getMax(); // add 2
        System.out.println();

        s.pop();   s.show(); s.getMax(); // pop from stack
        System.out.println();

        s.pop();   s.show(); s.getMax(); // pop from stack
        System.out.println();
    }
}
/*
 *Name: Seungmin Lee
 * Date: 03/16/2015
 * CS2 Assignment 4
 * Question Number: 4,5 (Queue (that builds with 2 stacks) with Max function O(1) & Max function with sliding window )
 * ## This class uses my StackWithMax<T> class to find max for both stacks a,b, and return the greater max for the queue. ##
*/

public class Queue<T> extends StackWithMax<T> {
       private StackWithMax<T> a;
       private StackWithMax<T> b;
       
       public Queue() {
           a = new StackWithMax<T>();
           b = new StackWithMax<T>();
       }
// Question 4. This is the max function //
       public T max() {
        if (a.max() == null)
         return b.max();
        if (b.max() == null)
         return a.max();
        return (Integer)a.max() > (Integer) b.max() ? a.max() : b.max(); 
       }
// Question 5. This is the sliding window max function //   
       public void max(int slidingWindow)
       {
        int i = 0;
        System.out.print("The Max with sliding window (" + slidingWindow + ") is : ");
        
        while (i < slidingWindow)
        {
            if (a.max() == null)
             { System.out.print(b.max() + " "); i++; b.maxStack.pop(); continue; }
            if (b.max() == null)
             { System.out.print(a.max() + " "); i++; a.maxStack.pop(); continue; }

         if ( (Integer)a.max() > (Integer)b.max() )
         {
          System.out.print( a.max() + " " );
           a.maxStack.pop();
         } 
         else 
         {
          System.out.print( b.max() + " " );         
          b.maxStack.pop();
         }
         
         i++;
        }
        System.out.println();
       }
       
       public void showStacks() {
        System.out.println("~~~STACK INFOS~~~");
        System.out.println("-Stack A INFO");
        a.show();
        System.out.println("-Stack B INFO");
        b.show();
        System.out.println("~~~END OF INFO~~~");
       }
       

       
       private void move()
       {
           while(!a.isEmpty())
           {
               T v = a.peek();
               b.push(v);
               a.pop();
           }
       }

       public void enqueue(T v)
       {
           a.push(v);
       }
   
       public T front() {
           if ( b.isEmpty() )
              move();
           return b.peek();
       }

       public void dequeue()
       {
           if ( b.isEmpty() )
              move();
           b.pop();
       }

       public boolean isEmpty()
       {
          return a.isEmpty() && b.isEmpty();
       }
       
       public Queue<T> copy(Queue<T> original)
       {
        Queue<T> temp = new Queue<T>();
        Queue<T> copy = new Queue<T>();
        
        while (!original.isEmpty())
        {
         temp.enqueue(original.front());
         copy.enqueue(original.front());
         original.dequeue();         
        }
        while (!temp.isEmpty())
        {
         original.enqueue(temp.front());
         temp.dequeue();
        }
        return copy;
         
       }     

       public void print()
       {
        Queue<T> copied = copy(this);
        System.out.print("The Queue: ");
           while( !copied.isEmpty() )
           {
               System.out.print(copied.front() + " ");
               copied.dequeue();
           }
           System.out.println();
       }     

       public static void main(String[] args)
       {
           Queue<Integer> q1 = new Queue<Integer>();
           q1.enqueue(4);
           q1.enqueue(4);
           q1.enqueue(3);
           q1.dequeue();
           q1.enqueue(4);
           q1.enqueue(5);
           q1.enqueue(6); 
           q1.enqueue(1);//q1 should look like "4,3,4,5,6,1"
           q1.print();
           System.out.println("The Max of Queue 'q1' is: " + q1.max()); //should return "6" 
           q1.max(3); //show max with sliding window 3. In this example, should return "6,6,5"
           System.out.println();
           
           Queue<Integer> test2 = new Queue<Integer>();
           test2.enqueue(1);
           test2.enqueue(3);
           test2.enqueue(2);
           test2.enqueue(5);
           test2.enqueue(4);
           test2.print();
           System.out.println("The Max of Queue 'test2' is: " + test2.max()); //should return "6"
           test2.max(4); //show max with sliding window 4. In this example, should return "5,5,3,3"
           
           /* test2 Queue looks like:
            * Queue: 1 3 2 5 4
            * Max:   1 3 3 5 5
            * So if max of sliding window 4 is called, it should return the max values when the Queue is at 3 2 5 4
            * Which is 3 3 5 5.
            * My sliding window max function returns 5 5 3 3 (reversed) because of my definition of Stack.
            * I could adjust it to reverse it for printing purpose, but Professor said don't worry about "print" at all. 
            * So I am leaving it as is. But everything should work!
            */
           
           
       }    
           
}          
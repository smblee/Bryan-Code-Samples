/*
 *  Name: Seungmin Lee
 *  Date : 2015/02/28
 * Assignment 3
 * Question 2
*/
import java.util.*;

public class Circular 
{
    private int[] a; // array variable
    private int front, back; // pointers for front and back
    private int counter; // counter variable 
    
    // ****************Constructors********************//
    public Circular()
    {
      front =0;
      back = 1;
      counter = 0;
      this.a = new int[10];
    }
    public Circular(int N)
    {
        front = 0; 
        back = 1;
        counter = 0; 
        this.a = new int[N];
    }
//************************************************//   
    //helper functions
    private int checkIndex(int b, int[] a)
    {
        if (b >= a.length) 
          b = b % a.length;
        return b;
    }

    public boolean isEmpty()
    {
        return front == back - 1 && counter == 0;
    }
    
    
    //main functions
    public void getItem(int i) // returns the position of the int i.
    {
        int index = (front + i) % a.length;
        System.out.println(a[index]); //prints the position
    }
    
    public void addItem(int item) // This will set the next empty spot. If the next spot index > array length, then the item will go into array[0].
    {                             
        if (back >= this.a.length)
        {
            back = back % this.a.length;
            this.a[back] = item;
            back++;
            counter++;
        }
        else
        {
            this.a[counter] = item;
            back++;
            counter++;
        }
        back = checkIndex(back,a);
        counter = checkIndex(counter,a);
    }
  public String toString ()
  {
      return Arrays.toString(this.a);
  }
  
    public static void main(String[] args)
    {
        Circular test = new Circular();
        System.out.println(test.isEmpty()); //Test if the isEmpty function works
        test.addItem(2);
        test.addItem(10);
        test.addItem(6);      
        test.addItem(3);
        test.addItem(7);
        test.addItem(5);
        test.addItem(4);
        test.addItem(15);
        test.addItem(23);
        test.addItem(5);
        System.out.println("The Circular Array is full --> " + test);        
        test.addItem(99); //index 0 should have become 2.
        System.out.println("The item was added to test[0] --> " + test);
        test.getItem(5); // test getItem function. Should return "5"
    }
}
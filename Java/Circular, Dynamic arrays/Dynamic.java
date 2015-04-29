/*
 *  Name: Seungmin Lee
 *  Date : 2015/02/28
 * Assignment 3
 * Question 2
*/
import java.util.*;
public class Dynamic
{
    private int[] a;
    private int front;
    private int back;
    private int counter;
 
    // ****************Constructors********************//    
    public Dynamic()
    {
      front =0;
      back = 1;
      counter = 0;
      this.a = new int[5];
    }
    
    public Dynamic(int N)
    {
        front = 0; 
        back = 1;
        counter = 0;
        this.a = new int[N];
    }
//************************************************//   
    //Helper function
    public int checkIndex(int b, int[] a)
    {
        if (b >= a.length)
          b = b%this.a.length;
        return b;
    }

    public void getItem(int i) // returns the position of the int i.
    {
        int index = (front + i) % a.length;
        System.out.println(a[index]); //prints the position
    }
    
    //main functions
    public void setItem(int item) // This will set the next empty spot. If the next spot index > array length, then the item will go into array[0].
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
    
    
    public boolean isEmpty()
    {
        return front == back - 1;
    }
    
    
    public void pushFront(int i)
    {
        int[] b = new int[a.length + 1];
        b[0] = i;
        for (int j = 1; j < b.length; j++)
        {
            b[j] = a[j-1];
        }
//        System.out.println(back);
        this.a = b;
    }
    public void pushBack(int i)
    {
        a = Arrays.copyOf(this.a,this.a.length + 1);
        this.a[this.a.length - 1] = i;
        back++;
    }
    public void popBack() //pops only the last element in the array
    {
        this.a = Arrays.copyOf(this.a, a.length-1);
        back--;
        if (back > 0) back = 0;
        counter++;
    }
    
    public void popFront() // only pops the first element of the array
    {
        int[] c = new int[this.a.length-1];
        for (int i = 1; i < this.a.length; i++)
        {
             c[i-1] = a[i];
        }
        this.a = c;
        counter--;
    }
    
    public String toString()
    {
        return Arrays.toString(this.a);
    }
    public static void main(String[] args)
    {
        int size = 5;
        Dynamic a = new Dynamic(size);
        System.out.println(a);
        a.pushBack(6); //size should become 1 larger.
        System.out.println(a);
        a.setItem(1); // adds elements to the next empty slot.
        a.setItem(3);
        a.setItem(2);
        System.out.println(a);
        a.popFront(); // removes the first element in list.
        System.out.println(a);
        a.pushFront(7); //Adds element to the beginning, and pushes all other elements to the right.
        System.out.println(a);
        a.popBack(); //Pops the last element
        System.out.println(a);
    }
}
/*
 * @author Seungmin Lee
 * CS 2 - Jiang
 * Assignment 5
 * 2015/03/28
 * 
 */
public class QuickSort {
	public static void swap (int A[], int x, int y)
	{
		int temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}
	
    public static int partition(int[] a, int first, int last)
    {
        int n = (int)(Math.random() * (last-first+1)) + first;
        swap(a, first, n); 
         
        int i = first+1;
        int j = last;
        // {I: a[first+1,i) <= a[first], a(j..last] > a[first]}
        while ( i <= j )
        {
            if (a[i] <= a[first]) 
                 ++i;
            else {
                swap(a, i, j);
                --j;
            }
        }
        swap(a, first, j);
        return j;
    }

                         
    public static void quickSort(int[] a, int first, int last)
    {
       if (first >= last) return;
       int k = partition(a, first, last);
       quickSort(a, first, k-1);
       quickSort(a, k+1, last);
    }

    public static void quickSort(int[] a)
    {
       quickSort(a, 0, a.length-1);
    }
    
    public static void show(int[] a)
    {
       for (int e: a)
         System.out.print(e + " ");
       System.out.println();
    }
    
    public static void main(String[] Args)
    {
    	int[] a = {9,5,6,3,4,2,8,1,7};
    	quickSort(a);
    	show(a);
    }
    
    
}

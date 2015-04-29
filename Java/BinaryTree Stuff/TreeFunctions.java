/**
 * @author Seungmin Bryan Lee
 * Assignment 6
 * 2015/04/14
 */
public class TreeFunctions {

	//	1. We have a binary tree which has an integer in each node. Write a function to return the largest number in the tree. You can assume that the input tree at least has one node.
	
	public static int findLargest(TreeNode<Integer> node)
	{
		int max = node.v;
		if(node.left != null) {
			max = Math.max(max, findLargest(node.left));
		}
		if(node.right != null) {
			max = Math.max(max, findLargest(node.right));
	  	}
		return max;
	}
	
	// 2. Write a function to compute the length of the longest path from root node to a leaf node in a binary tree.
	
	public static int findLongestPath(TreeNode<Integer> node)
	{
		if (node == null)
			return 0;
		else
		{
			return 1 + Math.max(findLongestPath(node.left), findLongestPath(node.right) );
		}
	}
	
	
	// 3. Write a function to flip a binary tree, i.e., each left node becomes right node and right node becomes left node (the two subtrees are also flipped)
	
	public static void flip(TreeNode<Integer> root)
	{
		TreeNode<Integer> tmp = root.left; // hold the reference to the left node.
		root.left = root.right; // change the actually reference of left to right.
		root.right = tmp; // put the temporary reference to the right node.
		
		if (root.left != null) flip(root.left);
		if (root.right != null) flip(root.right);
	}
	
	// 4. Write a function to check whether two binary trees are the same (two corresponding nodes contain the same value).
	
	public static boolean checkSame(TreeNode<Integer> a, TreeNode<Integer> b)
	{
		if (a == b)
			return true;
		if (a == null || b == null)
			return false;
		return (a.v == b.v) && checkSame(a.left, b.left) && checkSame(a.right, b.right) ;
	}
	
	// Printer function
	public static void printBinaryTree(TreeNode<Integer> root, int level){
	    if(root==null)
	         return;
	    printBinaryTree(root.right, level+1);
	    if(level!=0){
	        for(int i=0;i<level-1;i++)
	            System.out.print("|\t");
	            System.out.println("|-----"+root.v);
	    }
	    else
	        System.out.println(root.v);
	    printBinaryTree(root.left, level+1);
	}    	
	
	public static void printBinaryTree(TreeNode<Integer> root)
	{
		printBinaryTree(root, 0);
	}
	
	
 
	public static void main (String[] args)
	{
	// build example tree //
	 TreeNode<Integer> testTreeRoot = new TreeNode<Integer>(2);
	 TreeNode<Integer> tempNodeLeft = testTreeRoot;
	 TreeNode<Integer> tempNodeRight = testTreeRoot;
	 
	 tempNodeLeft.left = new TreeNode<Integer>(4);
	 tempNodeLeft.left.left = new TreeNode<Integer>(5);
	 tempNodeLeft.left.right = new TreeNode<Integer>(3);
	
	 tempNodeRight.right = new TreeNode<Integer>(3);
	 tempNodeRight.right.left = new TreeNode<Integer>(2);
	 tempNodeRight.right.right = new TreeNode<Integer>(1);
	 tempNodeRight.right.right.right = new TreeNode<Integer>(6);
	 tempNodeRight.right.right.left = new TreeNode<Integer>(5);
	 tempNodeRight.right.right.right.right = new TreeNode<Integer>(8);
	//////////////////////////////////////////////////////////////////////// 	 

	 //****************** //
	 /* This example tree looks like
	  *         2
	  *      4      3
	  *    5   3  2   1
	  *    		     5 6
	  *    				8
	  * So it should return ::
	  * 8 as max.
	  * 5 as longest path.
	  * 
	  ********************/
	 System.out.println(":: THE ORIGINAL TREE ::");
	 printBinaryTree(testTreeRoot); //print's the original tree.
	 
	 int largest = findLargest(testTreeRoot);
	 System.out.println("The largest number is: " + largest);
	 int longest = findLongestPath(testTreeRoot);
	 System.out.println("The longest path is: " + longest);
	 flip(testTreeRoot);
	 System.out.println(":: THE FLIPPED TREE ::");
	 printBinaryTree(testTreeRoot); //print's the flipped tree.
	 
	 
	 //////////////////////////////////////////////////////////////////////
	 // Test for checking if two trees are identical.
	 System.out.println("## Now testing whether or not two trees are identical. ##");
	 //example tree a
	 TreeNode<Integer> a = new TreeNode<Integer>(2);
	 TreeNode<Integer> aLeft = a;
	 TreeNode<Integer> aRight = a;
	 
	 aLeft.left = new TreeNode<Integer>(4);
	 aLeft.left.left = new TreeNode<Integer>(5);
	 aLeft.left.right = new TreeNode<Integer>(3);
	
	 aRight.right = new TreeNode<Integer>(3);
	 aRight.right.left = new TreeNode<Integer>(2);
	 aRight.right.right = new TreeNode<Integer>(1);
	 aRight.right.right.right = new TreeNode<Integer>(5);
	 
	 // example tree b (identical to tree a)
	 TreeNode<Integer> b = new TreeNode<Integer>(2);
	 TreeNode<Integer> bLeft = b;
	 TreeNode<Integer> bRight = b;
	 
	 bLeft.left = new TreeNode<Integer>(4);
	 bLeft.left.left = new TreeNode<Integer>(5);
	 bLeft.left.right = new TreeNode<Integer>(3);
	
	 bRight.right = new TreeNode<Integer>(3);
	 bRight.right.left = new TreeNode<Integer>(2);
	 bRight.right.right = new TreeNode<Integer>(1);
	 bRight.right.right.right = new TreeNode<Integer>(5);
	 
	 // example tree c (different from a and b)
	 TreeNode<Integer> c = new TreeNode<Integer>(2);
	 TreeNode<Integer> cLeft = c;
	 TreeNode<Integer> cRight = c;
	 
	 cLeft.left = new TreeNode<Integer>(4);
	 cLeft.left.left = new TreeNode<Integer>(5);
	 cLeft.left.right = new TreeNode<Integer>(3);
	
	 cRight.right = new TreeNode<Integer>(3);
	 cRight.right.left = new TreeNode<Integer>(2);
	 cRight.right.right = new TreeNode<Integer>(2);
	 
	 
	 System.out.println("Tree a and b are identical: " +checkSame(a,b));
	 System.out.println("Tree b and c are identical: " +checkSame(b,c));

	}
}

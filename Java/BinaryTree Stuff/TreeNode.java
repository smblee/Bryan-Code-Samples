public class TreeNode<T> {
	T v;
	TreeNode<T> left;
	TreeNode<T> right;
	TreeNode(T value) //initialize treeNode with treeNode(value) 
	{
		v = value;
		left = null;
		right = null;
	}
}
import java.util.Stack;

public class BSTIterator {
	static public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
			left = null;
			right = null;
		}
	}
	
	private Stack<TreeNode> parentStack = null;
	
	public BSTIterator(TreeNode root) {
		parentStack = new Stack<TreeNode>();
		if (root == null) {
			return;
		}
		TreeNode node = root;
		parentStack.push(node);
		while (node.left != null) {
			node = node.left;
			parentStack.push(node);
		}
	}
	
	public boolean hasNext() {
		return !parentStack.empty();
	}
	
	public int next() {
		TreeNode node = parentStack.pop();
		int result = node.val;
		if (node.right != null) {
			node = node.right;
			parentStack.push(node);
			while(node.left != null) {
				node = node.left;
				parentStack.push(node);
			}
		}
		return result;
	}
}

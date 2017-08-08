import java.util.ArrayDeque;

class BSTNode{
	int data;
	BSTNode left, right, parent;
	
	public BSTNode(){}
	public BSTNode(int x){data=x;}
}

public class BinarySerchTree{//does not contain duplicates
	BSTNode root;
	
	static BSTNode search(BSTNode root, int key){
		if(root==null || root.data == key)	return root;
		if(key<root.data)	return search(root.left,key);
		else				return search(root.right,key);
	}
	
	static BSTNode insert(BSTNode root, int x){//return root of new BST
		BSTNode v = root;
		while(v!=null && v.data!=x){
			if(x<v.data){
				if(v.left!=null)	v=v.left;
				else{
					BSTNode newNode = new BSTNode(x);
					newNode.parent=v;
					v.left= newNode;
					v=null;
					}//v=null to break
			}else{
				if(v.right!=null)	v=v.right;
				else{
					BSTNode newNode = new BSTNode(x);
					newNode.parent=v;
					v.right=newNode;
					v=null;
					}
			}
		}
		if(root==null)//empty BST
			root=new BSTNode(x);
		System.out.println(x+" inserted");
		return root;
	}

	static BSTNode treeMax(BSTNode n){
		if(n==null)	return n;
		while(n.right!=null)	n = n.right;
		return n;
	}
	
	static BSTNode treeMin(BSTNode n){
		if(n==null)	return n;
		while(n.left!=null)	n = n.left;
		return n;
	}
	
	void transplant(BSTNode u, BSTNode v){
		if(u.parent==null) root=v;
	    else if(u==u.parent.left) u.parent.left=v;
	    else u.parent.right=v;
	    if(v!=null) v.parent = u.parent;
	}
	
	void delete(BSTNode z){
		if(z==null) return;
	    int key = z.data;
	    if(z.left==null)   transplant(z, z.right);
	    else if(z.right==null) transplant(z, z.left);
	    else{
	        BSTNode y = treeMin(z.right);
	        if(y.parent!=z){
	            transplant(y, y.right);
	            y.right = z.right;
	            y.right.parent = y;
	        }
	        transplant(z, y);
	        y.left = z.left;
	        y.left.parent = y;
	    }
	    System.out.printf("%d deleted\n",key);
	}
	
	static void preOrder(BSTNode root) {
	    if(root!=null){
	        System.out.print(root.data+" ");
	        preOrder(root.left);
	        preOrder(root.right);
	    }
	}
	
	static void postOrder(BSTNode root) {
	    if(root!=null){
	        postOrder(root.left);
	        postOrder(root.right);
	        System.out.print(root.data+" ");
	    }
	}

	static void inOrder(BSTNode root) {
	    if(root!=null){
	        inOrder(root.left);
	        System.out.print(root.data+" ");
	        inOrder(root.right);
	    }
	}
	
	static void LevelOrder(BSTNode root){
	    if(root==null)  return;
	    ArrayDeque<BSTNode> q = new ArrayDeque<BSTNode>();
	    q.add(root);
	    while(!q.isEmpty()){
	        BSTNode n = q.remove();
	        System.out.print(n.data+" ");
	        if(n.left!=null)	q.add(n.left);
	        if(n.right!=null)	q.add(n.right);
	    }
	    System.out.println();
	}
	
    static int height(BSTNode root){
        return computeHeight(root)-1;
    }
	static int computeHeight(BSTNode root) {
      	if(root==null)   return 0;
        else{
            int hLeft = computeHeight(root.left), hRight = computeHeight(root.right);
            return hLeft>hRight?1+hLeft:1+hRight;
        }
    }
	
	static boolean checkBST(BSTNode root, int minValue, int maxValue) {
	    if (root == null)
	        return true;
	    
	    if (root.data < minValue || root.data > maxValue)
	        return false;
	    
	    return (    checkBST(root.left, minValue, root.data - 1) 
	            &&  checkBST(root.right, root.data + 1, maxValue)
	            );
	}
	    
    static boolean checkBST(BSTNode root) {
    	return checkBST(root, 0, 10000);
    }
	
	public static void main(String[] args) {
		BinarySerchTree bst = new BinarySerchTree();
		System.out.println("Height: "+ height(bst.root)+"\n");
		bst.root = insert(bst.root,10);
		bst.root = insert(bst.root,5);
		bst.root = insert(bst.root,7);
		bst.root = insert(bst.root,12);
		bst.root = insert(bst.root,6);
		bst.root = insert(bst.root,72);
		bst.delete(search(bst.root, 12));
		bst.delete(search(bst.root, 3));
		System.out.println();
		
		System.out.println("Minimum: "+treeMin(bst.root).data+"\n");
		
		System.out.println("7 is "+(search(bst.root, 7)!=null?"in the tree":"not in the tree"));
		System.out.println("9 is "+(search(bst.root, 9)!=null?"in the tree\n":"not in the tree\n"));
		
		System.out.print("InOrder:");	inOrder(bst.root);
		System.out.print("\nPreOrder:");preOrder(bst.root);
		System.out.print("\nPostOrder:");postOrder(bst.root);
		System.out.print("\nLevelOrder:");LevelOrder(bst.root);
		
		System.out.println("\nHeight: "+ height(bst.root));
		
		System.out.println("Parent of 7: "+search(bst.root, 7).parent.data);
	}

}
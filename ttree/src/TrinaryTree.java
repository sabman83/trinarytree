import java.util.LinkedList;
import java.util.Queue;

public class TrinaryTree<T extends Comparable<T>>{

    public static class Node<T>
    {
        public Node(T value)
        {
        	if(value == null)
        	{
        		throw new NullPointerException("value of node cannot be null");
        	}
            this.value = value;
        }
        
        public T leftValue()
        {
        	return (left == null)?null:left.value;
        }
        
        public T centerValue()
        {
        	return (center == null)?null:center.value;
        }
        
        public T rightValue()
        {
        	return ( right == null)?null:right.value;
        }
        
        public T value = null;
        
        public Node<T> left = null;
        public Node<T> right = null;
        public Node<T> center = null;
    }
    
    private boolean isLeafNode(Node<T> node)
    {
    	return (node != null && node.center == null && node.left == null && node.right == null);
    }
    
    private boolean isParent(Node<T> p, T value)
    {
    	return (p.leftValue() == value || p.centerValue() == value || p.rightValue() == value );
    }
    
    private Node<T> parentOf(Node<T> root, T value)
    {
    	Node<T> parent = root;
    	
    	while( (parent != null) && (!isParent(parent, value)) )
    	{
    		parent = (parent.value.compareTo(value) > 0) ? parent.left : parent.right; 
    	}
		return parent;
    }
    
    public Node<T> search(Node<T> root, T value)
    {
    	Node<T> node = root;
    	while(node.value != value)
    	{
    		node  = (node.value.compareTo(value) > 0) ? node.left : node.right;
    	}
    	
    	return node;
    }
    
    
    public void deleteNode(Node<T> root, T value)
    {
    	
    }
    
    
    
    public Node<T> insertNode(Node<T> root, T value)
    {
        Node<T> newNode = new Node<T>(value);

        if(root == null)
        {
            root = newNode;
            return root;
        }
        
        Node<T> currentNode =  root;
        
        do
        {
        	if(currentNode.value == value)
            {
        		currentNode.center = newNode;
        		break;
            }
            
            if( currentNode.value.compareTo(value) > 0)
            {
            	if(currentNode.left != null)
            	{
            		currentNode = currentNode.left;
            	}
            	else
            	{
            		currentNode.left = newNode;
            		break;
            	}
            }
            else
            {
            	if(currentNode.right != null)
            	{
            		currentNode = currentNode.right;
            	}
            	else
            	{
            		currentNode.right = newNode;
            		break;
            	}
            }
        }while(currentNode != null);
        return root;
    }
    
    public void printTree(Node<T> root)
    {
        Queue<Node<T>> q = new LinkedList<Node<T>>();
        
        q.add(root);
        q.add(null); /* using null as a flag to indicate next level*/
        
        while( !q.isEmpty() )
        {
            Node<T> node = q.poll();
            if( node == null)
            {
                System.out.println("");
                q.add(null);
                if(q.peek() == null)
                	break;
                continue;
            }
            System.out.print(node.value + " ");
            if(node.left != null)
            {
                q.add(node.left);
            }
            if(node.center != null)
            {
                q.add(node.center);
            }
            if(node.right != null)
            {
                q.add(node.right);
            }
        }
    }
    
    public static void main(String args[])
    {
        TrinaryTree<Integer> tree = new TrinaryTree<Integer>();
        Integer[] nodes = new Integer[]{5, 4, 9, 5, 7, 2, 2};
        
        Node<Integer> root = null; 
        for (Integer val : nodes)
        {
            root = tree.insertNode(root, val);
        }
        
        tree.printTree(root);
    }
}

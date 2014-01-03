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
        
        public T value = null;
        
        public Node<T> left = null;
        public Node<T> right = null;
        public Node<T> center = null;
    }
    
    public Node<T> insertNode(Node<T> root, T value)
    {
        Node<T> newNode = new Node<T>(value);

        if(root == null)
        {
            root = newNode;
            return root;
        }
        
        Node<T> curentNode =  root;
        
        do()
        {
        	if(current.value == value)
            {
            	root.center = newNode;
            }
            
            if( root.value < value)
            {
            	if(root.left != null)
            	{
            		currentNode = currentNode.left;
            	}
            }
            else
            {
            	if(root.right != null)
            	{
            		currentNode = 
            	}
            }
        }while(currentNode != null);
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
                continue;
            }
            System.out.print(node.value);
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
        Integer r = 5;
        System.out.println(r.compareTo(null));
        Integer[] nodes = new Integer[]{5, 4, 9, 5, 7, 2, 2};
        
        Node<Integer> root = null; 
        for (Integer val : nodes)
        {
            root = tree.insertNode(root, val);
        }
        
        tree.printTree(root);
    }
}

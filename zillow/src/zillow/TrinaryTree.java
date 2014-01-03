package zillow;

import java.util.LinkedList;
import java.util.Queue;

public class TrinaryTree<T extends Comparable<T>>{

    public static class Node<T>
    {
        public Node(T value)
        {
            this.value = value;
        }
        
        public T value = null;
        
        public Node<T> left = new Node<T>(null);
        public Node<T> right = new Node<T>(null);
        public Node<T> center = new Node<T>(null);
    }
    
    public Node<T> insertNode(Node<T> root, T value)
    {
        Node<T> newNode = new Node<T>(value);

        if(root == null)
        {
            root = newNode;
            return root;
        }
        
        Node<T> parent = root;
        do{
            if(parent.value == value)
            {
                parent = parent.center;
                break;
            }
            
            parent = parent.value.compareTo(value) < 0 ? parent.left : parent.right;
        }while(parent != null);
        
        parent = newNode;
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

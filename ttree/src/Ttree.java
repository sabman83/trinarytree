import java.util.LinkedList;
import java.util.Queue;

public class Ttree<T extends Comparable<T>>
{
    public static class Node<T>
    {
        public T value = null;
        public Node (T value)
        {
            this.value = value;
        }
        
        public Node<T> left;
        public Node<T> center;
        public Node<T> right;
    }
    
    public Node<T> insert(Node<T> root, T value)
    {
        Node<T> newNode = new Node<T>(value);
        
        if(root == null)
        {
            return newNode;
        }
        
        Node<T> currNode = root;
        
        while(currNode != null){
            if(currNode.value == value)
            {
                if(currNode.center == null)
                {
                    currNode.center = newNode;
                    break;
                }else
                {
                    currNode = currNode.center;
                }
            }
            
            if(currNode.value.compareTo(value)>0)
            {
                if(currNode.left == null)
                {
                    currNode.left = newNode;
                    break;
                }
                else
                {
                    currNode = currNode.left;
                }
            }else
            {
                if(currNode.right == null)
                {
                    currNode.right = newNode;
                    break;
                }
                else
                {
                    currNode = currNode.right;
                }
            }
        }
        
        return newNode;
    }
    
    private void enqueueChildren( Queue<Node<T>> q , Node<T> node )
    {
        if(node.left != null)
            q.add(node.left);
        
        if(node.center != null)
            q.add(node.center);
        
        if(node.right != null)
            q.add(node.right);
    }
    
    public void printTree(Node<T> root){
        Queue<Node<T>> q = new LinkedList<Node<T>>();
        q.add(root);
        q.add(null); /*using null as flag for new level*/
        
        Node<T> next = null;
        while( !q.isEmpty()){
            next = q.poll();
            if(next == null){
                System.out.println("\n");/*null means new level, move to next line*/
                if( !q.isEmpty() ){ /*if q is empty then we have traversed all nodes*/
                    q.add(null);
                }
                continue;
            }
            System.out.print(next.value);
            enqueueChildren(q, next);
        }
    }
    
    public static void main(String args[])
    {
        Ttree<Integer> tree = new Ttree<Integer>();
        Integer[] nodes = new Integer[]{5, 4, 9, 5, 7, 2, 2};
        
        Node<Integer> root = new Node<Integer>(nodes[0]); 
        
        for (int i=1; i<nodes.length; i++)
        {
            tree.insert(root, nodes[i]);
        }
        
        tree.printTree(root);
    }
}
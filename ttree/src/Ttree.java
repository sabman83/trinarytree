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
    
    private boolean isLeaf(Node<T> node)
    {
    	return ( node.left == null && node.center == null && node.right == null);
    }
    
    private Node<T> search( Node<T> root, T value )
    {
    	Node<T> node = root;
    	
    	while(node != null && node.value != value)
    	{
    		node = (node.value.compareTo(value) < 0) ? node.left : node.right;
    	}
    	
    	return ( node.center != null ) ? node.center : node;
    }
    
    private boolean isParent( Node<T> p, Node<T> c )
    {
    	return ( p.left == c || p.center == c || p.right == c );
    }
    
    private Node<T> parentOf( Node<T> root, Node<T> child )
    {
    	if( root == null || child == null ) {return null;}
    	
    	Node<T> parent = root;
    	
    	do
    	{
    		if( isParent( parent, child ) ) { break; }
    		
    		if( parent.value == child.value ) { parent = parent.center; break; }
    		
    		parent = ( parent.value.compareTo( child.value ) > 0 ? parent.left : parent.right);
    		
    	}while( parent  != null );
    	
    	return parent;
    }
    
    private void replaceChild( Node<T> parent, Node<T> child, Node<T> newChild)
    {
        if( parent == null || child == null) return;
            
        if(parent.left == child)
        {
            parent.left = newChild;
        }else if( parent.right == child)
        {
            parent.right = newChild;
        }else if( parent.center == child){
            parent.center = newChild;
        }
        
        child = null;
    }
    
    
   public Node<T> merge( Node<T> root, Node<T> l, Node<T> r)
   {
       Node<T> mergeRoot = null;
       
       if(isLeaf(r))
       {
           r.left = l;
           return r;
       }
       
       mergeRoot = l;
       
       while( mergeRoot.right != null )
       {
           mergeRoot = mergeRoot.right;
       }
       
       Node<T> parent = parentOf(root,mergeRoot);
       mergeRoot.right = r;
       replaceChild(parent, mergeRoot, null);
       
       return mergeRoot;
   }
    
   public void delete(Node<T> root, T value)
    {
    	Node<T> node = search( root,value );
    	Node<T> parent = parentOf( root,node );
    	
    	if( isLeaf( node ) )
    	{
    	    replaceChild(parent, node, null);
    		return;
    	}
    	

    	if(node.center != null)
    	{
    	    node.center.left = node.left;
    	    node.center.right = node.right;
    	    replaceChild(parent, node, node.center);
    	    return;
    	}
    	
    	Node<T> newChild = merge(root,node.left,node.right);
    	replaceChild(parent, node, newChild);
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
        tree.delete(root, 5);
        tree.delete(root, 5);
        tree.printTree(root);
        
    }
}
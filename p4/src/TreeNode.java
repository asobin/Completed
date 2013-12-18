/** The TreeNode class represents a single node in the family tree. A node has 
 * the infomation of a person (as a Person) and also the infomation about its 
 * parent node (as a TreeNode) and children nodes (as a List of TreeNodes).
 * 
 * DO NOT MODIFY THIS CLASS
 */
import java.util.*;

public class TreeNode<E> 
{
	private Person person;
	private TreeNode<E> parentNode;
	private List<TreeNode<E>> children;
	
	/** Constructs a TreeNode with person and parentNode. */
	public TreeNode (Person person, TreeNode<E> parentNode) 
	{
		this.person = person;
		this.parentNode = parentNode;
		children = new ArrayList<TreeNode<E>>();
	}
	
	/** Return the person in this node */
	public Person getPerson() 
	{
		return person;
	}
	
	/** Return the parent for the person in this node */
	public TreeNode<E> getParent()	
	{
		return parentNode;
	}
	
	/** Return the children for the person in this node */
	public List<TreeNode<E>> getChildren() 
	{
		return children;
	}
	
	/** Return the person in this node */
	public void addChild(TreeNode<E> childNode) 
	{
		children.add(childNode);
	}
}
import java.util.*;

/**
 * The class FamilyTree creates a general tree that stores the family members 
 * and can return data on individuals in the tree. Many of the methods
 * implemented are recursive.
 *
 * <p>Bugs:none known
 *
 *@author asobin
 */
public class FamilyTree
{
	static TreeNode<Person> root; //node that represents root of family tree
	String firstAncestor; //will hold the name of the mother at the root node.
	int maxGenerations; //holds the value of the # of generations in the tree
	Person person; //the node of the desired person
	List<Person> peopleEyes; //holds the eye color of the desired people
	List<Person> peopleWeight; //holds the weight of the desired people
	List<Person> aunts; //holds the aunts of an individual
	List<Person> siblings; //holds the siblings of an individual
	List<Person> sibsCousins; //holds all the cousins of the siblings
	List<Person> greatAunts; //holds all the great aunts of an individual

	/**
	 * FamilyTree
	 * constructs a family tree, initializes all variables
	 */
	public FamilyTree()
	{
		root = null;
		maxGenerations = 0;
		peopleEyes = new ArrayList<Person>();
		peopleWeight = new ArrayList<Person>();
		siblings = new ArrayList<Person>();
		firstAncestor = null;
	}
	/**
	 * getFirstAncestor
	 * gets the name of the first ancestor in this family tree
	 * @return firstAncestor
	 */
	public String getFirstAncestor()
	{
		if(root != null)
		{
			firstAncestor = root.getPerson().getName();
			return firstAncestor;
		}
		else
		{
			//if root is removed
			return "there is no ancestor";
		}
	}
	/**
	 * getNumPeople
	 * Return the number of people in this family tree
	 * @return numPeople
	 */
	public int getNumPeople()
	{
		int numberOfNodes = 0;

		if(root != null) 
		{
			numberOfNodes = getNumberOfNodes(root) + 1; //1 for the root!
		}
		return numberOfNodes;
	}
	/**
	 * getNumberOfNodes
	 * private recursive method returns the number of nodes in the tree/ number
	 * of people in the tree
	 * @param node
	 * @return
	 */
	private int getNumberOfNodes(TreeNode<Person> node) 
	{
		int numberOfNodes = node.getChildren().size();
		//for each person in the children's list increment count
		for(TreeNode<Person> child : node.getChildren()) 
		{
			numberOfNodes += getNumberOfNodes(child);
		}
		return numberOfNodes;
	}
	/**
	 * getMaxGenerations
	 * Return the maximum number of generations from the first ancestor to the
	 * farthest descendant in the family tree.
	 * @return maxGenerations
	 */
	public int getMaxGenerations()
	{
		//Return 0 for an empty family tree
		if(root == null)
		{
			return 0;
		}
		maxGenerations = getMaxGenerations(root);
		return maxGenerations;
	}
	/**
	 * getMaxGenerations
	 * private recursive method keeps count of the number of generations/height
	 * of the tree
	 * @param root2
	 * @return maxHeight
	 */
	private int getMaxGenerations(TreeNode<Person> root2) 
	{
		if(root2 == null)
		{
			return 0; //base
		}
		if(root2.getChildren().isEmpty())
		{
			return 1; //base
		}
		int maxHeight = 0;
		Iterator <TreeNode<Person>> itr = root2.getChildren().iterator();
		while(itr.hasNext())
		{
			int childHt = getMaxGenerations(itr.next());
			if(childHt > maxHeight)
			{
				maxHeight = childHt;
			}
		}
		return 1+maxHeight;
	}
	/**
	 * getPerson
	 * Returns the person of given name
	 * @param name
	 * @return person
	 */
	Person getPerson(String name)
	{
		if(contains(name))
		{
			return findPerson(root,name).getPerson();
		}
		return null;
	}
	/**
	 * addPerson
	 * Add person with given parent.
	 * @param person
	 * @param parentName
	 * @return true iff person is added correctly(no duplicate/has parent)
	 */
	public boolean addPerson(Person person, String parentName)
	{
		//When the root and parentName are both null, return true.  
		//Do not throw an IllegalArgumentException for this circumstance only.
		if(parentName == null && root == null)
		{
			root = new TreeNode<Person>(person, null);
			return true;
		}
		if(parentName.equals(root.getPerson().getName()))
		{
			TreeNode<Person> childNode = new TreeNode<Person>(person, root);
			root.addChild(childNode);
			return true;
		}
		//if the family tree already has this person, don't do anything
		//and return false
		if(contains(person.getName()))
		{
			return false;
		}
		//If there is no such parent, return false
		if(contains(parentName) == false)
		{
			return false;
		}
		//otherwise add the person and return true.
		addNewChild(root, parentName, person);
		return true;
	}
	/**
	 * contains
	 * Return true if the family tree contains person with given name, 
	 * otherwise return false.
	 * @param name
	 * @return true iff the family tree contains name
	 */
	public boolean contains(String name)
	{
		//return contains(root, name);
		if(findPerson(root,name) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	/**
	 * removeWithName
	 * Remove the person with given Name from the family tree, and remove all
	 * that person's descendants at the same time.
	 * @param name
	 * @return true iff removal was valid (name exists)
	 */
	public boolean removeWithName(String name)
	{
		//If there is no such person, return false
		if(!contains(name))
		{
			return false;
		}
		if(root.getPerson().getName().equals(name))
		{
			root = null;
			maxGenerations = 0;
			peopleEyes = new ArrayList<Person>();
			peopleWeight = new ArrayList<Person>();
			siblings = new ArrayList<Person>();
			firstAncestor = null;
			return true;
		}
		//  otherwise, return true after the removal
		return removePerson(name);
	}
	/**
	 * removePerson
	 * private recursive method that determines if a person was removed or not
	 * @param name
	 * @return true iff person was properly removed
	 */
	private boolean removePerson(String name) 
	{
		if(findPerson(root,name) != null){
			TreeNode<Person> removed = findPerson(root,name);
			removed.getParent().getChildren().remove(removed);
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * getPeopleWithEyeColor
	 * Return a list of people that have the given eye color.
	 * @param eyeColor
	 * @return peopleEyes
	 */
	public List<Person> getPeopleWithEyeColor(String eyeColor)
	{
		//create new arrayList for the person being passed in to be returned
		peopleEyes = new ArrayList<Person>();
		peopleEyes = getPeopleWithEyeColor(root, eyeColor);
		return peopleEyes;
	}
	/**
	 * getPeopleWithEyeColor
	 * private recursive method returns list of people with matching eye color
	 * @param n
	 * @param eyeColor
	 * @return peopleEyes
	 */
	private List<Person> getPeopleWithEyeColor(TreeNode<Person> n, 
			String eyeColor) 
			{
		if(n == null)
		{
			return null;
		}
		if(n.getPerson().getEyeColor().equals(eyeColor))
		{
			peopleEyes.add(n.getPerson());
		}
		Iterator<TreeNode<Person>> itr = n.getChildren().iterator();
		while(itr.hasNext())
		{
			getPeopleWithEyeColor(itr.next(),eyeColor);
		}
		return peopleEyes;
			}
	/**
	 * getPeopleInWeightRange
	 * Return the list of people whose weights are in the range from weight1 to
	 * weight2 inclusive (if weight1 <= weight2), or from weight2 to weight1
	 * inclusive (if weight2 < weight1).
	 * @param weightOne
	 * @param weightTwo
	 * @return peopleWeight
	 */
	public List<Person> getPeopleInWeightRange(double weightOne,
			double weightTwo)
			{
		//create new arrayList for the person being passed in to be returned
		peopleWeight = new ArrayList<Person>();
		peopleWeight = getPeopleInWeightRange(root, weightOne,weightTwo);
		return peopleWeight;
			}
	/**
	 * getPeopleInWeightRange
	 * private recursive method returns the list of people within the weight
	 * range.
	 * @param n
	 * @param weightOne
	 * @param weightTwo
	 * @return peopleWeight
	 */
	private List<Person> getPeopleInWeightRange(TreeNode<Person> n,
			double weightOne, double weightTwo) 
			{
		if(n == null)
		{
			return null;
		}
		//if youre between weight1 and weight 2 youre added to the list
		if((n.getPerson().getWeight() >= weightOne &&
				n.getPerson().getWeight() <= weightTwo) || 
				(n.getPerson().getWeight() <= weightOne &&
						n.getPerson().getWeight() >= weightTwo))
		{
			peopleWeight.add(n.getPerson());
		}
		Iterator<TreeNode<Person>> itr = n.getChildren().iterator();
		while(itr.hasNext())
		{
			getPeopleInWeightRange(itr.next(),weightOne,weightTwo);
		}
		return peopleWeight;
			}
	/**
	 * getAunts
	 * Return the list of aunts (mother's sisters) of this person.
	 * @param name
	 * @return aunts
	 */
	public List<Person> getAunts(String name)
	{
		// If there is no such person in the family tree or the person doesn't
		//have an aunt, return null.
		aunts = new ArrayList<Person>();
		aunts = getAunts(root,name);
		return aunts;
	}
	/**
	 * getAunts
	 * private recursive method returns a list of Aunts
	 * @param n
	 * @param name
	 * @return aunts
	 */
	private List<Person> getAunts(TreeNode<Person> n, String name) 
	{
		if(n == null)
		{
			return null;
		}
		if(n.getPerson().getName().equals(name))
		{
			List<TreeNode<Person>> tmp = n.getParent().
			getParent().getChildren();
			Iterator<TreeNode<Person>> itr = tmp.iterator();
			while(itr.hasNext())
			{
				TreeNode<Person> tmp1 = itr.next();
				
				//if youre not the person passed in add to list
				if(!tmp1.getPerson().getName().equals(findPerson(root,name).
						getParent().getPerson().getName()))
				{
					aunts.add(tmp1.getPerson());
				}//end if
			}//end while
		}//end if
		Iterator<TreeNode<Person>> itr = n.getChildren().iterator();
		while(itr.hasNext())
		{
			getAunts(itr.next(),name);
		}
		return aunts;
	}
	/**
	 * getSiblings
	 * Return the list of siblings of this person.
	 * @param name
	 * @return siblings
	 */
	public List<Person> getSiblings(String name)
	{
		//If there is no such person in the family tree or the person doesn't
		//have any sibling, return null.
		siblings = new ArrayList<Person>();
		siblings = getSiblings(root, name);
		return siblings;
	}
	/**
	 * getSiblings
	 * gets and returns the list of persons for the siblings list
	 * @param n
	 * @param name
	 * @return siblings
	 */
	private List<Person> getSiblings(TreeNode<Person> n, String name) 
	{
		if(n == null)
		{
			return null;
		}
		if(n.getPerson().getName().equals(name))
		{
			List<TreeNode<Person>> tmp = n.getParent().getChildren();
			Iterator<TreeNode<Person>> itr = tmp.iterator();
			while(itr.hasNext())
			{
				TreeNode<Person> tmp1 = itr.next();
				//if youre not the person passed in add to list
				if(!tmp1.getPerson().getName().equals(name))
				{
					siblings.add(tmp1.getPerson());
				}//end if
			}//end while
		}//end if
		Iterator<TreeNode<Person>> itr = n.getChildren().iterator();
		while(itr.hasNext())
		{
			getSiblings(itr.next(),name);
		}
		return siblings;
	}
	/**
	 * getAllSiblingsCousins
	 * Return the list of people who are siblings or cousins of the
	 * given person.
	 * @param name
	 * @return sibsCousins
	 */
	public List<Person> getAllSiblingsCousins(String name)
	{
		// If there is no such person in the family tree or the person doesn't
		//have siblings or cousins, return null.
		sibsCousins = new ArrayList<Person>();
		sibsCousins = getAllSiblingsCousins(root,name);
		return sibsCousins;
	}
	/**
	 * getAllSiblingsCousins
	 * private recursive method returns a list of the persons siblings and 
	 * cousins
	 * @param n
	 * @param name
	 * @return sibsCousins
	 */
	private List<Person> getAllSiblingsCousins(TreeNode<Person> n,
			String name) 
			{
		if(n == null)
		{
			return null;
		}
		if(n.getPerson().getName().equals(name))
		{
			List<TreeNode<Person>> tmp = 
				n.getParent().getParent().getChildren();
			//iterates through list of parents 
			Iterator<TreeNode<Person>> itr3 = tmp.iterator();
			while(itr3.hasNext())
			{
				TreeNode<Person> tmp3 = itr3.next();
				List<TreeNode<Person>> tmp1 = tmp3.getChildren();
				//iterates through list of children for this parent
				Iterator<TreeNode<Person>> itr2 = tmp1.iterator();
				while(itr2.hasNext())
				{
					TreeNode<Person> tmp2 = itr2.next();
					//add everyone except the person being passed in
					if(!tmp2.getPerson().getName()
							.equals(findPerson(root,name).
									getPerson().getName()))
					{
						sibsCousins.add(tmp2.getPerson());
					}
				}//end while
			}//end while
		}//end if
		Iterator<TreeNode<Person>> itr = n.getChildren().iterator();
		while(itr.hasNext())
		{
			getAllSiblingsCousins(itr.next(),name);
		}
		return sibsCousins;
			}
	/**
	 * getGreatAunts
	 * Return the list of people who are great aunts of the given person.
	 * @param name
	 * @return greatAunts
	 */
	public List<Person> getGreatAunts(String name)
	{
		// If there is no such person in the family tree or the person doesn't
		// have great aunts, return null.
		greatAunts = new ArrayList<Person>();
		greatAunts = greatAunts(root, name);
		return greatAunts;
	}
	/**
	 * greatAunts
	 * private recursive method for greatAunts
	 * @param n
	 * @param name
	 * @return list of greatAunts
	 */
	private List<Person> greatAunts(TreeNode<Person> n, String name) 
	{
		if(n == null)
		{
			return null;
		}
		if(n.getPerson().getName().equals(name))
		{
			List<TreeNode<Person>> tmp = n.getParent().
			getParent().getParent().getChildren();
			Iterator<TreeNode<Person>> itr = tmp.iterator();
			while(itr.hasNext())
			{
				TreeNode<Person> tmp1 = itr.next();
				//if youre not the person passed in add to list
				if(!tmp1.getPerson().getName().equals(
						findPerson(root, name).getParent().
						getParent().getPerson().getName()))
				{	
					greatAunts.add(tmp1.getPerson());
				}//end if
			}//end while
		}//end if
		Iterator<TreeNode<Person>> itr = n.getChildren().iterator();
		while(itr.hasNext())
		{
			greatAunts(itr.next(),name); //recursive case
		}
		return greatAunts;
	}
	/**
	 * addNewChild
	 * recursive private method for addNewChild
	 * @param name
	 * @param parentName
	 * @param p
	 */
	private  void addNewChild(TreeNode<Person> name, 
			String parentName, Person p)
	{
		if(name.getChildren() == null) 
			return; //base
		if(name.getPerson().getName().equals(parentName))
		{
			name.addChild(new TreeNode<Person>(p, name));
			return; //base
		}
		Iterator<TreeNode<Person>> itr = name.getChildren().iterator();
		while(itr.hasNext())
		{
			addNewChild(itr.next(), parentName, p); //recursive case
		}
	}
	/**
	 * findPerson
	 * Returns the Person with given name, otherwise
	 * return null.
	 * @param n, name
	 * @return Person if found in the tree, otherwise return null
	 */
	private TreeNode<Person> findPerson(TreeNode<Person> n , String name)
	{
		if(n == null)
		{
			return null; //base
		}
		if(n.getPerson().getName().equals(name))
		{
			return n; //base
		}
		if(n.getChildren().isEmpty())
		{
			return null; //base
		}
		Iterator<TreeNode<Person>> itr = n.getChildren().iterator();
		while(itr.hasNext())
		{
			TreeNode<Person> tmp = findPerson(itr.next(),name);
			//when the recursion finds a person, return that person
			if(tmp != null)
			{
				return tmp;
			}
		}
		return null;

	}
}

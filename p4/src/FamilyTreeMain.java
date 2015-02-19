import java.util.*;
import java.io.*;
/**
 * The class FamilyTreeMain loads in a file and breaks up its data line by line
 * creating a general tree that can do a number of processes on the data.
 *
 *<p>Bugs: If data file passed in is altered there may be issues with 
 *loading in the data. 
 *
 * @author asobin
 */
public class FamilyTreeMain
{
	/**
	 * main
	 * executes program FamilyTree
	 * @param args
	 */
	public static void main(String[] args)
	{
		boolean stop = false;
		//Step 1: Check whether exactly one argument is given
		if(args.length >= 2 || args.length < 1)
		{
			System.out.println("invalid command-line args");
			System.exit(-1);
		}
		//Step 2: Check whether the input file exists and is readable
		String filename = args[0];
		File f = new File(filename);
		if(!f.canRead()||!f.exists())
		{
			System.out.println("Error: Cannot access input file");
			System.exit(-1);
		}
		/* Step 3: Load the data from the input file and use it to
		 *  construct a family tree. Note: people are to be added to the
		 *  family tree in the order in which they appear in the text file.
		 */
		FamilyTree tree = new FamilyTree();
		String regex = ",";
		try
		{
			Scanner in = new Scanner(f);
			while(in.hasNextLine())
			{
				String familyFile = in.nextLine();
				in.useDelimiter(", *");
				//holds the info in a line of the file in an array parsed by
				//commas
				String[] fileLine = familyFile.split(regex);
				if(fileLine.length < 3)
				{
					//ignore blank lines in file to avoid arrayOBE
				}
				else{
					//holds the name of the individual in each line
					String name = fileLine[0];
					//holds eyecolor of the individual in each line
					String eyecolor = fileLine[1];
					//holds weight of the individual in each line
					double weight = Double.parseDouble(fileLine[2]);
					//constructs new person
					Person newPerson = new Person(name, eyecolor, weight);
					if(fileLine.length == 4)
					{
						//if person has a parent, add person to tree
						tree.addPerson(newPerson, fileLine[3]);    
					}
					else
					{
						//if person does not have a parent person to tree as root
						tree.addPerson(newPerson, null);
					}
				}
			} //end while
			in.close();
		}//end try
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
		}
		/* Step 4: Prompt the user to enter command options and
		 *  process them until the user types x for exit.
		 */
		while (!stop)
		{
			Scanner stdin = new Scanner(System.in);
			System.out.println("Enter Command:");
			String input = stdin.nextLine();
			String remainder = null;
			if (input.length() > 0)
			{
				char option = input.charAt(0);
				if (input.length() > 1)
				{
					remainder = input.substring(1).trim();
				}
				switch (option)
				{
				/*
				 * case c prints the name(s) of all the siblings and cousins 
				 * of the person with given name. Print the names on separate
				 * lines. 
				 */
				case 'c':
				{
						if(tree.contains(remainder))
						{
							if(tree.getAllSiblingsCousins(remainder) != null)
							{
								List<Person> sibCousin = tree.
								getAllSiblingsCousins(remainder);
								//if person has no sibling of cousin
								if(sibCousin.size() == 0)
								{
									System.out.println("the person has no " +
											"sibling or cousin" + "\n");
								}
								else
								{
									Iterator <Person> itr = 
										sibCousin.iterator();
									while(itr.hasNext())
									{
										System.out.println(itr.next().
												getName());
									}
									System.out.println();
								}//end else
							}// end if
						}//end if
						else //if no such person is found
						{
							System.out.println("person not found" + "\n");
						}
					break;
				}//end c
				/*
				 * case d displays information about the family tree
				 */
				case 'd':
				{
						System.out.println("# of persons in family tree: "
								+ tree.getNumPeople());
						System.out.println("max generations in family tree: " +
								"" + tree.getMaxGenerations());
						System.out.println("first ancestor: " 
								+ tree.getFirstAncestor() + "\n");
					break;
				}
				/*
				 * case e prints the name(s) of the person(s) that has the given
				 * eye color.
				 */
				case 'e':
				{
						List<Person> eyes =
							tree.getPeopleWithEyeColor(remainder);
						if(eyes.size() == 0)
						{
							System.out.println("person not found" + "\n");
						}
						else
						{
							Iterator <Person> itr = eyes.iterator();
							while(itr.hasNext())
							{
								System.out.println(itr.next().getName());
							}
							System.out.println();
						}//end else
					break;
				}//end e
				/*
				 * case g prints the name(s) of the great aunts of the person 
				 * with given name.
				 */
				case 'g':
				{
						if(tree.contains(remainder) == false)
						{
							System.out.println("person not found" + "\n");
						}
						else
						{
							List <Person> gaunts = 
								tree.getGreatAunts(remainder);
							if(gaunts.size() == 0)
							{
								System.out.println("person not found"+ "\n");
							}
							else
							{
								Iterator <Person> itr = gaunts.iterator();
								while(itr.hasNext())
								{
									System.out.println(itr.next().getName());
								}
								System.out.println();
							}//end else
						}//end else
					break;
				}//end g
				/*
				 * case r removes the person with given name from the family
				 * tree (also removes all descendants)
				 */
				case 'r':
				{
					if(tree.contains(remainder))
					{
						if(tree.removeWithName(remainder) == true)
						{
							System.out.println("person removed"+ "\n");
						}      
					}
					else
					{
						System.out.println("person not found" + "\n");
					}
					break;
				}//end r
				/*
				 * case s prints the name(s) of the siblings of the person with
				 * given name.
				 */
				case 's':
				{
					if(tree.contains(remainder) == false)
					{
						System.out.println("person not found"+ "\n");
					}
					else
					{
						List <Person> siblings = tree.getSiblings(remainder);
						if(siblings.size() == 0)
						{
							System.out.println("the person has no sibling"
									+ "\n");
						}
						else
						{
							Iterator <Person> itr = siblings.iterator();
							while(itr.hasNext())
							{
								System.out.println(itr.next().getName());
							}
							System.out.println();
						}//end else
					}//end else
					break;
				}//end s
				/*
				 * case u prints the name(s) of the aunts of the person with 
				 * given name. 
				 */
				case 'u':
				{
						if(tree.contains(remainder) == false)
						{
							System.out.println("person not found"+ "\n");
						}
						else
						{
							List <Person> aunts = tree.getAunts(remainder);
							if(aunts.size() == 0)
							{
								System.out.println("person has no aunt"+ "\n");
							}
							else
							{
								Iterator <Person> itr = aunts.iterator();
								while(itr.hasNext())
								{
									System.out.println(itr.next().getName());
								}
								System.out.println();
							}//end else  
						}//end else
					break;
				}//end u
				/*
				 * case w prints the name(s) of the person(s) whose weight are
				 * between weight1 and weight2
				 */
				case 'w':
				{
					try
					{
						//try block used to prevent any non double input
						// from triggering an exception
						String [] weights = remainder.split(",");
						double weightOne = Double.parseDouble(weights[0]);
						double weightTwo = Double.parseDouble(weights[1]);
						List<Person> peopleWeight = 
							tree.getPeopleInWeightRange(weightOne, weightTwo);
						if(peopleWeight.size() == 0)
						{
							System.out.println("person not found"+ "\n");
						}
						else
						{
							Iterator <Person> itr = peopleWeight.iterator();
							while(itr.hasNext())
							{
								System.out.println(itr.next().getName());
							}
							System.out.println();
						}
					}
					catch(NumberFormatException e)
					{
						System.out.println("person not found"+ "\n");
					}
					break;
				}
				//***case x exits program***
				case 'x':
				{
					stop = true;
					System.out.println("exit");
					break;
				}
				default:
					break;
				} //end switch
			} //end if
		} //end while
	} //end main
} //end FamilyTreeMain

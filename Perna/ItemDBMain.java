import java.io.*;
import java.util.*;

/**
 * The application program, ItemDBMain, creates and uses a ItemsDatabase to 
 * represent and process information about data passed into it. It is meant 
 * to be a fairly simple application to interact with a database.
 * The information is read from a text file and then the program processes user 
 * commands. 
 * 
 * <p>Bugs: none
 * @author sobin
 *
 */
public class ItemDBMain 
{

	//tracks and holds the value for the Item with the most # of subItems 
	private static int mostInt = 0;

	//tracks and holds the value for the Item with the least # of subItems
	private static int leastInt = 0;

	//tracks and holds the value for the average # of subItems in the ItemsDB
	private static int averageInt = 0;
	/**
	 * main check whether exactly one command-line argument is given; 
	 * if not, display "Usage: java ItemDBMain FileName" and quit.
	 * Check whether the input file exists and is readable; if not, 
	 * display "Error: Cannot access input file" and quit.
	 * Load the data from the input file and use it to construct a Item
	 * database. Note: subItems are to be added to the Item database in
	 * the order in which they appear in the text file.
	 * Prompt the user to enter command options and process them 
	 * until the user types x for exit.

	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		//tracks and holds value of the # of subItems in the ItemsDB
		int subItemscount = 0;
		/**
		 * 1.
		 * Check whether exactly one command-line argument is given; 
		 * if not, display "Usage: java ItemDBMain FileName" and quit.
		 */
		if ( args.length == 0 || args.length > 1 || args.equals(null))
		{
			System.out.println("Usage: java ItemDBMain FileName");
			System.exit(0);
		}
		/**
		 * 2.
		 * Check whether the input file exists and is readable; 
		 * if not, display "Error: Cannot access input file" and quit.
		 */
		File inputFile = new File(args[0]);
		if (!inputFile.canRead() || !inputFile.exists())
		{
			System.out.println("Error: Cannot access input file");
			System.exit(0);
		}

		ItemsDatabase database = new ItemsDatabase();
		//regex hold the regular expression value used for parsing args
		String regex1 = ": ";
		String regex2 = " ";
		String regex3 = "\\|";
		/**
		 * 3.
		 * Load the data from the input file and use it to construct an item 
		 * database.
		 */
		try
		{
			Scanner in = new Scanner(inputFile);
			while(in.hasNextLine())
			{
				String ItemsDBFileIn = in.nextLine();
				in.useDelimiter("[:\\s]");

				//ItemDatabase holds the value of the line in the scanner  
				String[] itemsDatabase = ItemsDBFileIn.split(regex1);

				//one holds the string before the regex in the line
				String orthologousGroupID = itemsDatabase[0];
				database.addItem(orthologousGroupID);
				//two holds the values after the regex in the line
				//System.out.println(orthologousGroupID);
				String [] remainder = null;
				String [] first = null; //first subItem in the line
										//we make exception for it bc its regex
										//is uniquely different from the rest
				String [] rest = null;
				String [] species = null;
				String [] gene = null;
				String [] p1 = null;
				String [] p2 = null;
				for(int i = 1; i < itemsDatabase.length; i++)
				{
					remainder = itemsDatabase[i].split(regex2);
					first = remainder[0].split(regex3);
					p1 = first[1].split(regex3);
					

				}
				SubItem firstSubItem = new SubItem(first[0],p1[0]);
				database.addsubItem(firstSubItem, orthologousGroupID);
				

				for(int j = 0; j < remainder.length; j++)
				{
					rest = remainder[j].split(regex3);
					//System.out.println(rest[0] +" "+ rest[1]);
					SubItem restSubItem = new SubItem(rest[0],rest[1]);
					database.addsubItem(restSubItem, orthologousGroupID);
				}
				
			} //end while
			
		} //end try
		catch(FileNotFoundException e)
		{
			System.out.println("Error: Cannot access input file");
		}

		/**
		 * 4.
		 * Prompt the user to enter command options and process them until the 
		 * user types x for exit.
		 */
		Scanner stdin = new Scanner(System.in);
		boolean stop = false;
		while (!stop)
		{
			System.out.println("Enter Options");
			String input = stdin.nextLine();
			String remainder = null;
			input = input.toLowerCase();

			if (input.length() > 0)
			{
				char option = input.charAt(0);
				if (input.length() > 1)
				{
					remainder = input.substring(1).trim();
				}
				switch (option)
				{
				case 'c':
				{
					/**
					 * Print out each subItem in the Item with the name 
					 * Item_name along with its certification date in the 
					 * format:
					 * subItem_1
					 * subItem_2
					 * etc.
					 * If a Item with the name Item_ID is not in the 
					 * database, display "Item not found".
					 */
					Iterator<SubItem> itr = 
						database.getSubItemFromItem(remainder).iterator();

					try 
					{
						if(database.containsItem(remainder))
						{
							while(itr.hasNext())
							{
								SubItem temp = itr.next();
								System.out.println(temp.getStringOne()+","+
										temp.getStringTwo());
							}
						}
						else
							if(!database.containsItem(remainder))
							{
								System.out.println("Item not found");
							}
					}
					catch(NullPointerException n)
					{
						System.out.println("Item not found");
					}
					finally{}
				}
				break;

				case 'd':
				{
					//calls method to do stats for database
					countStatsForSubItems(database);
					/**
					 * 1. Displays information about the database by doing the 
					 * following: Displays on a line: 
					 * "subItems: integer, Items: integer"
					 * This is the number of subItems followed by the total 
					 * number of unique Items.
					 */

					System.out.println("SubItems: "+numSubItems(database)
							+", Items: "+database.size());

					/** 2. Displays on a line: "# of subItems/Item: 
					 * most integer, least integer, average integer" 
					 * where most is the largest number of subItems that any 
					 * Item has, least is the fewest, and average is arithmetic 
					 * mean number of subItems per Item.
					 */

					System.out.println("# of subItems/item: most "+mostInt+"," 
							+" least "+leastInt+", average "+averageInt);

					break;
				}
				case 'l':
				{
					/**
					 * (Note: the command character is a lower-case L). 
					 * Prints out the Item and the subItem with the name subItem_ID using the format 
					 * "Item: Item_ID subItem: subItem_ID 
					 * If a subItem with the ID subItem_ID is not in the 
					 * database, display "subItem not found".
					 */
					boolean done = false;
					Iterator<Item> itr = database.iterator();
					try
					{
						while (itr.hasNext())
						{
							Item itemID = itr.next();
							List<SubItem> sbitm = itemID.getSubItems();
							Iterator<SubItem> litr = sbitm.iterator();
							while(litr.hasNext())
							{
								SubItem l = litr.next();
								if(l.getStringOne().equals(remainder))
								{
									done = true;

									//specified Item name
									String sO = itemID.getItemName();

									//specified subItemID
									String sT = l.getStringTwo();
									System.out.println("Item:"+sO
											+" SubItem date:" + sT);
								}
							}
						}
						if (done == false)
						{
							System.out.println("subItem not found");
						}
					}
					finally{}

					break;
				}
				case 'r':
				{
					/**
					 * remove the Item with the given ID from the database and
					 *  display "Item removed". 
					 * If there is no Item with the name Item_name in the 
					 * database, display "Item not found".
					 */
					try
					{
						if(!database.containsItem(remainder))
						{
							System.out.println("Item not found");
						}
						if(database.containsItem(remainder))
						{
							database.removeItem(remainder);
							System.out.println("Item removed");
						}
					}
					finally{}
					break;
				}
				case 'x':
				{
					stop = true;
					System.out.println("exit");
					break;
				}
				default:
					break;
				} 
			}
		}
	}
	/**
	 * countStatsForsubItems will do the work for command D, where the
	 * program needs to return information on the database's subItems. 
	 * most subItems, least subItems, and the average number of subItems per
	 * Item.
	 * 
	 * @param ItemDB
	 */
	private static void countStatsForSubItems(ItemsDatabase itemDB)
	{
		//integer that will be used to hold the value of the Item with the most
		//number of subItems in the database
		int calcMost = 0;

		//integer that will be used to hold the value of the Item with the least
		//number of subItems in the database
		int calcLeast;

		//integer that will be used to calculate the average number of subItems
		//per Item in the database.
		int calcAverage = 0;

		Iterator<Item> itemitr = itemDB.iterator();
		if(itemitr.hasNext())
		{
			Item tmp = itemitr.next();
			//set least to the temp Item's subItem's list size
			calcLeast = tmp.getSubItems().size();
		}
		else
		{
			return;
		}
		Iterator<Item> itr = itemDB.iterator();
		while(itr.hasNext())
		{
			Item item = itr.next();
			if(item.getSubItems().size() > calcMost)
			{
				//set calcMost to the length of current Item's list size.
				calcMost = item.getSubItems().size();
			}
			if(item.getSubItems().size()<calcLeast)
			{
				//set calcLeast to the length of the current Item's list size
				calcLeast = item.getSubItems().size();
			}
			//set the calcAverage count to the number of subItems in the 
			//current Item's subItem's list
			calcAverage += item.getSubItems().size();
		}

		//take the average and set it equal to calcAverage
		calcAverage = calcAverage/itemDB.size();

		//mostInt is the value for calcMost to be passed up into main
		mostInt = calcMost;

		//leastInt is the value for calcLeast to be passed up into main
		leastInt = calcLeast;

		//averageInt is the value for calcAverage to be passed up into main
		averageInt = calcAverage;

	}

	/**
	 * numsubItems will determine the number of subItems a Item has
	 * this value will be used for command 'd'
	 * @param c
	 * @return
	 */
	private static int numSubItems(ItemsDatabase c)
	{
		int count = 0;
		Iterator<Item> itr = c.iterator();
		while(itr.hasNext())
		{
			Item c1 = itr.next();
			count += c1.getSubItems().size();
		}
		return count;
	}
	private static void printArray(String[] items)
	{
		System.out.println("Item: "+ items[0]+ " subItems: "+items[1]);
	}
}


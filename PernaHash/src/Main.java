import java.io.*;
import java.util.*;
public class Main
{
    /*freqWord is the hashmap to store all the words in a file and their 
     *number of occurrences */
    private static SimpleHashMap<String, Integer, String> database;
 
    /**
     * This method dictates how Collections.sort will arrange the sorted
     * values.  First by value then sort the highest values in alphabetical 
     * order
     * 
     * @param args args is the command line argument that is the name 
     * of a text file to be read in, parsed and mapped to a SimpleHashMap
     */
    public static final void main(String[] args) 
    {
        database = new SimpleHashMap<String, Integer, String>();
        if(args.length > 1 || args.length == 0)
        {
            System.out.println("Program accepts only " +
                    "one command line argument. Goodbye");
            System.exit(-1);
        }
        String regex1 = ": ";
		String regex2 = " ";
		String regex3 = "\\|";
		/**
		 * 3.
		 * Load the data from the input file and use it to construct an item 
		 * database.
		 */
		File inputFile = new File(args[0]);
		try
		{
			Scanner in = new Scanner(inputFile);
			while(in.hasNextLine())
			{
				String ItemsDBFileIn = in.nextLine();
				in.useDelimiter("[:\\s]");

				//ItemDatabase holds the value of the line in the scanner  
				String[] Database = ItemsDBFileIn.split(regex1);

				//one holds the string before the regex in the line
				String orthologousGroupID = Database[0];
				
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
				for(int i = 1; i < Database.length; i++)
				{
					remainder = Database[i].split(regex2);
					first = remainder[0].split(regex3);
					p1 = first[1].split(regex3);
					

				}
				int species1 = Integer.parseInt(first[0]);
				//SubItem firstSubItem = new SubItem(first[0],p1[0]);
				SimpleHashMap.Entry<String, Integer, String> entry1
				= new SimpleHashMap.Entry<String, Integer, String>(orthologousGroupID, species1, p1[0]);
				database.put(entry1.getKey(),entry1.getSpecies(), entry1.getGene());
				

				for(int j = 0; j < remainder.length; j++)
				{
					rest = remainder[j].split(regex3);
					int species2 = Integer.parseInt(rest[0]);
					SimpleHashMap.Entry<String, Integer, String> entry2
					= new SimpleHashMap.Entry<String, Integer, String>(orthologousGroupID, species2, rest[1]);
					
					System.out.println(entry2.getKey()+ " "+ entry2.getSpecies()+" "+ entry2.getGene());
					
					database.put(entry2.getKey(),entry2.getSpecies(), entry2.getGene());
				}
				
			} //end while
			
		} //end try
        catch (FileNotFoundException e) 
        {
            System.out.println("Please enter a valid filename");
            System.exit(-1);
        } 
        catch (IOException e) 
        {
            System.out.println("Please enter a valid filename");
            System.exit(-1);
        }
        //print top eleven most frequently occuring word
        printMostFreq();
    }
 
    /**
     * This method sorts the entries in the hashmap and then prints out the
     * top eleven most frequent words by numerical respective alphabetical
     * order.  Method is called in main.
     * 
     */
    private static void printMostFreq()
    {
        //create new linked list of entries to sort
        List<SimpleHashMap.Entry<String, Integer, String>> topeleven = 
                database.entries();
        //sort linked list by value then by alphabetical order
        Collections.sort(topeleven, 
                new ComparableOG<String,Integer>());
        //if linked list is greater than eleven print top eleven
        if(topeleven.size() >= 11)
        {
            for(int i = 0; i < 11; i++)
            {
                System.out.println(topeleven.get(i).getKey());
            }
        }
        //print the size of the list if less than 11
        else
        {
            for(SimpleHashMap.Entry<String, Integer, String> entry: topeleven)
            {
                System.out.println(entry.getKey());
            }
        }
    }
}


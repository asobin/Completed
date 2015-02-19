
import java.io.*;
import java.util.*;
 

public class WordFrequencyMain
{
    /*freqWord is the hashmap to store all the words in a file and their 
     *number of occurrences */
    private static SimpleHashMap<String, Integer> freqWord;
 
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
         
 
        freqWord = new SimpleHashMap<String, Integer>();
 
        if(args.length > 1 || args.length == 0){
            System.out.println("Program accepts only " +
                    "one command line argument. Goodbye");
            System.exit(-1);
        }
 
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] words = line.split(" ");
                for (String word: words) 
                {
                    //if word already exists increment number of occurrence
                    if(freqWord.get(word) != null)
                    {
                        int temp = freqWord.get(word);
                        freqWord.put(word, temp+1);
 
                    }
                    else
                    {
                        /*if word doesn't exist in the hashmap add it to the
                         *freqWord hashmap and set number of occurrences to 1*/
                        SimpleHashMap.Entry<String, Integer> entry  = new SimpleHashMap.Entry<String, Integer>(word,1);
                        freqWord.put(entry.getKey(),entry.getValue());
                    }
 
                }
            }
            br.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("Please enter a valid filename");
            System.exit(-1);
        } catch (IOException e) 
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
        List<SimpleHashMap.Entry<String, Integer>> topeleven = 
                freqWord.entries();
        //sort linked list by value then by alphabetical order
        Collections.sort(topeleven, 
                new CompareValueThenKey<String,Integer>());
     
        //if linked list is greater than eleven print top eleven
        if(topeleven.size() >= 11){
            for(int i = 0; i < 11; i++){
                System.out.println(topeleven.get(i).getKey());
            }
 
        }
        //print the size of the list if less than 11
        else{
            for(int i = 0; i < topeleven.size(); i++){
                System.out.println(topeleven.get(i).getKey());
            }
 
        }
 
    }
 
}

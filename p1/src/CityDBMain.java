///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 1
// Files:            Landmark.java, City.java, CityDatabase.java,
//                   CityDBMain.java
// Semester:         CS367 Fall 2013
//
// Author:           Alexander Sobin
// Email:            asobin@wisc.edu
// CS Login:         sobin
// Lecturer's Name:  Skrentny
// Lab Section:      N/A
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     (name of your pair programming partner)
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          David Maman
//////////////////////////// 80 columns wide //////////////////////////////////
import java.io.*;
import java.util.*;

/**
 * The application program, CityDBMain, creates and uses a CityDatabase to 
 * represent and process information about cities and landmarks. It is meant 
 * to be a fairly simple application to interact with a database of cities 
 * and their landmarks. The city and landmark information is read from a 
 * text file and then the program processes user commands. 
 * 
 * <p>Bugs: none
 * @author sobin
 *
 */
public class CityDBMain 
{ 
 //tracks and holds the value for the city with the most # of landmarks 
 private static int mostInt = 0;

 //tracks and holds the value for the city with the least # of landmarks
 private static int leastInt = 0;

 //tracks and holds the value for the average # of landmarks in the cityDB
 private static int averageInt = 0;
 /**
  * main check whether exactly one command-line argument is given; 
  * if not, display "Usage: java CityDBMain FileName" and quit.
  * Check whether the input file exists and is readable; if not, 
  * display "Error: Cannot access input file" and quit.
  * Load the data from the input file and use it to construct a city
  * database. Note: landmarks are to be added to the city database in
  * the order in which they appear in the text file.
  * Prompt the user to enter command options and process them 
  * until the user types x for exit.

  * @param args
  * @throws IOException
  */
 public static void main(String[] args) throws IOException
 {
  //tracks and holds value of the # of landmarks in the cityDB
  int landmarkcount = 0;
  /**
   * 1.
   * Check whether exactly one command-line argument is given; 
   * if not, display "Usage: java CityDBMain FileName" and quit.
   */
  if ( args.length == 0 || args.length > 1 || args.equals(null))
  {
   System.out.println("Usage: java CityDBMain FileName");
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

  CityDatabase database = new CityDatabase();
  //regex hold the regular expression value used for parsing args
  String regex = ";";
  /**
   * 3.
   * Load the data from the input file and use it to construct a city 
   * database. Note: landmarks are to be added to the city database in 
   * the order in which they appear in the text file.
   */
  try
  {
   Scanner in = new Scanner(inputFile);
   while(in.hasNextLine())
   {
    String cityDBFileIn = in.nextLine();

    //cityDatabase holds the value of the line in the scanner  
    String[] cityDatabase = cityDBFileIn.split(regex);

    //n holds the value of name of the landmark
    String n = cityDatabase[0].toLowerCase();

    //name holds the value of the city's name
    String name = cityDatabase[1].toLowerCase();

    //c holds the value of the landmark's certification date
    String c = cityDatabase[2].toLowerCase();

    //adds city to the database
    database.addCity(name);

    //adds the landmark to the database
    database.addLandmark((new Landmark(n,c)), name);

    //increase the count of landmarks in database
    landmarkcount++;
   }
  }
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
      * Print out each landmark in the city with the name 
      * city_name along with its certification date in the 
      * format:
      * landmark_1_name, landmark_1_certification_date
      * landmark_2_name, landmark_2_certification_date
      * etc.
      * If a city with the name city_name is not in the 
      * database, display "city not found".
      */
     Iterator<Landmark> itr = 
      database.getLandmarksFromCity(remainder).iterator();

     try 
     {
      if(database.containsCity(remainder))
      {
       while(itr.hasNext())
       {
        Landmark temp = itr.next();
        System.out.println(temp.getName()+","+
          temp.getCertificationDate());
       }
      }
      else
       if(!database.containsCity(remainder))
       {
        System.out.println("city not found");
       }
     }
     catch(NullPointerException n)
     {
      System.out.println("city not found");
     }
     finally{}
    }
    break;

    case 'd':
    {
     //calls method to do stats for database
     countStatsForLandmarks(database);
     /**
      * 1. Displays information about the database by doing the 
      * following: Displays on a line: 
      * "Landmarks: integer, Cities: integer"
      * This is the number of landmarks followed by the total 
      * number of unique cities.
      */

     System.out.println("Landmarks: "+numLandmarks(database)
       +", Cities: "+database.size());

     /** 2. Displays on a line: "# of landmarks/city: 
      * most integer, least integer, average integer" 
      * where most is the largest number of landmarks that any 
      * city has, least is the fewest, and average is arithmetic 
      * mean number of landmarks per city.
      */

     System.out.println("# of landmarks/city: most "+mostInt+"," 
       +" least "+leastInt+", average "+averageInt);

     /** 3. Displays the landmarks with the 3 oldest 
      * certification dates on multiple lines 
      * in the following form:
      * Landmarks with 3 oldest certification dates:
      * landmark name1 [String]
      * landmark name2 [String]
      * etc.
      */

     System.out.println("Landmarks with 3 oldest certification" +
     " dates:");
     database.printLandmarksWithThreeOldestDates();

     /** 4. Displays the landmarks with the 3 newest 
      * certification dates on multiple lines 
      * in the following form:
      * Landmarks with 3 newest certification dates:
      * landmark name1 [String]
      * landmark name2 [String]
      * etc.
      */

     System.out.println("Landmarks with 3 newest certification" +
     " dates:");
     database.printLandmarksWithThreeNewestDates();

     break;
    }
    case 'l':
    {
     /**
      * (Note: the command character is a lower-case L). 
      * Prints out the city and certification date for the 
      * landmark with the name landmark_name using the format 
      * "City: city_name Certification date: certification_date. 
      * If a landmark with the name landmark_name is not in the 
      * database, display "landmark not found".
      */
     boolean done = false;
     Iterator<City> itr = database.iterator();
     try
     {
      while (itr.hasNext())
      {
       City name = itr.next();
       List<Landmark> lndmrk = name.getLandmarks();
       Iterator<Landmark> litr = lndmrk.iterator();
       while(litr.hasNext())
       {
        Landmark l = litr.next();
        if(l.getName().equals(remainder))
        {
         done = true;

         //specified city name
         String cN = name.getName();

         //specified certification date
         String cD = l.getCertificationDate();
         System.out.println("City:"+cN
           +" Certification date:" + cD);
        }
       }
      }
      if (done == false)
      {
       System.out.println("landmark not found");
      }
     }
     finally{}

     break;
    }
    case 'n':
    {
     /** 
      * finds the newest certification date in that city's
      * landmarks list and Print the names of all that city's 
      * landmarks that have that certification date.
      * Prints the name(s) of the appropriate landmark(s) 
      * on a separate line(s). If more than one landmark ties for
      * newest, the order of the tieing landmarks should coincide
      * with the order in which the landmarks were added 
      * to the database. 
      */
     try
     {
      if(!database.containsCity(remainder))
      {
       System.out.println("city not found");
      }
      if(database.getLandmarksFromCity(remainder).size() == 1)
      {
       List<Landmark> litr = new ArrayList<Landmark>();
       System.out.println(litr.get(0).getName());
      }
      if(database.containsCity(remainder))
      {
       List<Landmark> litr = new ArrayList<Landmark>();
       litr = (database.getLandmarksFromCity(remainder));

       //holds a landmark(s) that has the newest cert. date
       String newest =  returnNewestLandmark(litr);
       List<Landmark> newestList = 
        database.getLandmarksWithCertificationDate(newest);
       Iterator<Landmark> newLandmark = newestList.iterator();
       while(newLandmark.hasNext())
       {
        Landmark temp = newLandmark.next();
        System.out.println(temp.getName());
       }
      }
     }
     catch (NullPointerException n)
     {
      System.out.println("city not found");
     }
     break;
    }
    case 'o':
    {
     /**
      * Prints the name(s) of the landmark(s) that has the oldest 
      * certification date in the city with the name city_name in
      * the manner described for the 'n' command above.
      */
     try
     {
      if(!database.containsCity(remainder))
      {
       System.out.println("city not found");
      }
      if(database.getLandmarksFromCity(remainder).size() == 1)
      {
       List<Landmark> litr = new ArrayList<Landmark>();
       System.out.println(litr.get(0).getName());
      }
      if(database.containsCity(remainder))
      {
       List<Landmark> litr = new ArrayList<Landmark>();
       litr = (database.getLandmarksFromCity(remainder));
       //holds a landmark(s) that has the oldest cert. date
       String oldest = returnOldestLandmark(litr);
       List<Landmark> oldestlist = 
        database.getLandmarksWithCertificationDate(oldest);
       Iterator<Landmark> oldLandmark = oldestlist.iterator();
       while(oldLandmark.hasNext())
       {
        Landmark temp = oldLandmark.next();
        System.out.println(temp.getName());
       }
      }
     }
     catch (NullPointerException o)
     {
      System.out.println("city not found");
     }

     break;
    }
    case 'r':
    {
     /**
      * remove the city with the given name from the database and
      *  display "city removed". 
      * If there is no city with the name city_name in the 
      * database, display "city not found".
      */
     try
     {
      if(!database.containsCity(remainder))
      {
       System.out.println("city not found");
      }
      if(database.containsCity(remainder))
      {
       database.removeCity(remainder);
       System.out.println("city removed");
      }
     }
     finally{}
     break;
    }
    case 's':
    {
     /**
      * Given two landmarks with names name_landmark1 and 
      * name_landmark2, search the database and display 
      * "equal dates" if they have the same certification date,
      * or "not equal dates" if they have different certification
      * dates. If either of the landmarks are not found in the 
      * database display "landmarks are not valid".
      */
     //date1 will hold the first date to compare
     String date1 = "";

     //date2 will hold the second date to compare
     String date2 = "";
     try
     {
      //array list to hold the names of the cities for
      //comparison purposes
      String [] names = remainder.split(";");

      //first name in the array
      String name1 = names[0].toLowerCase();

      //second name in the array
      String name2 = names[1].toLowerCase();
      if(!database.containsLandmark(name1) ||
        !database.containsLandmark(name2))
      {
       System.out.println("landmarks are not valid");
      }
      else
      {
       Iterator<City> itr1 = database.iterator();

       while(itr1.hasNext())
       {
        City city1 = itr1.next();
        List<Landmark> lmk1 = city1.getLandmarks();
        Iterator<Landmark> litr1 = lmk1.iterator();
        while(litr1.hasNext())
        {
         Landmark l1 = litr1.next();
         if(l1.getName().equals(name1))
         {
          //set date1 for comparison
          date1 = l1.getCertificationDate();
         }
        }
       }
       Iterator<City> itr2 = database.iterator();
       while(itr2.hasNext())
       {
        City city2 = itr2.next();
        List<Landmark> lmk2 = city2.getLandmarks();
        Iterator<Landmark> litr2 = lmk2.iterator();
        while(litr2.hasNext())
        {
         Landmark l2 = litr2.next();
         if(l2.getName().equals(name2))
         {
          //set date2 for comparison
          date2 = l2.getCertificationDate(); 
         }
        }
       }
       if(date1.equals(date2))
       {
        System.out.println("equal dates");
       }
       else
       {
        System.out.println("not equal dates");
       }
      }
     }
     catch(NullPointerException e)
     {
      System.out.println("landmarks are not valid");
     }

     break;
    }
    case 'w':
    {
     /**
      * Display the names of all landmarks with the certification
      * date date on separate lines. If no landmarks have that 
      * certification date, display "no landmarks found".
      */
     try
     {
      List<Landmark> wlist = 
       database.getLandmarksWithCertificationDate
       (remainder);
      Iterator<Landmark> wlistitr = wlist.iterator();
      while(wlistitr.hasNext())
      {
       Landmark landmarkName = wlistitr.next();
       System.out.println(landmarkName.getName());
      }

     } catch(NullPointerException e)
     {
      System.out.println("no landmarks found");
     }

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
  * countStatsForLandmarks will do the work for command D, where the
  * program needs to return information on the database's landmarks. 
  * most landmarks, least landmarks, and the average number of landmarks per
  * city.
  * 
  * @param cityDB
  */
 private static void countStatsForLandmarks(CityDatabase cityDB)
 {
  //integer that will be used to hold the value of the city with the most
  //number of landmarks in the database
  int calcMost = 0;

  //integer that will be used to hold the value of the city with the least
  //number of landmarks in the database
  int calcLeast;

  //integer that will be used to calculate the average number of landmarks
  //per city in the database.
  int calcAverage = 0;

  Iterator<City> cityitr = cityDB.iterator();
  if(cityitr.hasNext())
  {
   City tmp = cityitr.next();
   //set least to the temp city's landmark's list size
   calcLeast = tmp.getLandmarks().size();
  }
  else
  {
   return;
  }
  Iterator<City> itr = cityDB.iterator();
  while(itr.hasNext())
  {
   City city = itr.next();
   if(city.getLandmarks().size() > calcMost)
   {
    //set calcMost to the length of current city's list size.
    calcMost = city.getLandmarks().size();
   }
   if(city.getLandmarks().size()<calcLeast)
   {
    //set calcLeast to the length of the current city's list size
    calcLeast = city.getLandmarks().size();
   }
   //set the calcAverage count to the number of landmarks in the 
   //current city's landmark's list
   calcAverage += city.getLandmarks().size();
  }

  //take the average and set it equal to calcAverage
  calcAverage = calcAverage/cityDB.size();

  //mostInt is the value for calcMost to be passed up into main
  mostInt = calcMost;

  //leastInt is the value for calcLeast to be passed up into main
  leastInt = calcLeast;

  //averageInt is the value for calcAverage to be passed up into main
  averageInt = calcAverage;

 }

 /**
  * numLandmarks will determine the number of landmarks a city has
  * this value will be used for command 'd'
  * @param c
  * @return
  */
 private static int numLandmarks(CityDatabase c)
 {
  int count = 0;
  Iterator<City> itr = c.iterator();
  while(itr.hasNext())
  {
   City c1 = itr.next();
   count += c1.getLandmarks().size();
  }
  return count;
 }

 /**
  * returnOldestLandmark will be used for command 'o' in main this method 
  * will determine which landmark(s) in a city is/are the oldest landmark(s)
  * @param cityname
  * @return
  */
 private static String returnOldestLandmark(List<Landmark> cityname)
 {
  //holds a value that will be determine which landmark is oldest
  String oldest = cityname.get(0).getCertificationDate();
  Iterator<Landmark> litr = cityname.iterator();
  while(litr.hasNext())
  {
   Landmark land = litr.next();
   if(Integer.parseInt(oldest) > 
   Integer.parseInt(land.getCertificationDate()))
   {
    //set oldest equal to the oldest cert date
    oldest = land.getCertificationDate();
   }
  }
  return oldest;
 }

 /**
  * returnNewestLandmark will be used for command 'n' in main this method 
  * will determine which landmark(s) in a city is/are the newest landmark(s)
  * @param cityname
  * @return
  */
 private static String returnNewestLandmark(List<Landmark> cityname)
 {
  //get first landmark in list and set to newest
  String newest = cityname.get(0).getCertificationDate();
  Iterator<Landmark> litr = cityname.iterator();
  while(litr.hasNext())
  {
   Landmark land = litr.next();
   if(Integer.parseInt(newest) < 
     Integer.parseInt(land.getCertificationDate()))
   {
    //set newest equal to the newest cert date
    newest = land.getCertificationDate();
   }
  }
  return newest;
 }
}


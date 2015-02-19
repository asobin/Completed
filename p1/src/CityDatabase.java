
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * CityDatabase class
 * The CityDatabase class stores the City objects
 * 
 * <p>Bugs: none
 * @author sobin
 *
 */
public class CityDatabase 
{
 private List<City> cityDB;
 private List<Landmark> lndmrkDB;
 public int count = 0;

 /**
  * constructor for CityDatabase
  */

 public CityDatabase()
 {
  //cityDB is the name of the ArrayList of cities for the database
  cityDB = new ArrayList<City>();

  //count is used for debugging processes
  count = 0;

  //lndmrkDB is the name of the ArrayList of landmarks for the database
  lndmrkDB = new ArrayList<Landmark>();
 }

 /**
  * addCity
  * Adds a city with the given name city to the end of the database.
  * If a city with the name p is already in the database, just return.
  * @param city
  */

 public void addCity(String city)
 {
  if(this.containsCity(city))
  {
   return;
  }
  else
   cityDB.add(new City(city));
  this.count = 0;
 }

 /**
  * addLandmark
  * Adds the given landmark to city in the database. If city is not in the
  * database throw a java.lang.IllegalArgumentException. 
  * If landmark is already in the list of landmarks associated with city, 
  * just return.
  * @param landmark
  * @param city
  */

 public void addLandmark(Landmark landmark, String city)
 {
  if(this.getLandmarksFromCity(city).contains(landmark))
  {
   return;
  }
  if(this.getLandmarksFromCity(city).contains(null))
  {
   throw new IllegalArgumentException();
  }
  else this.getLandmarksFromCity(city).add(landmark);
  compileLandmarks(landmark);
 }

 /**
  * containsCity
  * Return true iff city is in the database.
  * @param city
  * @return
  */

 public boolean containsCity(String city)
 {
  Iterator<City> itr = cityDB.iterator();
  while(itr.hasNext())
  {
   City tempCity = itr.next();
   count++;
   if(tempCity.getName().equals(city))
   {
    return true;
   }
  }
  return false;
 }

 /**
  * containsLandmark
  * Return true iff landmark with the name lmname appears in at
  * least one city's list of landmarks in the database.
  * @param lmname
  * @return
  */

 public boolean containsLandmark(String lmname)
 {
  Iterator<City> itr = cityDB.iterator();
  while(itr.hasNext())
  { 
   List<Landmark> temp = itr.next().getLandmarks();
   Iterator<Landmark> ltr = temp.iterator();
   while(ltr.hasNext())
   {
    Landmark landmarktemp = ltr.next();
    if(landmarktemp.getName().equals(lmname))
    {
     return true;
    }
   }
  }
  return false;
 }

 /**
  * hasLandmark
  * Returns true iff landmark with the name lmname is in 
  * the list of landmarks for city city. If city city 
  * is not in the database, return false.
  * @param lmname
  * @param city
  * @return
  */

 public boolean hasLandmark(String lmname, String city)
 {
  if(containsCity(city))
  {
   if(containsLandmark(lmname))
   {
    return true;
   }
  }
  return false;
 }

 /**
  * getLandmarksWithCertificationDate
  * Return a list of landmarks that have the certification date cdate. 
  * If no landmarks in the database have a certification date cdate, return 
  * null.
  * @param cdate
  * @return
  */

 public List<Landmark> getLandmarksWithCertificationDate(String cdate)
 {
  Iterator<City> itr = cityDB.iterator();
  List<Landmark> lmk = new ArrayList<Landmark>();
  while(itr.hasNext())
  {
   City cityName = itr.next();
   List<Landmark> cityLmk = cityName.getLandmarks();
   Iterator<Landmark> ltr = cityLmk.iterator();
   while(ltr.hasNext())
   {
    Landmark tempLmk = ltr.next();
    if(tempLmk.getCertificationDate().equals(cdate))
    {
     lmk.add(tempLmk);
    }
   }
  }
  if(lmk.size() > 0)
  { 
   return lmk;
  }
  else
  {
   return null;
  }

 }

 /**
  * getLandmarksFromCity
  * Return the list of landmarks for the city. 
  * If city is not in the database, return null.
  * @param city
  * @return
  */

 public List<Landmark> getLandmarksFromCity(String city)
 {
  this.count = 0;
  if(containsCity(city))
  {
   return cityDB.get(this.count-1).getLandmarks();
  }
  return null;
 }

 /**
  * printLandmarksWithThreeOldestDates
  * Print the names of the landmarks that have the 3 oldest certification
  * dates, 1 landmark per line. Note: multiple landmarks may have the same 
  * certification date so there may be more than 3 landmarks printed out. 
  * You must find the 3 oldest certification dates and print all landmarks 
  * that have these certification dates. The landmarks should be printed in 
  * chronological order starting with the oldest date. For landmarks with 
  * the same certification date, their order should coincide with the order 
  * in which the landmarks were added to the database. 
  */

 public void printLandmarksWithThreeOldestDates()
 {
  List<String> oldestDates = new ArrayList<String>();
  for(int i = 0; i < 3; i++)
  {
   oldestDates.add(lndmrkDB.get(i).getCertificationDate());
  }
  for(int j=0; j < oldestDates.size(); j++)
  {
   List<Landmark> litr = 
    getLandmarksWithCertificationDate(oldestDates.get(j));
   for(int k = 0; k < litr.size(); k++)
   {
    System.out.println(litr.get(k).getName());
   }
  }
 }

 /**
  * printLandmarksWithThreeNewestDates
  * Print the names of the landmarks that have the 3 newest certification 
  * dates, 1 landmark per line. Note: multiple landmarks may have the same 
  * certification date so there may be more than 3 landmarks printed out. 
  * You must find the 3 newest certification dates and print all landmarks 
  * that have these certification dates. The landmarks should be printed in 
  * chronological order starting with the newest date. For landmarks with 
  * the same certification date, their order should coincide with the order 
  * in which the landmarks were added to the database. 
  */

 public void printLandmarksWithThreeNewestDates()
 {
  List<String> newestDates = new ArrayList<String>();
  for(int i = lndmrkDB.size()-1; i > lndmrkDB.size()-4; i--)
  {
   newestDates.add(lndmrkDB.get(i).getCertificationDate());
  }
  for(int j=0; j < newestDates.size(); j++)
  {
   List<Landmark> litr = 
    getLandmarksWithCertificationDate(newestDates.get(j));
   for(int k = 0; k < litr.size(); k++)
   {
    System.out.println(litr.get(k).getName());
   }
  }
 }

 /**
  * removeCity
  * Remove city from the database. If city is not in the database, 
  * return false; otherwise (i.e., the removal is successful) return true.
  * @param city
  * @return
  */

 public boolean removeCity(String city)
 {
  Iterator<City> itr = cityDB.iterator();
  this. count = 0;
  while(itr.hasNext())
  {
   City name = itr.next();
   if(name.getName().equals(city))
   {
    cityDB.remove(name);
    List<Landmark> lndmrks = name.getLandmarks();
    for(int i=0; i< lndmrks.size(); i++)
    {
     lndmrkDB.remove(lndmrks.get(i));
    }
    this.count++;
    return true;
   }
  }
  return false;
 }

 /**
  * removeLandmarkWithName
  * Remove landmark with the name lmname from the database. If landmark with
  * the name lmname is not in the database, return false; otherwise 
  * (i.e., the removal is successful) return true.
  * @param lmname
  * @return
  */

 public boolean removeLandmarkWithName(String lmname)
 {
  Iterator<City> itr = cityDB.iterator();
  this.count = 0;
  while(itr.hasNext())
  {
   if(containsLandmark(lmname))
   {
    cityDB.remove(lmname);
    this.count++;
   }
  }
  return false;
 }

 /**
  * removeLandmarkWithCertificationDate
  * Remove landmarks with the certification date cdate from the database, 
  * i.e., remove ALL landmarks that have the certification date cdate from 
  * the database. If landmarks with the certification date cdate are not in 
  * the database, return false; otherwise (i.e., the removal is successful) 
  * return true.
  * @param cdate
  * @return
  */

 public boolean removeLandmarkWithCertificationDate(String cdate)
 {
  Iterator<City> itr = cityDB.iterator();
  this.count = 0;
  while(itr.hasNext())
  {
   getLandmarksWithCertificationDate(cityDB.get
     (this.count).toString()).remove(cdate);
   this.count++;
   return true;
  }
  return false;
 }

 /**
  * size
  * Return the number of cities in this database.
  * @return
  */

 public int size()
 {
  return cityDB.size();
 }

 /**
  * Iterator
  * Return an Iterator over the City objects in the database. 
  * The cities should be returned in the order they were added to the 
  * database (resulting from the order in which they are in the text file).
  * @return
  */

 public Iterator<City> iterator()
 {
  Iterator<City> itr = cityDB.iterator();
  return itr;
 }

 /**
  * compileLandmarks is used in the addLandmark method in order to sort
  * the landmarks ass they are added to the database.
  * @param lndmrk
  */

 private void compileLandmarks(Landmark lndmrk)
 {
  for(int i = 0; i < lndmrkDB.size(); i++)
  {
   if(Integer.parseInt(lndmrkDB.get(i).getCertificationDate())
     > Integer.parseInt(lndmrk.getCertificationDate()))
   {
    lndmrkDB.add(i, lndmrk);
    return;
   }
  }
  lndmrkDB.add(lndmrk);
 }
}

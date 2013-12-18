import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* ItemsDatabase class
* The ItemsDatabase class stores the Item objects
* 
* <p>Bugs: none
* @author sobin
*
*/
public class ItemsDatabase 
{
private List<Item> itemDB;
private List<SubItem> subItemDB;
public int count = 0;

/**
* constructor for ItemDatabase
*/

public ItemsDatabase()
{
//itemDB is the name of the ArrayList of items for the database
itemDB = new ArrayList<Item>();

//count is used for debugging processes
count = 0;

//subItemDB is the name of the ArrayList of subItems for the database
subItemDB = new ArrayList<SubItem>();
}

/**
* addItem
* Adds an Item with the given ID to the end of the database.
* If a item with the name p is already in the database, just return.
* @param ID
*/

public void addItem(String ID)
{
if(this.containsItem(ID))
{
return;
}
else
itemDB.add(new Item(ID));
this.count = 0;
}

/**
* addsubItem
* Adds the given subItem to item in the database. If item is not in the
* database throw a java.lang.IllegalArgumentException. 
* If subItem is already in the list of subItems associated with item, 
* just return.
* @param subItem
* @param ID
*/

public void addsubItem(SubItem subItem, String ID)
{
if(this.getSubItemFromItem(ID).contains(subItem))
{
return;
}
if(this.getSubItemFromItem(ID).contains(null))
{
throw new IllegalArgumentException();
}
else this.getSubItemFromItem(ID).add(subItem);
}

/**
* containsitem
* Return true iff item is in the database.
* @param item
* @return
*/

public boolean containsItem(String ID)
{
Iterator<Item> itr = itemDB.iterator();
while(itr.hasNext())
{
Item tempItem = itr.next();
count++;
if(tempItem.getItemName().equals(ID))
{
return true;
}
}
return false;
}

/**
* containsSubItem
* Return true iff subItem with the name subItemID appears in at
* least one item's list of subItems in the database.
* @param lmname
* @return
*/

public boolean containsSubItem(String subItemID)
{
Iterator<Item> itr = itemDB.iterator();
while(itr.hasNext())
{ 
List<SubItem> temp = itr.next().getSubItems();
Iterator<SubItem> ltr = temp.iterator();
while(ltr.hasNext())
{
SubItem subItemtemp = ltr.next();
if(subItemtemp.getStringOne().equals(subItemID))
{
return true;
}
}
}
return false;
}

/**
* hasSubItem
* Returns true iff subItem with the name subItemID is in 
* the list of subItems for Item item. If Item item 
* is not in the database, return false.
* @param lmname
* @param item
* @return
*/

public boolean hasSubItem(String subItemID, String item)
{
if(containsItem(item))
{
if(containsSubItem(subItemID))
{
return true;
}
}
return false;
}

/**
* getSubItemsWithStringTwo
* Return a list of subItems that have the String stwo. 
* If no subItems in the database have a String stwo, return 
* null.
* @param cdate
* @return
*/

public List<SubItem> getSubItemsWithStringTwo(String stwo)
{
Iterator<Item> itr = itemDB.iterator();
List<SubItem> sbitm = new ArrayList<SubItem>();
while(itr.hasNext())
{
Item itemID = itr.next();
List<SubItem> itemstwo = itemID.getSubItems();
Iterator<SubItem> ltr = itemstwo.iterator();
while(ltr.hasNext())
{
SubItem tempSubItem = ltr.next();
if(tempSubItem.getStringTwo().equals(stwo))
{
sbitm.add(tempSubItem);
}
}
}
if(sbitm.size() > 0)
{ 
return sbitm;
}
else
{
return null;
}

}

/**
* getSubItemsFromItem
* Return the list of subItems for the item. 
* If item is not in the database, return null.
* @param item
* @return
*/

public List<SubItem> getSubItemFromItem(String ID)
{
this.count = 0;
if(containsItem(ID))
{
return itemDB.get(this.count-1).getSubItems();
}
return null;
}

/**
* removeitem
* Remove item from the database. If item is not in the database, 
* return false; otherwise (i.e., the removal is successful) return true.
* @param item
* @return
*/

public boolean removeItem(String item)
{
Iterator<Item> itr = itemDB.iterator();
this. count = 0;
while(itr.hasNext())
{
Item itemID = itr.next();
if(itemID.getItemName().equals(item))
{
itemDB.remove(itemID);
List<SubItem> sbitms = itemID.getSubItems();
for(int i=0; i< sbitms.size(); i++)
{
subItemDB.remove(sbitms.get(i));
}
this.count++;
return true;
}
}
return false;
}

/**
* removeSubItemWithStringOne
* Remove subItem with the name sOne from the database. If subItem with
* the name sOne is not in the database, return false; otherwise 
* (i.e., the removal is successful) return true.
* @param lmname
* @return
*/

public boolean removeSubItemWithStringOne(String sOne)
{
Iterator<Item> itr = itemDB.iterator();
this.count = 0;
while(itr.hasNext())
{
if(containsSubItem(sOne))
{
itemDB.remove(sOne);
this.count++;
}
}
return false;
}

/**
* removeSubItemWithStringTwo
* Remove subItems with the certification date sTwo from the database, 
* i.e., remove ALL subItems that have the String sTwo from 
* the database. If subItems with the String sTwo are not in 
* the database, return false; otherwise (i.e., the removal is successful) 
* return true.
* @param cdate
* @return
*/

public boolean removeSubItemWithStringTwo(String sTwo)
{
Iterator<Item> itr = itemDB.iterator();
this.count = 0;
while(itr.hasNext())
{
getSubItemsWithStringTwo(itemDB.get
(this.count).toString()).remove(sTwo);
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
return itemDB.size();
}

/**
* Iterator
* Return an Iterator over the item objects in the database. 
* The Items should be returned in the order they were added to the 
* database (resulting from the order in which they are in the text file).
* @return
*/

public Iterator<Item> iterator()
{
Iterator<Item> itr = itemDB.iterator();
return itr;
}
}



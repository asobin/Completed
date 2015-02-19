import java.util.*;
 
public class SimpleHashMap<K, V> 
{
 
    /**
     * A map entry (key-value pair).
     */
    public static class Entry<K, V>
    {
        private K key; //represents the Key for an entry
        private V value; //represents the value for an entry
 
        /**
         * Constructs the map entry with the specified key and value.
         */
        public Entry(K k, V v) 
        {
            this.key = k;
            this.value = v;
        }
 
        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         */
        public K getKey()
        {
            return key;
        }
 
        /**
         * Returns the value corresponding to this entry.
         *
         * @return the value corresponding to this entry
         */
        public V getValue() 
        {
            return value;
        }
 
        /**
         * Replaces the value corresponding to this entry with the specified
         * value.
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         */
        public V setValue(V value) 
        {
            V temp = this.getValue();
            this.value = value;
            return temp;
        }
    }
    final int TABLE_SIZE; //this represents the table size of the HashMap
    final double LF; //represents the load factor 
                     //involved in filling the HashMap
    LinkedList<SimpleHashMap.Entry<K,V>> [] map; //represents HashMap
    int size; //amount of occupied elements in the HashMap
    int oldTS; //stores the old size of the HashMap for resizing 
 
    /**
     * Constructs an empty hash map with initial capacity <tt>11</tt> and
     * maximum load factor <tt>0.75</tt>.
     **/
    public SimpleHashMap() 
    {
        this.LF = 0.75;
        this.TABLE_SIZE = 11;
        map = new LinkedList[TABLE_SIZE];
        this.oldTS = map.length;
    }
 
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or <tt>null</tt>
     * if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is <tt>null</tt>
     */
    public V get(Object key) 
    {
        if(key == null){
            throw new NullPointerException();
        }
        //represents the index in the HashMap
        int bucketIndex = key.hashCode()%map.length;
        if(bucketIndex < 0)
        {
            //change the index to a non negative value
            bucketIndex +=map.length;
        }
        if(map[bucketIndex] != null) //make sure that the index is occupied
        {
            LinkedList<Entry<K,V>> bucket = map[bucketIndex];
            //iterate through the LinkedList at this index
            for(Entry<K,V> entry: bucket)
                if(entry.getKey().equals(key))
                {
                    return entry.getValue();
                }
        }
        //if no mappings for this key return null
        return null;
    }
 
    public V put(K key, V value) 
    {
        if(key == null)
        {
            throw new NullPointerException();
        }
        if (get(key) != null)
        {
            //represents the index in the HashMap
            int index = key.hashCode()%map.length; 
            if(index < 0)
            {
                index+=map.length;
            }
            LinkedList<Entry<K,V>> bucket = map[index];
            //iterate through the LinkedList at this index
            for(Entry<K,V> entry: bucket)
                if(entry.getKey().equals(key))
                {
                    V temp = entry.getValue();
                    entry.setValue(value);
                    return temp;
                }
        }
         
        if(size >= (map.length*LF))
        {
            rehash();
        }
        //represents the index in the HashMap
        int index = key.hashCode()%map.length;
        if(index < 0)
        {
            index+=map.length;
        }
        if(map[index] == null)
        {
            //add new bucket at this index
            map[index] = new LinkedList<Entry<K,V>>();
             
        }
        //add an entry to this bucket and increment size
        map[index].add(new SimpleHashMap.Entry<K,V>(key, value));
        size++;
        //if no mappings for this key return null
        return null;
    }
 
    /**
     * Removes the mapping for the specified key from this map if present. This
     * method does nothing if the key is not in the hash table.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null
     * if there was no mapping for key.
     * @throws NullPointerException if key is null
     */
    public V remove(Object key)
    {
        if (key == null)
        {
            throw new NullPointerException();
        }
         
        //represents the index in the HashMap
        int index = key.hashCode()%map.length;
        if(index < 0)
        {
            index+=map.length;
        }
        LinkedList<Entry<K,V>> bucket = map[index];
        //iterate through the LinkedList at this index
        for(Entry<K,V> entry: bucket)
        {
            if(entry.getKey().equals(key))
            {
                V temp = entry.getValue();
                bucket.remove(entry);
                size--;
                return temp;
                 
            }//end if
        }//end for
        //if no mappings for this key return null
        return null;
    }//end remove
 
    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int size() 
    {
        return this.size;
    }
 
    /**
     * Returns a list of all the mappings contained in this map. This method
     * will iterate over the hash table and collect all the entries into a new
     * list. If the map is empty, return an empty list (not null).
     * The order of entries in the list can be arbitrary.
     *
     * @return a list of mappings in this map
     */
    public List<Entry<K, V>> entries() 
    {
        List<Entry<K,V>> entries = new LinkedList<Entry<K,V>>();
        //iterate through the current map to find entries in the buckets
        for(int i = 0; i < map.length; i++)
        {
            if(map[i] != null)
            {
                LinkedList<Entry<K,V>> bucket = map[i];
                //iterate through the LinkedList at this index and add entry
                for(Entry<K,V> entry: bucket)
                {
                    entries.add(entry);
                }//end for
            }//end if 
        }//end for
        //return list of entries
        return entries;
    }//end list
    /**
     * rehash
     * rehash will resize the hashMap when the load factor has been reached
     */
    private void rehash()
    {
         
        int newTS = ((map.length * 2) + 1);
        LinkedList<Entry<K,V>> [] newMap = new LinkedList[newTS];
         
        //iterate through the indexes of the current map to find the buckets
        for(int i = 0; i < map.length; i++)
        {
            if(map[i] != null)
            {
                LinkedList<Entry<K,V>> bucket = map[i];
                //iterate through the LinkedList at this index
                for(Entry<K,V> entry: bucket)
                {
                    int index = entry.getKey().hashCode()%newTS;
                    if(index < 0){
                        //make index a non negative value
                        index += newTS;
                    }
                    if(newMap[index] == null)
                    {
                        //create a new bucket and add the entry
                        newMap[index] = new LinkedList<Entry<K,V>>();
                        newMap[index].add(entry);
                         
                    }
                    else
                        //add the new entry
                        newMap[index].add(entry);
                     
                     
                }//end for
            }//end if 
        }//end for
        //assign current map to the new enlarged map
        map = newMap;
    }//end rehash
}

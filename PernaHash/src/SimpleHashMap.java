import java.util.*;
 
/**
 * This class implements a generic map based on hash tables using chained
 * buckets for collision resolution.
 *
 * <p>A map is a data structure that creates a key-value mapping. Keys are
 * unique in the map. That is, there cannot be more than one value associated
 * with a same key. However, two keys can map to a same value.</p>
 *
 * <p>The <tt>SimpleHashMap</tt> class takes two generic parameters, <tt>K</tt>
 * and <tt>V</tt>, standing for the types of keys and values respectively. Items
 * are stored in a hash table. Hash values are computed from the
 * <tt>hashCode()</tt> method of the <tt>K</tt> type objects.</p>
 *
 * <p>The chained buckets are implemented using Java's <tt>LinkedList</tt>
 * class.  When a hash table is created, its initial table size and maximum
 * load factor is set to <tt>11</tt> and <tt>0.75</tt>. The hash table can hold
 * arbitrarily many key-value pairs and resizes itself whenever it reaches its
 * maximum load factor.</p>
 *
 * <p><tt>null</tt> values are not allowed in <tt>SimpleHashMap</tt> and a
 * NullPointerException is thrown if used. You can assume that <tt>equals()</tt>
 * and <tt>hashCode()</tt> on <tt>K</tt> are defined, and that, for two
 * non-<tt>null</tt> keys <tt>k1</tt> and <tt>k2</tt>, if <tt>k1.equals(k2)</tt>
 * then <tt>k1.hashCode() == k2.hashCode()</tt>. Do not assume that if the hash
 * codes are the same that the keys are equal since collisions are possible.</p>
 * @param <G>
 */
public class SimpleHashMap<K, S, G> 
{
 
    /**
     * A map entry (key-value pair).
     */
    public static class Entry<K, S, G>
    {
        private K key; //represents the Key for an entry
        private S species; //represents the species for an entry
        private G gene; //represents the gene for an entry
 
        /**
         * Constructs the map entry with the specified key and value.
         */
        public Entry(K k, S v, G g) 
        {
            this.key = k;
            this.species = v;
            this.gene = g;
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
         * Returns the species corresponding to this entry.
         *
         * @return the value corresponding to this entry
         */
        public S getSpecies() 
        {
            return species;
        }
        
        /**
         * Returns the gene corresponding to this entry
         * @return gene
         */
        public G getGene()
        {
        	return gene;
        }
 
        /**
         * Replaces the value corresponding to this entry with the specified
         * value.
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         */
        public S setSpecies(S species) 
        {
            S temp = this.getSpecies();
            this.species = species;
            return temp;
        }
        
        public G setGene(G gene)
        {
        	G temp = this.getGene();
        	this.gene = gene;
        	return temp;
        }
    }
    final int TABLE_SIZE; //this represents the table size of the HashMap
    final double LF; //represents the load factor 
                     //involved in filling the HashMap
    LinkedList<SimpleHashMap.Entry<K,S,G>> [] map; //represents HashMap
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
    public S getSpecies(Object key) 
    {
        if(key == null)
        {
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
            LinkedList<Entry<K,S,G>> bucket = map[bucketIndex];
            //iterate through the LinkedList at this index
            for(Entry<K,S,G> entry: bucket)
                if(entry.getKey().equals(key))
                {
                    return entry.getSpecies();
                }
        }
        //if no mappings for this key return null
        return null;
    }
    
    public G getGene(Object key) 
    {
        if(key == null)
        {
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
            LinkedList<Entry<K,S,G>> bucket = map[bucketIndex];
            //iterate through the LinkedList at this index
            for(Entry<K,S,G> entry: bucket)
                if(entry.getKey().equals(key))
                {
                    return entry.getGene();
                }
        }
        //if no mappings for this key return null
        return null;
    }
 
    /**
     * <p>Associates the specified value with the specified key in this map.
     * Neither the key nor the value can be <tt>null</tt>. If the map
     * previously contained a mapping for the key, the old value is replaced.</p>
     *
     * <p>If the load factor of the hash table after the insertion would exceed
     * the maximum load factor <tt>0.75</tt>, then the resizing mechanism is
     * triggered. The size of the table should grow at least by a constant
     * factor in order to ensure the amortized constant complexity, but you
     * are free to decide the exact value of the new table size (e.g. whether
     * to use a prime or not). After that, all of the mappings are rehashed to
     * the new table.</p>
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @throws NullPointerException if the key or value is <tt>null</tt>
     */
    public void put(K key, S species, G gene) 
    {
        if(key == null)
        {
            throw new NullPointerException();
        }
        if (getSpecies(key) != null)
        {
            //represents the index in the HashMap
            int index = key.hashCode()%map.length; 
            if(index < 0)
            {
                index+=map.length;
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
            map[index] = new LinkedList<Entry<K,S,G>>();           
        }
        //add an entry to this bucket and increment size
        map[index].add(new SimpleHashMap.Entry<K,S,G>(key, species, gene));
        size++;
        //if no mappings for this key return null
    }
 
    /**
     * Removes the mapping for the specified key from this map if present. This
     * method does nothing if the key is not in the hash table.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or <tt>null</tt>
     * if there was no mapping for <tt>key</tt>.
     * @throws NullPointerException if key is <tt>null</tt>
     */
    public void removeSpecies(Object key)
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
        LinkedList<Entry<K,S,G>> bucket = map[index];
        //iterate through the LinkedList at this index
        for(Entry<K,S,G> entry: bucket)
        {
            if(entry.getKey().equals(key))
            {
                bucket.remove(entry);
                size--;
            }//end if
        }//end for
        //if no mappings for this key return null
    }//end remove
    
    public void removeGene(Object key)
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
        LinkedList<Entry<K,S,G>> bucket = map[index];
        //iterate through the LinkedList at this index
        for(Entry<K,S,G> entry: bucket)
        {
            if(entry.getKey().equals(key))
            {
                bucket.remove(entry);
                size--;            
            }//end if
        }//end for
        //if no mappings for this key return null
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
     * list. If the map is empty, return an empty list (not <tt>null</tt>).
     * The order of entries in the list can be arbitrary.
     *
     * @return a list of mappings in this map
     */
    public List<Entry<K, S, G>> entries() 
    {
        List<Entry<K,S, G>> entries = new LinkedList<Entry<K,S, G>>();
        //iterate through the current map to find entries in the buckets
        for(int i = 0; i < map.length; i++)
        {
            if(map[i] != null)
            {
                LinkedList<Entry<K,S,G>> bucket = map[i];
                //iterate through the LinkedList at this index and add entry
                for(Entry<K,S,G> entry: bucket)
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
        LinkedList<Entry<K,S,G>> [] newMap = new LinkedList[newTS];       
        //iterate through the indexes of the current map to find the buckets
        for(int i = 0; i < map.length; i++)
        {
            if(map[i] != null)
            {
                LinkedList<Entry<K,S,G>> bucket = map[i];
                //iterate through the LinkedList at this index
                for(Entry<K,S,G> entry: bucket)
                {
                    int index = entry.getKey().hashCode()%newTS;
                    if(index < 0)
                    {
                        //make index a non negative value
                        index += newTS;
                    }
                    if(newMap[index] == null)
                    {
                        //create a new bucket and add the entry
                        newMap[index] = new LinkedList<Entry<K,S,G>>();
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
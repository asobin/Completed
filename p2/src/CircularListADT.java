/**
 * A CircularListADT is a sequence of items arranged in a circular
 * structure. A CircularListADT keeps track of a current position, and can be
 * modified by adding and removing an item at current position or current
 * position by an offset.
 *
 * Positions in a CircularListADT are identified by the current position and
 * an offset. Positive offsets represent positions next to the current
 * position; negative offsets represent positions prior to the current
 * position; zero offset represents the current position iteself.
 *
 * The abstract values of offsets can be greater than or equal to the size of
 * the list. In this case, two operations with different offsets are equivalent
 * if they are congruent modulo the size of the list.
 */
public interface CircularListADT<E> {

    /**
     * Adds the given item to the current position in list. If the list was
     * empty, the current position should point to the new item. Otherwise,
     * the element currently in this position should be shifted to be next to
     * the new item, and the new item becomes the current position.
     *
     * @param item the item to add
     */
    void add(E item);
    
    /**
     * Removes the item at current position. The current position should point
     * to item that was next to the removed item (if exists). If the list was
     * empty, throws an <tt>ElementNotFoundException</tt>.
     *
     * @return the element that was removed from the list.
     * @throws ElementNotFoundException if the list is empty
     */
    E remove() throws ElementNotFoundException;
    
    /**
     * Returns the item at an offset from the current position. If the list
     * is empty, throws an <tt>ElementNotFoundException</tt>.
     *
     * @param offset the offset of the item to return from the current position
     * @return the item at the given position
     * @throws ElementNotFoundException if the list is empty
     */
    E get(int offset) throws ElementNotFoundException;
    
    /**
     * Determines if this List is empty, i.e., contains no items.
     *
     * @return true if the List is empty; false otherwise
     */
    boolean isEmpty();
    
    /**
     * Returns the number of items in this List.
     *
     * @return the number of items in this List
     */
    int size();

    /**
     * Offsets the current position by the specified value. Has no effect if
     * the list is empty.
     *
     * @param offset the offset by which the current position to be adjusted
     */
    void setCurrentPosition(int offset);
}

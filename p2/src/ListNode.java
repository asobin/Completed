/**
 * Generic doubly linked list node. It serves as the basic building block for 
 * storing data in doubly linked chains of nodes.
 * 
 * <b>Do not modify this file in any way!</b>
 */
class Listnode<E> 
{
    private E data;                // data to be stored 
    private Listnode<E> next, previous;   // connnection to next node and previous node

    /**
     * Constructs a new list node with no link to next node or previous node.
     * @param data the data to be stored in this node
     */
    Listnode(E data) 
    {
        this(data, null, null);
    }
    
    /**
     * Constructs a new list node with a link to next node and a link to previous node.
     * @param data the data to be stored in this node
     * @param next the node after this one
     */
    Listnode(E data, Listnode<E> next, Listnode<E> previous) 
    {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    /**
     * Returns the current data.
     * @return the current data
     */
    E getData() 
    {
        return data;
    }

    /**
     * Returns the next node.
     * @return the next node
     */
    Listnode<E> getNext() 
    {
        return next;
    }

    /**
     * Returns the previous node.
     * @return the previous node
     */
    Listnode<E> getPrevious() 
    {
        return previous;
    }

    /**
     * Sets the data to the given new value.
     * @param data the new data
     */
    void setData(E data) 
    {
        this.data = data;
    }

    /**
     * Sets the next node to the given node.
     * @param next the next node
     */
    void setNext(Listnode<E> next)
    {
        this.next = next;
    }

    /**
     * Sets the previous node to the given node.
     * @param previous the previous node
     */
    void setPrevious(Listnode<E> previous)
    {
        this.previous = previous;
    }
}

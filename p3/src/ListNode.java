///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FindKnightsPath.java
// File:             ListNode.java
// Semester:         CS367 Fall 2013
//
// Author:           David Maman
// CS Login:         maman
// Lecturer's Name:  Jim Skrentny
// Lab Section:      N/A
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Alexander Sobin
// CS Login:         sobin
// Lecturer's Name:  Jim Skrentny
// Lab Section:      N/A
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          N/A
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * Generic doubly linked list node. It serves as the basic building block for 
 * storing data in doubly linked chains of nodes.
 * 
 * <b>Do not modify this file in any way!</b>
 */
class ListNode<E> 
{
	private E data;                // data to be stored 
	private ListNode<E> next, previous;   // connection to next node 
	// and previous node


	/**
	 * Constructs a new list node with no link to next node or previous node.
	 * @param data the data to be stored in this node
	 */
	ListNode(E data) 
	{
		this(data, null, null);
	}
	ListNode(E e, ListNode<E> n) 
	{
		data = e;
		next = n;
	}

	/**
	 * Constructs a new list node with a link to next node and a link to 
	 * previous node.
	 * @param data the data to be stored in this node
	 * @param next the node after this one
	 */
	ListNode(E data, ListNode<E> next, ListNode<E> previous) 
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
	ListNode<E> getNext() 
	{
		return next;
	}

	/**
	 * Returns the previous node.
	 * @return the previous node
	 */
	ListNode<E> getPrevious() 
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
	void setNext(ListNode<E> next) 
	{
		this.next = next;
	}

	/**
	 * Sets the previous node to the given node.
	 * @param previous the previous node
	 */
	void setPrevious(ListNode<E> previous) 
	{
		this.previous = previous;
	}
}
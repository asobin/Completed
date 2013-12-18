///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FindKnightsPath.java
// File:             SimpleQueue.java
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
 * An ordered collection of items, where items are added to the back and 
 * removed from the front.
 * <p>Bugs: none
 *
 * @authors maman, sobin
 */
public class SimpleQueue<E> implements QueueADT<E>
{

	/**
	 * Number of elements are being stored in the queue
	 * 
	 */
	private int size;
	/**
	 * Node reference points to the front element of the queue 
	 */
	private ListNode<E> front;

	/**
	 * Node reference points to the rear element of the queue
	 */
	private ListNode<E> rear;

	/**
	 * constructor for queue to be used in solving knights path application
	 * An ordered collection of items, where items are added to the back and 
	 * removed from the front.
	 */
	public SimpleQueue() 
	{
		size = 0;
		front = rear = null;
	}

	/**
	 * Returns the size of the queue.
	 *
	 * @return the size of the queue
	 */
	public int size() 
	{

		return size;
	}

	/**
	 * Checks if the queue is empty.
	 * @return true if queue is empty; otherwise false
	 */
	public boolean isEmpty() 
	{
		return (size == 0);
	}

	/**
	 * Adds an item to the rear of the queue.
	 *
	 * @param item the item to add to the queue
	 */
	public void enqueue(E item)
	{
		ListNode<E> newNode = new ListNode<E>(item, null);
		if (isEmpty()) 
		{
			front = newNode;
		} 
		else 
		{
			rear.setNext(newNode);
		}
		rear = newNode;
		size++;
	}

	/**
	 * removes and returns the front item of the queue.
	 *
	 * @return the front item of the queue
	 * @throws EmptyQueueException if the queue is empty
	 */
	public E dequeue() throws EmptyQueueException 
	{
		if (isEmpty()) 
		{
			throw new EmptyQueueException();
		}
		E old = front.getData();
		front = front.getNext();
		size--;
		if(isEmpty()) 
		{
			rear = null;
		}
		return old;
	}
	/**
	 * Returns a string representation of the queue.
	 * For printing when debugging your implementation.
	 * Format 1 item per line from front to rear.
	 *
	 * @return a string representation of the queue
	 */
	public String toString() 
	{
		String s = "["; //s is a string to make output look as specified in 
		if (size >= 1) //program specs should appear ex: [1,1]
		{
			s += front.getData();
		}
		if (size > 1) 
		{
			ListNode<E> temp = front.getNext();
			while (temp != null) 
			{
				s += ", " + temp.getData();
				temp = temp.getNext();
			}
		}
		s += "]";
		return s;
	}
}

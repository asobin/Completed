/**
 * An ordered collection of items, where items are added to the back and 
 * removed from the front.
 */
public interface QueueADT<E> 
{
	/**
	 * Checks if the queue is empty.
	 * @return true if queue is empty; otherwise false
	 */
	boolean isEmpty();

	/**
	 * removes and returns the front item of the queue.
	 *
	 * @return the front item of the queue
	 * @throws EmptyQueueException if the queue is empty
	 */
	E dequeue() throws EmptyQueueException;


	/**
	 * Adds an item to the rear of the queue.
	 *
	 * @param item the item to add to the queue
	 */
	void enqueue(E item);

	/**
	 * Returns the size of the queue.
	 *
	 * @return the size of the queue
	 */
	int size();

	/**
	 * Returns a string representation of the queue.
	 * For printing when debugging your implementation.
	 * Format 1 item per line from front to rear.
	 *
	 * @return a string representation of the queue
	 */
	String toString(); 
}
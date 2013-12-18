import java.util.Iterator;


public class CircularLinkedListIterator<E> implements Iterator<E> 
{
	private CLL<E> list;
	private Listnode<E> curr;
	
	public CircularLinkedListIterator(CLL<E> list)
	{
		this.list = list;
		
	}
	
	@Override
	public boolean hasNext() 
	{
		return curr != null;
		
	}
	
	public E prev()
	{
		return curr.getPrevious().getData();
	}
	
	public boolean hasPrevious()
	{
		return curr != null;
	}
	@Override
	public E next() 
	{
		// TODO Auto-generated method stub
		return curr.getNext().getData();
	}

	@Override
	public void remove() 
	{
		try {
			list.remove();
		} 
		catch (ElementNotFoundException e) 
		{
		}
	}	
}


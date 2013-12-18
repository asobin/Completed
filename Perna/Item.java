
	import java.util.ArrayList;
	import java.util.List;

	public class Item 
	{
		private String orthologousGroupID;
		private List<SubItem> subItems;
		
		public Item(String itemName)
		{
			this.orthologousGroupID = itemName; 
			subItems=new ArrayList<SubItem>();
		}
		
		public String getItemName() 
		{
			return orthologousGroupID;
		}

		List<SubItem> getSubItems()
		{
			return subItems;
		}
		
		public boolean equals(Item c)
		{ 
			return orthologousGroupID.equalsIgnoreCase(c.getItemName());
		} 
	}


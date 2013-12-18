
public class SubItem 
{

		private String sequenceVersionID;
		private String uniqueGeneID;
		
		public SubItem(String o, String t)
		{
			sequenceVersionID = o;
			uniqueGeneID = t;
		}
		public String getStringOne() 
		{
			return sequenceVersionID;
		}
		
		public String getStringTwo() 
		{
			return uniqueGeneID;
		}
}

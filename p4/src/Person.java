public class Person 
{
	private String name;
	private String eyeColor;
	private double weight;
	
	/**Constructs a person with name, age, eyeColor and weight. */
	public Person(String name, String eyeColor, double weight) 
	{
		this.name = name;
		this.eyeColor = eyeColor;
		this.weight = weight;
	}
	
	/** Return the name of this person */
	public String getName() 
	{
		return name;
	}
	
	/** Return the eye color of this person */
	public String getEyeColor() 
	{
		return eyeColor;
	}
	
	/** Return the weight of this person */
	public double getWeight() 
	{
		return weight;
	}
}

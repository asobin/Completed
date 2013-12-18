import java.util.ArrayList;
import java.util.List;

public class City {
	private String name;
	private List<Landmark> landmarks;
	
	public City(String name){
		this.name = name; 
		landmarks=new ArrayList<Landmark>();
	}
	
	public String getName() {
		return name;
	}

	List<Landmark> getLandmarks(){
		return landmarks;
	}
	
	public boolean equals(City c){ 
		return name.equalsIgnoreCase(c.getName());
	} 
}

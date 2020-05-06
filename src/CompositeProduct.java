import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem, Serializable{
	
	public List<MenuItem> items = new ArrayList<MenuItem>();
	String name;
	
	public CompositeProduct(String name) {
		this.name = name;
	}
	
	public float computePrice() {
		float price = 0;
		
		for(MenuItem item: items) {
			price += item.computePrice();
		}
		
		return price;
	}
	
	public void addComponentProduct(MenuItem mItem) {
		this.items.add(mItem);
	}
	
	public String toString() {
		return this.name + " " + this.computePrice();
	}
}

import java.io.Serializable;

public class BaseProduct implements MenuItem, Serializable{
	
	public String name;
	public float price;
	
	public BaseProduct(String name, float price) {
		this.name = name;
		this.price = price;
	}
	
	public float computePrice() {
		return this.price;
	}
	
	public String toString() {
		return this.name + " " + this.price;
	}
}

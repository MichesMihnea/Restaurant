import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Order {
	Date date;
	int orderID;
	public Order(int id) {
		this.orderID = id;
		this.date = new Date();
	}
	
	public int hashCode() {
		return this.orderID;
	}
	
	public String toString() {
		return this.orderID + " " + this.date.toString();
	}
	
}

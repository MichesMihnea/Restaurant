import java.util.List;
import java.util.Map;

public interface RestaurantProcessing {
	
	public List <MenuItem> getMenuItems();
	public MenuItem getItemAtIndex(int index);
	public void addItem(MenuItem mi);
	public void removeItemAtIndex(int index);
	public void editItemAtIndex(int index, String name, float price);
	public void addOrder(Order o, List <MenuItem> items);
	public Map <Order, List <MenuItem>> getOrders();
	public float computePrice(int index);
	public void export(int index);
	public int getMaxIndex();
	public void importMenu();
	public void exportMenu();
	public void printItems();
	
}

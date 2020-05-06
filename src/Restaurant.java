import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class Restaurant extends Observable implements RestaurantProcessing, Serializable{
	
	List <MenuItem> menu = new ArrayList<MenuItem> ();
	Map <Order, List <MenuItem>> orders = new HashMap <Order, List <MenuItem>> ();
	
	
	public List <MenuItem> getMenuItems() {
		assert menu != null : menu;
		return menu;
	}

	public MenuItem getItemAtIndex(int index) {
		if(index < 0)
			throw new IllegalArgumentException();
		int i = 0;
		for(MenuItem mi: menu) {
			if(i == index) {
				assert isOk();
				assert mi != null : mi;
				return mi;
			}
			i ++;
		}
		assert isOk();
		return null;
	}
	
	public void addItem(MenuItem mi) {
		if(mi == null)
			throw new IllegalArgumentException();
		assert isOk();
		this.menu.add(mi);
	}
	
	public void removeItemAtIndex(int index) {
		if(index < 0)
			throw new IllegalArgumentException();
		assert isOk();
		menu.remove(index);
	}
	
	public void editItemAtIndex(int index, String name, float price) {
		if(index < 0 || price < 0 || name == null)
			throw new IllegalArgumentException();
		int i = 0;
		for(MenuItem mi: menu) {
			if(i == index) {
				assert mi != null : mi;
				if(mi.getClass() == BaseProduct.class)
				{
					BaseProduct bp = (BaseProduct) mi;
					bp.name = name;
					bp.price = price;
					assert bp.name != null && bp.price > 0 : bp;
				}
				else {
					CompositeProduct cp = (CompositeProduct) mi;
					cp.name = name;
					assert cp.name != null : cp;
				}
			}
			i ++;
		}
		
		assert isOk();
	}
	
	public void addOrder(Order o, List <MenuItem> items) {
		if(o == null || items == null)
			throw new IllegalArgumentException();
		
		this.orders.put(o, items);
		
		assert isOk();
		
		Iterator <MenuItem> it = items.iterator();
		while(it.hasNext()) {
			MenuItem mi = it.next();
			assert mi != null : mi;
			if(mi.getClass() == CompositeProduct.class) {
				setChanged();
				notifyObservers(items);
				break;
			}
		}
	}

	public Map<Order, List <MenuItem>> getOrders() {
		assert orders != null : orders;
		assert isOk();
		return orders;
	}

	@Override
	public float computePrice(int index) {
		if(index < 0)
			throw new IllegalArgumentException();
		
		float price = 0;
		Iterator <Order> it = orders.keySet().iterator();
		Order o = null;
		int i = 0;
		while(it.hasNext()) {
			if(i == index) {
				o = it.next();
				assert o != null : o;
				break;
			}
			else {
				it.next();
				i ++;
			}
		}
		for(MenuItem mi: orders.get(o)) {
			assert mi != null : mi;
			price += mi.computePrice();
		}
		
		assert isOk();
		return price;
	}

	@Override
	public void export(int index) {
		if(index < 0)
			throw new IllegalArgumentException();
		
		float price = computePrice(index);
		
		assert price > 0 : price;
		
		Iterator <Order> it = orders.keySet().iterator();
		List <MenuItem> items = null;
		Order o = null;
		int i = 0;
		
		assert isOk();
		
		while(it.hasNext()) {
			if(i == index) {
				o = it.next();
				items = orders.get(o);
				assert o != null : o;
				assert items != null : items;
				break;
			}
			else {
				it.next();
				i ++;
			}
		}/*
		String fileName = new String();
		fileName = ("Order" + "-" + o.orderID + ".txt");
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println("Order placed at:  " + o.date);
			Iterator <MenuItem> itProd = items.iterator();
			writer.println("Contents: ");
			while(itProd.hasNext())
				writer.println(itProd.next().toString());
			writer.println("Price:   " + price);
			writer.println("Finalized at:   " + new Date());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		
		
	}

	@Override
	public void importMenu() {
		RestaurantSerializator rs = new RestaurantSerializator();
		assert rs != null : rs;
		assert isOk();
		
		this.menu = rs.deserialize();
		
		assert menu != null : menu;
		
	}

	@Override
	public void exportMenu() {
		RestaurantSerializator rs = new RestaurantSerializator();
		assert rs != null : rs;
		assert isOk();
		assert menu != null : menu;
		
		rs.serialize(this.menu);
		
	}
	
	private Boolean isOk() {
		Iterator <Order> it = orders.keySet().iterator();
		
		while(it.hasNext()) {
			if(orders.get(it.next()).isEmpty())
				return false;
		}
		
		Iterator <MenuItem> mit = menu.iterator();
		
		while(it.hasNext()) {
			MenuItem mi = mit.next();
			if(mi == null)
				return false;
			if(mi.getClass() == CompositeProduct.class) {
				CompositeProduct cp = (CompositeProduct) mi;
				if(cp.name == null || cp.items.isEmpty())
					return false;
			}
			else	{
				BaseProduct bp = (BaseProduct) mi;
				if(bp.name == null || bp.price < 0)
					return false;
			}
			
		}
		
		return true;
	}

	@Override
	public int getMaxIndex() {
		assert isOk();
		return this.orders.size() + 1;
	}
	
} 

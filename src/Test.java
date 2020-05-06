
public class Test {

	public static void main(String[] args) {
		Restaurant r = new Restaurant();
		BaseProduct bp1 = new BaseProduct("Base Product 1", 100);
		BaseProduct bp2 = new BaseProduct("Base Product 2", 100);
		BaseProduct bp3 = new BaseProduct("Base Product 3", 100);
		BaseProduct bp4 = new BaseProduct("Base Product 4", 100);
		BaseProduct bp5 = new BaseProduct("Base Product 5", 100);
		CompositeProduct cp1 = new CompositeProduct("CompositeProduct1");
		/*cp1.addBaseProduct(bp1);
		cp1.addBaseProduct(bp2);
		cp1.addBaseProduct(bp3);*/
		r.menu.add(bp1);
		r.menu.add(bp2);
		r.menu.add(bp3);
		r.menu.add(bp4);
		r.menu.add(bp5);
		r.menu.add(cp1);
		for(MenuItem mi: r.menu) {
			System.out.println(mi.computePrice());
		}
		MainGUI main = new MainGUI(r);
	}
	
}

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ChefGUI extends JFrame implements Observer{
	public JPanel content;
	public String jobs = new String("Products to cook:\n");
	public JTextArea toCook = new JTextArea(20,20);
	
	public ChefGUI (){
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
		content.setLayout(new FlowLayout());
		toCook.setText(jobs);

		content.add(new JLabel("Welcome Mr. Chef"));

		content.add(toCook);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(250, 400);
		this.setTitle("Warehouse");
		this.setVisible(false);
	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		List <MenuItem> list = (List <MenuItem>) arg;
		Iterator <MenuItem> it = list.iterator();
		while(it.hasNext()) {
			MenuItem mi = it.next();
			if(mi.getClass() == CompositeProduct.class) {
				CompositeProduct cp = (CompositeProduct) mi;
				this.jobs += cp.name + "\n";
			}
		}
		this.toCook.setText(this.jobs);
		
	}

}

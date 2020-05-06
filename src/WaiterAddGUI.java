import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class WaiterAddGUI extends JFrame{
	public JComboBox<String> w_products;
	public JButton w_final = new JButton("Finalize");
	public JButton w_addProd = new JButton("Add Product");
	public RestaurantProcessing rp;
	public JPanel content;
	public List <MenuItem> items;
	public GridBagConstraints gbc;
	public JCheckBox jcb = new JCheckBox();
	public WaiterAddGUI (Restaurant rest){
		this.rp = rest;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		content.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 0;
		content.add(new JLabel("Add a new Order"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 20;
		content.add(new JLabel("Products: "), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 80;
		w_products = new JComboBox<String> ();
		for(MenuItem mi: rp.getMenuItems())
			w_products.addItem(mi.toString());
		content.add(w_products, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 90;
		content.add(w_addProd, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 100;
		content.add(w_final, gbc);
		this.addFinalListener(new FinalListener());
		this.addProdListener(new AddProdListener());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(200, 170);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
	
	public void addFinalListener(ActionListener aFinal) {
		this.w_final.addActionListener(aFinal);
	}
	
	public void addProdListener(ActionListener aProd) {
		this.w_addProd.addActionListener(aProd);
	}
	
	class FinalListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Order o = new Order(rp.getMaxIndex());
			rp.addOrder(o, items);
			items = null;
		}
		
	}
	
	class AddProdListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(items == null)
				items = new ArrayList <MenuItem> ();
			items.add(rp.getItemAtIndex(w_products.getSelectedIndex()));
			
		}
		
	}
}
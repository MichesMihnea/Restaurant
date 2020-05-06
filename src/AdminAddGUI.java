import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class AdminAddGUI extends JFrame{
	public JComboBox<String> n_basics;
	public JTextField n_name = new JTextField(20);
	public JTextField n_price = new JTextField(20);
	public JButton n_add = new JButton("Add To Menu");
	public JButton n_addBasics = new JButton("Add Basics");
	public RestaurantProcessing rp;
	public JPanel content;
	public CompositeProduct cp;
	public GridBagConstraints gbc;
	public JCheckBox jcb = new JCheckBox();
	public AdminAddGUI (Restaurant rest){
		this.rp = rest;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		content.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 0;
		content.add(new JLabel("Add a new Menu Item"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 20;
		content.add(new JLabel("Name: "), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 30;
		content.add(n_name, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 40;
		content.add(new JLabel("Price: "), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 50;
		content.add(n_price, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 80;
		n_basics = new JComboBox<String> ();
		for(MenuItem mi: rp.getMenuItems())
			n_basics.addItem(mi.toString());
		content.add(n_basics, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 90;
		content.add(n_addBasics, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 100;
		content.add(n_add, gbc);
		this.addContinueListener(new FinalAddListener());
		this.addBasicsListener(new BasicsAddListener());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(250, 220);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
	
	public void addContinueListener(ActionListener lAdd) {
		this.n_add.addActionListener(lAdd);
	}
	
	public void addBasicsListener(ActionListener lBasics) {
		this.n_addBasics.addActionListener(lBasics);
	}
	
	class FinalAddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(cp != null) {
			rp.addItem(cp);
			rp.printItems();
			cp = null;
			}
			else {
				BaseProduct bp = new BaseProduct(n_name.getText(), Integer.parseInt(n_price.getText()));
				rp.addItem(bp);
				rp.printItems();
			}
		}
		
	}
	
	class BasicsAddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(cp == null) {
				cp = new CompositeProduct(n_name.getText());
			}
			
			cp.addComponentProduct(rp.getItemAtIndex(n_basics.getSelectedIndex()));
			cp.name = n_name.getText();
			
		}
		
	}
}

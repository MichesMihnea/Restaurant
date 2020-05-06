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

public class AdminEditGUI extends JFrame{
	public JButton e_edit = new JButton("Save Modification");
	public JTextField e_name = new JTextField(20);
	public JTextField e_price = new JTextField(20);
	public JComboBox<String> n_basics;
	public RestaurantProcessing rp;
	public JPanel content;
	public GridBagConstraints gbc;
	public JCheckBox jcb = new JCheckBox();
	public AdminEditGUI (Restaurant rest){
		this.rp = rest;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		content.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 0;
		content.add(new JLabel("Edit an item from the menu"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 30;
		n_basics = new JComboBox<String> ();
		for(MenuItem mi: rp.getMenuItems())
			n_basics.addItem(mi.toString());
		content.add(n_basics, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 55;
		content.add(new JLabel("New Name"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 60;
		content.add(e_name, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 65;
		content.add(new JLabel("New Price"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 70;
		content.add(e_price, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 80;
		content.add(e_edit, gbc);
		
		this.addEditItemListener(new EditItemListener());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(250, 190);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
	
	public void addEditItemListener(ActionListener aEdit) {
		this.e_edit.addActionListener(aEdit);
	}
	
	class EditItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			rp.editItemAtIndex(n_basics.getSelectedIndex(), e_name.getText(), Float.parseFloat(e_price.getText()));
			
		}
		
	}
}
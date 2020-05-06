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

public class AdminRemoveGUI extends JFrame{
	public JButton n_remove = new JButton("Remove From Menu");
	public JComboBox<String> n_basics;
	public RestaurantProcessing rp;
	public JPanel content;
	public GridBagConstraints gbc;
	public JCheckBox jcb = new JCheckBox();
	public AdminRemoveGUI (Restaurant rest){
		this.rp = rest;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		content.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 0;
		content.add(new JLabel("Remove an item from the menu"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 30;
		n_basics = new JComboBox<String> ();
		for(MenuItem mi: rp.getMenuItems())
			n_basics.addItem(mi.toString());
		content.add(n_basics, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 50;
		content.add(n_remove, gbc);
		
		this.addRemoveListener(new RemoveItemListener());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(250, 120);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
	
	public void addRemoveListener(ActionListener aRemove) {
		this.n_remove.addActionListener(aRemove);
	}
	
	class RemoveItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			rp.removeItemAtIndex(n_basics.getSelectedIndex());
			
		}
		
	}
}
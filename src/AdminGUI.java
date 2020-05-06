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

public class AdminGUI extends JFrame{
	public JButton n_add = new JButton("Add To Menu");
	public JButton n_remove = new JButton("Remove From Menu");
	public JButton n_edit = new JButton("Edit From Menu");
	public JButton n_view = new JButton("View Menu");
	public RestaurantProcessing rp;
	public JPanel content;
	public GridBagConstraints gbc;
	public JCheckBox jcb = new JCheckBox();
	public AdminAddGUI aGUI;
	public AdminRemoveGUI rGUI;
	public AdminEditGUI eGUI;
	public AdminViewGUI vGUI;
	public Restaurant restConstr;
	public AdminGUI (Restaurant rest){
		this.rp = rest;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		content.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 0;
		content.add(new JLabel("Welcome Mr. Admin"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 30;
		content.add(n_add, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 50;
		content.add(n_remove, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 60;
		content.add(n_edit, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 70;
		content.add(n_view, gbc);
		
		
		this.addAddListener(new AddItemListener());
		this.addRemoveListener(new RemoveItemListener());
		this.addEditListener(new EditItemListener());
		this.addViewListener(new ViewListener());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(170, 170);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
	
	public void addAddListener(ActionListener aAdd) {
		this.n_add.addActionListener(aAdd);
	}
	
	public void addRemoveListener(ActionListener aRemove) {
		this.n_remove.addActionListener(aRemove);
	}
	
	public void addEditListener(ActionListener aEdit) {
		this.n_edit.addActionListener(aEdit);
	}
	
	public void addViewListener(ActionListener aView) {
		this.n_view.addActionListener(aView);
	}
	
	class AddItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			aGUI = new AdminAddGUI((Restaurant)rp);
			
		}
		
	}
	
	class RemoveItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			rGUI = new AdminRemoveGUI((Restaurant)rp);
			
		}
		
	}
	
	class EditItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			eGUI = new AdminEditGUI((Restaurant)rp);
			
		}
		
	}
	
	class ViewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			vGUI = new AdminViewGUI((Restaurant)rp);
			
		}
		
	}
}
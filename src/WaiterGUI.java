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

public class WaiterGUI extends JFrame{
	public JButton w_add = new JButton("Add an order");
	public JButton w_calc = new JButton("Finalize Order");
	//public JButton w_export = new JButton("Export bill");
	public JButton w_view = new JButton("View Orders");
	public RestaurantProcessing rp;
	public JPanel content;
	public GridBagConstraints gbc;
	public JCheckBox jcb = new JCheckBox();
	public WaiterAddGUI aGUI;
	public WaiterCompGUI wGUI;
	public AdminEditGUI eGUI;
	public WaiterViewGUI vGUI;
	public Restaurant restConstr;
	public WaiterGUI (Restaurant rest){
		this.rp = rest;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		content.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 0;
		content.add(new JLabel("Welcome Mr. Waiter"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 30;
		content.add(w_add, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 50;
		content.add(w_calc, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 60;
		content.add(w_view, gbc);

		
		this.addAddListener(new AddOrderListener());
		this.addCalcListener(new CalculateListener());
		this.addViewListener(new ViewListener());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(150, 150);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
	
	public void addAddListener(ActionListener wAdd) {
		this.w_add.addActionListener(wAdd);
	}
	
	public void addCalcListener(ActionListener wCalc) {
		this.w_calc.addActionListener(wCalc);
	}
	
	public void addViewListener(ActionListener wView) {
		this.w_view.addActionListener(wView);
	}
	
	class AddOrderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			aGUI = new WaiterAddGUI((Restaurant)rp);
			
		}
		
	}
	
	class CalculateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			wGUI = new WaiterCompGUI((Restaurant)rp);
			
		}
		
	}
	
	class ViewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			vGUI = new WaiterViewGUI((Restaurant)rp);
			
		}
		
	}
	
}
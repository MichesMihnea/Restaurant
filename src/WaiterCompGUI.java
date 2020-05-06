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

public class WaiterCompGUI extends JFrame{
	public JButton w_calc = new JButton("Calculate");
	public JButton w_export = new JButton("Export");
	public JComboBox<String> w_orders;
	public JTextField w_price = new JTextField(20);
	public RestaurantProcessing rp;
	public JPanel content;
	public GridBagConstraints gbc;
	public JCheckBox jcb = new JCheckBox();
	public WaiterCompGUI (Restaurant rest){
		this.rp = rest;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		content.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 0;
		content.add(new JLabel("Compute price for an order"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 30;
		w_orders = new JComboBox<String> ();
		for(Order o: rp.getOrders().keySet())
			w_orders.addItem(o.toString());
		content.add(w_orders, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 50;
		content.add(w_calc, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 55;
		content.add(new JLabel("Price:"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 60;
		content.add(w_price, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 70;
		content.add(w_export, gbc);
		
		this.addCalcListener(new CalcListener());
		this.addExportListener(new ExportListener());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(250, 180);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
	
	public void addCalcListener(ActionListener wCalc) {
		this.w_calc.addActionListener(wCalc);
	}
	
	public void addExportListener(ActionListener wExport) {
		this.w_export.addActionListener(wExport);
	}
	
	class CalcListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			w_price.setText(Float.toString(rp.computePrice(w_orders.getSelectedIndex())));
			
		}
		
	}
	
	class ExportListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			rp.export(w_orders.getSelectedIndex());	
		}
		
	}
}
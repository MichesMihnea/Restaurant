import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import java.util.Set;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class WaiterViewGUI extends JFrame{
	public JButton n_remove = new JButton("Remove From Menu");
	public JComboBox<String> n_basics;
	public RestaurantProcessing rp;
	public JPanel content;
	public JTable menu;

	public WaiterViewGUI (Restaurant rest){
		this.rp = rest;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		content = new JPanel();
        content.setLayout(new BorderLayout());
		
		List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Order ID");
        columns.add("Date Placed");
        columns.add("Price");
        
        Set <Order> list = rp.getOrders().keySet();
        int index = 0;
        Object[] o = new Object[3];
        for(Order oLst: list) {
        	o[0] = oLst.orderID;
        	o[1] = oLst.date;
        	o[2] = rp.computePrice(index);
        	index ++;
            values.add(new String[] {o[0].toString(), o[1].toString(), o[2].toString()});
        }
        
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        menu = new JTable(tableModel);
        
        content.add(new JScrollPane(menu), BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(500, 200);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
}
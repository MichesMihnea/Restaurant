import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainGUI extends JFrame{
	public JButton m_admin = new JButton("Admin");
	public JButton m_waiter = new JButton("Waiter");
	public JButton m_chef = new JButton("Chef");
	public RestaurantProcessing rp;
	public JPanel content;
	public GridBagConstraints gbc;
	public AdminGUI aGUI;
	public WaiterGUI wGUI;
	public ChefGUI cGUI;
	public Restaurant restConstr;
	public MainGUI (Restaurant rest){
		this.rp = rest;
		rp.importMenu();
		cGUI = new ChefGUI();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		MainGUI frame = this;
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        frame, "Are you sure?");
                if( result==JOptionPane.OK_OPTION){
                	rp.exportMenu();
                    // NOW we change it to dispose on close..
                    frame.setDefaultCloseOperation(
                            JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
		rest.addObserver(cGUI);
		content = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		content.setLayout(layout);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 0;
		content.add(new JLabel("Welcome to Restaurant Simulator!"), gbc);
		
		gbc.gridx = 10;
		gbc.gridy = 20;
		content.add(new JLabel("Log In As"), gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 30;
		content.add(m_admin, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 50;
		content.add(m_waiter, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 10;
		gbc.gridy = 60;
		content.add(m_chef, gbc);
		
		this.addAdminListener(new AdminListener());
		this.addWaiterListener(new WaiterListener());
		this.addChefListener(new ChefListener());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setContentPane(content);
		this.setSize(230, 160);
		this.setTitle("Warehouse");
		this.setVisible(true);
	}
	
	public void addAdminListener(ActionListener aAdd) {
		this.m_admin.addActionListener(aAdd);
	}
	
	public void addWaiterListener(ActionListener aWaiter) {
		this.m_waiter.addActionListener(aWaiter);
	}
	
	public void addChefListener(ActionListener aChef) {
		this.m_chef.addActionListener(aChef);
	}
	
	class AdminListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			aGUI = new AdminGUI((Restaurant)rp);
			
		}
		
	}
	
	class WaiterListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			wGUI = new WaiterGUI((Restaurant)rp);
			
		}
		
	}
	
	class ChefListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			cGUI.setVisible(true);
			
		}
		
	}
	
}
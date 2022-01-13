package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ordersDAO;
import utilities.DBConnection;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Don extends JPanel {
	private JTable table;
	Connection conn;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Don() throws ClassNotFoundException, IOException, SQLException {
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));
		this.setBounds(271, 115, 705, 479);
		setLayout(null);
		
		DBConnection.init("database.properties");
		conn = DBConnection.getConnection();
		ordersDAO orders_dao = new ordersDAO(conn);
		
		
		ArrayList<String> ngay = new ArrayList<String>();
		ngay = getNgay();
		String [] arrayngay = ngay.toArray(new String[ngay.size()]);
		JComboBox comboBox = new JComboBox(arrayngay);
		comboBox.setBounds(10, 68, 160, 36);
		add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Chọn ngày");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 10, 160, 36);
		add(lblNewLabel);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 10, 481, 448);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn nh\u00E2n vi\u00EAn", "Ng\u00E0y", "Th\u1EDDi gian", "Th\u00E0nh ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		orders_dao.getOrdertable(table, model);
		scrollPane.setViewportView(table);
		
		JButton btntailai = new JButton(new ImageIcon(new ImageIcon("images/truyen.png").getImage().getScaledInstance(46, 46,20)));
		btntailai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ngaycombo = comboBox.getSelectedItem().toString();
				try {
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					orders_dao.getOrderngaytable(table, model, ngaycombo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btntailai.setBounds(10, 119, 46, 46);
		add(btntailai);		
	}
	
	public ArrayList<String> getNgay() throws ClassNotFoundException, IOException, SQLException{		
		String query = "Select ngay_order from orders Group by (ngay_order);";
		PreparedStatement stat = conn.prepareStatement(query);		
		ResultSet result = stat.executeQuery();		
		boolean check = false;
		ArrayList<String> menuList = new ArrayList<String>();
		if(result.next()) {
			check = true;						
			do {
				String hish = result.getString(1);				
				menuList.add(hish);				
			}while (result.next());
		}
		return menuList;			
	}
}
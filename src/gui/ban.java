package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.banDAO;
import utilities.DBConnection;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ban extends JPanel {
	private JTable table;
	private JTable table_1;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public ban() throws ClassNotFoundException, IOException, SQLException {
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));
		setBounds(271, 115, 705, 479);
		setLayout(null);
		setVisible(true);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		banDAO ban_dao = new banDAO(conn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 250, 390);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID b\u00E0n", "Tr\u1EA1ng th\u00E1i"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		ban_dao.getbanDSDtable(table, model);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(270, 10, 250, 390);
		add(scrollPane_1);
		
		table_1 = new JTable();
		DefaultTableModel df = (DefaultTableModel) table_1.getModel();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID b\u00E0n", "Tr\u1EA1ng th\u00E1i"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Float.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		ban_dao.getbanTrongtable(table_1, df);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("S\u1ED1 b\u00E0n \u0111ang ho\u1EA1t \u0111\u1ED9ng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(20, 410, 236, 35);
		add(lblNewLabel);
		
		JLabel lblSBnTrng = new JLabel("S\u1ED1 b\u00E0n tr\u1ED1ng");
		lblSBnTrng.setHorizontalAlignment(SwingConstants.CENTER);
		lblSBnTrng.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSBnTrng.setBounds(280, 410, 236, 35);
		add(lblSBnTrng);
		
		String lbtrong = String.valueOf(table_1.getRowCount());
		String lbdhd = String .valueOf(table.getRowCount());
		JLabel dhd = new JLabel(lbdhd);
		dhd.setForeground(Color.RED);
		dhd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		dhd.setHorizontalAlignment(SwingConstants.CENTER);
		dhd.setBounds(93, 443, 65, 26);
		add(dhd);
		
		JLabel trong = new JLabel(lbtrong);
		trong.setForeground(Color.RED);
		trong.setFont(new Font("Times New Roman", Font.BOLD, 20));
		trong.setHorizontalAlignment(SwingConstants.CENTER);
		trong.setBounds(373, 443, 65, 26);
		add(trong);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statu = "trống";
				try {
					ban_dao.themBan(statu);
					DefaultTableModel m = (DefaultTableModel)table_1.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					DefaultTableModel m2 = (DefaultTableModel)table.getModel();
					m2.getDataVector().removeAllElements();
					m2.fireTableDataChanged();
					ban_dao.getbanTrongtable(table_1, df);
					ban_dao.getbanDSDtable(table, model);
					String lbtrong = String.valueOf(table_1.getRowCount());	
					trong.setText(lbtrong);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnThem.setBounds(552, 53, 122, 59);
		add(btnThem);
		
		JButton btnXoa = new JButton("Xo\u00E1");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();			
				int r2 = table_1.getSelectedRow();
				if(table.getSelectedRow()==-1) {
					try {
						int id2 = (int) table_1.getValueAt(r2, 0); 
						ban_dao.xoaBan(id2);
						DefaultTableModel m = (DefaultTableModel)table_1.getModel();
						m.getDataVector().removeAllElements();
						m.fireTableDataChanged();	
						ban_dao.getbanTrongtable(table_1, df);
						String lbtrong = String.valueOf(table_1.getRowCount());
						trong.setText(lbtrong);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (table_1.getSelectedRow() == -1) {
					try {
						int id = (int) table.getValueAt(r, 0);
						ban_dao.xoaBan(id);
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.getDataVector().removeAllElements();
						m.fireTableDataChanged();	
						ban_dao.getbanDSDtable(table, model);
						String lbdhd = String .valueOf(table.getRowCount());
						dhd.setText(lbdhd);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnXoa.setBounds(552, 180, 122, 59);
		add(btnXoa);
		
	}
}

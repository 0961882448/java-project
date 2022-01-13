package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JPanel;
import utilities.DBConnection;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import dao.menuDAO;
import dto.menuDTO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class menu extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public menu() throws ClassNotFoundException, IOException, SQLException {
		
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));
		this.setBounds(271, 115, 705, 479);
		setLayout(null);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		menuDAO menu_dao = new menuDAO(conn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 525, 459);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn m\u00F3n \u0103n", "Gi\u00E1 ($)"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		menu_dao.getmenutable(table, model);
		scrollPane.setViewportView(table);
		
		JButton btnthem = new JButton(new ImageIcon(new ImageIcon("images/them.png").getImage().getScaledInstance(20, 20,0)));
		btnthem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnthem.setHorizontalAlignment(SwingConstants.LEFT);
		btnthem.setBackground(new Color(152, 251, 152));
		btnthem.setText("Th\u00EAm M\u00F3n");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showInputDialog(null,new themMenu());
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					menu_dao.getmenutable(table, model);
				} catch (HeadlessException | ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnthem.setBounds(545, 10, 150, 40);
		add(btnthem);
		
		JButton btnsua = new JButton(new ImageIcon(new ImageIcon("images/sua.jpg").getImage().getScaledInstance(20, 20,0)));
		btnsua.setText("S\u1EEDa M\u00F3n");
		btnsua.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnsua.setHorizontalAlignment(SwingConstants.LEFT);
		btnsua.setBackground(new Color(152, 251, 152));
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				if(r != -1) {
					int id = (int)(table.getValueAt(r,0));
					String ten = (String)(table.getValueAt(r,1));
					double gia = (Double)(table.getValueAt(r,2));		
					menuDTO neww = new menuDTO(id,ten, gia);
					try {					
						menu_dao.suaMenu(neww);					
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.getDataVector().removeAllElements();
						m.fireTableDataChanged();
						menu_dao.getmenutable(table, model);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (r == -1) {
					JOptionPane.showMessageDialog(null,"Vui lòng chọn món ăn cần Sửa.");
				}

			}
		});
		btnsua.setBounds(545, 60, 150, 40);
		add(btnsua);
		
		JButton btnxoa = new JButton(new ImageIcon(new ImageIcon("images/xoa.jpg").getImage().getScaledInstance(20, 20,0)));
		btnxoa.setText("Xo\u00E1 M\u00F3n");
		btnxoa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnxoa.setHorizontalAlignment(SwingConstants.LEFT);
		btnxoa.setBackground(new Color(152, 251, 152));
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				if(r != -1) {
					int id = (int)(table.getValueAt(r,0));
					try {
						menu_dao.xoaMenu(id);
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.getDataVector().removeAllElements();
						m.fireTableDataChanged();
						menu_dao.getmenutable(table, model);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (r== -1) {
					JOptionPane.showMessageDialog(null,"Vui lòng chọn món ăn cần Xoá .");
				}				
			}
		});
		btnxoa.setBounds(545, 110, 150, 40);
		add(btnxoa);
	}
}

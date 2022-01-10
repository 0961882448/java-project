package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.nguyenlieuDAO;
import dto.nguyenlieuDTO;
import utilities.DBConnection;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class nguyenlieu extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public nguyenlieu() throws ClassNotFoundException, IOException, SQLException {
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));
		this.setBounds(271, 115, 705, 479);
		setLayout(null);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		nguyenlieuDAO nguyenlieu_dao = new nguyenlieuDAO(conn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 525, 414);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn nguy\u00EAn li\u1EC7u", "Gi\u00E1 (1 \u0111\u01A1n v\u1ECB)", "S\u1ED1 l\u01B0\u1EE3ng", "Ng\u00E0y nh\u1EADp"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, Integer.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		nguyenlieu_dao.getNhanVientable(table, model);
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("T\u1ED5ng gi\u00E1 ti\u1EC1n: ");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(37, 424, 234, 45);
		add(lblNewLabel);
		
		
		Double sum = nguyenlieu_dao.giaNguyenLieu();
		String sumString = String.valueOf(sum);
		JLabel tong = new JLabel(sumString);
		tong.setForeground(Color.RED);
		tong.setHorizontalAlignment(SwingConstants.CENTER);
		tong.setFont(new Font("Times New Roman", Font.BOLD, 25));
		tong.setBounds(301, 424, 234, 45);
		add(tong);
		
		JButton btnthem = new JButton(new ImageIcon(new ImageIcon("images/them.png").getImage().getScaledInstance(20,20,20)));
		btnthem.setBackground(new Color(127, 255, 0));
		btnthem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnthem.setHorizontalAlignment(SwingConstants.LEFT);
		btnthem.setText("Th\u00EAm NL");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showInputDialog(null,new themNguyenLieu());
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					nguyenlieu_dao.getNhanVientable(table, model);
				} catch (HeadlessException | ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnthem.setBounds(545, 10, 150, 40);
		add(btnthem);
		
		JButton btnsua = new JButton(new ImageIcon(new ImageIcon("images/sua.jpg").getImage().getScaledInstance(20,20,20)));
		btnsua.setBackground(new Color(127, 255, 0));
		btnsua.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnsua.setHorizontalAlignment(SwingConstants.LEFT);
		btnsua.setText("S\u1EEDa NL");
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				
				int id = (int) table.getValueAt(r, 0);
				String ten = (String) table.getValueAt(r, 1);
				double gia = (Double) table.getValueAt(r, 2);
				int sl = (int) table.getValueAt(r, 3);
				String ngayString = (String) table.getValueAt(r, 4);
				LocalDate ngay = LocalDate.parse(ngayString);
				
				nguyenlieuDTO neww = new nguyenlieuDTO(id,ten, gia, sl, ngay);
				try {
					nguyenlieu_dao.suaNguyenLieu(neww);
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					nguyenlieu_dao.getNhanVientable(table, model);
				} catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnsua.setBounds(545, 60, 150, 40);
		add(btnsua);
		
		JButton btnxoa = new JButton(new ImageIcon(new ImageIcon("images/xoa.jpg").getImage().getScaledInstance(20,20,20)));
		btnxoa.setBackground(new Color(127, 255, 0));
		btnxoa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnxoa.setHorizontalAlignment(SwingConstants.LEFT);
		btnxoa.setText("Xo\u00E1 NL");
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				int id = (int) table.getValueAt(r, 0);
				try {
					nguyenlieu_dao.xoaNguyenLieu(id);
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					nguyenlieu_dao.getNhanVientable(table, model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnxoa.setBounds(545, 110, 150, 40);
		add(btnxoa);
		
//	      table.addMouseListener(new MouseListener(){
//	  		@Override
//	  		public void mouseClicked(MouseEvent e) {
//	  			// TODO Auto-generated method stub
//	  			int r = table.getSelectedRow();
//	  			int r_model = -1;
//	  			if (r != -1){
//	  				r_model = table.convertRowIndexToModel(r);
//	  			}
//	  			tfid.setText(String.valueOf(table.getValueAt(r_model,0)));
//	  			tften.setText(String.valueOf(table.getValueAt(r_model,1)));
//	  			tfgia.setText(String.valueOf(table.getValueAt(r_model,2)));
//	  			tfsl.setText(String.valueOf(table.getValueAt(r_model,3)));
//	  			tfngay.setText(String.valueOf(table.getValueAt(r_model,4)));
//	  		}
//	  		@Override
//	  		public void mouseEntered(MouseEvent e) {
//	  			// TODO Auto-generated method stub	  			
//	  		}
//	  		@Override
//	  		public void mouseExited(MouseEvent e) {
//	  			// TODO Auto-generated method stub	  			
//	  		}
//	  		@Override
//	  		public void mousePressed(MouseEvent e) {
//	  			// TODO Auto-generated method stub	  			
//	  		}
//	  		@Override
//	  		public void mouseReleased(MouseEvent e) {
//	  			// TODO Auto-generated method stub	  			
//	  		}	      	  
//	        });
	}
}

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

public class nguyenlieu extends JPanel {
	private JTable table;
	private JTextField tfngay;
	private JTextField tfsl;
	private JTextField tfgia;
	private JTextField tften;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@SuppressWarnings("serial")
	public nguyenlieu() throws ClassNotFoundException, IOException, SQLException {
		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(705, 479));
		this.setBounds(271, 115, 705, 479);
		setLayout(null);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		nguyenlieuDAO nguyenlieu_dao = new nguyenlieuDAO(conn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 525, 311);
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
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, Integer.class, String.class
			};
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
		
		JLabel tong = new JLabel("");
		tong.setForeground(Color.RED);
		tong.setHorizontalAlignment(SwingConstants.CENTER);
		tong.setFont(new Font("Times New Roman", Font.BOLD, 25));
		tong.setBounds(301, 424, 234, 45);
		add(tong);
		
		JButton btnthem = new JButton(new ImageIcon(new ImageIcon("images/them.png").getImage().getScaledInstance(150, 150,20)));
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
		btnthem.setBounds(545, 10, 150, 150);
		add(btnthem);
		
		JButton btnsua = new JButton(new ImageIcon(new ImageIcon("images/sua.jpg").getImage().getScaledInstance(150, 150,20)));
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				String ten = tften.getText();
				double gia = Double.parseDouble(tfgia.getText());
				int sl = Integer.parseInt(tfsl.getText());
				LocalDate ngay = LocalDate.parse(tfngay.getText()) ;		
				nguyenlieuDTO neww = new nguyenlieuDTO(r,ten, gia, sl, ngay);
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
		btnsua.setBounds(545, 171, 150, 138);
		add(btnsua);
		
		JButton btnxoa = new JButton(new ImageIcon(new ImageIcon("images/xoa.jpg").getImage().getScaledInstance(150, 150,20)));
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				System.out.println(r);
				try {
					nguyenlieu_dao.xoaNguyenLieu(r);
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
		btnxoa.setBounds(545, 319, 150, 150);
		add(btnxoa);
		
		tfngay = new JTextField();
		tfngay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tfngay.setColumns(10);
		tfngay.setBounds(439, 357, 96, 33);
		add(tfngay);
		
		tfsl = new JTextField();
		tfsl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tfsl.setColumns(10);
		tfsl.setBounds(333, 357, 96, 33);
		add(tfsl);
		
		tfgia = new JTextField();
		tfgia.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tfgia.setColumns(10);
		tfgia.setBounds(227, 357, 96, 33);
		add(tfgia);
		
		tften = new JTextField();
		tften.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tften.setColumns(10);
		tften.setBounds(116, 357, 96, 33);
		add(tften);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 331, 96, 19);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("T\u00EAn ");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(116, 331, 96, 19);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Gi\u00E1");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(227, 331, 96, 19);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(333, 331, 96, 19);
		add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Ng\u00E0y nh\u1EADp");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setBounds(439, 331, 96, 19);
		add(lblNewLabel_1_4);
		
		JLabel tfid = new JLabel("");
		tfid.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tfid.setHorizontalAlignment(SwingConstants.CENTER);
		tfid.setBounds(10, 357, 96, 33);
		add(tfid);
		
	      table.addMouseListener(new MouseListener(){
	  		@Override
	  		public void mouseClicked(MouseEvent e) {
	  			// TODO Auto-generated method stub
	  			int r = table.getSelectedRow();
	  			int r_model = -1;
	  			if (r != -1){
	  				r_model = table.convertRowIndexToModel(r);
	  			}
	  			tfid.setText(String.valueOf(table.getValueAt(r_model,0)));
	  			tften.setText(String.valueOf(table.getValueAt(r_model,1)));
	  			tfgia.setText(String.valueOf(table.getValueAt(r_model,2)));
	  			tfsl.setText(String.valueOf(table.getValueAt(r_model,3)));
	  			tfngay.setText(String.valueOf(table.getValueAt(r_model,4)));
	  		}
	  		@Override
	  		public void mouseEntered(MouseEvent e) {
	  			// TODO Auto-generated method stub	  			
	  		}
	  		@Override
	  		public void mouseExited(MouseEvent e) {
	  			// TODO Auto-generated method stub	  			
	  		}
	  		@Override
	  		public void mousePressed(MouseEvent e) {
	  			// TODO Auto-generated method stub	  			
	  		}
	  		@Override
	  		public void mouseReleased(MouseEvent e) {
	  			// TODO Auto-generated method stub	  			
	  		}	      	  
	        });
	}
}

package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import dao.NhanVienDAO;
import dto.NhanVienDTO;
import utilities.DBConnection;




@SuppressWarnings("serial")
public class NhanVien extends JPanel {
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblHTn;
	private JLabel lblNgySinh;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblQuyn;
	private JLabel lblLng;
	private JTextField tf_hoten;
	private JTextField tf_ngaysinh;
	private JTextField tf_user;
	private JTextField tf_quyen;
	private JTextField tf_luong;
	private JPasswordField tf_pass;

	/**
	 * Create the panel.
	 */
	public NhanVien() throws SQLException, ClassNotFoundException, IOException {		
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(705, 479));
		this.setLayout(null);
		setBounds(271, 115, 705, 479);
		JLabel tf_manv = new JLabel();
		tf_manv.setFont(new Font("Tahoma", Font.BOLD, 13));
		tf_manv.setBackground(Color.WHITE);
		tf_manv.setHorizontalAlignment(SwingConstants.CENTER);
		tf_manv.setBounds(10, 350, 97, 30);
		add(tf_manv);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 685, 305);
		add(scrollPane);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		NhanVienDAO nhanvien = new NhanVienDAO(conn);
		
			
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 NV", "H\u1ECD T\u00EAn", "Ng\u00E0y Sinh", "Username", "Password", "Quy\u1EC1n", "L\u01B0\u01A1ng"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		try {
			nhanvien.getNhanVientable(table,model);			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		scrollPane.setViewportView(table);
		
		JButton btnadd = new JButton("Thêm NV");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(new DKNhanVien());
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.getDataVector().removeAllElements();
				m.fireTableDataChanged();
				try {
					nhanvien.getNhanVientable(table,model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
		}});
		btnadd.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnadd.setBounds(21, 422, 141, 47);
		add(btnadd);
		
		JButton btnRemove = new JButton("Xo\u00E1");
		btnRemove.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				int r = table.getSelectedRow();
				if (r != -1){
					//int r_model = table.convertRowIndexToModel(r);
					int id = (int) table.getValueAt(r, 0);
					try {
						nhanvien.xoaNhanvien(id);		
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.getDataVector().removeAllElements();
						m.fireTableDataChanged();
						nhanvien.getNhanVientable(table,model);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}	else if (r == -1) {
					JOptionPane.showMessageDialog(null,	"Vui lòng chọn nhân viên cần Xoá.");
				}				
			}
		});
		btnRemove.setBounds(460, 422, 131, 47);
		add(btnRemove);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					int ma = Integer.parseInt(tf_manv.getText());
					String name = tf_hoten.getText();
					LocalDate date = LocalDate.parse(tf_ngaysinh.getText());
					String user = tf_user.getText();
					String pas = tf_pass.getText();					
					String qu = tf_quyen.getText();
					int lu = Integer.parseInt(tf_luong.getText());					
					NhanVienDTO update = new NhanVienDTO(ma,name, date, user, pas, qu, lu);
					nhanvien.suaNhanvien(update);						
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					nhanvien.getNhanVientable(table,model);
				} catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		}});
		btnupdate.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnupdate.setBounds(230, 422, 131, 47);
		add(btnupdate);
		
		lblNewLabel = new JLabel("Mã NV");
		lblNewLabel.setBounds(10, 312, 97, 25);
		add(lblNewLabel);
		 		
		lblHTn = new JLabel("Họ Tên");
		lblHTn.setBounds(108, 314, 97, 25);
		add(lblHTn);
		
		lblNgySinh = new JLabel("Ngày Sinh");
		lblNgySinh.setBounds(204, 314, 97, 25);
		add(lblNgySinh);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(300, 314, 97, 25);
		add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(397, 314, 97, 25);
		add(lblPassword);
		
		lblQuyn = new JLabel("Quyền");
		lblQuyn.setBounds(494, 314, 97, 25);
		add(lblQuyn);
		
		lblLng = new JLabel("Lương");
		lblLng.setBounds(598, 314, 97, 25);
		add(lblLng);
		
		tf_hoten = new JTextField();
		tf_hoten.setColumns(10);
		tf_hoten.setBounds(108, 349, 97, 33);
		add(tf_hoten);
		
		tf_ngaysinh = new JTextField();
		tf_ngaysinh.setColumns(10);
		tf_ngaysinh.setBounds(204, 349, 97, 33);
		add(tf_ngaysinh);
		
		tf_user = new JTextField();
		tf_user.setColumns(10);
		tf_user.setBounds(300, 349, 97, 33);
		add(tf_user);
		
		tf_quyen = new JTextField();
		tf_quyen.setColumns(10);
		tf_quyen.setBounds(494, 349, 97, 33);
		add(tf_quyen);
		
		tf_luong = new JTextField();
		tf_luong.setColumns(10);
		tf_luong.setBounds(591, 349, 104, 33);
		add(tf_luong);
		
		tf_pass = new JPasswordField();
		tf_pass.setBounds(397, 349, 97, 33);
		add(tf_pass);
	      table.addMouseListener(new MouseListener(){
	  		@Override
	  		public void mouseClicked(MouseEvent e) {
	  			// TODO Auto-generated method stub
	  			int r = table.getSelectedRow();
	  			int r_model = -1;
	  			if (r != -1){
	  				r_model = table.convertRowIndexToModel(r);
	  			}
	  			tf_manv.setText(String.valueOf(table.getValueAt(r_model,0)));
	  			tf_hoten.setText(String.valueOf(table.getValueAt(r_model,1)));
	  			tf_ngaysinh.setText(String.valueOf(table.getValueAt(r_model,2)));
	  			tf_user.setText(String.valueOf(table.getValueAt(r_model,3)));
	  			tf_pass.setText(String.valueOf(table.getValueAt(r_model,4)));
	  			tf_quyen.setText(String.valueOf(table.getValueAt(r_model,5)));
	  			tf_luong.setText(String.valueOf(table.getValueAt(r_model,6)));
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

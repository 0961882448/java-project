package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.UserDAO;
import dto.AdminDTO;
import hash_password.PBKDF2_Verify_Password;
import utilities.DBConnection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class admin extends JPanel {
	private JTable table;
	private JTextField tf_user;
	private JPasswordField tf_pass;
	private JLabel tf_id;
	private JLabel lb2;
	private JLabel lb3;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */

	public admin() throws ClassNotFoundException, IOException, SQLException {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(705, 479));
		this.setLayout(null);
		setBounds(271, 115, 705, 479);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 685, 305);
		add(scrollPane);
		
		DBConnection.init("database.properties");
		Connection conn = DBConnection.getConnection();
		UserDAO admin = new UserDAO(conn);
		
		table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Username", "Password"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		admin.getAdmintable(table, model);
		scrollPane.setViewportView(table);
		
		JButton btnadd = new JButton("Thêm NV");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(new DKAdmin());				
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
					int r_model = table.convertRowIndexToModel(r);
					int id = (int) table.getValueAt(r_model, 0);
					try {
						admin.xoaAdmin(id);		
						DefaultTableModel m = (DefaultTableModel)table.getModel();
						m.getDataVector().removeAllElements();
						m.fireTableDataChanged();
						admin.getAdmintable(table, model);
						//nhanvien.getNhanVientable(table,model);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}				
			}
		});
		btnRemove.setBounds(460, 422, 131, 47);
		add(btnRemove);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ma = Integer.parseInt(tf_id.getText());				
					String pas = tf_pass.getText();
					String user = tf_user.getText();
					String hash_password;
					hash_password = PBKDF2_Verify_Password.generateStrongPasswordHash(pas);
					AdminDTO update = new AdminDTO(ma, user, hash_password );
					admin.suaAdmin(update);		
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					admin.getAdmintable(table, model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
				}
		}});
		JButton tailai = new JButton(new ImageIcon(new ImageIcon("images/tailai.png").getImage().getScaledInstance(30, 40,20)));
		tailai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.getDataVector().removeAllElements();
					m.fireTableDataChanged();
					admin.getAdmintable(table, model);					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		tailai.setBounds(634, 422, 61, 47);
		add(tailai);
		btnupdate.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnupdate.setBounds(230, 422, 131, 47);
		add(btnupdate);
		
		JLabel lb1 = new JLabel("ID");
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(10, 315, 231, 31);
		add(lb1);
		
		tf_user = new JTextField();
		tf_user.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tf_user.setBounds(242, 350, 231, 31);
		add(tf_user);
		tf_user.setColumns(10);
		
		tf_pass = new JPasswordField();
		tf_pass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tf_pass.setBounds(483, 350, 212, 31);
		add(tf_pass);
		
		tf_id = new JLabel();
		tf_id.setHorizontalAlignment(SwingConstants.CENTER);
		tf_id.setFont(new Font("Tahoma", Font.BOLD, 15));
		tf_id.setBounds(10, 349, 231, 31);
		add(tf_id);
		
		lb2 = new JLabel("Username");
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb2.setBounds(242, 315, 231, 31);
		add(lb2);
		
		lb3 = new JLabel("Passwword");
		lb3.setHorizontalAlignment(SwingConstants.CENTER);
		lb3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb3.setBounds(483, 315, 212, 31);
		add(lb3);
		
		table.addMouseListener(new MouseListener(){
	  		@Override
	  		public void mouseClicked(MouseEvent e) {
	  			// TODO Auto-generated method stub
	  			int r = table.getSelectedRow();
	  			int r_model = -1;
	  			if (r != -1){
	  				r_model = table.convertRowIndexToModel(r);
	  			}
	  			tf_id.setText(String.valueOf(table.getValueAt(r_model,0)));
	  			tf_user.setText(String.valueOf(table.getValueAt(r_model,1)));
	  			tf_pass.setText(String.valueOf(table.getValueAt(r_model,2)));

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

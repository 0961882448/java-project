package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dao.NhanVienDAO;
import dao.UserDAO;
import hash_password.PBKDF2_Verify_Password;
import utilities.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class form_dangnhap extends JFrame {

	private JPanel contentPane;
	private JTextField tfDN;
	private JTextField tfMK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					form_dangnhap frame = new form_dangnhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public form_dangnhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setLayout(null);
		this.setVisible(true);
		this.setTitle("Đăng nhập");
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb1 = new JLabel("QU\u1EA2N L\u00DD NH\u00C0 H\u00C0NG");		
		lb1.setIcon(new ImageIcon(new ImageIcon("images/trangchu.png").getImage().getScaledInstance(40, 40,20)));		
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lb1.setForeground(Color.MAGENTA);
		lb1.setBackground(Color.LIGHT_GRAY);
		lb1.setBounds(0, 10, 436, 37);
		contentPane.add(lb1);		

		
		JSeparator separator = new JSeparator(); 
		separator.setBounds(0, 57, 436, 2);
		contentPane.add(separator);
		
		
		ImageIcon icon = new ImageIcon(new ImageIcon("images/trangchu.gif").getImage().getScaledInstance(103, 93,20));		
		JLabel lb_anh = new JLabel(icon);
		lb_anh.setBounds(10, 89, 103, 93);		
		contentPane.add(lb_anh);
		
		JLabel lblNewLabel = new JLabel("Ng\u01B0\u1EDDi d\u00F9ng: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(123, 100, 88, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u: ");
		lblMtKhu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMtKhu.setBounds(123, 149, 72, 21);
		contentPane.add(lblMtKhu);
		
		tfDN = new JTextField();
		tfDN.setBounds(221, 100, 179, 21);
		contentPane.add(tfDN);
		tfDN.setColumns(10);
		
		tfMK = new JPasswordField();
		tfMK.setColumns(10);
		tfMK.setBounds(221, 151, 179, 21);
		contentPane.add(tfMK);
		
		JButton btDangnhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btDangnhap.setBackground(new Color(255, 0, 0));
		btDangnhap.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btDangnhap.setBounds(204, 217, 114, 36);
		contentPane.add(btDangnhap);
		
		btDangnhap.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Load panel1 vao vi tri cua contentPanel	
				String tenDN = tfDN.getText();
				String mk = tfMK.getText();
				try {
					DBConnection.init("database.properties");
					try {
						Connection conn = DBConnection.getConnection();
						UserDAO user_dao = new UserDAO(conn);
						NhanVienDAO users_dao = new NhanVienDAO(conn);
						if(user_dao.layIdAdmin(tenDN)>0){							
							String stored_password = user_dao.layPassAdmin(tenDN);							
							boolean is_match = PBKDF2_Verify_Password.validatePassword(mk, stored_password);
							
							if (is_match){
								new FormAdmin();
								dispose();							

							}else  {
								JOptionPane.showMessageDialog(null,"Tên người dùng hoặc mật khẩu không hợp lệ. (Admin)");
							}
								
						}else if (users_dao.layIdNhanvien(tenDN) >0) {
							
							String stored_passwordnv = users_dao.layPassNhanvien(tenDN);							
							boolean  is_match2 = PBKDF2_Verify_Password.validatePassword(mk, stored_passwordnv);
					
							if (is_match2){
								new formTrangChu();
								dispose();
							}else
								JOptionPane.showMessageDialog(null,"Tên người dùng hoặc mật khẩu không hợp lệ. (Nhân viên)");
						}else{
							JOptionPane.showMessageDialog(null,"Tên người dùng hoặc mật khẩu không hợp lệ.");
						}
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidKeySpecException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
			}			
		});
		
		JButton btThoat = new JButton("Tho\u00E1t");
		btThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btThoat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btThoat.setBounds(329, 217, 97, 36);
		contentPane.add(btThoat);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 192, 436, 2);
		contentPane.add(separator_1);
		
		
	}
}

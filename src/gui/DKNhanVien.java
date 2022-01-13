package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.NhanVienDAO;
import dto.NhanVienDTO;
import utilities.DBConnection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class DKNhanVien extends JPanel {
	private JTextField txthoten, txtngaysinh, txtUsername, txtquyen, txtluong;
	private JPasswordField txtPassword, txtConfirmPassword;


	/**
	 * Create the panel.
	 */
	public DKNhanVien() {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(705, 479));
		setLayout(null);
	
		JLabel lblTitle = new JLabel("REGISTRATION NHÂN VIÊN");
		lblTitle.setForeground(new Color(220, 20, 60));
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(43, 10, 593, 51);
		add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("Họ Tên :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(53, 75, 205, 34);
		add(lblNewLabel);
		
		txthoten = new JTextField();
		txthoten.setBounds(313, 72, 254, 37);
		add(txthoten);
		txthoten.setColumns(10);
		
		txtngaysinh = new JTextField("yyyy/MM/dd");
		txtngaysinh.setColumns(10);
		txtngaysinh.setBounds(313, 119, 254, 37);
		add(txtngaysinh);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(313, 169, 254, 37);
		add(txtUsername);
		
		txtquyen = new JTextField();
		txtquyen.setColumns(10);
		txtquyen.setBounds(313, 310, 254, 37);
		add(txtquyen);
		
		txtluong = new JTextField();
		txtluong.setColumns(10);
		txtluong.setBounds(313, 357, 254, 37);
		add(txtluong);
		
		JLabel lblNewLabel_1 = new JLabel("Ngày Sinh");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(53, 122, 205, 34);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(53, 172, 205, 34);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Lương :");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(53, 360, 205, 34);
		add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Quyền :");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_2.setBounds(53, 313, 205, 34);
		add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Confirm Password :");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_3.setBounds(53, 266, 205, 34);
		add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Password :");
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2_4.setBounds(53, 219, 205, 34);
		add(lblNewLabel_2_4);
		
		JButton btnSave = new JButton("SIGN IN");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					if(RegisterData()) {
							
							JOptionPane.showMessageDialog(null,"Thêm nhân viên thành công");
					}
				} catch (HeadlessException |NoSuchAlgorithmException |InvalidKeySpecException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		});
		btnSave.setFont(new Font("Monotype Corsiva", Font.BOLD, 35));
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setForeground(Color.RED);
		btnSave.setBounds(244, 418, 204, 51);
		add(btnSave);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(313, 216, 254, 37);
		add(txtPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(313, 266, 254, 37);
		add(txtConfirmPassword);
	}
	
	private Boolean RegisterData() throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException
	{
		String strHoTen = txthoten.getText();
		LocalDate strNgaySinh = LocalDate.parse(txtngaysinh.getText()) ;
		String strUsername = txtUsername.getText();
		String strPassword = new String(txtPassword.getPassword());
		String strConfirmPassword = new String(txtConfirmPassword.getPassword());		
		String strQuyen = txtquyen.getText();
		String strluong =txtluong.getText();
		int strluongg = Integer.parseInt(txtluong.getText());
		
		if(strUsername.equals("")) // Username
		{
			JOptionPane.showMessageDialog(null,
					"Vui lòng nhập Username.");
			txtUsername.requestFocusInWindow(); 
			return false;
		}
		if(strHoTen.equals("")) // Username
		{
			JOptionPane.showMessageDialog(null,
					"Vui lòng nhập Họ Tên.");
			txthoten.requestFocusInWindow(); 
			return false;
		}
		if(strNgaySinh.equals("")) // Username
		{
			JOptionPane.showMessageDialog(null,
					"Vui lòng nhập Ngày Sinh.");
			txtngaysinh.requestFocusInWindow(); 
			return false;
		}
		if(strQuyen.equals("")) // Username
		{
			JOptionPane.showMessageDialog(null,
					"Vui lòng nhập Quyền.");
			txtquyen.requestFocusInWindow(); 
			return false;
		}
		if(strluong.equals("")) // Username
		{
			JOptionPane.showMessageDialog(null,
					"Vui lòng nhập Lương.");
			txtluong.requestFocusInWindow(); 
			return false;
		}
		if(strPassword.equals("")) // Password
		{
			JOptionPane.showMessageDialog(null,
					"Vui lòng nhập Password.");
			txtPassword.requestFocusInWindow(); 
			return false;
		}
		
		if(strConfirmPassword.equals("")) // Confirm Password
		{
			JOptionPane.showMessageDialog(null,
					"Vui lòng nhập Confirm Password.");
			txtConfirmPassword.requestFocusInWindow(); 
			return false;
		}
		if(!strPassword.equals(strConfirmPassword)) // Password math
		{
			JOptionPane.showMessageDialog(null,
					"Mật khẩu không khớp");
			txtPassword.requestFocusInWindow(); 
			return false;
		}	
		
		Boolean status = false;
		NhanVienDTO new_user = new NhanVienDTO(strHoTen,strNgaySinh,strUsername, strPassword,strQuyen,strluongg);
		try {
			DBConnection.init("database.properties");
				Connection conn = DBConnection.getConnection();
				NhanVienDAO user_dao = new NhanVienDAO(conn);				
				status = user_dao.themNhanVien(new_user);
				resetRegistrationForm();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return status;
	}
	
	public void resetRegistrationForm(){
		txtUsername.setText("");
		txtPassword.setText("");
		txtConfirmPassword.setText("");
		txthoten.setText("");
		txtluong.setText("");
		txtngaysinh.setText("");
		txtquyen.setText("");
	}
}

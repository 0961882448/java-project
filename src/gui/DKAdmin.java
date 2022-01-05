package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import dao.UserDAO;
import dto.AdminDTO;
import utilities.DBConnection;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DKAdmin extends JPanel {
	private JTextField txtUsername;
	private JPasswordField txtConfirmPassword, txtPassword;

	/**
	 * Create the panel.
	 */
	public DKAdmin() {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(705, 479));
		setLayout(null);
	
		
		JLabel lblTitle = new JLabel("ĐĂNG KÝ ADMIN");
		lblTitle.setForeground(new Color(220, 20, 60));
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(121, 10, 465, 51);
		add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(29, 115, 255, 37);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(71, 197, 213, 37);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_2.setBounds(10, 284, 274, 37);
		add(lblNewLabel_2);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(333, 115, 274, 34);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(333, 197, 274, 37);
		add(txtPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(333, 284, 274, 37);
		add(txtConfirmPassword);
		
		JButton btnSave = new JButton("SIGN IN");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(RegisterData()) {
						JOptionPane.showMessageDialog(null,"Đăng ký Admin thành công.");
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Monotype Corsiva", Font.BOLD, 35));
		btnSave.setBackground(Color.LIGHT_GRAY);
		btnSave.setForeground(Color.RED);
		btnSave.setBounds(242, 374, 204, 51);
		add(btnSave);

	}
	
	private Boolean RegisterData() throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		
		String strUsername = txtUsername.getText();
		String strPassword = new String(txtPassword.getPassword());
		String strConfirmPassword = new String(txtConfirmPassword.getPassword());
		
		if(strUsername.equals("")) // Username
		{
			JOptionPane.showMessageDialog(null,
					"Vui lòng nhập Username.");
			txtUsername.requestFocusInWindow(); 
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
					"Mật khẩu không khớp.");
			txtPassword.requestFocusInWindow(); 
			return false;
		}	
		
		Boolean status = false;
		AdminDTO new_user = new AdminDTO(strUsername, strPassword);
		try {
			DBConnection.init("database.properties");
			try {
				Connection conn = DBConnection.getConnection();
				UserDAO user_dao = new UserDAO(conn);
				
				status = user_dao.themAdmin(new_user);
				resetRegistrationForm();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return status;

	}
	
	public void resetRegistrationForm(){
		txtUsername.setText("");
		txtPassword.setText("");
		txtConfirmPassword.setText("");
	}
}

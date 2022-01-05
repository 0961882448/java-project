package dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.AdminDTO;
import hash_password.PBKDF2_Verify_Password;

public class UserDAO {
	private Connection conn;	
	public UserDAO(Connection conn){
		this.conn = conn;
	}
	
	
	public int layIdAdmin(String username) throws SQLException{
		int id = -1;
		String query = "SELECT id FROM nhahang.adminn WHERE admin_name = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, username);
		ResultSet result = stat.executeQuery();
		if (result.next()){
			return result.getInt(1);
		}
		return id;
	}
	
	public String layPassAdmin(String username) throws SQLException{
		String password = "";
		String query = "SELECT passwordd FROM adminn WHERE admin_name = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, username);
		ResultSet result = stat.executeQuery();
		if (result.next()){
			return result.getString(1);
		}
		return password;
	}

	public AdminDTO getAdmin(String username) throws SQLException{
		AdminDTO current_user = null;
		String query = "SELECT * FROM adminn WHERE admin_name = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, username);
		ResultSet result = stat.executeQuery();
		if (result.next()){
			int id = result.getInt(1);
			String userName = result.getString(2);
			String password = result.getString(3);;
			current_user = new AdminDTO(id, userName, password);
		}
		return current_user;	
	}
	
	public boolean themAdmin(AdminDTO u) throws NoSuchAlgorithmException, InvalidKeySpecException {		
		String username = u.getUsername();
		String password = u.getPassword();
		String hash_password;		
		hash_password = PBKDF2_Verify_Password.generateStrongPasswordHash(password);		
		String query = "call insert_admin(?, ?)";
		try (PreparedStatement stat = conn.prepareStatement(query)){			
			stat.setString(1, username);
			stat.setString(2, hash_password);			
			int p = stat.executeUpdate();
			if (p == 1){
				System.out.println("Insert new record");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	public void suaAdmin(AdminDTO u) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		int id = u.getId();
		String user = u.getUsername();
		String pass = u.getPassword();
		String hash_password;
		hash_password = PBKDF2_Verify_Password.generateStrongPasswordHash(pass);
		String query = "call update_admin(?, ?, ?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, user);
		stat.setString(2, hash_password);
		stat.setInt(3, id);
		stat.execute();
	}
	
	public boolean xoaAdmin(int id) throws SQLException {
		String query = "call delete_admin(?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, id);
		stat.execute();
		return false;		
	}
	public void getAdmintable(JTable tab, DefaultTableModel df) throws SQLException {			
		df = (DefaultTableModel) tab.getModel();		
		String query = "SELECT * FROM adminn;";
		PreparedStatement stat = conn.prepareStatement(query);
		ResultSet result = stat.executeQuery();		
		if(result.next()){
			do {
			int id = result.getInt(1);
			String userName = result.getString(2);
			String password = result.getString(3);				
			df.addRow(new Object [] {id, userName, password} );			
			} while (result.next());
			}	
	}
	

}

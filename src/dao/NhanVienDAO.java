package dao;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.NhanVienDTO;
import hash_password.PBKDF2_Verify_Password;



public class NhanVienDAO {
	private Connection conn;
	//private ArrayList<NhanVienDTO> bookList;
	
	public NhanVienDAO(Connection conn) {
		this.conn = conn;
	}
	
	public int layIdNhanvienhoTen(String username) throws SQLException{
		int id = -1 ;
		String query = "SELECT maNV FROM nhahang.nhanvien WHERE hoTen = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, username);
		ResultSet result = stat.executeQuery();
		if (result.next()){
			//System.out.println(result.next());			 
			return  result.getInt(1);
		}
		return id;
	}
	
	
	public int layIdNhanvien(String username) throws SQLException{
		int id = -1 ;
		String query = "SELECT maNV FROM nhahang.nhanvien WHERE users = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, username);
		ResultSet result = stat.executeQuery();
		if (result.next()){			
			return result.getInt(1);
		}
		return id;
	}
	public String layPassNhanvien(String username) throws SQLException{
		String passwordd = "";
		String query = "SELECT pass FROM nhanvien WHERE users = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, username);
		ResultSet result = stat.executeQuery();
		if (result.next()){ 
			return result.getString(1);
		}
		return passwordd;
	}
	
	public NhanVienDTO getNhanvien(String username) throws SQLException{
		NhanVienDTO current_user = null;
		String query = "SELECT * FROM nhanvien WHERE users = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, username);
		ResultSet result = stat.executeQuery();
		if (result.next()){
			int id = result.getInt(1);
			String hoten = result.getString(2);
			LocalDate ngaysinh = LocalDate.parse(result.getString(3));
			String userName = result.getString(4);
			String password = result.getString(5);
			String quyen = result.getString(6);
			int luong = Integer.parseInt(result.getString(7));
			current_user = new NhanVienDTO(id,hoten,ngaysinh, userName, password,quyen,luong);
		}
		return current_user;	
	}
	
	public void getNhanVientable(JTable tab, DefaultTableModel df) throws SQLException {			
		df = (DefaultTableModel) tab.getModel();		
		String query = "SELECT * FROM nhanvien;";
		PreparedStatement stat = conn.prepareStatement(query);
		ResultSet result = stat.executeQuery();		
		if(result.next()){
			do {
			int id = result.getInt(1);
			String hoten = result.getString(2);
			LocalDate ngaysinh = LocalDate.parse(result.getString(3));
			String userName = result.getString(4);
			String password = result.getString(5);
			String quyen = result.getString(6);
			int luong = Integer.parseInt(result.getString(7));		
			df.addRow(new Object [] {id, hoten, ngaysinh, userName, password, quyen, luong} );			
			} while (result.next());
			}	
	}
	
	public boolean themNhanVien(NhanVienDTO u) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String hoten = u.getHoTen();
		String ngaysinh = u.getNgaySinh().toString();
		String username = u.getUser();
		String password = u.getPass();
		String hash_password;		
		String quyen = u.getQuyen();
		int luong = u.getLuong();		
		hash_password = PBKDF2_Verify_Password.generateStrongPasswordHash(password);
		String query = "call insert_nv(?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stat = conn.prepareStatement(query)){			
			stat.setString(1, hoten);
			stat.setString(2, ngaysinh);
			stat.setString(3, username);
			stat.setString(4, hash_password);
			stat.setString(5, quyen);
			stat.setInt(6, luong);			
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
	

	
	public void suaNhanvien(NhanVienDTO u) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		String hoTen = u.getHoTen();
		String ngaySinh = u.getNgaySinh().toString();
		String user = u.getUser();
		String pass = u.getPass();
		String quyen = u.getQuyen();
		int luong = u.getLuong();	
		int id = u.getMaNV();
		String hash_password;
		hash_password = PBKDF2_Verify_Password.generateStrongPasswordHash(pass);
		String query = "call update_nv(?, ?, ?, ?, ?, ?, ?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, hoTen);
		stat.setString(2, ngaySinh);
		stat.setString(3, user);
		stat.setString(4, hash_password);
		stat.setString(5, quyen);
		stat.setInt(6, luong);
		stat.setInt(7, id);
		stat.execute();
		JOptionPane.showMessageDialog(null,	"Sửa nhân viên thành công.");
	}
	
	public boolean xoaNhanvien(int maNV) throws SQLException {
		String query = "call delete_nv(?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, maNV);
		stat.execute();
		return false;		
	}
	
}


	


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
import dto.banDTO;


public class banDAO {
	private Connection conn;
	public banDAO(Connection conn){
		this.conn = conn;
	}
	
	public void getbanDSDtable(JTable tab, DefaultTableModel df) throws SQLException {			
		df = (DefaultTableModel) tab.getModel();		
		String query = "SELECT * FROM ban WHERE statuss = 'Đang sữ dụng';";
		PreparedStatement stat = conn.prepareStatement(query);
		ResultSet result = stat.executeQuery();		
		if(result.next()){
			do {
			int id = result.getInt(1);
			String status = result.getString(2);					
			df.addRow(new Object [] {id, status} );			
			} while (result.next());
		}	
	}
	
	public void getbanTrongtable(JTable tab, DefaultTableModel df) throws SQLException {			
		df = (DefaultTableModel) tab.getModel();		
		String query = "SELECT * FROM ban WHERE statuss = 'trống';";
		PreparedStatement stat = conn.prepareStatement(query);
		ResultSet result = stat.executeQuery();		
		if(result.next()){
			do {
			int id = result.getInt(1);
			String status = result.getString(2);					
			df.addRow(new Object [] {id, status} );			
			} while (result.next());
		}	
	}
	
//	public banDTO getban(String username) throws SQLException{
//		banDTO current_ban = null;
//		String query = "SELECT * FROM ban WHERE statuss = 'đầy';";
//		PreparedStatement stat = conn.prepareStatement(query);		
//		ResultSet result = stat.executeQuery();
//		if (result.next()){
//			int id = result.getInt(1);
//			String hoten = result.getString(2);
//			LocalDate ngaysinh = LocalDate.parse(result.getString(3));
//			String userName = result.getString(4);
//			String password = result.getString(5);
//			String quyen = result.getString(6);
//			int luong = Integer.parseInt(result.getString(7));
//			current_user = new NhanVienDTO(id,hoten,ngaysinh, userName, password,quyen,luong);
//		}
//		return current_user;	
//	}
	
	public boolean themBan(String u) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String tinhtrang = "trống";				
		String query = "call insert_ban(?)";
		try (PreparedStatement stat = conn.prepareStatement(query)){			
			stat.setString(1, tinhtrang);
			int p = stat.executeUpdate();
			if (p == 1){
				JOptionPane.showMessageDialog(null,"Thêm bàn thành công");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	public void suaBan(banDTO u) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		String tinhtrang = u.getStatus();	
		int idb = u.getId();
		String query = "call update_ban(?, ?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, tinhtrang);
		stat.setInt(2, idb);
		stat.execute();
	}
	
	public boolean xoaBan(int id) throws SQLException {
		String query = "call delete_ban(?)"; 
		PreparedStatement stat = conn.prepareStatement(query);	
		stat.setInt(1, id);
		int p = stat.executeUpdate();
		if (p == 1){
			JOptionPane.showMessageDialog(null,"Xoá bàn thành công");
			return true;
		}
		stat.execute();
		
		return false;		
	}
}

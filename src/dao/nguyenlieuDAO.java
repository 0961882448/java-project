package dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.nguyenlieuDTO;


public class nguyenlieuDAO {
	private Connection conn;
	public nguyenlieuDAO(Connection conn){
		this.conn = conn;
	}
	
	public Double giaNguyenLieu() throws SQLException {	
		
		 
		
		Double sum = 0.0 ;
		String query = "select gia, so_luong from nguyen_lieu;";
		PreparedStatement stat = conn.prepareStatement(query);		
		ResultSet result = stat.executeQuery();
		if(result.next()) {
			do {
				Double gia = result.getDouble(1);
				int sl = result.getInt(2);
				sum = sum + gia*sl;				
			} while (result.next());
		}
		return sum;
		
	}

	public boolean themNguyenLieu(nguyenlieuDTO u) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String tenNL = u.getTenNguyenLieu();
		Double gia = u.getGiaNguyenLieu();
		int sl = u.getSoluong();
		String ngay = u.getNgayNhap().toString();
				
		String query = "call insert_nguyenlieu(?, ?, ?, ?)";
		try (PreparedStatement stat = conn.prepareStatement(query)){			
			stat.setString(1,tenNL);
			stat.setDouble(2, gia);
			stat.setInt(3, sl);
			stat.setString(4, ngay);
			int p = stat.executeUpdate();
			if (p == 1){
				JOptionPane.showMessageDialog(null,"Thêm nguyên liệu thành công.");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	public void getNhanVientable(JTable tab, DefaultTableModel df) throws SQLException {			
		df = (DefaultTableModel) tab.getModel();		
		String query = "SELECT * FROM nguyen_lieu;";
		PreparedStatement stat = conn.prepareStatement(query);
		ResultSet result = stat.executeQuery();		
		if(result.next()){
			do {
			int id = result.getInt(1);
			String ten = result.getString(2);
			Double gia = result.getDouble(3);
			int soluong = result.getInt(4);
			String ngaynhap = result.getString(5);
			df.addRow(new Object [] {id, ten, gia, soluong,ngaynhap} );			
			} while (result.next());
			}	
	}
	
	public void suaNguyenLieu(nguyenlieuDTO u) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		int id = u.getId();
		String Ten = u.getTenNguyenLieu();
		double gia = u.getGiaNguyenLieu();
		int sl = u.getSoluong();
		String ngayNhap = u.getNgayNhap().toString();		
		String query = "call update_nguyenlieu(?, ?, ?, ?, ?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, id);
		stat.setString(2, Ten);
		stat.setDouble(3, gia);
		stat.setInt(4, sl);
		stat.setString(5, ngayNhap);		
		stat.execute();
	}
	
	public boolean xoaNguyenLieu(int id) throws SQLException {
		String query = "call delete_nguyenlieu(?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, id);
		stat.execute();
		return false;		
	}
	
	
}

package dao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.ordersDTO;
import utilities.DBConnection;

public class ordersDAO {
	private Connection conn;
	ArrayList<String> menuList;
	public ordersDAO(Connection conn){
		this.conn = conn;
	}
	
	public ordersDTO getOrdersID(int idor) throws SQLException{
		ordersDTO current_orders = null;
		String query = "SELECT * FROM orders WHERE id = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, idor);
		ResultSet result = stat.executeQuery();
		if (result.next()){
			int id = result.getInt(1);
			int idnv = result.getInt(2);
			LocalDate ngayOrders = LocalDate.parse(result.getString(3)); 
			Time timeOrders = Time.valueOf(result.getString(4)) ;
			Double price = result.getDouble(5);
			current_orders = new ordersDTO(id, idnv, ngayOrders,timeOrders,price);
		}
		return current_orders;	
	}
	
	
	public Double getGiaOrder(int u) throws SQLException {
		Double gia = null;
		String query = "SELECT price_order FROM nhahang.orders where  id = ?;";		
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, u);
		ResultSet result = stat.executeQuery();
		if (result.next()){ 
			return result.getDouble(1);
		}		
		return gia;
	}
	
	public String getTenNvID(int u) throws SQLException {
		String ten = "";
		String query = "select hoTen from nhanvien where maNV = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, u);
		ResultSet result = stat.executeQuery();
		if (result.next()){ 
			return result.getString(1);
		}
		return ten;
		
	}
	
	public void getOrderngaytable(JTable tab, DefaultTableModel df,String u) throws SQLException {			
		df = (DefaultTableModel) tab.getModel();		
		String query = "SELECT * FROM orders where ngay_order = ?;";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, u);
		ResultSet result = stat.executeQuery();		
		if(result.next()){
			do {
			int id = result.getInt(1);
			int idnv = result.getInt(2);
			String ten = getTenNvID(idnv);
			LocalDate ngay = LocalDate.parse(result.getString(3));
			String time = result.getString(4) ;
			Double gia = result.getDouble(5);	
			df.addRow(new Object [] {id, ten, ngay, time, gia} );			
			} while (result.next());
			}	
	}
	
	public void getOrdertable(JTable tab, DefaultTableModel df) throws SQLException {			
		df = (DefaultTableModel) tab.getModel();		
		String query = "SELECT * FROM orders;";
		PreparedStatement stat = conn.prepareStatement(query);
		ResultSet result = stat.executeQuery();		
		if(result.next()){
			do {
			int id = result.getInt(1);
			int idnv = result.getInt(2);
			String ten = getTenNvID(idnv);
			LocalDate ngay = LocalDate.parse(result.getString(3));
			String time = result.getString(4) ;
			Double gia = result.getDouble(5);	
			df.addRow(new Object [] {id, ten, ngay, time, gia} );			
			} while (result.next());
			}	
	}
	
	public boolean themOrders(ordersDTO u) throws NoSuchAlgorithmException, InvalidKeySpecException {
		int idnv = u.getIdnv();
		String ngay = u.getNgayOrDate().toString();
		String gio = u.getTime();
		Double gia = u.getPriceOrder();
		String query = "call insert_orders(?, ?, ?, ?)";
		try (PreparedStatement stat = conn.prepareStatement(query)){			
			stat.setInt(1, idnv);
			stat.setString(2, ngay);
			stat.setString(3, gio);
			stat.setDouble(4, gia);
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
	
//	public int getIdOrder(Integer hish) throws SQLException {		
//		String query = "call call new_orders(?)";
//		PreparedStatement stat = conn.prepareStatement(query);
//		stat.setInt(1, hish);
//		ResultSet result = stat.executeQuery();
//		if (result.next()){
//			hish = result.getInt(1);
//		}			
//		return hish;		
//	}
	
	public void suaOrders(ordersDTO u) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		int idnv = u.getIdnv();
		String ngay = u.getNgayOrDate().toString();	
		String gio = u.getTimeOrder().toString();
		Double gia = u.getPriceOrder();
		String query = "call update_orders(?, ?, ?, ?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, idnv);
		stat.setString(2, ngay);
		stat.setString(3, gio);
		stat.setDouble(4, gia);
		
		stat.execute();
	}
	
	public boolean xoaOrders(int id) throws SQLException {
		String query = "call delete_orders(?)"; 
		PreparedStatement stat = conn.prepareStatement(query);		
		stat.setInt(1, id);
		stat.execute();
		return false;		
	}
	

	public ArrayList<String> getHish() throws ClassNotFoundException, IOException, SQLException{
		
		DBConnection.init("database.properties");	
		Connection conn = DBConnection.getConnection();
		String query = "Select ten_mon from menu";
		PreparedStatement stat = conn.prepareStatement(query);		
		ResultSet result = stat.executeQuery();		
		boolean check = false;
		if(result.next()) {
			check = true;
			menuList = new ArrayList<String>();			
			do {
				String hish = result.getString(1);				
				menuList.add(hish);				
			}while (result.next());
		}
		return menuList;		
	}
}

package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dto.order_itemDTO;

public class order_itemDAO {
	private Connection conn;
	public order_itemDAO(Connection conn) {
		this.conn = conn;
	}
	
	public order_itemDTO getOrderItem_IdOrders(int idOr) throws SQLException{
		order_itemDTO current_order_item = null;
		String query = "SELECT * FROM order_item WHERE id_order = ?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, idOr);
		ResultSet result = stat.executeQuery();
		if (result.next()){
			int id = result.getInt(1);
			int idOrder = result.getInt(2);
			int idmenu = result.getInt(3);
			int sl = result.getInt(4);
			Double discount = result.getDouble(5);
			int idban = result.getInt(6);
			current_order_item = new order_itemDTO(id, idOrder, idmenu, sl, discount, idban);
		}
		return current_order_item;	
	}
	
	public boolean themItem(order_itemDTO u) throws SQLException {
		int idor = u.getIdOreder();
		int idmenu = u.getIdMenu();
		int sl = u.getSoLuong();
		Double giamgia = u.getDiscount();
		int idb = u.getIdb();
		
		
		try {
			CallableStatement stat = conn.prepareCall("call insert_item(?, ?, ?, ?, ?)");
			stat.setInt(1, idor);
			stat.setInt(2, idmenu);
			stat.setInt(3, sl);
			stat.setDouble(4, giamgia);
			stat.setInt(5, idb);
			stat.executeUpdate();
			
			//stat.execute();
			//stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	public void suaItem(order_itemDTO u) throws SQLException {
		int idor = u.getIdOreder();
		int idmenu = u.getIdMenu();
		int sl = u.getSoLuong();
		Double giamgia = u.getDiscount();
		int idb = u.getIdb();
		String query = "call update_item(?, ?, ?, ?, ?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, idor);
		stat.setInt(2, idmenu);
		stat.setInt(3, sl);
		stat.setDouble(4, giamgia);
		stat.setInt(5, idb);
		stat.execute();
	}
	
	public boolean xoaItem(int id) throws SQLException {
		String query = "call delete_item(?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, id);
		stat.execute();
		return false;
	}
}

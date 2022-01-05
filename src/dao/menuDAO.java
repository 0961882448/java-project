package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.menuDTO;

public class menuDAO {
	private Connection conn;
	ArrayList<String> menulist;
	public menuDAO(Connection conn) {
		this.conn = conn;
	}
	
	public boolean themmenu(menuDTO u) throws SQLException {
		String tenMon = u.getTenMon();
		Double gia = u.getGia();
		CallableStatement stat = conn.prepareCall("call insert_menu(?, ?)");
		stat.setString(1, tenMon);
		stat.setDouble(2, gia);
		stat.execute();
		return false;		
	}
	
	public void getmenutable(JTable tab, DefaultTableModel df) throws SQLException {			
		df = (DefaultTableModel) tab.getModel();		
		String query = "SELECT * FROM menu;";
		PreparedStatement stat = conn.prepareStatement(query);
		ResultSet result = stat.executeQuery();		
		if(result.next()){
			do {
			int id = result.getInt(1);
			String ten = result.getString(2);			
			Double gia = result.getDouble(3);
			df.addRow(new Object [] {id, ten, gia} );			
			} while (result.next());
			}	
	}
	
	public void suaMenu(menuDTO u) throws SQLException {
		String tenMon = u.getTenMon();
		Double gia = u.getGia();
		int id = u.getId();
		String query = "call update_menu(?, ?, ?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, tenMon);
		stat.setDouble(2, gia);
		stat.setInt(3, id);
		stat.execute();
	}
	
	public boolean xoaMenu(int id) throws SQLException {
		String query = "call delete_menu(?)"; 
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, id);
		stat.execute();
		return false;
	}	
	public Double getPriceMenu(String u) throws SQLException {
		Double gia = null;
		String query = "SELECT gia FROM menu where ten_mon = ?";		
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, u);
		ResultSet result = stat.executeQuery();
		if (result.next()){ 
			return result.getDouble(1);
		}
		
		return gia;
		
	}
	
	public String getMenuid(int u) throws SQLException {
		String tenmon = "";
		String query = "SELECT ten_mon FROM nhahang.menu where  id = ? ;";		
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setInt(1, u);
		ResultSet result = stat.executeQuery();
		if (result.next()){ 
			return result.getString(1);
		}
		
		return tenmon;
	}
	

}

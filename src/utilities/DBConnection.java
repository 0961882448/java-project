package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static String url;
	private static String username;
	private static String password;
	
	public static void init(String fileName) throws IOException, ClassNotFoundException{
		Properties props = new Properties();
		FileInputStream in = new FileInputStream(fileName);
		props.load(in);
		
		url = props.getProperty("jdbc.url");
		username = props.getProperty("jdbc.username");
		password = props.getProperty("jdbc.password");
		
		String driver = props.getProperty("jdbc.driver");
		if (driver != null){
			Class.forName(driver);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);	
	}
}
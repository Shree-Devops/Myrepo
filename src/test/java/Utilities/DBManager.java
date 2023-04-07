package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
	
	private static Connection connection = null;
	
	public static void setDBConnection() throws SQLException {
		
		String mySQLDriver = "com.mysql.jdbc.Driver";
		String mySQLConnectionURL = "jdbc:mysql://localhost:3306/13thjune2020";
		String mySQLUserName = "root";
		String mySQLpassword = "9424372324@Sh";
		
		try {
			   Class.forName(mySQLDriver);
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
			}
		connection = DriverManager.getConnection(mySQLConnectionURL, mySQLUserName, mySQLpassword);
		if (!connection.isClosed()) {
			System.out.println("Successfully connected to MySQL Server");
		}
	}
	
	public static List<String> getMySQLQuery(String query) throws SQLException {
		Statement stmt =  connection.createStatement();
		ResultSet result = stmt.executeQuery(query);
		List<String> values = new ArrayList<String>();
		
		while(result.next()) {
			values.add(result.getString(1));
		}
		return values;
	}
	
}

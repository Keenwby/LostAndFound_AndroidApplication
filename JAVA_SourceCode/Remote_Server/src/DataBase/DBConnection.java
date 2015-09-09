package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static final String JDBC = "com.mysql.jdbc.Driver";
	static final String LHURL = "jdbc:mysql://localhost:3306/";
	public static final String URL = LHURL + "LostFound";
	static final String USER = "root";
	static final String PASSWORD = "";
	
	//Open a Connection from local host
	public Connection getLHConnection(String host) throws SQLException{
		Connection conn = DriverManager.getConnection(host, USER, PASSWORD);
		return conn!=null ? conn : null;
	}
	//Get a Connection of the LostandFoundDB database
	public Connection getConnection(String db) throws SQLException{
		return getLHConnection(db);
	}
	//Close Connection
	public void closeConnection(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
		}
		else
			return;
	}
	//Close Statement
	public void closeStat(Statement st) throws SQLException{
		st.close();
	}
}

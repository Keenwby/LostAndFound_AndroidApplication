package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUpdate extends DBConnection{

	private Connection conn = null;
	private Statement st = null;
	
	public void updateUser(String usrName, String newPW, String newContInfo, String newIP){
		
		String pw = null;
		String cont = null;
		String ip = null;
		
		OpenDB();
		try{
			ResultSet res = st.executeQuery("SELECT * FROM USER WHERE UserName = '"+usrName+"';");
			while(res.next()){
				pw = res.getString("Password");
				cont = res.getString("Contact");
				ip = res.getString("User_IP");
			}
			System.out.println("Success!");
			if(!newPW.equals(pw)){
				st.executeUpdate("UPDATE USER SET Password = "+newPW+" WHERE UserName = '"+usrName+"';");
			}
			System.out.println("Success!");
			if(!newContInfo.equals(cont)){
				st.executeUpdate("UPDATE USER SET Contact = "+newContInfo+" WHERE UserName = '"+usrName+"';");
			}
			System.out.println("Success!");
			if(!newIP.equals(ip)){
				st.executeUpdate("UPDATE USER SET User_IP = "+newIP+" WHERE UserName = '"+usrName+"';");
			}
			System.out.println("Success!");
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
	}
	public void OpenDB(){
		try {
			conn = getConnection(URL);
			st = conn.createStatement();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeDB(){
		try {
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

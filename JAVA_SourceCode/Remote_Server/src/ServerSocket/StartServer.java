package ServerSocket;

import java.net.InetAddress;

import java.net.UnknownHostException;
import java.sql.SQLException;
import DataBase.CreateTable;


public class StartServer implements SocketClientConstants{

	public static void main(String[] args){
		String strLocalHost = "";
		  try{
		      strLocalHost = InetAddress.getLocalHost().toString();
		  }
		 catch (UnknownHostException e){
		      System.err.println ("Unable to find local host");
		 }
		 //Create a table 
		 CreateTable ct = new CreateTable();
			try {
				ct.createAutoTable();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			DefaultSocketServer d = new DefaultSocketServer(strLocalHost, IPORT);
			d.start();
		}
}

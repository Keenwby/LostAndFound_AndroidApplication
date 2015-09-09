package Adapter;

import android.content.Context;
import android.database.Cursor;
import DataBase.EditUserTable;
import Entities.User;

public abstract class proxyUserEditor {

	EditUserTable editusrtb;
	
	public void updateUser(String name, String newpw, String newInfo, Context context) {
		// TODO Auto-generated method stub
		editusrtb = new EditUserTable(context);
		editusrtb.updateRecord(name, newpw, newInfo);
	}

	public User createUser(String name, String pw, String contact, String ip) {
		// TODO Auto-generated method stub
		return new User(name, pw, contact, ip);
	}
	
	public boolean checkusername() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkpassword() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//EditUserDB	
	public void saveUsertoDataBase(User user, Context context){
		editusrtb = new EditUserTable(context);
		editusrtb.insertRecord(user.getUsrname(), user.getPassword(), user.getCont(), user.getIP());
	}
		
	public Cursor getUserfromDB(String name, Context context){
		editusrtb = new EditUserTable(context);
		editusrtb.open();
		return editusrtb.getOneRecord(name);
	}
	
	public void closeDB(){
		editusrtb.close();
	}
}

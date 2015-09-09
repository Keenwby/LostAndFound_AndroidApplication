package Adapter.User;

import Entities.User;
import android.content.Context;
import android.database.Cursor;

public interface EditUserDB {
	
	public void saveUsertoDataBase(User user, Context context);
	
	public Cursor getUserfromDB(String catname, Context context);
	
	public void updateUser(String name, String newpw, String newInfo, Context context);
	
	public void closeDB();
}

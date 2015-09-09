package Adapter.Category;

import android.content.Context;
import android.database.Cursor;
import Entities.Category;

public interface EditCategoryDB {

	public void saveCattoDB(Category category, String location, String time, Context context, String usrtype);
	
	public Cursor getHistoryfromDB(String usrtype, Context context);
	
	public void closeDB();
	
}

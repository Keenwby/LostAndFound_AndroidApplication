package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class EditCategoryTable extends CreateCategoryTable{

	public EditCategoryTable(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// inserts a new record of student score in the database
    public void insertRecord(String catname, int value, String loc, String time,
    						 byte[] image1, byte[] image2, String q1, String q2, String q3, String usrtype) {
        ContentValues newRecord = new ContentValues();
        newRecord.put(KEY_CATEGORY, catname);
        newRecord.put(KEY_VALUE, value);
        newRecord.put(KEY_LOCATION, loc);
        newRecord.put(KEY_TIME, time);
        newRecord.put(KEY_IMAGE1, image1);
        newRecord.put(KEY_IMAGE2, image2);
        newRecord.put(KEY_QUESTION1, q1);
        newRecord.put(KEY_QUESTION2, q2);
        newRecord.put(KEY_QUESTION3, q3);
        newRecord.put(KEY_USRTYPE, usrtype);
        
        open(); // open the database
        database.insert(TABLE_NAME, null, newRecord);
        close(); // close the database
    } // end method insertContact

    // inserts a new record in the database
    public void updateRecord(String catname, int value, String loc, String time,
			 byte[] image1, byte[] image2, String q1, String q2, String q3, String usrtype) {
        ContentValues editRecord = new ContentValues();
        editRecord.put(KEY_CATEGORY, catname);
        editRecord.put(KEY_VALUE, value);
        editRecord.put(KEY_LOCATION, loc);
        editRecord.put(KEY_TIME, time);
        editRecord.put(KEY_IMAGE1, image1);
        editRecord.put(KEY_IMAGE2, image2);
        editRecord.put(KEY_QUESTION1, q1);
        editRecord.put(KEY_QUESTION2, q2);
        editRecord.put(KEY_QUESTION3, q3);
        editRecord.put(KEY_USRTYPE, usrtype);
        
        /**********Need more modification****************/
        open(); // open the database
        database.update(TABLE_NAME, editRecord, KEY_LOCATION + "=?", new String[]{ loc });
        close(); // close the database
    } // end method updateRecords


   //Get location and time list
    public Cursor getLocandTimeofCategory(String usrtype) {
    	return database.query(TABLE_NAME, new String[] {KEY_CATEGORY, KEY_LOCATION,
        		KEY_TIME},
        		KEY_USRTYPE + "=?", new String[]{ usrtype }, null, null,
                null, null);
    }
    
    /**********Need more modification****************/
    // delete the contact specified by the given String name
    public void deleteRecord(String loc) {
        open(); // open the database
        database.delete(TABLE_NAME, KEY_LOCATION + "=?", new String[]{ loc });
        close(); // close the database
    } // end method deleteContact

}

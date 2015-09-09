package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class EditUserTable extends CreateUserTable{

	public EditUserTable(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// inserts a new record of student score in the database
    public void insertRecord(String name, String pw, String cont, String ip) {
        ContentValues newRecord = new ContentValues();
        newRecord.put(KEY_NAME, name);
        newRecord.put(KEY_PW, pw);
        newRecord.put(KEY_CONT, cont);
        newRecord.put(KEY_IP, ip);
        open(); // open the database
        database.insert(TABLE_NAME, null, newRecord);
        close(); // close the database
    } // end method insertContact

    // inserts a new record in the database
    public void updateRecord(String name, String pw, String cont) {
        ContentValues editRecord = new ContentValues();
        editRecord.put(KEY_PW, pw);
        editRecord.put(KEY_CONT, cont);

        open(); // open the database
        database.update(TABLE_NAME, editRecord, KEY_NAME + "=?", new String[]{ name });
        close(); // close the database
    } // end method updateRecords

    // return a Cursor with all records information in the database
    public Cursor getAllRecords() {
        return database.query(TABLE_NAME, new String[] { KEY_NAME, KEY_PW, KEY_CONT}, 
        		null, null, null, null, null, null);
    } // end method getAllRecords

    // get a Cursor containing all information about the contact specified
    // by the given id
    public Cursor getOneRecord(String name) {
    	return database.query(TABLE_NAME, new String[] { KEY_NAME,
    			KEY_PW, KEY_CONT}, KEY_NAME + "=?", new String[]{ name }, null, null,
                null, null);
    } // end method getOnContact

    // delete the contact specified by the given String name
    public void deleteRecord(String name) {
        open(); // open the database
        database.delete(TABLE_NAME, KEY_NAME + "=?", new String[]{ name });
        close(); // close the database
    } // end method deleteContact
}

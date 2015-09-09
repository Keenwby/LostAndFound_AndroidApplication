package DataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class CreateUserTable {

    // database name
    protected static final String DATABASE_NAME = "USDB_NEW";
    protected SQLiteDatabase database; // database object
    protected DatabaseOpenHelper databaseOpenHelper; // database helper
    
    //table name
    protected static final String TABLE_NAME = "USTB_NEW";
    //column names
    protected static final String KEY_ID = "id";
    protected static final String KEY_NAME = "username";
    protected static final String KEY_PW = "password";
    protected static final String KEY_CONT = "contact";
    protected static final String KEY_IP = "ip";
      
    // public constructor for DatabaseConnector
    public CreateUserTable(Context context) {
        // create a new DatabaseOpenHelper
        databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME,
                null, 1);
    } // end DatabaseConnector constructor

    // open the database connection
    public void open() throws SQLException {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close() {
        if (database != null)
            database.close(); // close the database connection
    } // end method close
    
    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                CursorFactory factory, int version) {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the records table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            // query to create a new table named contacts
            String  createQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  KEY_NAME + " TEXT, " + KEY_PW + " TEXT, "
                + KEY_CONT + " TEXT, " + KEY_IP + " TEXT" + ")";
            db.execSQL(createQuery); // execute the query
        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        	// Drop older table if existed
        	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    		// Create tables again
    		onCreate(db);
        } // end method onUpgrade
    } // end class DatabaseOpenHelper

}

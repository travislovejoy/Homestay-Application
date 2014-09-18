// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package ca.demo.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";
	
	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	public static final String KEY_NAME = "username";
	public static final String KEY_PASSWORD= "password";
	public static final String KEY_Pets = "Pets";
	public static final String KEY_Clean = "Clean";
	public static final String KEY_REALNAME = "realname";
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_PHONE = "Phone";
	//public static final String COLUMN_PHOTO = "Photo";
	public static final String KEY_AGE = "Age";
	public static final String KEY_SMOKING = "Smoking";
	public static final String KEY_BEGINMONTH = "BeginMonth";
	public static final String KEY_BEGINDAY = "BeginDay";
	public static final String KEY_BEGINYEAR = "BeginYear";
	public static final String KEY_ENDMONTH = "EndMonth";
	public static final String KEY_ENDDAY = "EndDay";
	public static final String KEY_ENDYEAR = "EndYear";
	public static final String KEY_TYPE = "HostOrStudent";
	public static final String KEY_DIST = "Dist";
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_NAME = 1;
	public static final int COL_PASSWORD = 2;
	public static final int COL_Pets = 3;
	public static final int COL_Clean = 4;
	public static final int COL_realname = 5;
	public static final int COL_address = 6;
	public static final int COL_email = 7;
	public static final int COL_Phone = 8;
	public static final int COL_Age = 9;
	public static final int COL_Type = 10;
	public static final int COL_Smoking = 11;
	public static final int COL_BeginMonth = 12;
	public static final int COL_BeginDay = 13;
	public static final int COL_BeginYear = 14;
	public static final int COL_EndMonth = 15;
	public static final int COL_EndDay = 16;
	public static final int COL_EndYear = 17;
	public static final int COL_Dist = 18;

	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_Pets, KEY_Clean, KEY_REALNAME,
		KEY_ADDRESS, KEY_EMAIL, KEY_PHONE, KEY_AGE, KEY_TYPE, KEY_SMOKING, KEY_BEGINMONTH, KEY_BEGINDAY, KEY_BEGINYEAR, KEY_ENDMONTH,
		KEY_ENDDAY, KEY_ENDYEAR, KEY_DIST};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "MyDb";
	public static final String DATABASE_TABLE = "mainTable";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 11;	
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_NAME + " text not null, "
			+ KEY_PASSWORD + " string not null, "
			+ KEY_Pets + " string not null, "
			+ KEY_Clean + " string not null, "
			+ KEY_REALNAME + " string not null, "
			+ KEY_ADDRESS + " string not null, "
			+ KEY_EMAIL + " string not null, "
			+ KEY_PHONE + " string not null, "
			+ KEY_AGE + " integer not null, "
			+ KEY_TYPE + " string not null, "
			+ KEY_SMOKING + " string not null, "
			+ KEY_BEGINMONTH + " string not null, "
			+ KEY_BEGINDAY + " string not null, "
			+ KEY_BEGINYEAR + " string not null, "
			+ KEY_ENDMONTH + " string not null, "
			+ KEY_ENDDAY + " string not null, "
			+ KEY_ENDYEAR + " string not null,"
			+ KEY_DIST + " integer not null"
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////
	
	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	// Add a new set of values to the database.
	public long insertRow(String name, String password, String Pets, String Clean, String RealName, String Address,
			String Email, String Phone, int Age, String Type, String Smoking, String Bmonth, String Bday, String Byear, String Emonth,
			String Eday, String Eyear, int Dist) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PASSWORD, password);
		initialValues.put(KEY_Pets, Pets);
		initialValues.put(KEY_Clean, Clean);
		initialValues.put(KEY_REALNAME, RealName);
		initialValues.put(KEY_ADDRESS, Address);
		initialValues.put(KEY_EMAIL, Email);
		initialValues.put(KEY_PHONE, Phone);
		initialValues.put(KEY_AGE, Age);
		initialValues.put(KEY_TYPE, Type);
		initialValues.put(KEY_SMOKING, Smoking);
		initialValues.put(KEY_BEGINMONTH, Bmonth);
		initialValues.put(KEY_BEGINDAY, Bday);
		initialValues.put(KEY_BEGINYEAR, Byear);
		initialValues.put(KEY_ENDMONTH, Emonth);
		initialValues.put(KEY_ENDDAY, Eday);
		initialValues.put(KEY_ENDYEAR, Eyear);
		initialValues.put(KEY_DIST, Dist);
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(String query) {
		String where = "_id = " + query;
		//String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String name, String password, String Pets, String Clean, String RealName, String Address,
			String Email, String Phone, int Age, String Type, String Smoking, String Bmonth, String Bday, String Byear, String Emonth,
			String Eday, String Eyear, int Dist) {
		String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_PASSWORD, password);
		newValues.put(KEY_Pets, Pets);
		newValues.put(KEY_Clean, Clean);
		newValues.put(KEY_REALNAME, RealName);
		newValues.put(KEY_ADDRESS, Address);
		newValues.put(KEY_EMAIL, Email);
		newValues.put(KEY_PHONE, Phone);
		newValues.put(KEY_AGE, Age);
		newValues.put(KEY_TYPE, Type);
		newValues.put(KEY_SMOKING, Smoking);
		newValues.put(KEY_BEGINMONTH, Bmonth);
		newValues.put(KEY_BEGINDAY, Bday);
		newValues.put(KEY_BEGINYEAR, Byear);
		newValues.put(KEY_ENDMONTH, Emonth);
		newValues.put(KEY_ENDDAY, Eday);
		newValues.put(KEY_ENDYEAR, Eyear);
		newValues.put(KEY_DIST, Dist);
		
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}
}

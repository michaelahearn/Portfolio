package com.example.michael.trailerviewer;

        import android.content.*;
        import android.database.*;
        import android.database.sqlite.*;
        import android.util.Log;

public class DBAdapter {
    public static final String KEY_FILENAME = "filename";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESC ="description";
    public static final String KEY_RATING ="rating";
    public static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "TrailerDB";
    private static final String DATABASE_TABLE = "trailers";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS trailers (filename VARCHAR, name VARCHAR, description VARCHAR, rating VARCHAR);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db)
        {
            try{
                db.execSQL(DATABASE_CREATE);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }//end method onCreate

        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {
            Log.w(TAG,"Upgrade database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS trailers");
            onCreate(db);
        }//end method onUpgrade
    }//end inner class dataBaseHelper

    //open the database
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //close the database
    public void close()
    {
        db.close();
        DBHelper.close();

    }

    //insert a contact into the database
    public long insertTrailer(String filename, String name, String description, String rating)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_FILENAME, filename);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_DESC, description);
        initialValues.put(KEY_RATING, rating);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //delete a particular contact
    public boolean deleteTrailer(String filename)
    {
        return db.delete(DATABASE_TABLE, KEY_FILENAME + "=" + "'" + filename + "'",null) >0;//0 used for bool return
    }

    public boolean deleteTrailers()
    {
        return db.delete(DATABASE_TABLE,null,null) >0;//0 used for bool return
    }

    public boolean deleteDatabase()
    {
        return context.deleteDatabase(DATABASE_NAME);
    }

    //retrieve all the contacts
    public Cursor getAllTrailers()
    {
        return db.query(DATABASE_TABLE,new String[]{KEY_FILENAME, KEY_NAME,
                KEY_DESC},null,null,null,null,null);
    }

    //retrieve a single contact
    public Cursor getTrailer(String filename) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_FILENAME, KEY_NAME, KEY_DESC, KEY_RATING}, KEY_FILENAME + "=" + "'" + filename + "'",null,null,null,null,null);
        if(mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //updates a Rating
    public boolean updateRating(String filename,String rating)
    {
        ContentValues cval = new ContentValues();
        cval.put(KEY_RATING, rating);
        return db.update(DATABASE_TABLE, cval, KEY_FILENAME + "=" + "'" + filename + "'",null) >0;
        //Old - return db.update(DATABASE_TABLE, cval, KEY_ROWID + "=" + rowId,null) >0;
    }

}//end class DBAdapter














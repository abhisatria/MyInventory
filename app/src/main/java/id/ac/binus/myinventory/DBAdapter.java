package id.ac.binus.myinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DBAdapter extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "itemDB.db";
    private static final String TABLE_NAME = "items";

    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_DESCRIPTION="description";
    private static final String COLUMN_QUANTITY="quantity";

    SQLiteDatabase db;

    public DBAdapter(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ITEMS_TABLE = "CREATE TABLE "+ TABLE_NAME
                +"("+COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_NAME+ " TEXT NOT NULL,"
                +COLUMN_DESCRIPTION+ " TEXT NOT NULL,"
                +COLUMN_QUANTITY+" TEXT NOT NULL)";
        db.execSQL(CREATE_ITEMS_TABLE);

    }

    public void insertItem(Item item)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,item.getName());
        contentValues.put(COLUMN_DESCRIPTION,item.getDescription());
        contentValues.put(COLUMN_QUANTITY,item.getQuantity());

        db.insert(TABLE_NAME,null,contentValues);
    }
    public Cursor getAllItems(){
        Cursor cursor = db.query(TABLE_NAME,new String[]{COLUMN_NAME,COLUMN_DESCRIPTION,COLUMN_QUANTITY},null,null,null,null,null);
        return cursor;
    }
    public boolean updateItems(long rowID,String newName,String newDescription,String newQuantity){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,newName);
        contentValues.put(COLUMN_DESCRIPTION,newDescription);
        contentValues.put(COLUMN_QUANTITY,newQuantity);
        return db.update(TABLE_NAME,contentValues,COLUMN_ID + "="+rowID,null)>0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onOpen() {
        super.onOpen(db);
        db = this.getWritableDatabase();
    }
}

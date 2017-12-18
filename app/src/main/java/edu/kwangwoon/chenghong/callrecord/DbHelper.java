package edu.kwangwoon.chenghong.callrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CHG on 2017/11/10.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "numberDb";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE = "create table " + DbContract.TABLE_NAME +
            " (id integer primary key autoincrement, " + DbContract.INCOMING_NUMBER + " text);";
    private static final String DROP_TABLE = "drop table if exixts " + DbContract.TABLE_NAME;

    /*public DbHelper(Context context,
                      String name,
                      SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }*/
    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DROP_TABLE);
        onCreate(database);

    }

    public void saveNumber(String number, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.INCOMING_NUMBER, number);
        /**
         *     public long insert(String table,
         *                        String nullColumnHack
         *                        ContentValues values) {
                throw new RuntimeException("Stub!");
         }
         */
        database.insert(DbContract.TABLE_NAME, null, contentValues);
    }

    public Cursor readNumber(SQLiteDatabase database) {
        String[] projection = {"id", DbContract.INCOMING_NUMBER};
        /**
         *     public Cursor query(String table, String[] columns,
         *                          String selection,
         *                          String[] selectionArgs,
         *                          String groupBy,
         *                          String having,
         *                          String orderBy) {
                throw new RuntimeException("Stub!");
         }
         */
        return (database.query(DbContract.TABLE_NAME, projection, null, null, null, null, null));
    }
}

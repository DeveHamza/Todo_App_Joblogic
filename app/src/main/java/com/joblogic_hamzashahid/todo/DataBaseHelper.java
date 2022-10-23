package com.joblogic_hamzashahid.todo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Sell_Items = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRI = "price";
    private static final String KEY_QTY = "quantity";
    public DataBaseHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Sell_Items + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_PRI + " INTEGER,"
                + KEY_QTY + " INTEGER"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Sell_Items);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new Item Details
    void insertItemDetails(String name, String price, String quantity){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_PRI, price);
        cValues.put(KEY_QTY, quantity);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Sell_Items,null, cValues);
        db.close();
    }
    // Get Item Details
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> itemList = new ArrayList<>();
        String query = "SELECT name, price, quantity FROM "+ TABLE_Sell_Items;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> item = new HashMap<>();
            item.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            item.put("quantity",cursor.getString(cursor.getColumnIndex(KEY_QTY)));
            item.put("price",cursor.getString(cursor.getColumnIndex(KEY_PRI)));
            itemList.add(item);
        }
        return  itemList;
    }
    // Get Item Details based on itemid
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetItemByItemrId(int itemid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> itemList = new ArrayList<>();
        String query = "SELECT name, price, quantity FROM "+ TABLE_Sell_Items;
        Cursor cursor = db.query(TABLE_Sell_Items, new String[]{KEY_NAME, KEY_PRI, KEY_QTY}, KEY_ID+ "=?",new String[]{String.valueOf(itemid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> item = new HashMap<>();
            item.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            item.put("quantity",cursor.getString(cursor.getColumnIndex(KEY_QTY)));
            item.put("price",cursor.getString(cursor.getColumnIndex(KEY_PRI)));
            itemList.add(item);
        }
        return  itemList;
    }
    // Delete Item Details
    public void DeleteItem(int itemid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Sell_Items, KEY_ID+" = ?",new String[]{String.valueOf(itemid)});
        db.close();
    }
    // Update Item Details
    public int UpdateItemDetails(String price, String quantity, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_PRI, price);
        cVals.put(KEY_QTY, quantity);
        int count = db.update(TABLE_Sell_Items, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}
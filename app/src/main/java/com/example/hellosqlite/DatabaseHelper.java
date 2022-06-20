package com.example.hellosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HelloSQLite";
    public static final String COLUMN_ID = "id";

    public static final String TABLE_USERS = "UserInfo";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_DATE_OF_BIRTH = "dateOfBirth";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("LOG_TAG", "--- onCreate database ---");

        db.execSQL("create table " + TABLE_USERS + " ("
                + COLUMN_ID + " integer primary key autoincrement,"
                + COLUMN_NAME + " text,"
                + COLUMN_PHONE + " text,"
                + COLUMN_DATE_OF_BIRTH + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean insertUser (User user) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME, user.getName());
            contentValues.put(COLUMN_PHONE, user.getPhone());
            contentValues.put(COLUMN_DATE_OF_BIRTH, user.getDate_of_birth());
            if(user.getId() == 0){
                db.insert(TABLE_USERS, null, contentValues);
            }else{
                db.update(TABLE_USERS,contentValues, "id = ?",
                        new String[] {String.valueOf(user.getId())});
            }
            return true;
        }catch (Exception e){

        }
       return false;
    }

    public ArrayList<User> getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                null, null, null, null, null, null);
        ArrayList<User> subs = new ArrayList<>();
        User user;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setPhone(cursor.getString(2));
                user.setDate_of_birth(cursor.getString(3));
                subs.add(user);
            }
        }
        cursor.close();
        db.close();
        return subs;
    }

    public boolean deleteUser(int id){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_ID + " = " + id + ";");
            return true;
        }catch (Exception e){

        }
        return false;
    }
}

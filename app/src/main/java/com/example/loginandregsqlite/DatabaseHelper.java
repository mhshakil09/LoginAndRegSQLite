package com.example.loginandregsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "database";
    static int version = 1;

    String createUserInformationTable = "CREATE TABLE if not exists \"user_information\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\t\"dob\"\tTEXT,\n" +
            "\t\"country\"\tTEXT\n" +
            ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);

        getWritableDatabase().execSQL(createUserInformationTable);
    }


    // to insert data into the database
    public void insertUser(ContentValues contentValues){
        getWritableDatabase().insert( "user_information", "",contentValues);
    }


    // checking whether the username and password matched or not
    public boolean isLoginValid(String username, String password){
        String sql = "Select count(*) from user_information where username='" + username + "' and password='" + password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);

        long l = statement.simpleQueryForLong();
        statement.close();

        if (l == 1){
            return true;
        }
        else {
            return false;
        }

    }

    // updating the password
    public boolean isNewPass(String username, String newPass){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", newPass);

        db.update("user_information", values , "username = ?",  new String[]{username});

        return true;

    }

    // checking whether the username is available or not
    public boolean isUsernameAvailable(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "Select username from user_information where username='" + username + "'";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() <= 0 ){
            return false;
        }
        else {
            return true;
        }

    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

package com.numbrcase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.numbrcase.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "numbrcase.db";

    public static final String CONTACTS_TABLE_NAME   = "contact";

    public static final String CONTACTS_COLUMN_ID    = "id";
    public static final String CONTACTS_COLUMN_NAME  = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_PHONE = "phone";


    public ContactDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contact  (" +
        "id            INTEGER PRIMARY KEY," +
        "name          TEXT,               " +
        "phone         TEXT,               " +
        "email         TEXT,               " +
        "request_place TEXT,               " +
        "status        INTEGER            )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }


    public Cursor getData(int id){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM contact WHERE id =" + id + ";", null);
    }


    public boolean insertContact (Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name"         , contact.getName());
        contentValues.put("phone"        , contact.getPhone());
        contentValues.put("email"        , contact.getEmail());
        contentValues.put("request_place", contact.getRequestPlace());
        contentValues.put("status"       , contact.getStatus());

        db.insert("contact", null, contentValues);
        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
    }

    public boolean updateContact (Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name"         , contact.getName());
        contentValues.put("phone"        , contact.getPhone());
        contentValues.put("email"        , contact.getEmail());
        contentValues.put("request_place", contact.getRequestPlace());
        contentValues.put("status"       , contact.getStatus());

        db.update("contact", contentValues, "id = ? ", new String[] { Integer.toString(contact.getID()) } );
        return true;
    }


    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contact", "id = ? ", new String[] { Integer.toString(id) });
    }


    public List<String> getAllCotacts() {

//        List<String> array_list = new ArrayList<>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor res = db.rawQuery( "select * from contact", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//            res.moveToNext();
//        }
//
//        return array_list;
        return  null;
    }

}

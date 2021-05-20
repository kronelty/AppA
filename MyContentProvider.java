package com.example.appa;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

    private Uri contentUri = Uri.parse("com.example.appa.MyContentProvider");
    private SQLiteDatabase db;

    @Override
    public boolean onCreate(){
        Context context = getContext();
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        db = mySQLiteOpenHelper.getWritableDatabase();
        return db != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor c = db.query("A_Table", null, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri muri = null;
        long id = db.insert("A_Table", "name", values);
        if(id > 0){
            muri = ContentUris.withAppendedId(contentUri, id);
            getContext().getContentResolver().notifyChange(muri, null);
        }else{

        }
        return muri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int id = db.delete("A_Table", selection, selectionArgs);
        if(id > 0){
            Uri muri = ContentUris.withAppendedId(contentUri, id);
            getContext().getContentResolver().notifyChange(muri, null);
        }else{

        }
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int id = db.update("A_Table", values, selection, selectionArgs);
        if(id>0){
            Uri muri = ContentUris.withAppendedId(contentUri, id);
            getContext().getContentResolver().notifyChange(muri, null);
        }else{

        }
        return id;
    }
}

package com.example.appa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter {
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    protected CursorAdapter Adapter(Context context){

        sqLiteOpenHelper = new MySQLiteOpenHelper(context);
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();

        List<String> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from A_Table", null);
        while(cursor .moveToNext()){
            list.add(cursor .getString(cursor .getColumnIndex("name")));
        }

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(context, android.R.layout.simple_expandable_list_item_2, cursor, new String[]{"_id", "name"},
                new int[]{android.R.id.text1, android.R.id.text2});

        return cursorAdapter;
    }
}
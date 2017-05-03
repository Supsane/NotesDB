package com.chashurin.notesdb.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.chashurin.notesdb.database.NotesDBSchema.NotesTable;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class NotesBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "notesBase.db";

    public NotesBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NotesTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                NotesTable.Cols.UUID + " integer, " +
                NotesTable.Cols.TITLE_NOTES + " text, " +
                NotesTable.Cols.TEXT_NOTES + " text, " +
                NotesTable.Cols.DATE +
                " text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.chashurin.notesdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chashurin.notesdb.database.NotesCursorWrapper;
import com.chashurin.notesdb.database.NotesDBSchema.NotesTable;
import com.chashurin.notesdb.database.NotesBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Чашурин on 03.05.2017.
 */

class NotesListArray {

    private static NotesListArray sNotesListArray;
    private Context mContext;
    private SQLiteDatabase mDataBase;

    static NotesListArray get(Context context) {
        if (sNotesListArray == null) {
            sNotesListArray = new NotesListArray(context);
        }
        return sNotesListArray;
    }

    private NotesListArray(Context context) {
        mContext = context.getApplicationContext();
        mDataBase = new NotesBaseHelper(mContext).getWritableDatabase();
    }

    void addNotes(Notes notes) {
        ContentValues values = getContentValues(notes);

        mDataBase.insert(NotesTable.NAME, null, values);
    }

    public List<Notes> getmNotes() {
        List<Notes> notes = new ArrayList<>();

        NotesCursorWrapper cursor = queryNotes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                notes.add(cursor.getNotes());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return notes;
    }

    public Notes getNotes(UUID id) {

        NotesCursorWrapper cursor = queryNotes(NotesTable.Cols.UUID + " = ?", new String[] {id.toString()});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getNotes();
        } finally {
            cursor.close();
        }
    }

    public void updateNotes(Notes notes) {
        String uuidString = notes.getmId().toString();
        ContentValues values = getContentValues(notes);

        mDataBase.update(NotesTable.NAME, values, NotesTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private static ContentValues getContentValues(Notes notes) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.Cols.UUID, notes.getmId().toString());
        values.put(NotesTable.Cols.TITLE_NOTES, notes.getmTitle());
        values.put(NotesTable.Cols.TEXT_NOTES, notes.getmText());
        values.put(NotesTable.Cols.DATE, notes.getmDate().toString());

        return values;
    }

    private NotesCursorWrapper queryNotes (String whereClause, String[] whereArgs) {
        Cursor cursor = mDataBase.query(NotesTable.NAME, null, whereClause, whereArgs, null, null, null);

        return new NotesCursorWrapper(cursor);
    }
}

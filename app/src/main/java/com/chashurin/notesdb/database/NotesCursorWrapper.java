package com.chashurin.notesdb.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.chashurin.notesdb.Notes;
import com.chashurin.notesdb.database.NotesDBSchema.NotesTable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Chashurin Evgeny on 03.05.2017.
 */

public class NotesCursorWrapper extends CursorWrapper {

    public NotesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Notes getNotes() {
        String uuidString = getString(getColumnIndex(NotesTable.Cols.UUID));
        String titlleNotes = getString(getColumnIndex(NotesTable.Cols.TITLE_NOTES));
        long date = getLong(getColumnIndex(NotesTable.Cols.DATE));
        String textNotes = getString(getColumnIndex(NotesTable.Cols.TEXT_NOTES));

        Notes notes = new Notes(UUID.fromString(uuidString));
        notes.setmTitle(titlleNotes);
        notes.setmDate(new Date(date));
        notes.setmText(textNotes);

        return notes;
    }
}

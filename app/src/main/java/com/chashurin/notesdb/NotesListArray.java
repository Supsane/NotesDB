package com.chashurin.notesdb;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class NotesListArray {

    private static NotesListArray sNotesListArray;
    private List<Notes> mNotes;

    public static NotesListArray get(Context context) {
        if (sNotesListArray == null) {
            sNotesListArray = new NotesListArray(context);
        }
        return sNotesListArray;
    }

    private NotesListArray(Context context) {
        mNotes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Notes notes = new Notes();
            notes.setmTitle("Notes # " + i);
            mNotes.add(notes);
        }
    }

    public List<Notes> getmNotes() {
        return mNotes;
    }

    public Notes getNotes(UUID id) {
        for (Notes notes : mNotes) {
            if (notes.getmId().equals(id)) {
                return notes;
            }
        }
        return null;
    }
}

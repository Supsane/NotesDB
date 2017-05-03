package com.chashurin.notesdb;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Чашурин on 03.05.2017.
 */

class NotesListArray {

    private static NotesListArray sNotesListArray;
    private List<Notes> mNotes;

    static NotesListArray get(Context context) {
        if (sNotesListArray == null) {
            sNotesListArray = new NotesListArray(context);
        }
        return sNotesListArray;
    }

    private NotesListArray(Context context) {
        mNotes = new ArrayList<>();
    }

    void addNotes (Notes notes) {
        mNotes.add(notes);
    }

    List<Notes> getmNotes() {
        return mNotes;
    }

    Notes getNotes(UUID id) {
        for (Notes notes : mNotes) {
            if (notes.getmId().equals(id)) {
                return notes;
            }
        }
        return null;
    }
}

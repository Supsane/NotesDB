package com.chashurin.notesdb;

import java.util.Date;
import java.util.UUID;
import com.chashurin.notesdb.database.NotesCursorWrapper;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class Notes {

    private UUID mId;
    private String mTitle;
    private String mText;
    private Date mDate;

    public Notes() {
        this(UUID.randomUUID());
    }

    public Notes(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmText() {
        return mText;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}

package com.chashurin.notesdb;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Чашурин on 03.05.2017.
 */

class Notes {

    private UUID mId;
    private String mTitle;
    private Date mDate;

    Notes() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    UUID getmId() {
        return mId;
    }

    String getmTitle() {
        return mTitle;
    }

    void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    Date getmDate() {
        return mDate;
    }

    void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}

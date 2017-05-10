package com.chashurin.notesdb.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Чашурин on 10.05.2017.
 */

public class NotesProvider extends ContentProvider {

    final String LOG_TAG = "NotesProvider";

    static final String DATABASE_NAME = "notesBase.db";
    static final int VERSION = 1;

    static final String TABLE_NAME = "notes";

    static final String TITLE_NOTES = "title";
    static final String TEXT_NOTES = "text";
    static final String DATE = "date";
    static final String UUID = "uuid";

    static final String AUTHORITY = "com.chashurin.providers.notesdb";
    static final String NOTES_PATH = "notes";

    public static final Uri NOTES_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NOTES_PATH);

    static final String NOTES_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + NOTES_PATH;
    static final String NOTES_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + NOTES_PATH;

    static final int URI_NOTES = 1;
    static final int URI_NOTES_ID = 2;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, NOTES_PATH, URI_NOTES);
        uriMatcher.addURI(AUTHORITY, NOTES_PATH + "/#", URI_NOTES_ID);
    }

    NotesBaseHelper notesBaseHelper;

    @Override
    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        notesBaseHelper = new NotesBaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Log.d(LOG_TAG, "query, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_NOTES: {
                Log.d(LOG_TAG, "URI NOTES");
            }
        }


        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

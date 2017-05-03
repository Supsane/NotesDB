package com.chashurin.notesdb.database;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class NotesDBSchema {
    public static final class NotesTable {
        public static final String NAME = "notes";

        public static final class Cols {
            public static final String TITLE_NOTES = "title";
            public static final String TEXT_NOTES = "text";
            public static final String DATE = "date";
            public static final String UUID = "uuid";
        }
    }
}

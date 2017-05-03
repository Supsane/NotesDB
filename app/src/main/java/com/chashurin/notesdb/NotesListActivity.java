package com.chashurin.notesdb;

import android.support.v4.app.Fragment;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class NotesListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NotesListFragment();
    }
}

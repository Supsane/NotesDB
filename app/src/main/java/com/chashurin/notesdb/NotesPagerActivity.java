package com.chashurin.notesdb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class NotesPagerActivity extends AppCompatActivity {

    private static final String EXTRA_NOTES_ID = "notes_id";

    private ViewPager mViewPager;
    private List<Notes> mNotes;

    public static Intent newIntent(Context packageContext, UUID notesId) {
        Intent intent = new Intent(packageContext, NotesPagerActivity.class);
        intent.putExtra(EXTRA_NOTES_ID, notesId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_pager);

        UUID notesID = (UUID) getIntent().getSerializableExtra(EXTRA_NOTES_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_notes_pager);
        mNotes = NotesListArray.get(this).getmNotes();

        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Notes notes = mNotes.get(position);
                return NotesFragment.newInstance(notes.getmId());
            }

            @Override
            public int getCount() {
                return mNotes.size();
            }
        });

        for (int i = 0; i < mNotes.size(); i++) {
            if (mNotes.get(i).getmId().equals(notesID)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}

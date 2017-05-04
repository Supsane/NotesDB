package com.chashurin.notesdb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class NotesFragment extends Fragment {

    private static final String ARG_NOTES_ID = "notes_id";
    private Notes mNotes;
    private EditText mTitleNotes, mTextNotes;
    private Button mDateButton;
    final SimpleDateFormat format = new SimpleDateFormat("E, dd.MM.yyyy, HH:mm");

    public static NotesFragment newInstance(UUID notesid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTES_ID, notesid);

        NotesFragment notesFragment = new NotesFragment();
        notesFragment.setArguments(args);
        return notesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID notesId = (UUID) getArguments().getSerializable(ARG_NOTES_ID);
        mNotes = NotesListArray.get(getActivity()).getNotes(notesId);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        NotesListArray.get(getActivity()).updateNotes(mNotes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        mTitleNotes = (EditText) view.findViewById(R.id.notes_title);
        mTitleNotes.setText(mNotes.getmTitle());
        mTextNotes = (EditText) view.findViewById(R.id.notes_text);
        mTextNotes.setText(mNotes.getmText());
        mDateButton = (Button) view.findViewById(R.id.notes_date_button);
        Date date = mNotes.getmDate();
        mDateButton.setText(format.format(date));
        mTitleNotesBehavior();
        mTextNotesBehavior();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_notes_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_notes: {
                NotesListArray.get(getActivity()).deleteNotes(mNotes.getmId());
                getActivity().finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void mTextNotesBehavior() {
        mTextNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNotes.setmText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void mTitleNotesBehavior() {

        mTitleNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNotes.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

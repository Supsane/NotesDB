package com.chashurin.notesdb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.UUID;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class NotesFragment extends Fragment {

    private static final String ARG_NOTES_ID = "notes_id";
    private static final String DIALOOG_DATE = "dialogDate";
    private Notes mNotes;
    private EditText mTitleNotes;
    private Button mDateButton;

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
        mDateButton = (Button) view.findViewById(R.id.notes_date_button);
        mDateButton.setText(mNotes.getmDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(fragmentManager, DIALOOG_DATE);
            }
        });
        mTitleNotesBehavior();
        return view;
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

package com.chashurin.notesdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Чашурин on 03.05.2017.
 */

public class NotesListFragment extends Fragment {

    private RecyclerView mNotesRecyclerView;
    private NotesAdapter notesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        mNotesRecyclerView = (RecyclerView) view.findViewById(R.id.notes_recycler_view);
        mNotesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        Log.d("NotesListFragment", "onResume");
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_notes: {
                Notes notes = new Notes();
                NotesListArray.get(getActivity()).addNotes(notes);
                Intent intent = NotesPagerActivity.newIntent(getActivity(), notes.getmId());
                startActivity(intent);
                return true;
            }
            case R.id.action_delete_all_notes: {
                NotesListArray.get(getActivity()).deleteAllNotes();
                NotesListArray notesListArray = NotesListArray.get(getActivity());
                List<Notes> notes = notesListArray.getmNotes();
                notesAdapter = new NotesAdapter(notes);
                mNotesRecyclerView.setAdapter(notesAdapter);
                updateSubTitle();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    void updateSubTitle() {
        NotesListArray notesListArray = NotesListArray.get(getActivity());
        int notesCount = notesListArray.getmNotes().size();
        String subtitle = getString(R.string.subtitle_format, notesCount);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    void updateUI() {
        NotesListArray notesListArray = NotesListArray.get(getActivity());
        List<Notes> notes = notesListArray.getmNotes();

        if (notesAdapter == null) {
            notesAdapter = new NotesAdapter(notes);
            mNotesRecyclerView.setAdapter(notesAdapter);
        } else {
            notesAdapter.setNotes(notes);
            notesAdapter.notifyDataSetChanged();
        }
        updateSubTitle();
    }

    private class NotesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitleNotes, mDateNotes;
        private Notes mNotes;

        NotesHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleNotes = (TextView) itemView.findViewById(R.id.list_item_notes_title_text_view);
            mDateNotes = (TextView) itemView.findViewById(R.id.list_item_notes_date_text_view);
        }

        public void setElementListNotes(Notes notes) {
            mNotes = notes;
            mTitleNotes.setText(mNotes.getmTitle());
            mDateNotes.setText(mNotes.getmDate().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = NotesPagerActivity.newIntent(getActivity(), mNotes.getmId());
            startActivity(intent);
        }
    }

    public class NotesAdapter extends RecyclerView.Adapter<NotesHolder> {

        private List<Notes> mNotes;

        NotesAdapter(List<Notes> notes) {
            mNotes = notes;
        }

        @Override
        public NotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_notes, parent, false);
            return new NotesHolder(view);
        }

        @Override
        public void onBindViewHolder(NotesHolder holder, int position) {
            Notes notes = mNotes.get(position);
            holder.setElementListNotes(notes);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        public void setNotes(List<Notes> notes) {
            mNotes = notes;
        }
    }


}

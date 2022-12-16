package com.acjl.foodmenu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acjl.foodmenu.R;
import com.acjl.foodmenu.adapter.NoteAdapter;
import com.acjl.foodmenu.adapter.NoteAdapterInterface;
import com.acjl.foodmenu.model.Note;
import com.acjl.foodmenu.utils.NoteConstants;
import com.acjl.foodmenu.viewmodel.NoteViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotesFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;
    private NoteDetailsFragment noteDetailsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        noteAdapter = new NoteAdapter(getContext(), new ArrayList());
        noteAdapter.setNoteAdapterInterface(new NoteAdapterInterface() {
            @Override
            public void onItemDelete(Note note) {
                noteViewModel.deleteNote(note);
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onClickItem(Note note) {
                Intent intent = new Intent(getContext(), ReadNoteActivity.class);
                intent.putExtra(NoteConstants.NOTE_DETAILS, note);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(noteAdapter);

        noteViewModel = ViewModelProviders.of(requireActivity()).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> noteAdapter.setNotes(notes));

    }
}
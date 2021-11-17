package com.suffixit.stickynote.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.Note;
import com.suffixit.stickynote.viewmodel.NoteViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteDetailsFragment extends Fragment {

    //UI
    @BindView(R.id.txtNoteTitle)
    TextView txtNoteTitle;

    @BindView(R.id.txtNoteDetails)
    TextView txtNoteDetails;

    @OnClick(R.id.btnDelete)
    public void deleteNote() {
        noteViewModel.deleteNote(note);
        getActivity().onBackPressed();
    }

    //Data
    private Note note;
    private View mainActivity;
    private Group group;
    private NoteViewModel noteViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            note = bundle.getParcelable("NOTE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_details, container, false);

        ButterKnife.bind(this, view);

        noteViewModel = ViewModelProviders.of(requireActivity()).get(NoteViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        setView();
    }

    private void setView() {
        getGroup();
        txtNoteTitle.setText(note.getTitle());
        txtNoteDetails.setText(note.getDescription());
    }

    private void getGroup() {
        mainActivity = getActivity().findViewById(R.id.groupHome);
        if (mainActivity instanceof Group) {
            group = (Group) mainActivity;
            group.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        group.setVisibility(View.VISIBLE);
    }
}
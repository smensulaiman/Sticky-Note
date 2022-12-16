package com.acjl.foodmenu.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.acjl.foodmenu.R;
import com.acjl.foodmenu.model.Note;
import com.acjl.foodmenu.utils.NoteConstants;
import com.acjl.foodmenu.utils.Utils;
import com.acjl.foodmenu.viewmodel.CategoryViewModel;
import com.acjl.foodmenu.viewmodel.NoteViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadNoteActivity extends AppCompatActivity {
    private static final String TAG = "ReadNoteActivity";

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;

    @BindView(R.id.cardView)
    MaterialCardView cardView;

    @BindView(R.id.imgIcon)
    ImageView imgIcon;

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.txtDescription)
    TextView txtDescription;

    @OnClick(R.id.imgDelete)
    public void deleteNote() {
        AlertDialog successDialog = new MaterialAlertDialogBuilder(this)
                .setTitle("Alert!!!")
                .setMessage("Are you sure want to delete the note?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    noteViewModel.deleteNote(note);
                    dialog.dismiss();
                    onBackPressed();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create();
        successDialog.show();
    }

    @OnClick(R.id.imgEdit)
    public void editNote() {
        Intent intent = new Intent(this, NewNoteActivity.class);
        intent.putExtra(NoteConstants.NOTE_DETAILS, note);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imgShare)
    public void shareNote() {
        Utils.shareText(this, note);
    }

    private CategoryViewModel categoryViewModel;
    private NoteViewModel noteViewModel;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);
        ButterKnife.bind(this);

        setupToolbar();

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        note = getIntent().getParcelableExtra(NoteConstants.NOTE_DETAILS);

        cardView.setCardBackgroundColor(getColor(note.getNoteBackgroundColor()));
        imgIcon.setImageResource(categoryViewModel.getCategoryById(note.getNoteCategoryId()).getIcon());
        imgIcon.setColorFilter(getColor(note.getNoteBackgroundColor()));
        imgIcon.getDrawable().setColorFilter(getColor(note.getNoteBackgroundColor()), PorterDuff.Mode.SRC_ATOP);
        txtTitle.setText(note.getTitle());
        txtDescription.setText(note.getDescription());

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Read Note");
        toolbar.setTitleTextColor(getColor(android.R.color.white));
        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

}
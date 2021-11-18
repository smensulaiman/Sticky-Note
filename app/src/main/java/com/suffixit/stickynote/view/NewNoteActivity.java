package com.suffixit.stickynote.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.adapter.CategoryAdapter;
import com.suffixit.stickynote.model.CategoryModel;
import com.suffixit.stickynote.model.Note;
import com.suffixit.stickynote.utils.NoteConstants;
import com.suffixit.stickynote.viewmodel.CategoryViewModel;
import com.suffixit.stickynote.viewmodel.NoteViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewNoteActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;

    @BindView(R.id.txtNoteTitleInput)
    TextInputLayout txtNoteTitleInput;

    @BindView(R.id.txtNoteCategoryInput)
    TextInputLayout txtNoteCategoryInput;

    @BindView(R.id.txtDateTime)
    TextInputEditText txtDateTime;

    @BindView(R.id.txtNoteDescriptionInput)
    TextInputLayout txtNoteDescriptionInput;

    @BindView(R.id.autoCompleteCategory)
    MaterialAutoCompleteTextView autoCompleteCategory;

    @BindView(R.id.materialButton)
    MaterialButton btnSubmit;

    @OnClick(R.id.materialButton)
    public void addNewNote() {
        if (validationIsPassed()) {
            Note myNote = new Note(txtNoteTitleInput.getEditText().getText().toString(),
                    txtNoteDescriptionInput.getEditText().getText().toString(),
                    currentCategory.getCategoryId(),
                    currentCategory.getColor(),
                    System.currentTimeMillis());
            if (getIntent().hasExtra(NoteConstants.NOTE_DETAILS)) {
                myNote.setId(oldNote.getId());
                noteViewModel.updateNote(myNote);
                AlertDialog successDialog = new MaterialAlertDialogBuilder(this)
                        .setTitle("Success")
                        .setMessage("Your note is updated successfully")
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                            onBackPressed();
                        })
                        .create();
                successDialog.show();
            } else {
                noteViewModel.insetNote(myNote);
                AlertDialog successDialog = new MaterialAlertDialogBuilder(this)
                        .setTitle("Success")
                        .setMessage("Your note is added successfully")
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                            onBackPressed();
                        })
                        .create();
                successDialog.show();
            }

        } else {
            Toast.makeText(this, "Please fill all fields first", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validationIsPassed() {
        return !(txtNoteTitleInput.getEditText().getText().toString().trim().isEmpty()
                || txtNoteCategoryInput.getEditText().getText().toString().trim().isEmpty()
                || txtNoteDescriptionInput.getEditText().getText().toString().trim().isEmpty());
    }

    private CategoryAdapter categoryAdapter;
    private CategoryViewModel categoryViewModel;
    private CategoryModel currentCategory;
    private NoteViewModel noteViewModel;
    private Note oldNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        ButterKnife.bind(this);

        setupToolbar();
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        if (getIntent().hasExtra(NoteConstants.NOTE_DETAILS)) {
            oldNote = getIntent().getParcelableExtra(NoteConstants.NOTE_DETAILS);
            currentCategory = categoryViewModel.getCategoryById(oldNote.getNoteCategoryId());
            txtNoteTitleInput.getEditText().setText(oldNote.getTitle());
            txtNoteDescriptionInput.getEditText().setText(oldNote.getDescription());
            txtNoteCategoryInput.getEditText().setText(currentCategory.getCategoryTitle());
            btnSubmit.setText("Update");
        }

        txtDateTime.setText(new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss a").format(new Date()));

        categoryAdapter = new CategoryAdapter(this, R.layout.dropdown_category, R.id.txtCategoryTitle, new ArrayList());
        autoCompleteCategory.setAdapter(categoryAdapter);

        autoCompleteCategory.setOnClickListener(v -> autoCompleteCategory.showDropDown());
        autoCompleteCategory.setOnItemClickListener((parent, view, position, id) -> {
            currentCategory = (CategoryModel) parent.getItemAtPosition(position);
            autoCompleteCategory.setText(currentCategory.getCategoryTitle());
        });
        categoryViewModel.getAllCategories().observe(this, categoryModels -> categoryAdapter.setDataList(categoryModels));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Create New Note");
        toolbar.setTitleTextColor(getColor(android.R.color.white));
        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
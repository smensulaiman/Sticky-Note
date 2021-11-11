package com.suffixit.stickynote.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.adapter.CategoryAdapter;
import com.suffixit.stickynote.model.CategoryModel;
import com.suffixit.stickynote.model.Note;
import com.suffixit.stickynote.viewmodel.CategoryViewModel;
import com.suffixit.stickynote.viewmodel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

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

    @BindView(R.id.txtNoteDescriptionInput)
    TextInputLayout txtNoteDescriptionInput;

    @BindView(R.id.autoCompleteCategory)
    MaterialAutoCompleteTextView autoCompleteCategory;

    @OnClick(R.id.materialButton)
    public void addNewNote() {
        if (validationIsPassed()) {
            noteViewModel.insetNote(new Note(txtNoteTitleInput.getEditText().getText().toString(),
                    txtNoteDescriptionInput.getEditText().getText().toString(),
                    1,
                    R.color.color_pink_light,
                    System.currentTimeMillis()
            ));
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
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        ButterKnife.bind(this);

        setupToolbar();
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        categoryAdapter = new CategoryAdapter(this, R.layout.dropdown_category, R.id.txtCategoryTitle, new ArrayList());
        autoCompleteCategory.setAdapter(categoryAdapter);

        autoCompleteCategory.setOnClickListener(v -> autoCompleteCategory.showDropDown());
        autoCompleteCategory.setOnItemClickListener((parent, view, position, id) -> autoCompleteCategory.setText(categoryViewModel.getAllCategories().getValue().get(position).getCategoryTitle()));
        categoryViewModel.getAllCategories().observe(this, categoryModels -> categoryAdapter.setDataList(categoryModels));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Create New Note");
        toolbar.setTitleTextColor(getColor(android.R.color.black));

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.getNavigationIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
    }
}
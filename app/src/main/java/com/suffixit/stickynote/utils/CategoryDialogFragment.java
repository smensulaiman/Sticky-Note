package com.suffixit.stickynote.utils;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.CategoryModel;

import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;


public class CategoryDialogFragment extends DialogFragment {
    private int mDefaultColor ;
    private int icon;
    private String hexColor;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        }
        return inflater.inflate(R.layout.layout_sample_dialog, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDefaultColor = ContextCompat.getColor(getContext(), R.color.color_white);

        final TextInputEditText etTitle = view.findViewById(R.id.etTitle);
        final Button btChooseColor = view.findViewById(R.id.btnColorPicker);
        final RadioGroup radioGroupIcon = view.findViewById(R.id.radioGroupIcon);
        final RadioButton radioButtonDate = view.findViewById(R.id.radioBtnDate);
        final RadioButton radioBtnFire = view.findViewById(R.id.radioBtnFire);
        final RadioButton radioBtnNote = view.findViewById(R.id.radioBtnNote);
        final RadioButton radioBtnCloud = view.findViewById(R.id.radioBtnCloud);
        final RadioButton radioBtnKey = view.findViewById(R.id.radioBtnKey);
        final RadioButton radioBtnTrending = view.findViewById(R.id.radioBtnTrending);
        final View colorPreview = view.findViewById(R.id.preview_selected_color);
        Button btnDone = view.findViewById(R.id.btnDone);


        radioGroupIcon.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioBtnDate:
                    icon = 1;
                    break;
                case R.id.radioBtnFire:
                    icon = 2;
                    break;
                case R.id.radioBtnCloud:
                    icon = 3;
                    break;

                case R.id.radioBtnNote:
                    icon = 4;
                    break;
                case R.id.radioBtnKey:
                    icon = 5;
                    break;
                case R.id.radioBtnTrending:
                    icon = 6;
                    break;

            }
        });

        btChooseColor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        openColorPicker(colorPreview);
                    }
                });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryModel categoryModel = new CategoryModel(0,etTitle.getText().toString(),icon,hexColor);
                DialogListener dialogListener = (DialogListener) getActivity();
                dialogListener.onFinishEditDialog(categoryModel);
                dismiss();
            }
        });
    }

    public void openColorPicker(View colorPreview) {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getContext(), mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                colorPreview.setBackgroundColor(mDefaultColor);
                hexColor = String.format("#%06X", (0xFFFFFF & mDefaultColor));

            }
        });
        colorPicker.show();
    }


    public interface DialogListener {
        void onFinishEditDialog(CategoryModel categoryModel);
    }


    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(lp);
    }
}
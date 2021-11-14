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
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.adapter.ColorListInterface;
import com.suffixit.stickynote.adapter.IconAdapter;
import com.suffixit.stickynote.model.CategoryModel;
import com.suffixit.stickynote.model.Icon;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;


public class CategoryDialogFragment extends DialogFragment {

    private int mDefaultColor;
    private int icon;
    private String hexColor;
    private List<Icon> iconList;

    private DialogListener dialogListener;

    public interface DialogListener {
        void onFinishEditDialog(CategoryModel categoryModel);
    }

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_sample_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDefaultColor = ContextCompat.getColor(getContext(), R.color.color_white);

        final TextInputEditText etTitle = view.findViewById(R.id.etTitle);
        final Button btChooseColor = view.findViewById(R.id.btnColorPicker);
        createIcons();
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        final IconAdapter iconAdapter = new IconAdapter(getContext(),iconList);
        recyclerView.setAdapter(iconAdapter);
        iconAdapter.setColorListInterface(new ColorListInterface() {
            @Override
            public void onItemClick(int position, Icon categoryIcon) {
                icon = categoryIcon.getIcon();
            }
        });

        final View colorPreview = view.findViewById(R.id.preview_selected_color);
        Button btnDone = view.findViewById(R.id.btnDone);

        btChooseColor.setOnClickListener(v -> openColorPicker(colorPreview));

        btnDone.setOnClickListener(view1 -> {
            CategoryModel categoryModel = new CategoryModel(etTitle.getText().toString(), icon, hexColor);
            dialogListener.onFinishEditDialog(categoryModel);
            dismiss();
        });
    }

    private void createIcons() {
        iconList = new ArrayList<>();
        iconList.add(new Icon(R.drawable.ic_add));
        iconList.add(new Icon(R.drawable.ic_cloud));
        iconList.add(new Icon(R.drawable.ic_date));
        iconList.add(new Icon(R.drawable.ic_expense));
        iconList.add(new Icon(R.drawable.ic_fire));
        iconList.add(new Icon(R.drawable.ic_key));
        iconList.add(new Icon(R.drawable.ic_note));
        iconList.add(new Icon(R.drawable.ic_trending));
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(lp);
    }

    public void openColorPicker(View colorPreview) {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getContext(),
                mDefaultColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        dialog.getDialog().dismiss();
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
}
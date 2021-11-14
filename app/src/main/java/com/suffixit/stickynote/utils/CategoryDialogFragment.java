package com.suffixit.stickynote.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.adapter.ColorAdapter;
import com.suffixit.stickynote.adapter.ColorListInterface;
import com.suffixit.stickynote.adapter.IconAdapter;
import com.suffixit.stickynote.adapter.IconListInterface;
import com.suffixit.stickynote.model.CategoryModel;
import com.suffixit.stickynote.model.ColorModel;
import com.suffixit.stickynote.model.Icon;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;


public class CategoryDialogFragment extends DialogFragment {

    private int mDefaultColor;
    private int icon;
    private String hexColor;
    private List<Icon> iconList;
    private List<ColorModel> colorModelList;

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
        createIcons();
        createColor();
        setIconAdapter(view);
        setColorAdapter(view);

        Button btnDone = view.findViewById(R.id.btnDone);

        btnDone.setOnClickListener(view1 -> {
            CategoryModel categoryModel = new CategoryModel(etTitle.getText().toString(), icon, hexColor);
            dialogListener.onFinishEditDialog(categoryModel);
            dismiss();
        });
    }

    private void setColorAdapter(View view) {
        final RecyclerView colorRecyclerView = view.findViewById(R.id.colorRecyclerView);
        LinearLayoutManager colorLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        colorRecyclerView.setLayoutManager(colorLayoutManager);
        final ColorAdapter colorAdapter = new ColorAdapter(getContext(), colorModelList);
        colorRecyclerView.setAdapter(colorAdapter);
        colorAdapter.setColorListInterface(new ColorListInterface() {
            @Override
            public void onItemClick(int position, ColorModel categoryIcon) {
                hexColor = categoryIcon.getColor();
            }
        });
    }

    private void setIconAdapter(View view) {
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        final IconAdapter iconAdapter = new IconAdapter(getContext(),iconList);
        recyclerView.setAdapter(iconAdapter);
        iconAdapter.setIconListInterface(new IconListInterface() {
            @Override
            public void onItemClick(int position, Icon categoryIcon) {
                icon = categoryIcon.getIcon();
            }
        });
    }

    private void createColor() {
        colorModelList = new ArrayList<>();
        colorModelList.add(new ColorModel("#93C47D"));
        colorModelList.add(new ColorModel("#3d85c6"));
        colorModelList.add(new ColorModel("#45818e"));
        colorModelList.add(new ColorModel("#8e7cc3"));
        colorModelList.add(new ColorModel("#e06666"));
        colorModelList.add(new ColorModel("#cfe2f3"));
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

}
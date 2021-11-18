package com.suffixit.stickynote.view.personal;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textfield.TextInputEditText;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.adapter.ColorAdapter;
import com.suffixit.stickynote.adapter.IconAdapter;
import com.suffixit.stickynote.model.CategoryModel;
import com.suffixit.stickynote.model.ColorModel;
import com.suffixit.stickynote.model.IconModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryDialogFragment extends DialogFragment {

    @BindView(R.id.etTitle)
    TextInputEditText etTitle;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.colorRecyclerView)
    RecyclerView colorRecyclerView;

    @OnClick(R.id.btnDone)
    public void submit() {

        if (etTitle.getText().toString().trim().isEmpty()) {
            YoYo.with(Techniques.Shake)
                    .playOn(etTitle);
            etTitle.setError("Category can not be empty");
            return;
        }

        CategoryModel categoryModel = new CategoryModel(etTitle.getText().toString(), icon, color);
        dialogListener.onFinishEditDialog(categoryModel);
        dismiss();
    }

    private int mDefaultColor;
    private int icon;
    private int color;
    private ColorAdapter colorAdapter;
    private IconAdapter iconAdapter;

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

        View view = inflater.inflate(R.layout.layout_sample_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDefaultColor = ContextCompat.getColor(getContext(), R.color.color_white);

        initIconRecyclerView();
        initColorRecyclerView();
    }

    private void initIconRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        iconAdapter = new IconAdapter(getContext(), prepareIcons());
        recyclerView.setAdapter(iconAdapter);
        iconAdapter.setIconListInterface((position, categoryIcon) -> icon = categoryIcon.getIcon());
    }

    private void initColorRecyclerView() {
        colorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        colorAdapter = new ColorAdapter(getContext(), prepareColor());
        colorRecyclerView.setAdapter(colorAdapter);
        colorAdapter.setColorListInterface((position, categoryIcon) -> color = categoryIcon.getColor());
    }

    private List<IconModel> prepareIcons() {
        List<IconModel> iconList = new ArrayList();
        iconList.add(new IconModel(R.drawable.ic_add));
        iconList.add(new IconModel(R.drawable.ic_cloud));
        iconList.add(new IconModel(R.drawable.ic_date));
        iconList.add(new IconModel(R.drawable.ic_expense));
        iconList.add(new IconModel(R.drawable.ic_fire));
        iconList.add(new IconModel(R.drawable.ic_key));
        iconList.add(new IconModel(R.drawable.ic_note));
        iconList.add(new IconModel(R.drawable.ic_trending));
        return iconList;
    }

    private List<ColorModel> prepareColor() {
        List<ColorModel> colorModelList = new ArrayList();
        colorModelList.add(new ColorModel(R.color.color_green_light));
        colorModelList.add(new ColorModel(R.color.color_yellow_light));
        colorModelList.add(new ColorModel(R.color.color_pink_light));
        colorModelList.add(new ColorModel(R.color.color_red_light));
        colorModelList.add(new ColorModel(R.color.color_sky_light));
        colorModelList.add(new ColorModel(R.color.color_purple_light));
        colorModelList.add(new ColorModel(R.color.color_blue_light));
        return colorModelList;
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
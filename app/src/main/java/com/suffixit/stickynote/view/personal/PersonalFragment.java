package com.suffixit.stickynote.view.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.suffixit.stickynote.R;
import com.suffixit.stickynote.viewmodel.CategoryViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.btnCategory)
    Button btnAddCategory;

    private CategoryViewModel categoryViewModel;
    private CategoryDialogFragment dialogFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryViewModel = ViewModelProviders.of(requireActivity()).get(CategoryViewModel.class);
        btnAddCategory.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        if(dialogFragment == null){
            dialogFragment = new CategoryDialogFragment();
            dialogFragment.setDialogListener(categoryModel -> categoryViewModel.insetCategoryModel(categoryModel));
        }
        dialogFragment.show(getFragmentManager(), "dialog");
    }
}
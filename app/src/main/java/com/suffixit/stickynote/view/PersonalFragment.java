package com.suffixit.stickynote.view;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.suffixit.stickynote.model.CategoryModel;
import com.suffixit.stickynote.utils.CategoryDialogFragment;
import com.suffixit.stickynote.viewmodel.CategoryViewModel;
import com.suffixit.stickynote.viewmodel.NoteViewModel;
import com.suffixit.stickynote.viewmodel.PersonalViewModel;
import com.suffixit.stickynote.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalFragment extends Fragment implements View.OnClickListener,CategoryDialogFragment.DialogListener {

    @BindView(R.id.btnCategory)
    Button btnAddCategory;


    private PersonalViewModel mViewModel;
    private CategoryViewModel categoryViewModel;
    private CategoryDialogFragment dialogFragment;
    private CategoryModel modelCategory;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // mViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);
        //categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel = ViewModelProviders.of(requireActivity()).get(CategoryViewModel.class);

        btnAddCategory.setOnClickListener(this);
        dialogFragment = new CategoryDialogFragment();

        categoryViewModel.insetCategoryModel(modelCategory);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        dialogFragment = new CategoryDialogFragment();
        dialogFragment.show(transaction, "dialog");
    }

    @Override
    public void onFinishEditDialog(CategoryModel categoryModel) {
        modelCategory = categoryModel;
        Toast.makeText(getContext(), "Title : "+categoryModel.getCategoryTitle()+"\n"+"Color: "+categoryModel.getColor()+"\n"+"Icon: "+categoryModel.getIcon(), Toast.LENGTH_SHORT).show();
    }
}
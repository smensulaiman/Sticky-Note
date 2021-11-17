package com.suffixit.stickynote.view.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.suffixit.stickynote.R;
import com.suffixit.stickynote.view.MainActivity;
import com.suffixit.stickynote.viewmodel.CategoryViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalFragment extends Fragment {

    @BindView(R.id.layoutCategory)
    ConstraintLayout layoutCategory;

    @OnClick(R.id.layoutCategory)
    public void addCategory(){
        if(dialogFragment == null){
            dialogFragment = new CategoryDialogFragment();
            dialogFragment.setDialogListener(categoryModel -> categoryViewModel.insetCategoryModel(categoryModel));
        }
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    private CategoryViewModel categoryViewModel;
    private CategoryDialogFragment dialogFragment;
    private View mainActivity;
    private Group group;

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
        setView();
    }

    private void setView() {
        mainActivity = getActivity().findViewById(R.id.groupHome);
        if (mainActivity instanceof Group){
            group = (Group) mainActivity;
            group.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        group.setVisibility(View.VISIBLE);
    }
}
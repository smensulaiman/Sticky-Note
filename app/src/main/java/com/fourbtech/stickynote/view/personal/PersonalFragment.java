package com.fourbtech.stickynote.view.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.fourbtech.stickynote.R;
import com.fourbtech.stickynote.database.LocalStorage;
import com.fourbtech.stickynote.viewmodel.CategoryViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalFragment extends Fragment {

    @BindView(R.id.txtName)
    TextView txtName;

    private CategoryViewModel categoryViewModel;
    private CategoryDialogFragment dialogFragment;
    private EditPersonalInfoFragment editPersonalInfoFragment;
    private View mainActivity;
    private Group group;

    @OnClick(R.id.layoutCategory)
    public void addCategory() {
        if (dialogFragment == null) {
            dialogFragment = new CategoryDialogFragment();
            dialogFragment.setDialogListener(categoryModel -> categoryViewModel.insetCategoryModel(categoryModel));
        }
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    @OnClick(R.id.layoutPersonalInfo)
    public void editPersonalInfo() {

            Bundle bundle = new Bundle();
            bundle.putString("NAME", LocalStorage.getInstance(getContext()).getName());
            editPersonalInfoFragment = new EditPersonalInfoFragment();
            editPersonalInfoFragment.setArguments(bundle);
            editPersonalInfoFragment.setDialogListener((name,district) -> {
                LocalStorage.getInstance(getContext()).setName(name);
                LocalStorage.getInstance(getContext()).setDistrict(district);
                AlertDialog successDialog = new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Success")
                        .setMessage("successfully Information Changed")
                        .setPositiveButton("OK", (dialog, which) -> {
                            txtName.setText(name);
                            dialog.dismiss();
                            editPersonalInfoFragment.dismiss();
                        })
                        .create();
                successDialog.show();
            });

        editPersonalInfoFragment.show(getFragmentManager(), "personal");
    }


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
        txtName.setText(LocalStorage.getInstance(getContext()).getName());
        mainActivity = getActivity().findViewById(R.id.groupHome);
        if (mainActivity instanceof Group) {
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
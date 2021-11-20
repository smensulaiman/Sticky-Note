package com.suffixit.stickynote.view.personal;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.database.LocalStorage;
import com.suffixit.stickynote.model.District;
import com.suffixit.stickynote.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPersonalInfoFragment extends DialogFragment {

    @BindView(R.id.txtName)
    TextInputEditText txtName;

    @BindView(R.id.autoCompleteDistrict)
    AutoCompleteTextView txtDistrict;

    @OnClick(R.id.btnUpdate)
    public void update(){
        listener.onFinishDialog(txtName.getText().toString(),selectedDistrict);
    }

    @OnClick(R.id.autoCompleteDistrict)
    public void showDistricts() {
        txtDistrict.showDropDown();
    }

    private String name;
    private EditPersonalInfoListener listener;
    private List<District> districtList;
    private String selectedDistrict;

    public interface EditPersonalInfoListener {
        void onFinishDialog(String name,String district);
    }

    public void setDialogListener(EditPersonalInfoListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            name = bundle.getString("NAME");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_edit_personal_info, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDistrict();
        setView();
        setDistrictAdapter();
        txtDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDistrict = new Gson().toJson(districtList.get(position));
            }
        });
    }

    private void setDistrictAdapter() {
        districtList = new ArrayList<>();
        try {
            districtList = Utils.getAllDistrict(getContext()).getDistricts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                R.layout.support_simple_spinner_dropdown_item,
                districtList.stream()
                        .map(District::getName).collect(Collectors.toList()));
            txtDistrict.setAdapter(adapter);
    }

    private void setView() {
        txtName.setText(name);
    }



    private void getDistrict() {
        String storedDistrict = LocalStorage.getInstance(getContext()).getDistrict();
        if (storedDistrict != null) {
           District district = new Gson().fromJson(storedDistrict,District.class);
           txtDistrict.setText(district.getName());
       }
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

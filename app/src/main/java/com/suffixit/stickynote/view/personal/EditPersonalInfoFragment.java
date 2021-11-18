package com.suffixit.stickynote.view.personal;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.model.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditPersonalInfoFragment extends DialogFragment {

    @BindView(R.id.txtName)
    TextInputEditText txtName;


    private String name;
    private EditPersonalInfoListener listener;

    public interface EditPersonalInfoListener{
        void onFinishDialog(String name);
    }

    public void setDialogListener(EditPersonalInfoListener listener){
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
        if (bundle != null){
            name = bundle.getString("NAME");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_edit_personal_info,container,false);
        ButterKnife.bind(this,view);
        setView();
        return view;
    }

    private void setView() {
        txtName.setText(name);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

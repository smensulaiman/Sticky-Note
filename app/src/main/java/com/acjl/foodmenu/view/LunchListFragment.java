package com.acjl.foodmenu.view;

import android.os.Bundle;

import android.widget.TextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.acjl.foodmenu.R;
import com.acjl.foodmenu.adapter.LunchListAdapter;

public class LunchListFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LunchListAdapter lunchListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lunch_list, container, false);
        ButterKnife.bind(this, view);

        lunchListAdapter = new LunchListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(lunchListAdapter);

        return view;
    }
}
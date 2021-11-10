package com.suffixit.stickynote.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.adapter.NoteViewPagerAdapter;
import com.suffixit.stickynote.model.NoteViewPagerModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private NoteViewPagerAdapter  noteViewPagerAdapter;
    private List<NoteViewPagerModel> noteViewPagerModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteViewPagerModelList = new ArrayList();
        noteViewPagerModelList.add(new NoteViewPagerModel("Notes", new NotesFragment()));
        noteViewPagerModelList.add(new NoteViewPagerModel("To Do", new ToDoFragment()));

        noteViewPagerAdapter = new NoteViewPagerAdapter(getChildFragmentManager());
        noteViewPagerAdapter.setNoteViewPagerModelList(noteViewPagerModelList);

        viewPager.setAdapter(noteViewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }
}
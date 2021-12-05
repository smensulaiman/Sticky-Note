package com.fourbtech.stickynote.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fourbtech.stickynote.model.NoteViewPagerModel;

import java.util.ArrayList;
import java.util.List;

public class NoteViewPagerAdapter extends FragmentPagerAdapter {

    List<NoteViewPagerModel> noteViewPagerModelList;

    public NoteViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        noteViewPagerModelList = new ArrayList();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return noteViewPagerModelList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return noteViewPagerModelList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return noteViewPagerModelList.get(position).getTitle();
    }

    public void setNoteViewPagerModelList(List<NoteViewPagerModel> noteViewPagerModelList) {
        this.noteViewPagerModelList = noteViewPagerModelList;
    }
}

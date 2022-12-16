package com.acjl.foodmenu.model;

import androidx.fragment.app.Fragment;

public class NoteViewPagerModel {
    private String title;
    private Fragment fragment;

    public NoteViewPagerModel(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}

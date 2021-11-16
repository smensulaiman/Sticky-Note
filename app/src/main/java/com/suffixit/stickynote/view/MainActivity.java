package com.suffixit.stickynote.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.appbar.MaterialToolbar;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.adapter.StickyBottomBarViewItemChangeListener;
import com.suffixit.stickynote.utils.MenuItem;
import com.suffixit.stickynote.view.personal.PersonalFragment;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements StickyBottomBarViewItemChangeListener {

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;

    @BindView(R.id.imgHome)
    ImageView imgHome;

    @BindView(R.id.txtDateTime)
    TextView txtDateTime;

    @BindView(R.id.imgPersonal)
    ImageView imgPersonal;

    @BindView(R.id.imageView)
    SimpleDraweeView imageView;

    @BindView(R.id.groupWeather)
    Group groupWeather;

    @BindView(R.id.groupHome)
    Group groupHome;

    @OnClick(R.id.imgHome)
    public void changeFragmentToHome() {
        onItemSelected(MenuItem.ITEM_HOME);
    }

    @OnClick(R.id.imgPersonal)
    public void changeFragmentToPerson() {
        onItemSelected(MenuItem.ITEM_PERSONAL);
    }

    @OnClick(R.id.fab)
    public void createNote() {
        startActivity(new Intent(MainActivity.this, NewNoteActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();

        txtDateTime.setText(new Date().toString());
        Uri uri = Uri.parse("https://avatars.githubusercontent.com/u/49442391?v=4");
        imageView.setImageURI(uri);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeFragmentToHome();
    }

    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (currentFragment != null && currentFragment.getClass() == fragment.getClass()) {
            fragment = currentFragment;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

    @Override
    public void onItemSelected(MenuItem menuItem) {
        switch (menuItem) {
            case ITEM_HOME: {
                changeFragment(new HomeFragment());
                imgHome.setColorFilter(getColor(R.color.color_green));
                imgPersonal.setColorFilter(getColor(R.color.color_black));
                groupWeather.setVisibility(View.VISIBLE);
                break;
            }
            case ITEM_PERSONAL: {
                changeFragment(new PersonalFragment());
                imgPersonal.setColorFilter(getColor(R.color.color_green));
                imgHome.setColorFilter(getColor(R.color.color_black));
                groupWeather.setVisibility(View.GONE);
                break;
            }
            default: {
                imgHome.setColorFilter(getColor(R.color.color_black));
                imgPersonal.setColorFilter(getColor(R.color.color_black));
                break;
            }
        }
    }

}
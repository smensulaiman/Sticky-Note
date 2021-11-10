package com.suffixit.stickynote.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.facebook.drawee.view.SimpleDraweeView;
import com.suffixit.stickynote.R;
import com.suffixit.stickynote.adapter.StickyBottomBarViewItemChangeListener;
import com.suffixit.stickynote.utils.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements StickyBottomBarViewItemChangeListener {

    @BindView(R.id.imgHome)
    ImageView imgHome;

    @BindView(R.id.imgPersonal)
    ImageView imgPersonal;

    @BindView(R.id.imageView)
    SimpleDraweeView imageView;


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

        Uri uri = Uri.parse("https://avatars.githubusercontent.com/u/49442391?v=4");
        imageView.setImageURI(uri);
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeFragmentToHome();
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

    @Override
    public void onItemSelected(MenuItem menuItem) {
        switch (menuItem) {
            case ITEM_HOME: {
                changeFragment(new HomeFragment());
                imgHome.setColorFilter(getColor(R.color.color_green));
                imgPersonal.setColorFilter(getColor(R.color.color_black));
                break;
            }
            case ITEM_PERSONAL: {
                imgPersonal.setColorFilter(getColor(R.color.color_green));
                imgHome.setColorFilter(getColor(R.color.color_black));
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
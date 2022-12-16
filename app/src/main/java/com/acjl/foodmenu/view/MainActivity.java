package com.acjl.foodmenu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.appbar.MaterialToolbar;
import com.acjl.foodmenu.R;
import com.acjl.foodmenu.adapter.StickyBottomBarViewItemChangeListener;
import com.acjl.foodmenu.database.LocalStorage;
import com.acjl.foodmenu.utils.MenuItem;
import com.acjl.foodmenu.view.personal.PersonalFragment;
import com.acjl.foodmenu.viewmodel.WeatherViewModel;

import java.text.SimpleDateFormat;
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

    @BindView(R.id.txtTemperature)
    TextView txtTemperature;

    @BindView(R.id.imgPersonal)
    ImageView imgPersonal;

    @BindView(R.id.imageView)
    SimpleDraweeView imageView;

    @BindView(R.id.txtName)
    TextView txtName;

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

    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.getWeatherData().observe(this, weatherResponseModel ->
                txtTemperature.setText(Math.round(weatherResponseModel.getCurrent().getTemp()) + "\u2103"));


//        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
//        rotateAnimation.setFillAfter(false);

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeFragmentToHome();
    }

    private void setView() {
        txtDateTime.setText(new SimpleDateFormat("EEE, dd MMM yyyy").format(new Date()));
        //imageView.setImageURI(Uri.parse("https://spng.pngfind.com/pngs/s/5-52097_avatar-png-pic-vector-avatar-icon-png-transparent.png"));
        txtName.setText(LocalStorage.getInstance(this).getName());
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
                setView();
                imgHome.setColorFilter(getColor(R.color.color_green));
                imgPersonal.setColorFilter(getColor(R.color.color_black));
                groupHome.setVisibility(View.VISIBLE);
                break;
            }
            case ITEM_PERSONAL: {
                changeFragment(new PersonalFragment());
                imgPersonal.setColorFilter(getColor(R.color.color_green));
                imgHome.setColorFilter(getColor(R.color.color_black));
                groupHome.setVisibility(View.GONE);
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
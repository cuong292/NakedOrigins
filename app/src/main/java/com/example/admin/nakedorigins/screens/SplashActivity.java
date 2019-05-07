package com.example.admin.nakedorigins.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.admin.nakedorigins.MainActivity;
import com.example.admin.nakedorigins.data.pref.PrefWrapper;
import com.example.admin.nakedorigins.screens.main.MainPresenter;
import com.example.admin.nakedorigins.screens.splash.SplashPresenter;
import com.gemvietnam.base.ContainerActivity;
import com.gemvietnam.base.viper.ViewFragment;

public class SplashActivity extends ContainerActivity {

  @Override
  public ViewFragment onCreateFirstFragment() {
    return (ViewFragment) new MainPresenter(this).getFragment();
  }
}

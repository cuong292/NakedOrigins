package com.example.admin.nakedorigins.screens.main.support.supportsplash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.admin.nakedorigins.data.pref.PrefWrapper;
import com.example.admin.nakedorigins.screens.SplashActivity;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.utils.ActivityUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The DiscoverSplash Fragment
 */
public class DiscoverSplashFragment extends ViewFragment<DiscoverSplashContract.Presenter> implements DiscoverSplashContract.View {
  private PrefWrapper wrapper;

  public static DiscoverSplashFragment getInstance() {
    return new DiscoverSplashFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_discover_splash;
  }

  @Override
  public void onStart() {
    super.onStart();
    wrapper = new PrefWrapper(getViewContext());
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        wrapper.setCurrentTab(1);
        ActivityUtils.startActivity(getActivity(), SplashActivity.class);
        getActivity().finish();
      }
    }, 1000);
  }
}

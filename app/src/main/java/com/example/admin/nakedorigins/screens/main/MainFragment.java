package com.example.admin.nakedorigins.screens.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.example.admin.nakedorigins.MainActivity;
import com.example.admin.nakedorigins.customview.MenuNavigationView;
import com.example.admin.nakedorigins.customview.NonSwipeNormalViewPager;
import com.example.admin.nakedorigins.customview.NonSwipeableViewPager;
import com.example.admin.nakedorigins.data.pref.PrefWrapper;
import com.example.admin.nakedorigins.screens.about.AboutPresenter;
import com.example.admin.nakedorigins.screens.main.discover.DiscoverFragment;
import com.example.admin.nakedorigins.screens.main.discover.DiscoverPresenter;
import com.example.admin.nakedorigins.screens.main.journey.JourneyFragment;
import com.example.admin.nakedorigins.screens.main.journey.JourneyPresenter;
import com.example.admin.nakedorigins.screens.main.support.SupportPresenter;
import com.example.admin.nakedorigins.screens.main.support.supportmessage.SupportMessagePresenter;
import com.example.admin.nakedorigins.viewutilities.AlphaPagerTransformer;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.ActivityUtils;

import butterknife.BindView;

/**
 * The Main Fragment
 */
public class MainFragment extends ViewFragment<MainContract.Presenter> implements MainContract.View {
  @BindView(R.id.bottom_navigator)
  MenuNavigationView mBottomNavigator;
  @BindView(R.id.main_vp)
  NonSwipeNormalViewPager mViewPager;
  @BindView(R.id.scan_qr_fab)
  FloatingActionButton scanQrFab;

  private SupportPresenter firstPresenter;
  private JourneyPresenter secondPresenter;
  private DiscoverPresenter thirdPresenter;
  private PrefWrapper wrapper;

  public static MainFragment getInstance() {
    return new MainFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_main;
  }

  @Override
  protected void parseArgs(Bundle args) {
    super.parseArgs(args);
  }

  @Override
  public void initLayout() {
    super.initLayout();
    wrapper = new PrefWrapper(getViewContext());
    FragmentStatePagerAdapter pagerAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
      @Override
      public Fragment getItem(int i) {
        switch (i) {
          case 0:
            if (firstPresenter == null) {
              firstPresenter = new SupportPresenter((ContainerView) getActivity());
            }
            return firstPresenter.getFragment();
          case 1:
            if (secondPresenter == null) {
              secondPresenter = new JourneyPresenter((ContainerView) getActivity());
            }
            return secondPresenter.getFragment();
          case 2:
            if (thirdPresenter == null) {
              thirdPresenter = new DiscoverPresenter((ContainerView) getActivity());
            }
            return thirdPresenter.getFragment();
        }
        return null;
      }

      @Override
      public int getCount() {
        return 3;
      }
    };
    mViewPager.setAdapter(pagerAdapter);
//    mViewPager.setPageTransformer(false, new AlphaPagerTransformer());
    mBottomNavigator.setupWithViewPager(mViewPager);
    mViewPager.setCurrentItem(wrapper.getCurrentTab());
    wrapper.setCurrentTab(0);
    scanQrFab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ActivityUtils.startActivity(getViewContext(), MainActivity.class);
        getActivity().finish();
      }
    });
  }
}

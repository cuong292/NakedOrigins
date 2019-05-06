package com.example.admin.nakedorigins.screens.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.nakedorigins.customview.MenuNavigationView;
import com.example.admin.nakedorigins.customview.NonSwipeNoAnimationViewPager;
import com.example.admin.nakedorigins.customview.NonSwipeableViewPager;
import com.example.admin.nakedorigins.screens.about.AboutPresenter;
import com.example.admin.nakedorigins.screens.main.discover.DiscoverFragment;
import com.example.admin.nakedorigins.screens.main.discover.DiscoverPresenter;
import com.example.admin.nakedorigins.screens.main.journey.JourneyFragment;
import com.example.admin.nakedorigins.screens.main.journey.JourneyPresenter;
import com.example.admin.nakedorigins.screens.main.support.supportmessage.SupportMessagePresenter;
import com.example.admin.nakedorigins.viewutilities.AlphaPagerTransformer;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import butterknife.BindView;

/**
 * The Main Fragment
 */
public class MainFragment extends ViewFragment<MainContract.Presenter> implements MainContract.View {
  @BindView(R.id.bottom_navigator)
  MenuNavigationView mBottomNavigator;
  @BindView(R.id.main_vp)
  NonSwipeableViewPager mViewPager;

  private SupportMessagePresenter firstPresenter;
  private JourneyPresenter secondPresenter;
  private DiscoverPresenter thirdPresenter;

  public static MainFragment getInstance() {
    return new MainFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_main;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    FragmentStatePagerAdapter pagerAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
      @Override
      public Fragment getItem(int i) {
        switch (i) {
          case 0:
            if (firstPresenter == null) {
              firstPresenter = new SupportMessagePresenter((ContainerView) getActivity());
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
  }
}

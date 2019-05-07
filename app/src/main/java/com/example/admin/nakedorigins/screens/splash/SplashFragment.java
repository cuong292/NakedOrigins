package com.example.admin.nakedorigins.screens.splash;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.admin.nakedorigins.customview.NonSwipeableViewPager;
import com.example.admin.nakedorigins.screens.about.AboutPresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The Splash Fragment
 */
public class SplashFragment extends ViewFragment<SplashContract.Presenter> implements SplashContract.View {
  @BindView(R.id.product_view_pager)
  NonSwipeableViewPager mProductVp;

  private SplashProductAdapter mProductAdapter;
  private Timer timer;

  public static SplashFragment getInstance() {
    return new SplashFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_splash;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    ButterKnife.setDebug(true);
    mProductAdapter = new SplashProductAdapter(new SplashProductAdapter.OnProductClicked() {
      @Override
      public void onClick() {

      }
    });
    mProductVp.setAdapter(mProductAdapter);
  }

}

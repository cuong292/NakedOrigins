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
  @BindView(R.id.searching_tv)
  TextView mSearchingTv;

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
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(1000);
        anim.setRepeatCount(0);
        mSearchingTv.startAnimation(anim);
        mSearchingTv.setVisibility(View.GONE);
        mProductVp.setCurrentItem(1);
        timer = new Timer();
        timer.schedule(new TimerTask() {
          @Override
          public void run() {
            redirectToAboutFragment();
          }
        }, 1500);
      }
    });
    mProductVp.setAdapter(mProductAdapter);
  }

  public void redirectToAboutFragment() {
    if (timer != null) {
      timer.cancel();
    }
    new AboutPresenter((ContainerView) getActivity()).pushViewWithAnimation(R.anim.fade_in, R.anim.fade_out);
  }
}

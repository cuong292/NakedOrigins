package com.example.admin.nakedorigins.screens.main.support;

import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.nakedorigins.screens.main.support.supportmessage.SupportMessagePresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import butterknife.BindView;

/**
 * The Support Fragment
 */
public class SupportFragment extends ViewFragment<SupportContract.Presenter> implements SupportContract.View {
  @BindView(R.id.clickableView)
  View v;


  public static SupportFragment getInstance() {
    return new SupportFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_support;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    v.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new SupportMessagePresenter((ContainerView) getActivity()).pushViewWithAnimation(R.anim.fade_in, R.anim.fade_out);
      }
    });
  }


}

package com.example.admin.nakedorigins.screens.about;

import android.view.View;

import com.example.admin.nakedorigins.screens.SplashActivity;
import com.example.admin.nakedorigins.screens.main.MainPresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.ActivityUtils;

import butterknife.BindView;

/**
 * The About Fragment
 */
public class AboutFragment extends ViewFragment<AboutContract.Presenter> implements AboutContract.View {
  @BindView(R.id.about_root_view)
  View view;

  public static AboutFragment getInstance() {
    return new AboutFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_about;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ActivityUtils.startActivity(getContext(), SplashActivity.class);
        getActivity().finish();
      }
    });
  }
}

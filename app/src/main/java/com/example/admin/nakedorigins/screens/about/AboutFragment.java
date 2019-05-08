package com.example.admin.nakedorigins.screens.about;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

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
  @BindView(R.id.explore_coffee_tv)
  TextView exploreCoffeeTv;
  @BindView(R.id.coffee_brand_tv)
  TextView coffeeBrandTv;

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
    exploreCoffeeTv.setText(Html.fromHtml("<i>" + "Explore" + "</i>" + " The History Of Your Coffee"));
    coffeeBrandTv.setText(Html.fromHtml("<i>" + "Kati Kati" + "</i>" + " Blend"));
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ActivityUtils.startActivity(getContext(), SplashActivity.class);
        getActivity().finish();
      }
    });
  }
}

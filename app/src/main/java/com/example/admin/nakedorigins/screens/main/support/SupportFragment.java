package com.example.admin.nakedorigins.screens.main.support;

import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;

/**
 * The Support Fragment
 */
public class SupportFragment extends ViewFragment<SupportContract.Presenter> implements SupportContract.View {

  public static SupportFragment getInstance() {
    return new SupportFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_support;
  }
}

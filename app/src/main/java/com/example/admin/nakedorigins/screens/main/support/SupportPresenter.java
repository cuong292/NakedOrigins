package com.example.admin.nakedorigins.screens.main.support;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import android.os.Bundle;

/**
 * The Support Presenter
 */
public class SupportPresenter extends Presenter<SupportContract.View, SupportContract.Interactor>
    implements SupportContract.Presenter {

  public SupportPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SupportContract.View onCreateView(Bundle data) {
    return SupportFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public SupportContract.Interactor onCreateInteractor() {
    return new SupportInteractor(this);
  }
}

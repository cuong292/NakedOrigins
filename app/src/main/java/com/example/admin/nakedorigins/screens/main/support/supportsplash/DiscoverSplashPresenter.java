package com.example.admin.nakedorigins.screens.main.support.supportsplash;

import com.gemvietnam.base.viper.Presenter;

import android.os.Bundle;

import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The DiscoverSplash Presenter
 */
public class DiscoverSplashPresenter extends Presenter<DiscoverSplashContract.View, DiscoverSplashContract.Interactor>
    implements DiscoverSplashContract.Presenter {

  public DiscoverSplashPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public DiscoverSplashContract.View onCreateView(Bundle data) {
    return DiscoverSplashFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public DiscoverSplashContract.Interactor onCreateInteractor() {
    return new DiscoverSplashInteractor(this);
  }
}

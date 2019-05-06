package com.example.admin.nakedorigins.screens.splash;

import android.os.Bundle;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The Splash Presenter
 */
public class SplashPresenter extends Presenter<SplashContract.View, SplashContract.Interactor>
    implements SplashContract.Presenter {

  public SplashPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SplashContract.View onCreateView(Bundle data) {
    return SplashFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public SplashContract.Interactor onCreateInteractor() {
    return new SplashInteractor(this);
  }
}

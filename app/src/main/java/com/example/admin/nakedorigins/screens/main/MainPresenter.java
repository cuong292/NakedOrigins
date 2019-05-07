package com.example.admin.nakedorigins.screens.main;

import android.os.Bundle;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The Main Presenter
 */
public class MainPresenter extends Presenter<MainContract.View, MainContract.Interactor>
    implements MainContract.Presenter {

  public MainPresenter(ContainerView containerView) {
    super(containerView);
  }

  public MainPresenter(ContainerView containerView, Bundle data) {
    super(containerView, data);
  }

  @Override
  public MainContract.View onCreateView(Bundle data) {
    return MainFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public MainContract.Interactor onCreateInteractor() {
    return new MainInteractor(this);
  }
}

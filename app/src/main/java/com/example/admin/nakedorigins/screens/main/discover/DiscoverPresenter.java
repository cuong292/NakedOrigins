package com.example.admin.nakedorigins.screens.main.discover;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import android.os.Bundle;

/**
 * The Discover Presenter
 */
public class DiscoverPresenter extends Presenter<DiscoverContract.View, DiscoverContract.Interactor>
    implements DiscoverContract.Presenter {

  public DiscoverPresenter(ContainerView containerView) {
    super(containerView);
  }

  public DiscoverPresenter(ContainerView containerView, Bundle data) {
    super(containerView, data);
  }

  @Override
  public DiscoverContract.View onCreateView(Bundle data) {
    return DiscoverFragment.getInstance(data);
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public DiscoverContract.Interactor onCreateInteractor() {
    return new DiscoverInteractor(this);
  }
}

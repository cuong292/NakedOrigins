package com.example.admin.nakedorigins.screens.main.support.supportvideo;

import com.gemvietnam.base.viper.Presenter;

import android.os.Bundle;

import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The SupportVideo Presenter
 */
public class SupportVideoPresenter extends Presenter<SupportVideoContract.View, SupportVideoContract.Interactor>
    implements SupportVideoContract.Presenter {

  public SupportVideoPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SupportVideoContract.View onCreateView(Bundle data) {
    return SupportVideoFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public SupportVideoContract.Interactor onCreateInteractor() {
    return new SupportVideoInteractor(this);
  }
}

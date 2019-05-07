package com.example.admin.nakedorigins.screens.main.farmdetail;

import com.gemvietnam.base.viper.Presenter;

import android.os.Bundle;

import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The FarmDetail Presenter
 */
public class FarmDetailPresenter extends Presenter<FarmDetailContract.View, FarmDetailContract.Interactor>
    implements FarmDetailContract.Presenter {

  public FarmDetailPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public FarmDetailContract.View onCreateView(Bundle data) {
    return FarmDetailFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public FarmDetailContract.Interactor onCreateInteractor() {
    return new FarmDetailInteractor(this);
  }
}

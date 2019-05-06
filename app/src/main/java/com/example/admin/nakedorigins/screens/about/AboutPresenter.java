package com.example.admin.nakedorigins.screens.about;

import android.os.Bundle;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The About Presenter
 */
public class AboutPresenter extends Presenter<AboutContract.View, AboutContract.Interactor>
    implements AboutContract.Presenter {

  public AboutPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public AboutContract.View onCreateView(Bundle data) {
    return AboutFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public AboutContract.Interactor onCreateInteractor() {
    return new AboutInteractor(this);
  }
}

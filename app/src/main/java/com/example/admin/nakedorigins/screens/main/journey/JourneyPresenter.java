package com.example.admin.nakedorigins.screens.main.journey;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import android.os.Bundle;

/**
 * The Journey Presenter
 */
public class JourneyPresenter extends Presenter<JourneyContract.View, JourneyContract.Interactor>
    implements JourneyContract.Presenter {

  public JourneyPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public JourneyContract.View onCreateView(Bundle data) {
    return JourneyFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public JourneyContract.Interactor onCreateInteractor() {
    return new JourneyInteractor(this);
  }
}

package com.example.admin.nakedorigins.screens.main.support.supportmessage;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import android.os.Bundle;

/**
 * The SupportMessage Presenter
 */
public class SupportMessagePresenter extends Presenter<SupportMessageContract.View, SupportMessageContract.Interactor>
    implements SupportMessageContract.Presenter {

  public SupportMessagePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public SupportMessageContract.View onCreateView(Bundle data) {
    return SupportMessageFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public SupportMessageContract.Interactor onCreateInteractor() {
    return new SupportMessageInteractor(this);
  }
}

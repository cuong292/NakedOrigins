package com.example.admin.nakedorigins.screens.qrcodescan;

import com.gemvietnam.base.viper.Presenter;

import android.os.Bundle;

import com.gemvietnam.base.viper.interfaces.ContainerView;

/**
 * The QrCode Presenter
 */
public class QrCodePresenter extends Presenter<QrCodeContract.View, QrCodeContract.Interactor>
    implements QrCodeContract.Presenter {

  public QrCodePresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public QrCodeContract.View onCreateView(Bundle data) {
    return QrCodeFragment.getInstance();
  }

  @Override
  public void start() {
    // Start getting data here
  }

  @Override
  public QrCodeContract.Interactor onCreateInteractor() {
    return new QrCodeInteractor(this);
  }
}

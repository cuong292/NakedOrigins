package com.example.admin.nakedorigins.screens.qrcodescan;

import com.gemvietnam.base.viper.Interactor;

/**
 * The QrCode interactor
 */
class QrCodeInteractor extends Interactor<QrCodeContract.Presenter>
    implements QrCodeContract.Interactor {

  QrCodeInteractor(QrCodeContract.Presenter presenter) {
    super(presenter);
  }
}

package com.example.admin.nakedorigins.screens.qrcodescan;

import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;

/**
 * The QrCode Fragment
 */
public class QrCodeFragment extends ViewFragment<QrCodeContract.Presenter> implements QrCodeContract.View {

  public static QrCodeFragment getInstance() {
    return new QrCodeFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_qr_code;
  }
}

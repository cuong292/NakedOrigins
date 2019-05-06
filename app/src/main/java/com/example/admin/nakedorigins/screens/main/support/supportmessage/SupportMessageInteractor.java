package com.example.admin.nakedorigins.screens.main.support.supportmessage;

import com.gemvietnam.base.viper.Interactor;

/**
 * The SupportMessage interactor
 */
class SupportMessageInteractor extends Interactor<SupportMessageContract.Presenter>
    implements SupportMessageContract.Interactor {

  SupportMessageInteractor(SupportMessageContract.Presenter presenter) {
    super(presenter);
  }
}

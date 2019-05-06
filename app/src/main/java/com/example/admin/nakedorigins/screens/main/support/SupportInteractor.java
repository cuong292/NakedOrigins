package com.example.admin.nakedorigins.screens.main.support;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Support interactor
 */
class SupportInteractor extends Interactor<SupportContract.Presenter>
    implements SupportContract.Interactor {

  SupportInteractor(SupportContract.Presenter presenter) {
    super(presenter);
  }
}

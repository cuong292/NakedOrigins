package com.example.admin.nakedorigins.screens.main.farmdetail;

import com.gemvietnam.base.viper.Interactor;

/**
 * The FarmDetail interactor
 */
class FarmDetailInteractor extends Interactor<FarmDetailContract.Presenter>
    implements FarmDetailContract.Interactor {

  FarmDetailInteractor(FarmDetailContract.Presenter presenter) {
    super(presenter);
  }
}

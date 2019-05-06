package com.example.admin.nakedorigins.screens.main.journey;

import com.gemvietnam.base.viper.Interactor;

/**
 * The Journey interactor
 */
class JourneyInteractor extends Interactor<JourneyContract.Presenter>
    implements JourneyContract.Interactor {

  JourneyInteractor(JourneyContract.Presenter presenter) {
    super(presenter);
  }
}

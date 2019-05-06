package com.example.admin.nakedorigins.screens.about;

import com.gemvietnam.base.viper.Interactor;

/**
 * The About interactor
 */
class AboutInteractor extends Interactor<AboutContract.Presenter>
    implements AboutContract.Interactor {

  AboutInteractor(AboutContract.Presenter presenter) {
    super(presenter);
  }
}

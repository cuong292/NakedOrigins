package com.example.admin.nakedorigins.screens.main.discover.newfavourite;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

interface NewFavouriteContract {

	interface Interactor extends IInteractor<Presenter> {

	}

	interface View extends PresentView<Presenter> {

	}

	interface Presenter extends IPresenter<View, Interactor> {

	}

}




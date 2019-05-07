package com.example.admin.nakedorigins.screens.main.discover.newfavourite;

import android.os.Bundle;

import com.gemvietnam.base.viper.Presenter;
import com.gemvietnam.base.viper.interfaces.ContainerView;

public class NewFavouritePresenter extends Presenter<NewFavouriteContract.View, NewFavouriteContract.Interactor> implements NewFavouriteContract.Presenter {

	public NewFavouritePresenter(ContainerView containerView) {
		super(containerView);
	}

	public NewFavouritePresenter(ContainerView containerView, Bundle data) {
		super(containerView, data);
	}

	@Override
	public NewFavouriteContract.View onCreateView(Bundle data) {
		return NewFavouriteFragment.getInstance(data);
	}

	@Override
	public NewFavouriteContract.Interactor onCreateInteractor() {
		return new NewFavouriteInteractor(this);
	}

	@Override
	public void start() {
		// Start getting data here
	}

}
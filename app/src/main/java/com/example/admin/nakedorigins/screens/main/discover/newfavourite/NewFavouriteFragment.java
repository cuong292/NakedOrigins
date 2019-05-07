package com.example.admin.nakedorigins.screens.main.discover.newfavourite;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.admin.nakedorigins.customview.CustomRadarView;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;

import butterknife.BindView;

public class NewFavouriteFragment extends ViewFragment<NewFavouriteContract.Presenter> implements NewFavouriteContract.View {
	@BindView(R.id.custom_radar)
	CustomRadarView customRadarView;

	public static NewFavouriteFragment getInstance() {
		return new NewFavouriteFragment();
	}

	public static NewFavouriteFragment getInstance(Bundle data) {
		NewFavouriteFragment fragment = new NewFavouriteFragment();
		fragment.setArguments(data);
		return fragment;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_new_favourite;
	}

	@Override
	public void initLayout() {
		super.initLayout();

		customRadarView.setDataView(70, 55, 45, 35);
		customRadarView.setDataChangeListener(new CustomRadarView.OnDataChangeListener() {
			@Override
			public void onStartChange() {

			}

			@Override
			public void onChangeFinish(float bodyPercent, float acidityPercent, float aromaPercent, float bitternessPercent) {

			}
		});

		customRadarView.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				if (view.getId() == R.id.custom_radar) {
					view.getParent().requestDisallowInterceptTouchEvent(true);
					switch (event.getAction() & MotionEvent.ACTION_MASK) {
						case MotionEvent.ACTION_UP:
							view.getParent().requestDisallowInterceptTouchEvent(false);
							break;
					}
				}
				return false;
			}
		});
	}
}

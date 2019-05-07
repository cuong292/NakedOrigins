package com.example.admin.nakedorigins.screens.main.discover.newfavourite;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.nakedorigins.customview.CustomRadarView;
import com.example.admin.nakedorigins.data.dto.Coffee;
import com.example.admin.nakedorigins.screens.main.discover.DiscoverFragment;
import com.example.admin.nakedorigins.screens.main.discover.DiscoverPresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import butterknife.BindView;

public class NewFavouriteFragment extends ViewFragment<NewFavouriteContract.Presenter> implements NewFavouriteContract.View {

	@BindView(R.id.custom_radar)
	CustomRadarView customRadarView;

	@BindView(R.id.cf_name)
	TextView cfName;

	@BindView(R.id.img_cafe)
	ImageView imgCafe;

	private Coffee mCafe;

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
		mCafe = new Coffee();
		updateView();

		customRadarView.setDataView(mCafe.getBodyPercent(), mCafe.getAcidityPercent(), mCafe.getAromaPercent(), mCafe.getBitternessPercent());
		customRadarView.setDataChangeListener(new CustomRadarView.OnDataChangeListener() {
			@Override
			public void onStartChange() {

			}

			@Override
			public void onChangeFinish(float bodyPercent, float acidityPercent, float aromaPercent, float bitternessPercent) {
				Log.e("Finish", bodyPercent + ":" + acidityPercent + ":" + aromaPercent + ":" + bitternessPercent);
				updateCoffee(bodyPercent, acidityPercent, aromaPercent, bitternessPercent);
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

		imgCafe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putSerializable(DiscoverFragment.KEY_DATA, mCafe);
				bundle.putInt(DiscoverFragment.KEY_TYPE, 2);

				new DiscoverPresenter((ContainerView) getViewContext(), bundle).pushView();
			}
		});
	}

	private void updateCoffee(float bodyPercent, float acidityPercent, float aromaPercent, float bitternessPercent) {
		if (bodyPercent > 70 && acidityPercent <= 70 && acidityPercent > 40 && aromaPercent <= 40 && bitternessPercent <= 40) {
			mCafe.setName("Kati Kati Blend");
			mCafe.setResImg(R.drawable.cf_kati_kati);

		} else if (bodyPercent <= 40 && acidityPercent > 70 && aromaPercent > 70 && bitternessPercent > 40 && bitternessPercent <= 70) {
			mCafe.setName("Papua New Guine");
			mCafe.setResImg(R.drawable.cf_papua_new_guine);

		} else if (bodyPercent > 40 && bodyPercent <= 70 && acidityPercent <= 40 && aromaPercent > 70 && bitternessPercent > 70) {
			mCafe.setName("Pike Place Roast");
			mCafe.setResImg(R.drawable.cf_pike_place_roast);

		} else {
			mCafe.setName("New Favourite");
			mCafe.setResImg(R.drawable.cf_not_match);
		}

		updateView();
	}

	private void updateView() {
		cfName.setText(mCafe.getName());
		imgCafe.setImageResource(mCafe.getResImg());
	}
}

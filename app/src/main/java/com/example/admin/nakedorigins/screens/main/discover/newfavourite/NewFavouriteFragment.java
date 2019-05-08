package com.example.admin.nakedorigins.screens.main.discover.newfavourite;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.admin.nakedorigins.customview.CustomRadarView;
import com.example.admin.nakedorigins.data.dto.Coffee;
import com.example.admin.nakedorigins.screens.main.discover.DiscoverFragment;
import com.example.admin.nakedorigins.screens.main.discover.DiscoverPresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewFavouriteFragment extends ViewFragment<NewFavouriteContract.Presenter> implements NewFavouriteContract.View, ImageCoffeeAdapter.OnItemImageClickListener {

	@BindView(R.id.custom_radar)
	CustomRadarView customRadarView;

	@BindView(R.id.cf_name)
	TextView cfName;

	@BindView(R.id.vp_cafe)
	ViewPager vpCafe;

	private Coffee mCafe;
	private List<Coffee> mCoffeeList = new ArrayList<>();
	private ImageCoffeeAdapter mAdapter;
	private boolean isChangeData = false;

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
		initData();

		customRadarView.setDataChangeListener(new CustomRadarView.OnDataChangeListener() {
			@Override
			public void onStartChange() {
				isChangeData = true;
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
	}

	private void initData() {
		mCoffeeList.clear();
		Coffee katiCf = new Coffee();

		Coffee papuaCf = new Coffee();
		papuaCf.setId(2);
		papuaCf.setName("Papua New Guine");
		papuaCf.setResImg(R.drawable.cf_papua_new_guinea);
		papuaCf.setBodyPercent(60);
		papuaCf.setAcidityPercent(75);
		papuaCf.setAromaPercent(25);
		papuaCf.setBitternessPercent(45);

		Coffee pikeCf = new Coffee();
		pikeCf.setId(3);
		pikeCf.setName("Pike Place Roast");
		pikeCf.setResImg(R.drawable.cf_pike_place_roast);
		pikeCf.setBodyPercent(45);
		pikeCf.setAcidityPercent(60);
		pikeCf.setAromaPercent(83);
		pikeCf.setBitternessPercent(28);

		Coffee guatemalaCf = new Coffee();
		guatemalaCf.setId(4);
		guatemalaCf.setName("Flor Del Rosario");
		guatemalaCf.setResImg(R.drawable.cf_guatemala);
		guatemalaCf.setBodyPercent(30);
		guatemalaCf.setAcidityPercent(55);
		guatemalaCf.setAromaPercent(65);
		guatemalaCf.setBitternessPercent(80);

		Coffee verandaCf = new Coffee();
		verandaCf.setId(5);
		verandaCf.setName("Veranda Blend");
		verandaCf.setResImg(R.drawable.cf_veranda_blend);
		verandaCf.setBodyPercent(38);
		verandaCf.setAcidityPercent(66);
		verandaCf.setAromaPercent(52);
		verandaCf.setBitternessPercent(28);

		mCoffeeList.add(katiCf);
		mCoffeeList.add(papuaCf);
		mCoffeeList.add(pikeCf);
		mCoffeeList.add(guatemalaCf);
		mCoffeeList.add(verandaCf);

		mAdapter = new ImageCoffeeAdapter(getViewContext(), mCoffeeList, this);
		vpCafe.setAdapter(mAdapter);
		vpCafe.setPageMargin(35);
		vpCafe.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i1) {

			}

			@Override
			public void onPageSelected(int i) {
				if (isChangeData) {
					isChangeData = false;

				} else {
					mCafe = mCoffeeList.get(i);
					customRadarView.setDataView(mCafe.getBodyPercent(), mCafe.getAcidityPercent(),
							mCafe.getAromaPercent(), mCafe.getBitternessPercent(), true);
					updateView();
					Log.e(mCafe.getName(), "" + i);
				}
			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}
		});

		vpCafe.setCurrentItem(2);
		mCafe = mCoffeeList.get(2);
		customRadarView.setDataView(mCafe.getBodyPercent(), mCafe.getAcidityPercent(),
				mCafe.getAromaPercent(), mCafe.getBitternessPercent(), false);
		updateView();
	}

	private void updateCoffee(float bodyPercent, float acidityPercent, float aromaPercent, float bitternessPercent) {
		if (bodyPercent > 70 && acidityPercent <= 70 && aromaPercent <= 70 && bitternessPercent <= 70) {
			mCafe = mCoffeeList.get(0);
			vpCafe.setCurrentItem(0);

		} else if (bodyPercent <= 70 && acidityPercent > 70 && aromaPercent <= 70 && bitternessPercent <= 70) {
			mCafe = mCoffeeList.get(1);
			vpCafe.setCurrentItem(1);

		} else if (bodyPercent <= 70 && acidityPercent <= 70 && aromaPercent > 70 && bitternessPercent <= 70) {
			mCafe = mCoffeeList.get(2);
			vpCafe.setCurrentItem(2);

		} else if (bodyPercent <= 70 && acidityPercent <= 70 && aromaPercent <= 70 && bitternessPercent > 70) {
			mCafe = mCoffeeList.get(3);
			vpCafe.setCurrentItem(3);

		} else if ((bodyPercent > 75 && acidityPercent > 75 && aromaPercent > 75 && bitternessPercent > 75) ||
				(bodyPercent < 35 && acidityPercent < 35 && aromaPercent < 35 && bitternessPercent < 35)) {
			//not match

		} else {
			mCafe = mCoffeeList.get(4);
			vpCafe.setCurrentItem(4);
		}

		updateView();
	}

	private void updateView() {
		cfName.setText(mCafe.getName());
		//imgCafe.setImageResource(mCafe.getResImg());
	}

	@Override
	public void onItemImageClick(int position, Coffee item) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(DiscoverFragment.KEY_DATA, item);
		bundle.putInt(DiscoverFragment.KEY_TYPE, 2);

		new DiscoverPresenter((ContainerView) getViewContext(), bundle).pushView();
	}
}

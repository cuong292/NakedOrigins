package com.example.admin.nakedorigins.screens.main.discover;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.nakedorigins.data.dto.Coffee;
import com.example.admin.nakedorigins.screens.main.discover.newfavourite.NewFavouritePresenter;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import butterknife.BindView;

/**
 * The Discover Fragment
 */
public class DiscoverFragment extends ViewFragment<DiscoverContract.Presenter> implements DiscoverContract.View {
	public static final String KEY_DATA = "data";
	public static final String KEY_TYPE = "type";

	@BindView(R.id.cf_name)
	TextView tvCfName;

	@BindView(R.id.btn_next)
	Button btnNext;

	private Coffee mCafe;
	private int type = 1;

	public static DiscoverFragment getInstance() {
		return new DiscoverFragment();
	}

	public static DiscoverFragment getInstance(Bundle data) {
		DiscoverFragment fragment = new DiscoverFragment();
		fragment.setArguments(data);
		return fragment;
	}

	@Override
	protected void parseArgs(Bundle args) {
		super.parseArgs(args);
		mCafe = (Coffee) args.getSerializable(KEY_DATA);
		type = args.getInt(KEY_TYPE);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_discover;
	}

	@Override
	public void initLayout() {
		super.initLayout();
		if (mCafe == null) {
			mCafe = new Coffee();
		}

		if (type == 2) {
			btnNext.setText(getString(R.string.close));
		}

		tvCfName.setText(mCafe.getName());

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (type == 1) {
					new NewFavouritePresenter((ContainerView) getViewContext()).pushView();
					
				} else {
					getPresenter().back();
				}
			}
		});

	}
}

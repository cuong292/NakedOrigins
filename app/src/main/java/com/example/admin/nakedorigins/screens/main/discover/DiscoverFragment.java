package com.example.admin.nakedorigins.screens.main.discover;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.nakedorigins.screens.main.discover.newfavourite.NewFavouritePresenter;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import butterknife.BindView;

/**
 * The Discover Fragment
 */
public class DiscoverFragment extends ViewFragment<DiscoverContract.Presenter> implements DiscoverContract.View {
	@BindView(R.id.cf_name)
	TextView tvCfName;

	@BindView(R.id.btn_next)
	Button btnNext;

	public static DiscoverFragment getInstance() {
		return new DiscoverFragment();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_discover;
	}

	@Override
	public void initLayout() {
		super.initLayout();

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new NewFavouritePresenter((ContainerView) getViewContext()).pushView();
			}
		});

	}
}

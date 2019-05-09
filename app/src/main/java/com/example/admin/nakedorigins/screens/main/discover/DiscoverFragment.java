package com.example.admin.nakedorigins.screens.main.discover;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.nakedorigins.customview.CustomPercentView;
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

	@BindView(R.id.v_root)
	NestedScrollView vRoot;

	@BindView(R.id.v_parent)
	ConstraintLayout vParent;

	@BindView(R.id.title_first)
	TextView tvTitle;

	@BindView(R.id.cf_name)
	TextView tvCfName;

	@BindView(R.id.cp_acidity)
	CustomPercentView cpAcidity;

	@BindView(R.id.cp_body)
	CustomPercentView cpBody;

	@BindView(R.id.btn_next)
	Button btnNext;

	@BindView(R.id.close_iv)
	ImageView imgClose;

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
		imgClose.setVisibility(View.GONE);
		if (mCafe == null) {
			mCafe = new Coffee();
		}

		if (type == 2) {
			//tvTitle.setText("SINGLE ORIGIN");
			btnNext.setText(getString(R.string.close));
			imgClose.setVisibility(View.VISIBLE);
			imgClose.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mPresenter.back();
				}
			});
			vRoot.setBackgroundColor(getViewContext().getResources().getColor(R.color.bgDiscoverDetail));
			vParent.setBackgroundResource(R.drawable.bg_discover_detail);

		} else {
			//tvTitle.setText(R.string.what_you_re_drinking);
			vRoot.setBackgroundColor(getViewContext().getResources().getColor(R.color.bgDiscover));
			vParent.setBackgroundResource(R.drawable.bg_discover);
		}

		tvCfName.setText(mCafe.getName());
		cpAcidity.setPercent(mCafe.getAcidityPercent() / 20);
		cpBody.setPercent(mCafe.getBodyPercent() / 20);

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

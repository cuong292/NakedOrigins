package com.example.admin.nakedorigins.screens.main.discover.newfavourite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.nakedorigins.R;
import com.example.admin.nakedorigins.data.dto.Coffee;
import com.gemvietnam.utils.image.ImageUtils;

import java.util.List;

public class ImageCoffeeAdapter extends PagerAdapter {
	private Context mContext;
	private OnItemImageClickListener mListener;
	private List<Coffee> mData;

	public ImageCoffeeAdapter(Context context, List<Coffee> data, OnItemImageClickListener listener) {
		super();
		this.mContext = context;
		this.mData = data;
		this.mListener = listener;
	}

	public void updateData(List<Coffee> list) {
		mData = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public int getItemPosition(@NonNull Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
		return view == o;
	}

	@NonNull
	@Override
	public Object instantiateItem(@NonNull ViewGroup container, final int position) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_coffee, container, false);
		ImageView imageView = view.findViewById(R.id.img_cafe);
		final Coffee item = mData.get(position);

		if (item != null) {
			ImageUtils.loadImage(mContext, item.getResImg(), imageView, R.drawable.cf_not_match, R.drawable.cf_not_match, false);

		} else {
			ImageUtils.loadImage(mContext, R.drawable.cf_not_match, imageView, R.drawable.cf_not_match, R.drawable.cf_not_match, false);
		}

		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onItemImageClick(position, item);
			}
		});

		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
	}

	public interface OnItemImageClickListener {
		void onItemImageClick(int position, Coffee item);
	}
}

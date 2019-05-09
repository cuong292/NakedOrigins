package com.example.admin.nakedorigins.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.admin.nakedorigins.R;

public class CustomPercentView extends LinearLayout {
	private View[] mBean = new View[5];

	public CustomPercentView(Context context) {
		super(context);
		init();
	}

	public CustomPercentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomPercentView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public CustomPercentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.custom_percent_view, this);
		mBean[0] = findViewById(R.id.item1);
		mBean[1] = findViewById(R.id.item2);
		mBean[2] = findViewById(R.id.item3);
		mBean[3] = findViewById(R.id.item4);
		mBean[4] = findViewById(R.id.item5);
	}

	public void setPercent(int percent) {
		for (int i = 0; i < 5; i++) {
			if (i < percent) {
				mBean[i].setEnabled(true);

			} else {
				mBean[i].setEnabled(false);
			}
		}
	}
}

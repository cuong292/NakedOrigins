package com.example.admin.nakedorigins.screens.splash;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.nakedorigins.R;

public class SplashProductAdapter extends PagerAdapter {
  private OnProductClicked onClicked;

  public SplashProductAdapter(OnProductClicked onProductClicked) {
    this.onClicked = onProductClicked;
  }

  @Override
  public int getCount() {
    return 2;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, final int position) {
    View view = LayoutInflater.from(container.getContext()).inflate(R.layout.product_view, container, false);
    ImageView iv = view.findViewById(R.id.product_image_iv);
    if (position == 0) {
      iv.setImageDrawable(ContextCompat.getDrawable(container.getContext(), R.drawable.anh1));
    } else {
      iv.setImageDrawable(ContextCompat.getDrawable(container.getContext(), R.drawable.anh2));
    }
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (position == 0) {
          onClicked.onClick();
        }
      }
    });
    container.addView(view);
    return view;
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
    return view == o;
  }

  public interface OnProductClicked {
    void onClick();
  }
}

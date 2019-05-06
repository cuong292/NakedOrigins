package com.example.admin.nakedorigins.viewutilities;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class AlphaPagerTransformer implements ViewPager.PageTransformer {
  @Override
  public void transformPage(@NonNull View view, float position) {
    view.setTranslationX(view.getWidth() * -position);

    view.setTranslationX(-position * view.getWidth());

    view.setAlpha(1 - Math.abs(position));
  }

}

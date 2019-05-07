package com.example.admin.nakedorigins.screens.main.support;

import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.nakedorigins.screens.main.support.supportmessage.SupportMessagePresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import butterknife.BindView;

/**
 * The Support Fragment
 */
public class SupportFragment extends ViewFragment<SupportContract.Presenter> implements SupportContract.View {
  @BindView(R.id.support_iv)
  ImageView supportIv;

  private int xArea;
  private int yArea;

  public static SupportFragment getInstance() {
    return new SupportFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_support;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    Display display = getActivity().getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    xArea = 500;
    yArea = 1120;
    supportIv.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
          float xTouch = event.getX();
          float yTouch = event.getY();
          if (insideXArea(xTouch) && insideYArea(yTouch)) {
            new SupportMessagePresenter((ContainerView) getActivity()).pushView();
            return true;
          }
        }
        return false;
      }
    });
  }

  public boolean insideXArea(float point) {
    return xArea + 100 >= Math.round(point) && xArea - 100 <= Math.round(point);
  }

  public boolean insideYArea(float point) {
    return yArea + 100 >= Math.round(point) && yArea - 100 <= Math.round(point);
  }
}

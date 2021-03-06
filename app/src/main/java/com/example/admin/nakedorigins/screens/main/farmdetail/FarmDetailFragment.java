package com.example.admin.nakedorigins.screens.main.farmdetail;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.nakedorigins.screens.main.support.supportmessage.SupportMessagePresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import org.w3c.dom.Text;

import butterknife.BindView;

/**
 * The FarmDetail Fragment
 */
public class FarmDetailFragment extends ViewFragment<FarmDetailContract.Presenter> implements FarmDetailContract.View {
  @BindView(R.id.high_five_iv)
  ImageView highFiveTv;
  @BindView(R.id.cancel_tv)
  TextView cancelTv;
  @BindView(R.id.farmerTv)
  TextView farmerTv;
  @BindView(R.id.close_iv)
  ImageView closeIv;

  public static FarmDetailFragment getInstance() {
    return new FarmDetailFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_farm_detail;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    highFiveTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new SupportMessagePresenter((ContainerView) getActivity()).pushView();
      }
    });
    cancelTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });
    farmerTv.setText(Html.fromHtml("Meet " + "<i>" + "Azizi Okoye" + "</i>"));
    closeIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });
  }

}

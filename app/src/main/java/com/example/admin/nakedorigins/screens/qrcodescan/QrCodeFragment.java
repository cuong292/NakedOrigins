package com.example.admin.nakedorigins.screens.qrcodescan;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.admin.nakedorigins.screens.about.AboutPresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * The QrCode Fragment
 */
public class QrCodeFragment extends ViewFragment<QrCodeContract.Presenter> implements QrCodeContract.View {
  @BindView(R.id.camera_root_layout)
  FrameLayout rootView;
  @BindView(R.id.searching_tv)
  TextView mSearchingTv;

  private Timer timer;


  public static QrCodeFragment getInstance() {
    return new QrCodeFragment();
  }


  @Override
  protected int getLayoutId() {
    return R.layout.fragment_qr_code;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    QrCodeViewFragment fragment = new QrCodeViewFragment().setListener(new QrCodeViewFragment.OnScanQRSuccess() {
      @Override
      public void onDone() {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(1000);
        anim.setRepeatCount(0);
        mSearchingTv.startAnimation(anim);
        mSearchingTv.setVisibility(View.GONE);
        timer = new Timer();
        timer.schedule(new TimerTask() {
          @Override
          public void run() {
            redirectToAboutFragment();
          }
        }, 1000);
      }
    });

    mSearchingTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new AboutPresenter((ContainerView) getActivity()).pushViewWithAnimation(R.anim.fade_in, R.anim.fade_out);
      }
    });
    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.camera_root_layout, fragment);
    transaction.commit();
  }


  public void redirectToAboutFragment() {
    if (timer != null) {
      timer.cancel();
    }
    new AboutPresenter((ContainerView) getActivity()).pushViewWithAnimation(R.anim.fade_in, R.anim.fade_out);
  }
}

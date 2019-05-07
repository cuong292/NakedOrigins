package com.example.admin.nakedorigins.screens.main.support.supportvideo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.VideoView;

import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.utils.DialogUtils;

import java.net.URI;

import butterknife.BindView;

/**
 * The SupportVideo Fragment
 */
public class SupportVideoFragment extends ViewFragment<SupportVideoContract.Presenter> implements SupportVideoContract.View {
  @BindView(R.id.support_vv)
  VideoView videoView;

  public static SupportVideoFragment getInstance() {
    return new SupportVideoFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_support_video;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    Uri uri = Uri.parse("http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4");
    videoView.setVideoURI(uri);
    videoView.start();
    showProgress();
    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
      @Override
      public void onPrepared(MediaPlayer mp) {
        mp.start();
        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
          @Override
          public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            hideProgress();
            mp.start();
          }
        });
      }
    });

  }

}

package com.example.admin.nakedorigins.screens.main.support.supportvideo;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.admin.nakedorigins.screens.main.support.supportsplash.DiscoverSplashPresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;

import butterknife.BindView;

/**
 * The SupportVideo Fragment
 */
public class SupportVideoFragment extends ViewFragment<SupportVideoContract.Presenter> implements SupportVideoContract.View {
  @BindView(R.id.progrees_seekbar)
  SeekBar progressBar;
  @BindView(R.id.video_view)
  SurfaceView videoView;
  @BindView(R.id.mute_iv)
  ImageView muteIv;
  @BindView(R.id.checkout_tv)
  TextView checkoutTv;

  private MediaPlayer mPlayer;
  private boolean mIsMuted = false;
  private Handler mHandler;

  public static SupportVideoFragment getInstance() {
    return new SupportVideoFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_support_video;
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  public void initLayout() {
    super.initLayout();
    showProgress();
    mHandler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what == 123) {
          setupPlayer();
        }
      }
    };
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        Uri uri = Uri.parse("http://www.exit109.com/~dnn/clips/RW20seconds_1.mp4");
        mPlayer = MediaPlayer.create(getViewContext(), uri);
        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MOVIE).build();
        mPlayer.setAudioAttributes(attributes);
        Message message = new Message();
        message.what = 123;
        mHandler.sendMessage(message);
        mPlayer.start();
      }
    });
    thread.start();


    progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(final SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
          mPlayer.seekTo(progress);
          mPlayer.start();
          playCycle();
        }
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

    muteIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mIsMuted) {
          mIsMuted = false;
          muteIv.setImageResource(R.drawable.ic_mute);
          mPlayer.setVolume(1, 1);
        } else {
          mIsMuted = true;
          muteIv.setImageResource(R.drawable.ic_unmute);
          mPlayer.setVolume(0, 0);
        }
      }
    });

    checkoutTv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPlayer.stop();
        new DiscoverSplashPresenter((ContainerView) getActivity()).pushViewWithAnimation(R.anim.fade_in, R.anim.fade_out);
      }
    });
  }

  public void playCycle() {
    progressBar.setProgress(mPlayer.getCurrentPosition());
    if (mPlayer.isPlaying()) {
      Runnable runnable = new Runnable() {
        @Override
        public void run() {
          playCycle();
        }
      };
      Handler thread = new Handler();
      thread.postDelayed(runnable, 1000);
    }
  }

  public void setupPlayer() {
    SurfaceHolder holder = videoView.getHolder();
    mPlayer.setDisplay(holder);
    hideProgress();
    progressBar.setMax(mPlayer.getDuration());
    playCycle();
    mPlayer.start();
  }

  @Override
  public void onStop() {
    super.onStop();
    mPlayer.stop();
  }
}

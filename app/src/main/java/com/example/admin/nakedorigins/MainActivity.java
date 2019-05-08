package com.example.admin.nakedorigins;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.admin.nakedorigins.screens.SplashActivity;
import com.example.admin.nakedorigins.screens.qrcodescan.QrCodePresenter;
import com.gemvietnam.base.ContainerActivity;
import com.gemvietnam.base.viper.ViewFragment;
import com.gemvietnam.utils.ActivityUtils;
import com.gemvietnam.utils.PermissionUtils;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends ContainerActivity {

  @Override
  public ViewFragment onCreateFirstFragment() {
    return (ViewFragment) new QrCodePresenter(this).getFragment();
  }
}

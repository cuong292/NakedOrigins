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
import com.gemvietnam.utils.ActivityUtils;
import com.gemvietnam.utils.PermissionUtils;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
  private ZXingScannerView mScannerView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mScannerView = new ZXingScannerView(this);
    setContentView(mScannerView);
  }

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mScannerView.setResultHandler(this);
    mScannerView.setBorderColor(R.color.colorMenuDisable);
    mScannerView.setBorderCornerRadius(20);
    mScannerView.setBorderStrokeWidth(20);
    mScannerView.setLaserEnabled(false);
    if (PermissionUtils.checkToRequest(this, Manifest.permission.CAMERA, 200)) {
      mScannerView.startCamera();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    mScannerView.stopCamera();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void handleResult(Result rawResult) {
    ActivityUtils.startActivity(this, SplashActivity.class);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      mScannerView.startCamera();
    }
  }
}

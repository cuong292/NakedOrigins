package com.example.admin.nakedorigins.screens.qrcodescan;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.nakedorigins.R;
import com.gemvietnam.utils.PermissionUtils;
import com.google.zxing.Result;

import java.io.Serializable;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrCodeViewFragment extends Fragment implements ZXingScannerView.ResultHandler {
  private ZXingScannerView mScannerView;
  private OnScanQRSuccess onScanQRSuccess;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mScannerView = new ZXingScannerView(container.getContext());
    mScannerView.setResultHandler(this);
    mScannerView.setBorderColor(R.color.colorMenuDisable);
    mScannerView.setBorderCornerRadius(20);
    mScannerView.setBorderStrokeWidth(20);
    mScannerView.setLaserEnabled(false);
    mScannerView.setMaskColor(Color.TRANSPARENT);
    if (!PermissionUtils.needRequestPermissions(container.getContext(), this, new String[]{Manifest.permission.CAMERA}, 200)) {
      mScannerView.startCamera();
    }
    return mScannerView;
  }

  @Override
  public void handleResult(Result rawResult) {
    onScanQRSuccess.onDone();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      mScannerView.startCamera();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    mScannerView.stopCamera();
  }

  public interface OnScanQRSuccess {
    void onDone();
  }

  public QrCodeViewFragment setListener(OnScanQRSuccess onDone) {
    this.onScanQRSuccess = onDone;
    return this;
  }
}

package com.gemvietnam.base;

import com.gemvietnam.utils.ActivityUtils;
import com.gemvietnam.utils.DialogUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Base activity
 * Created by neo on 2/5/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

  private OnBackStackChangedListener mOnBackStackChangedListener = new OnBackStackChangedListener() {
    @Override
    public void onBackStackChanged() {
      onFragmentDisplay();
    }
  };

  public static Fragment getTopFragment(FragmentManager manager) {
    if (manager != null) {
      if (manager.getBackStackEntryCount() > 0) {
        FragmentManager.BackStackEntry backStackEntry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
        if (null != backStackEntry) {
          return manager.findFragmentByTag(backStackEntry.getName());
        }
      } else {
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null && fragments.size() > 0) {
          return fragments.get(0);
        }
      }
    }
    return null;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//    overridePendingTransition(CoreDefault.ANIM_IN, CoreDefault.ANIM_OUT);
    setContentView(getLayoutId());
    // Inject views
    ButterKnife.bind(this);

    // Prepare layout
    initLayout();
    getSupportFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);
  }

  @Override
  protected void onResume() {
    super.onResume();
    onFragmentDisplay();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    getSupportFragmentManager().removeOnBackStackChangedListener(mOnBackStackChangedListener);
  }

  private void onFragmentDisplay() {
    new Handler().post(new Runnable() {
      @Override
      public void run() {
        Fragment fragment = getTopFragment(getSupportFragmentManager());
        if (fragment instanceof BaseFragment) {
          ((BaseFragment) fragment).onDisplay();
        }
      }
    });
  }

  public void initLayout() {
  }

  public void showAlertDialog(String message) {
    DialogUtils.showErrorAlert(this, message);
  }

  public void showProgress() {
    DialogUtils.showProgressDialog(this);
  }

  public void hideProgress() {
    DialogUtils.dismissProgressDialog();
  }

  public void onRequestError(String errorCode, String errorMessage) {
    DialogUtils.showErrorAlert(this, errorMessage);
    hideProgress();
  }

  public void onRequestSuccess() {
    hideProgress();
  }

  /**
   * Return layout resource id for activity
   */
  protected abstract int getLayoutId();

  /**
   * Hide keyboard of current focus view
   */
  public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view == null) {
      view = findViewById(android.R.id.content);
    }
    if (view != null) {
      InputMethodManager imm =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
  }

  /**
   * Hide keyboard of current focus view
   */
  public void hideKeyboard(View view) {
    if (view != null) {
      InputMethodManager imm =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
  }

  /**
   * Show keyboard for {@link EditText}
   */
  public void showKeyboard(EditText editText) {
    editText.requestFocus();
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
  }

  public void addFragment(int containerId, BaseFragment fragment, boolean addToBackStack,
                          String tag) {
    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, containerId,
        addToBackStack, tag, false);
  }

  public void addFragmentWithCustomAnim(int containerId, BaseFragment fragment, boolean addToBackStack,
                                        String tag, int animIn, int animOut) {
    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, containerId,
        addToBackStack, tag, false, animIn, animOut);
  }

  public void addChildFragment(int containerId, FragmentManager fragmentManager, BaseFragment fragment, boolean addToBackStack,
                               String tag, boolean loadExisted) {
    ActivityUtils.addFragmentToActivity(fragmentManager, fragment, containerId,
        addToBackStack, tag, loadExisted);
  }

  public void addFragment(int containerId, BaseFragment fragment, boolean addToBackStack, boolean loadExisted) {
    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, containerId,
        addToBackStack, fragment.getClass().getSimpleName(), loadExisted);
  }

  public void addFragment(int containerId, BaseFragment fragment, boolean addToBackStack) {
    addFragment(containerId, fragment, addToBackStack, fragment.getClass().getSimpleName());
  }

  public void addFragment(int containerId, BaseFragment fragment) {
    addFragment(containerId, fragment, false, null);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    FragmentManager manager = getSupportFragmentManager();
    /*if (manager != null && manager.getFragments() != null && manager.getFragments().size() > 0) {
      for (Fragment fragment : manager.getFragments()) {
        if (fragment != null) {
          fragment.onActivityResult(requestCode, resultCode, data);
          FragmentManager fragmentManager = fragment.getChildFragmentManager();

        }
      }
    }*/

    onHandleFragmentResult(manager, requestCode, resultCode, data);
  }

  private void onHandleFragmentResult(FragmentManager manager, int requestCode, int resultCode, Intent data) {
    if (manager != null && manager.getFragments() != null && manager.getFragments().size() > 0) {
      for (Fragment fragment : manager.getFragments()) {
        if (fragment != null) {
          fragment.onActivityResult(requestCode, resultCode, data);
          FragmentManager childManager = fragment.getChildFragmentManager();
          onHandleFragmentResult(childManager, requestCode, resultCode, data);
        }
      }
    }
  }
}

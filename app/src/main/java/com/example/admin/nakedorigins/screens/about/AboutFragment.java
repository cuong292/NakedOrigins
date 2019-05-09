package com.example.admin.nakedorigins.screens.about;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.example.admin.nakedorigins.custom.CustomTypefaceSpan;
import com.example.admin.nakedorigins.screens.SplashActivity;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.utils.ActivityUtils;

import butterknife.BindView;

/**
 * The About Fragment
 */
public class AboutFragment extends ViewFragment<AboutContract.Presenter> implements AboutContract.View {
  @BindView(R.id.about_root_view)
  View view;
  @BindView(R.id.explore_coffee_tv)
  TextView exploreCoffeeTv;
  @BindView(R.id.coffee_brand_tv)
  TextView coffeeBrandTv;

  public static AboutFragment getInstance() {
    return new AboutFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_about;
  }

  @Override
  public void initLayout() {
    super.initLayout();
//    Spannable spannable = new SpannableString("Explore"+" The History Of Your Coffee");
//    Typeface font1 = Typeface.createFromAsset(getViewContext().getAssets(), "font/playfair_display_italic.ttf");
//    Typeface font2 = Typeface.createFromAsset(getViewContext().getAssets(), "font/playfair_display_regular.ttf");
//
//    spannable.setSpan(new CustomTypefaceSpan("playfair_display_italic",font1),0,7,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    spannable.setSpan(new CustomTypefaceSpan("playfair_display_regular",font2),7,34,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//    exploreCoffeeTv.setText(spannable.toString());
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.RGB_565;
    Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.about_bg, options);
    view.setBackground(new BitmapDrawable(getActivity().getResources(), bitmap));
    bitmap = null;
    System.gc();
    exploreCoffeeTv.setText(Html.fromHtml("<i>" + "Explore" + "</i>" + " The History Of Your Coffee"));
    coffeeBrandTv.setText(Html.fromHtml("<i>" + "Kati Kati" + "</i>" + " Blend"));
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ActivityUtils.startActivity(getContext(), SplashActivity.class);
        getActivity().finish();
      }
    });
  }
}

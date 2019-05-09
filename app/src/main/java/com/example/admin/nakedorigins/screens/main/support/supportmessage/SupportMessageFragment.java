package com.example.admin.nakedorigins.screens.main.support.supportmessage;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.nakedorigins.customview.TagView;
import com.example.admin.nakedorigins.data.Tag;
import com.example.admin.nakedorigins.screens.main.support.supportvideo.SupportVideoPresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.StringUtils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * The SupportMessage Fragment
 */
public class SupportMessageFragment extends ViewFragment<SupportMessageContract.Presenter> implements SupportMessageContract.View {
  @BindView(R.id.suggest_tag)
  TagView suggestTagView;
  @BindView(R.id.support_message_edt)
  TextView supportEdt;
  @BindView(R.id.skip_tv)
  TextView tvSkip;
  @BindView(R.id.send_view)
  View sendView;
  @BindView(R.id.close_iv)
  ImageView closeIv;

  public static SupportMessageFragment getInstance() {
    return new SupportMessageFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_support_message;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    suggestTagView.removeAll();
    suggestTagView.addTag(new Tag("Nice coffee!"));
    suggestTagView.addTag(new Tag("Hi-5! \\u270B"));
    suggestTagView.addTag(new Tag("Support"));
    suggestTagView.addTag(new Tag("Love It!"));
    suggestTagView.addTag(new Tag("Thanks For Your Hard Work! \\uD83D\\uDC4D"));
    suggestTagView.setOnTagClickListener(new TagView.OnTagClickListener() {
      @Override
      public void onTagClick(Tag tag, int position) {
        supportEdt.setText(StringEscapeUtils.unescapeEcmaScript(tag.text));
      }
    });

    supportEdt.addTextChangedListener(new TextWatcher() {

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(final Editable s) {
        if (StringUtils.isEmpty(s.toString())) {
          sendView.setVisibility(View.GONE);
          tvSkip.setVisibility(View.VISIBLE);
        } else {
          sendView.setVisibility(View.VISIBLE);
          tvSkip.setVisibility(View.GONE);
        }
      }
    });

    sendView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new SupportVideoPresenter((ContainerView) getActivity()).pushViewWithAnimation(R.anim.fade_in, R.anim.fade_out);
      }
    });

    tvSkip.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new SupportVideoPresenter((ContainerView) getActivity()).pushViewWithAnimation(R.anim.fade_in, R.anim.fade_out);
      }
    });

    closeIv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPresenter.back();
      }
    });
  }
}

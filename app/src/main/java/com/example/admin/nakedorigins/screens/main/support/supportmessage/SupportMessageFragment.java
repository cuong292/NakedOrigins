package com.example.admin.nakedorigins.screens.main.support.supportmessage;

import com.example.admin.nakedorigins.customview.TagView;
import com.example.admin.nakedorigins.data.Tag;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;

import butterknife.BindView;

/**
 * The SupportMessage Fragment
 */
public class SupportMessageFragment extends ViewFragment<SupportMessageContract.Presenter> implements SupportMessageContract.View {
  @BindView(R.id.suggest_tag)
  TagView suggestTagView;

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
    suggestTagView.addTag(new Tag("Nice!"));
    suggestTagView.addTag(new Tag("Hi-5!"));
    suggestTagView.addTag(new Tag("Support"));
    suggestTagView.addTag(new Tag("Thanks For Your Hard Work!"));
    suggestTagView.addTag(new Tag("Love It!"));
  }
}

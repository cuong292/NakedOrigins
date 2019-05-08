package com.example.admin.nakedorigins.screens.main.journey;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.nakedorigins.data.dto.FarmCoffeeDTO;
import com.example.admin.nakedorigins.data.dto.ProcessDTO;
import com.example.admin.nakedorigins.screens.main.farmdetail.FarmDetailPresenter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.RecyclerUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * The Journey Fragment
 */
public class JourneyFragment extends ViewFragment<JourneyContract.Presenter> implements JourneyContract.View {
  @BindView(R.id.farm_coffee_rv)
  RecyclerView mFarmRv;
  @BindView(R.id.root_view)
  NestedScrollView view;
  @BindView(R.id.process_name2_tv)
  TextView processTv;
  @BindView(R.id.question_tv)
  TextView questionTv;
  @BindView(R.id.process_detail1_tv)
  TextView process1;
  @BindView(R.id.header_bg)
  ImageView headerIv;
  @BindView(R.id.coffee1)
  ImageView coffeeIv;
  @BindView(R.id.coffee2)
  ImageView coffee2Iv;

  public static JourneyFragment getInstance() {
    return new JourneyFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_journey;
  }

  @Override
  public void initLayout() {
    super.initLayout();
    List<FarmCoffeeDTO> items = new ArrayList<>();
    items.add(new FarmCoffeeDTO("Azizi Okoye’s Farm", "Contributed to 45% of the Kati Kati Blend"));
    items.add(new FarmCoffeeDTO("Zelalem Tadesse’s Farm", "Contributed to 55% of the Kati Kati Blend"));
    CoffeeFarmAdapter adapter = new CoffeeFarmAdapter(items, new CoffeeFarmAdapter.OnDetailClick() {
      @Override
      public void onClick() {
        new FarmDetailPresenter((ContainerView) getActivity()).pushView();
      }
    });
    RecyclerUtils.setupVerticalRecyclerView(getViewContext(), mFarmRv);
    mFarmRv.setAdapter(adapter);
    mFarmRv.setNestedScrollingEnabled(false);
    processTv.setText(Html.fromHtml("The " + "<i>" + "Final" + "</i>" + " Touches"));
    questionTv.setText(Html.fromHtml("Where’s My " + "<i>" + "Coffee" + "</i>" + " From?"));
    process1.setText(Html.fromHtml(getString(R.string.process2)));
    Picasso.with(getViewContext()).load(R.drawable.header_image).into(headerIv);
    Picasso.with(getViewContext()).load(R.drawable.may_cafe).into(coffeeIv);
    Picasso.with(getViewContext()).load(R.drawable.may_cafe).into(coffee2Iv);
  }
}

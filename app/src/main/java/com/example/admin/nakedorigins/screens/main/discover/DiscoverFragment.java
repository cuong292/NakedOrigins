package com.example.admin.nakedorigins.screens.main.discover;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;

import com.example.admin.nakedorigins.data.dto.FarmCoffeeDTO;
import com.example.admin.nakedorigins.data.dto.ProcessDTO;
import com.example.admin.nakedorigins.screens.main.farmdetail.FarmDetailPresenter;
import com.example.admin.nakedorigins.screens.main.journey.CoffeeFarmAdapter;
import com.example.admin.nakedorigins.screens.main.journey.ProcessAdapter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.utils.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * The Discover Fragment
 */
public class DiscoverFragment extends ViewFragment<DiscoverContract.Presenter> implements DiscoverContract.View {
  @BindView(R.id.farm_coffee_rv)
  RecyclerView mFarmRv;
  @BindView(R.id.root_view)
  NestedScrollView view;
  @BindView(R.id.coffee_process_rv)
  RecyclerView mCoffeeProcessRv;

  public static DiscoverFragment getInstance() {
    return new DiscoverFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_discover;
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
    RecyclerUtils.setupVerticalRecyclerView(getViewContext(), mCoffeeProcessRv);
    mFarmRv.setAdapter(adapter);
    mFarmRv.setNestedScrollingEnabled(false);
    mCoffeeProcessRv.setNestedScrollingEnabled(false);
    List<ProcessDTO> processItems = new ArrayList<>();
    processItems.add(new ProcessDTO("The Final Touches", "The Brewing Process", "Ground yesterday, your cup of Kati Kati blend was brewed at 95 C, to extract all the flavour from the coffee."));
    ProcessAdapter processAdapter = new ProcessAdapter(processItems);
    mCoffeeProcessRv.setAdapter(processAdapter);
  }

}

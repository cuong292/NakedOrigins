package com.example.admin.nakedorigins.screens.main.journey;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.admin.nakedorigins.screens.dto.FarmCoffeeDTO;
import com.example.admin.nakedorigins.screens.main.discover.CoffeeFarmAdapter;
import com.gemvietnam.base.viper.ViewFragment;
import com.example.admin.nakedorigins.R;
import com.gemvietnam.utils.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * The Journey Fragment
 */
public class JourneyFragment extends ViewFragment<JourneyContract.Presenter> implements JourneyContract.View {
  @BindView(R.id.farm_coffee_rv)
  RecyclerView mFarmRv;

  public static JourneyFragment getInstance() {
    return new JourneyFragment();
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
    CoffeeFarmAdapter adapter = new CoffeeFarmAdapter(items);
    RecyclerUtils.setupVerticalRecyclerView(getViewContext(), mFarmRv);
    mFarmRv.setAdapter(adapter);
    mFarmRv.setNestedScrollingEnabled(false);
    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(getViewContext(), "Clicked", Toast.LENGTH_SHORT);
      }
    });
  }
}

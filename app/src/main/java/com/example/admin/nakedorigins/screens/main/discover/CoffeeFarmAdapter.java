package com.example.admin.nakedorigins.screens.main.discover;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.admin.nakedorigins.R;
import com.example.admin.nakedorigins.screens.dto.FarmCoffeeDTO;

import java.util.List;

public class CoffeeFarmAdapter extends BaseQuickAdapter<FarmCoffeeDTO, BaseViewHolder> {

  public CoffeeFarmAdapter(@Nullable List<FarmCoffeeDTO> data) {
    super(R.layout.item_view_farm_info, data);
  }

  @Override
  protected void convert(BaseViewHolder helper, FarmCoffeeDTO item) {
    helper.setText(R.id.farm_name_tv, item.getFarmName());
    helper.setText(R.id.farm_description_tv, item.getFarmDescription());
    helper.getView(R.id.farm_detail_iv).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

      }
    });
  }
}

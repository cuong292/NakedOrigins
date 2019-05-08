package com.example.admin.nakedorigins.screens.main.journey;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.admin.nakedorigins.R;
import com.example.admin.nakedorigins.data.dto.FarmCoffeeDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoffeeFarmAdapter extends BaseQuickAdapter<FarmCoffeeDTO, BaseViewHolder> {

  private OnDetailClick clicked;

  public CoffeeFarmAdapter(@Nullable List<FarmCoffeeDTO> data, OnDetailClick onDetailClick) {
    super(R.layout.item_view_farm_info, data);
    this.clicked = onDetailClick;
  }

  @Override
  protected void convert(BaseViewHolder helper, FarmCoffeeDTO item) {
    helper.setText(R.id.farm_name_tv, item.getFarmName());
    helper.setText(R.id.farm_description_tv, item.getFarmDescription());
    if (helper.getLayoutPosition() == 0) {
      Picasso.with(mContext).load(R.drawable.aizi_farm).into((ImageView) helper.getView(R.id.percent_iv));
    } else {
      Picasso.with(mContext).load(R.drawable.zealalem_farm).into((ImageView) helper.getView(R.id.percent_iv));
    }
    helper.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        clicked.onClick();
      }
    });
  }

  public interface OnDetailClick {
    void onClick();
  }
}

package com.example.admin.nakedorigins.screens.main.discover;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.admin.nakedorigins.R;
import com.example.admin.nakedorigins.screens.dto.ProcessDTO;

import java.util.List;

public class ProcessAdapter extends BaseQuickAdapter<ProcessDTO, BaseViewHolder> {
  public ProcessAdapter(@Nullable List<ProcessDTO> data) {
    super(R.layout.item_view_coffee_process, data);
  }

  @Override
  protected void convert(BaseViewHolder helper, ProcessDTO item) {
    helper.setText(R.id.process_name_tv, item.getProcessName());
    helper.setText(R.id.process_tv, item.getProcessStep());
    helper.setText(R.id.process_detail_tv, item.getProcessDescription());
  }
}

package com.example.admin.nakedorigins.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefWrapper {
  private SharedPreferences sharedPreferences;
  public static final String CURRENT_TAB = "current_tab";

  public PrefWrapper(Context context) {
    sharedPreferences = context.getSharedPreferences("DemoCoffe", Context.MODE_PRIVATE);
  }

  public int getCurrentTab() {
    return sharedPreferences.getInt(CURRENT_TAB, 0);
  }

  public void setCurrentTab(int tab) {
    sharedPreferences.edit().putInt(CURRENT_TAB, tab).apply();
  }


}

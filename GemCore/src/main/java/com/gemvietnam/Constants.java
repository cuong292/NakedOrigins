package com.gemvietnam;

import android.graphics.Color;

/**
 * Common constants
 * Created by neo on 7/18/2016.
 */
public interface Constants {
  String EMPTY_STRING = "";
  String GOOGLE_MAP_GEOCODE_URL = "http://maps.google.com/maps/api/geocode/";
  String GOOGLE_API_URL = "https://www.googleapis.com/";
  String OAUTH2_API_URL = "https://login.microsoftonline.com/common/oauth2/v2.0/";

  public static final float DEFAULT_LINE_MARGIN = 5;
  public static final float DEFAULT_TAG_MARGIN = 5;
  public static final float DEFAULT_TAG_TEXT_PADDING_LEFT = 4;
  public static final float DEFAULT_TAG_TEXT_PADDING_TOP = 4;
  public static final float DEFAULT_TAG_TEXT_PADDING_RIGHT = 4;
  public static final float DEFAULT_TAG_TEXT_PADDING_BOTTOM = 4;

  public static final float DEFAULT_TAG_TEXT_SIZE = 14f;
  public static final float DEFAULT_TAG_DELETE_INDICATOR_SIZE = 14f;
  public static final float DEFAULT_TAG_LAYOUT_BORDER_SIZE = 0f;
  public static final float DEFAULT_TAG_RADIUS = 100;
  public static final int DEFAULT_TAG_LAYOUT_COLOR = Color.parseColor("#D8D8D8");
  public static final int DEFAULT_TAG_LAYOUT_COLOR_PRESS = Color.parseColor("#88363636");
  public static final int DEFAULT_TAG_TEXT_COLOR = Color.parseColor("#030303");
  public static final int DEFAULT_TAG_DELETE_INDICATOR_COLOR = Color.parseColor("#ffffff");
  public static final int DEFAULT_TAG_LAYOUT_BORDER_COLOR = Color.parseColor("#ffffff");
  public static final String DEFAULT_TAG_DELETE_ICON = "×";
  public static final boolean DEFAULT_TAG_IS_DELETABLE = false;

}

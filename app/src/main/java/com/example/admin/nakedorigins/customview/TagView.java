package com.example.admin.nakedorigins.customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.nakedorigins.R;
import com.example.admin.nakedorigins.data.Tag;
import com.example.admin.nakedorigins.util.Utils;
import com.gemvietnam.Constants;

import java.util.ArrayList;
import java.util.List;

public class TagView extends RelativeLayout {

  /**
   * tag list
   */
  private List<Tag> mTags = new ArrayList<>();

  /**
   * System Service
   */
  private LayoutInflater mInflater;
  private ViewTreeObserver mViewTreeObserber;

  /**
   * listeners
   */
  private OnTagClickListener mClickListener;
  private OnTagDeleteListener mDeleteListener;
  private OnTagLongClickListener mTagLongClickListener;

  /**
   * view size param
   */
  private int mWidth;

  /**
   * layout initialize flag
   */
  private boolean mInitialized = false;

  /**
   * custom layout param
   */
  private int lineMargin;
  private int tagMargin;
  private int textPaddingLeft;
  private int textPaddingRight;
  private int textPaddingTop;
  private int textPaddingBottom;


  /**
   * constructor
   *
   * @param ctx
   */
  public TagView(Context ctx) {
    super(ctx, null);
    initialize(ctx, null, 0);
  }

  /**
   * constructor
   *
   * @param ctx
   * @param attrs
   */
  public TagView(Context ctx, AttributeSet attrs) {
    super(ctx, attrs);
    initialize(ctx, attrs, 0);
  }

  /**
   * constructor
   *
   * @param ctx
   * @param attrs
   * @param defStyle
   */
  public TagView(Context ctx, AttributeSet attrs, int defStyle) {
    super(ctx, attrs, defStyle);
    initialize(ctx, attrs, defStyle);
  }

  /**
   * initalize instance
   *
   * @param ctx
   * @param attrs
   * @param defStyle
   */
  private void initialize(Context ctx, AttributeSet attrs, int defStyle) {
    mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mViewTreeObserber = getViewTreeObserver();
    mViewTreeObserber.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        if (!mInitialized) {
          mInitialized = true;
          drawTags();
        }
      }
    });

    // get AttributeSet
    TypedArray typeArray = ctx.obtainStyledAttributes(attrs, R.styleable.TagView, defStyle, defStyle);
    this.lineMargin = (int) typeArray.getDimension(R.styleable.TagView_lineMargin, Utils.dipToPx(this.getContext(), Constants.DEFAULT_LINE_MARGIN));
    this.tagMargin = (int) typeArray.getDimension(R.styleable.TagView_tagMargin, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_MARGIN));
    this.textPaddingLeft = (int) typeArray.getDimension(R.styleable.TagView_textPaddingLeft, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_TEXT_PADDING_LEFT));
    this.textPaddingRight = (int) typeArray.getDimension(R.styleable.TagView_textPaddingRight, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_TEXT_PADDING_RIGHT));
    this.textPaddingTop = (int) typeArray.getDimension(R.styleable.TagView_textPaddingTop, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_TEXT_PADDING_TOP));
    this.textPaddingBottom = (int) typeArray.getDimension(R.styleable.TagView_textPaddingBottom, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_TEXT_PADDING_BOTTOM));
    typeArray.recycle();
  }

  /**
   * onSizeChanged
   *
   * @param w
   * @param h
   * @param oldw
   * @param oldh
   */
  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int width = getMeasuredWidth();
    if (width <= 0)
      return;
    mWidth = getMeasuredWidth();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawTags();
  }

  /**
   * tag draw
   */
  private void drawTags() {

    if (!mInitialized) {
      return;
    }

    // clear all tag
    removeAllViews();

    // layout padding left & layout padding right
    float total = getPaddingLeft() + getPaddingRight();

    int listIndex = 1;// List Index
    int indexBottom = 1;// The Tag to add below
    int indexHeader = 1;// The header tag of this line
    Tag tagPre = null;
    for (Tag item : mTags) {
      final int position = listIndex - 1;
      final Tag tag = item;

      View tagLayout = mInflater.inflate(R.layout.tagview_item, null);
      tagLayout.setId(listIndex);

      // tag text
      DisplayMetrics displaymetrics = new DisplayMetrics();
      ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
      TextView tagView = tagLayout.findViewById(R.id.tv_tag);
      tagView.setText(tag.text);

      tagLayout.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if (mClickListener != null) {
            mClickListener.onTagClick(tag, position);
          }
          v.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_border_black_pink_solid));
        }
      });

      tagLayout.setOnLongClickListener(new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          if (mTagLongClickListener != null) {
            mTagLongClickListener.onTagLongClick(tag, position);
          }
          return true;
        }
      });

      // calculate　of tag layout width
      float tagWidth = tagView.getPaint().measureText(tag.text) + textPaddingLeft + textPaddingRight;
      tagWidth += Utils.dipToPx(getContext(), 16);
      // tagView padding (left & right)

      // deletable text
//            LinearLayout.LayoutParams deleteParams = (LinearLayout.LayoutParams) deletableView.getLayoutParams();
//            deleteParams.setMargins(0, textPaddingTop, 0, textPaddingBottom);
//            deletableView.setLayoutParams(deleteParams);

      tagWidth += Utils.dipToPx(getContext(), 24);

      LayoutParams tagParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      //add margin of each line
      tagParams.bottomMargin = lineMargin;

      if (mWidth <= total + tagWidth + Utils.dipToPx(this.getContext(), 2)) {
        //need to add in new line
        if (tagPre != null) tagParams.addRule(RelativeLayout.BELOW, indexBottom);
        // initialize total param (layout padding left & layout padding right)
        total = getPaddingLeft() + getPaddingRight();
        indexBottom = listIndex;
        indexHeader = listIndex;
      } else {
        //no need to new line
        tagParams.addRule(RelativeLayout.ALIGN_TOP, indexHeader);
        //not header of the line
        if (listIndex != indexHeader) {
          tagParams.addRule(RelativeLayout.RIGHT_OF, listIndex - 1);
          tagParams.leftMargin = tagMargin;
          total += tagMargin;
          if (tagPre.tagTextSize < tag.tagTextSize) {
            indexBottom = listIndex;
          }
        }
      }
      total += tagWidth;
      addView(tagLayout, tagParams);
      tagPre = tag;
      listIndex++;

    }

  }

  private Drawable getSelector(Tag tag) {
    if (tag.background != null)
      return tag.background;

    StateListDrawable states = new StateListDrawable();
    GradientDrawable gdNormal = new GradientDrawable();
    gdNormal.setColor(tag.layoutColor);
    gdNormal.setCornerRadius(tag.radius);
    if (tag.layoutBorderSize > 0) {
      gdNormal.setStroke(Utils.dipToPx(getContext(), tag.layoutBorderSize), tag.layoutBorderColor);
    }
    GradientDrawable gdPress = new GradientDrawable();
    gdPress.setColor(tag.layoutColorPress);
    gdPress.setCornerRadius(tag.radius);
    states.addState(new int[]{android.R.attr.state_pressed}, gdPress);
    //must add state_pressed first，or state_pressed will not take effect
    states.addState(new int[]{}, gdNormal);
    return states;
  }


  //public methods
  //----------------- separator  -----------------//

  /**
   * @param tag
   */
  public void addTag(Tag tag) {

    mTags.add(tag);
    drawTags();
  }

  public void addTags(List<Tag> tags) {
    if (tags == null) return;
    mTags = new ArrayList<>();
    if (tags.isEmpty())
      drawTags();
    for (Tag item : tags) {
      mTags.add(item);
    }
    drawTags();
  }


  public void addTags(String[] tags) {
    if (tags == null) return;
    for (String item : tags) {
      Tag tag = new Tag(item);
      mTags.add(tag);
    }
    drawTags();
  }


  /**
   * get tag list
   *
   * @return mTags TagObject List
   */
  public List<Tag> getTags() {
    return mTags;
  }

  /**
   * remove tag
   *
   * @param position
   */
  public void remove(int position) {
    if (position < mTags.size()) {
      mTags.remove(position);
      drawTags();
    }
  }

  /**
   * remove all views
   */
  public void removeAll() {
    mTags.clear(); //clear all of tags
    removeAllViews();
  }

  public int getLineMargin() {
    return lineMargin;
  }

  public void setLineMargin(float lineMargin) {
    this.lineMargin = Utils.dipToPx(getContext(), lineMargin);
  }

  public int getTagMargin() {
    return tagMargin;
  }

  public void setTagMargin(float tagMargin) {
    this.tagMargin = Utils.dipToPx(getContext(), tagMargin);
  }

  public int getTextPaddingLeft() {
    return textPaddingLeft;
  }

  public void setTextPaddingLeft(float textPaddingLeft) {
    this.textPaddingLeft = Utils.dipToPx(getContext(), textPaddingLeft);
  }

  public int getTextPaddingRight() {
    return textPaddingRight;
  }

  public void setTextPaddingRight(float textPaddingRight) {
    this.textPaddingRight = Utils.dipToPx(getContext(), textPaddingRight);
  }

  public int getTextPaddingTop() {
    return textPaddingTop;
  }

  public void setTextPaddingTop(float textPaddingTop) {
    this.textPaddingTop = Utils.dipToPx(getContext(), textPaddingTop);
  }

  public int gettextPaddingBottom() {
    return textPaddingBottom;
  }

  public void settextPaddingBottom(float textPaddingBottom) {
    this.textPaddingBottom = Utils.dipToPx(getContext(), textPaddingBottom);
  }


  /**
   * setter for OnTagLongClickListener
   *
   * @param longClickListener
   */
  public void setOnTagLongClickListener(OnTagLongClickListener longClickListener) {
    mTagLongClickListener = longClickListener;
  }

  /**
   * setter for OnTagSelectListener
   *
   * @param clickListener
   */
  public void setOnTagClickListener(OnTagClickListener clickListener) {
    mClickListener = clickListener;
  }

  /**
   * setter for OnTagDeleteListener
   *
   * @param deleteListener
   */
  public void setOnTagDeleteListener(OnTagDeleteListener deleteListener) {
    mDeleteListener = deleteListener;
  }

  /**
   * Listeners
   */
  public interface OnTagDeleteListener {
    void onTagDeleted(TagView view, Tag tag, int position);
  }

  public interface OnTagClickListener {
    void onTagClick(Tag tag, int position);
  }

  public interface OnTagLongClickListener {
    void onTagLongClick(Tag tag, int position);
  }
}

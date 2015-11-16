package com.support.android.designlibdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

import static android.view.View.MeasureSpec.EXACTLY;

/**
 * When using a {@link CoordinatorLayout} in a dialog with a header that collapses into a toolbar,
 * somehow the {@link CoordinatorLayout} doesn't set its height to the maximum space available,
 * it subtract the app bar height, when the app bar is in exitUntilCollapsed mode.
 * Stupid fix here is to pretend our parent told us to use the entire height.
 */
public class DialogCoordinatorLayout extends CoordinatorLayout {
  public DialogCoordinatorLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int height = MeasureSpec.getSize(heightMeasureSpec);
    heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, EXACTLY);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }
}

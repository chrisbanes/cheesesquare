package com.support.android.designlibdemo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

public class CheeseRecyclerView extends RecyclerView {

  private Toast measureToast;
  int measureCount;

  public CheeseRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    Context context = getContext();
    setLayoutManager(new LinearLayoutManager(context));
    List<String> content = Arrays.asList(Cheeses.sCheeseStrings);
    setAdapter(new CheeseListFragment.SimpleStringRecyclerViewAdapter(context, content));
  }

  @Override protected void onMeasure(int widthSpec, int heightSpec) {
    super.onMeasure(widthSpec, heightSpec);
    measureCount++;
    if (measureToast != null) {
      measureToast.cancel();
    }
    measureToast = Toast.makeText(getContext(), "onMeasure(): " + measureCount, Toast.LENGTH_LONG);
    measureToast.show();
  }
}

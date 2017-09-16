package com.example.maintainsteward.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.maintainsteward.utils.ToolUitls;

/**
 * Created by Administrator on 2017/8/1.
 */

public class MyLayoutManager2 extends GridLayoutManager {

    public MyLayoutManager2(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        int itemCount = getItemCount();


        int measuredWidth = 0;
        int measuredHeight = 0;
        if (itemCount > 0) {
            int x = itemCount / 5;

            measuredWidth = View.MeasureSpec.getSize(widthSpec);
            for (int i = 0; i < x+1; i++) {
                View view = recycler.getViewForPosition(i);
                if (view != null) {
                    measureChild(view, widthSpec, heightSpec);
                    measuredHeight += view.getMeasuredHeight();
                }
            }
            setMeasuredDimension(measuredWidth, measuredHeight);

        }

    }
}

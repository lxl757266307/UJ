package com.example.maintainsteward.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/16.
 */

public class StrokeCircleImageView extends View {

    Paint paint;
    RectF rectF;

    public StrokeCircleImageView(Context context) {
        this(context, null);
    }

    public StrokeCircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokeCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        rectF = new RectF(5, 5, width-5 , height-5 );

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        canvas.drawArc(rectF, 0, 360, false, paint);

    }


}

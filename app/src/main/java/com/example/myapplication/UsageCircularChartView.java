//package com.example.myapplication;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.view.View;
//
//public class UsageCircularChartView extends View {
//
//    private float usagePercentage; // Calculate this value based on app usage
//
//    public UsageCircularChartView(Context context) {
//        super(context);
//    }
//
//    public UsageCircularChartView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public UsageCircularChartView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        float centerX = getWidth() / 2;
//        float centerY = getHeight() / 2;
//        float radius = Math.min(centerX, centerY);
//
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.BLUE);
//
//        // Calculate the angle based on the usage percentage
//        float angle = 360 * usagePercentage;
//
//        canvas.drawArc(0, 0, 2 * radius, 2 * radius, -90, angle, true, paint);
//    }
//
//    public void setUsagePercentage(float usagePercentage) {
//        this.usagePercentage = usagePercentage;
//        invalidate(); // Redraw the view with the new data
//    }
//}

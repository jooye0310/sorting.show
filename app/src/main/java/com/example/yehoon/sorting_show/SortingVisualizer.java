package com.example.yehoon.sorting_show;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

public class SortingVisualizer extends AlgorithmVisualizer {

    Paint paint;
    Paint highlightPaintSwap;
    Paint highlightPaintTrace;
    Paint textPaint;
    int[] array;
    int n;
    double zoom = 700;

    int highlightPositionOne = -1, highlightPositionTwo = -1;
    int highlightPosition = -1;
    int lineStrokeWidth = getDimensionInPixel(2);

    public SortingVisualizer(Context context) {
        super(context);
        initialise();
    }

    public SortingVisualizer(Context context, AttributeSet atrrs) {
        super(context, atrrs);
        initialise();
    }

    private void initialise() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#009688"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(lineStrokeWidth);

        highlightPaintSwap = new Paint(paint);
        highlightPaintSwap.setColor(Color.RED);

        highlightPaintTrace = new Paint(paint);
        highlightPaintTrace.setColor(Color.BLUE);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(getDimensionInPixelFromSP(5));
    }

    public void setFontSize(int x){
        textPaint.setTextSize(getDimensionInPixelFromSP(x));
    }
    public void setZoom(double x){
        zoom = x;
    }
    public void setBarWidth(int x){
        lineStrokeWidth = getDimensionInPixel(x);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array != null) {
            int numberOfLines = array.length;

            float margin = (getWidth() - (20 * numberOfLines)) / (numberOfLines + 1);

            float xPos = margin + getDimensionInPixel(10);
            for (int i = 0; i < array.length; i++) {

                if (i == highlightPositionOne || i == highlightPositionTwo) {
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / zoom) * getHeight()), xPos, getHeight(), highlightPaintSwap);
                } else if (i == highlightPosition)
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / zoom) * getHeight()), xPos, getHeight(), highlightPaintTrace);
                else {
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / zoom) * getHeight()), xPos, getHeight(), paint);
                }

                canvas.drawText(String.valueOf(array[i]), xPos - lineStrokeWidth / 3, getHeight() - (float) ((array[i] / zoom) * getHeight()) - 30, textPaint);

                xPos += margin + 30;
            }
            highlightPositionOne = -1;
            highlightPositionTwo = -1;
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setData(DataSet ds) {
        this.array = ds.arr;
        this.n = ds.n;
        invalidate();
    }
    public void highlightSwap(int one, int two) {
        this.highlightPositionOne = one;
        this.highlightPositionTwo = two;
        invalidate();
    }

    public void highlightTrace(int position) {
        this.highlightPosition = position;
        invalidate();
    }

    @Override
    public void onCompleted() {
        this.highlightPosition = -1;
        this.highlightPositionTwo = -1;
        this.highlightPositionOne = -1;
        invalidate();
    }
}

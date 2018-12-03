package com.example.yehoon.sorting_show;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.res.TypedArrayUtils;
import android.text.TextPaint;
import android.util.AttributeSet;

import java.util.Arrays;

public class SortingVisualizer extends AlgorithmVisualizer {

    Paint paint;
    Paint highlightPaintSwap;
    Paint highlightPaintTrace;
    Paint highlightPaintPivot; // for pivot in TimSort
    Paint highlightPaintDestination; // for destination in TimSort
    Paint highlightPaintShift; // for shifting in TimSort
    Paint textPaint;
    int[] array;
    int n;
    double zoom = 700;
    int margins = 30;

    int highlightPositionOne = -1, highlightPositionTwo = -1;
    int highlightPosition = -1;
    int highlightPositionPivot = -1; // for pivot in TimSort
    int highlightPositionDestination = -1; // for destination in TimSort
    int[] highlightPositionShift = new int[]{-1}; // for shift in TimSort
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

        // for pivot in TimSort
        highlightPaintPivot= new Paint(paint);
        highlightPaintPivot.setColor(getResources().getColor(R.color.colorPivot));

        // for destination in TimSort
        highlightPaintDestination = new Paint(paint);
        highlightPaintDestination.setColor(Color.GREEN);

        // for shift in TimSort
        highlightPaintShift = new Paint(paint);
        highlightPaintShift.setColor(getResources().getColor(R.color.colorShift));

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(getDimensionInPixelFromSP(5));
    }

    public void setFontSize(int x){
        textPaint.setTextSize(getDimensionInPixelFromSP(x));
    }
    public void setMargin(int x){
        margins = x;
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

            float margin = (getWidth() - (margins * numberOfLines)) / (numberOfLines + 1);

            float xPos = margin + getDimensionInPixel(10);
            for (int i = 0; i < array.length; i++) {

                if (i == highlightPositionOne || i == highlightPositionTwo)
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / zoom) * getHeight()), xPos, getHeight(), highlightPaintSwap);
                else if (i == highlightPosition)
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / zoom) * getHeight()), xPos, getHeight(), highlightPaintTrace);
                else if (i == highlightPositionPivot)
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / zoom) * getHeight()), xPos, getHeight(), highlightPaintPivot);
                else if (i == highlightPositionDestination)
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / zoom) * getHeight()), xPos, getHeight(), highlightPaintDestination);
                else if (contains(highlightPositionShift, i))
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / zoom) * getHeight()), xPos, getHeight(), highlightPaintShift);
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

    // for pivot in TimSort
    public void highlightPivot(int position) {
        this.highlightPositionPivot = position;
        highlightPositionDestination = -1;
        highlightPosition = -1;
        highlightPositionShift = new int[]{-1};
        invalidate();
    }

    // for destination in TimSort
    public void highlightDestination(int position) {
        this.highlightPositionDestination = position;
        invalidate();
    }

    // for shift in TimSort
    public void highlightShift(int[] positions) {
        this.highlightPositionShift = positions;
        invalidate();
    }

    // for clear in TimSort
    public void clearPositions() {
        this.highlightPosition = -1;
        this.highlightPositionShift = new int[]{-1}; // for shift in TimSort
        this.highlightPositionPivot = -1; // for pivot in TimSort
        this.highlightPositionDestination = -1; // for destination in TimSort
        this.highlightPositionTwo = -1;
        this.highlightPositionOne = -1;
        invalidate();
    }

    private boolean contains(int[] array, int index) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == index)
                return true;
        }
        return false;
    }

    @Override
    public void onCompleted() {
        this.highlightPosition = -1;
        this.highlightPositionShift = new int[]{-1};
        this.highlightPositionPivot = -1; // for pivot in TimSort
        this.highlightPositionDestination = -1; // for destination in TimSort
        this.highlightPositionTwo = -1;
        this.highlightPositionOne = -1;
        invalidate();
    }
}

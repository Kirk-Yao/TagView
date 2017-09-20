package com.example.admin.tagview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2017/9/20.
 */

public class TagView extends View {

    private int bgColor = Color.GREEN;
    private int textColor = Color.WHITE;
    private int textSize = 50;
    private int textWidth = 2;
e
    private int height;
    private int width;
    private int radius;
    private int rightX;

    private int lineOffset = 25;

    private Paint bgPaint;
    private Paint textPaint;

    private String text = "小说";
    boolean clickable = false;

    private OnDeleteClickListener onDeleteClickListener;

    public TagView(Context context) {
        this(context,null);
    }

    public TagView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(bgColor);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(textWidth);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        height = getMeasuredHeight();
        width = getMeasuredWidth();
        radius = height/2;

        int centerX = radius;
        int centerY = height/2;
        rightX = width - radius;
        Log.d("TagView","width-height:" + width+"-"+height);
        //draw background
        //first draw a rectangle
        canvas.drawRect(centerX,0,width - radius,height,bgPaint);
        //draw two circle
        canvas.drawCircle(centerX,centerY,radius,bgPaint);
        canvas.drawCircle(rightX,centerY,radius,bgPaint);

        //draw text
        canvas.drawText(text,width/2 - textPaint.measureText(text)/2,
                centerY - (textPaint.ascent() + textPaint.descent()) / 2,textPaint);

        //draw lines
        canvas.drawLine(rightX - lineOffset,centerY - lineOffset,
                rightX + lineOffset,centerY + lineOffset,textPaint);
        canvas.drawLine(rightX - lineOffset,centerY + lineOffset,
                rightX + lineOffset,centerY - lineOffset,textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX() > (rightX - radius) && event.getX() < width){
                    clickable = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (event.getX() > (rightX - radius) && event.getX() < width && clickable){
                    clickable = false;
                    if (onDeleteClickListener != null){
                        onDeleteClickListener.onDeleteClick();
                    }
                }
                break;
        }
        //此处return true，不然会交给父布局处理这次事件，ACTION_UP不会有响应
        return true;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }
}

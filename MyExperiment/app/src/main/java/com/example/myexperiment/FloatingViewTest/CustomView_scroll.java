package com.example.myexperiment.FloatingViewTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView_scroll extends View {

    public static String LOG = "log: ";

    private int lastX;
    private int lastY;

    public CustomView_scroll(Context context) {
        super(context);
    }

    public CustomView_scroll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView_scroll(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean onTouchEvent( MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch ( event.getAction() ) {
            case MotionEvent.ACTION_DOWN :
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE :
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View)getParent()).scrollBy(-offsetX, -offsetY);
                break;
        }
        return true;
    }
}

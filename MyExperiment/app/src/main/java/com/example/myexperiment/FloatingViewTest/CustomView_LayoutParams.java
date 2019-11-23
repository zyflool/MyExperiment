package com.example.myexperiment.FloatingViewTest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomView_LayoutParams extends View {
    int lastX;
    int lastY;

    public CustomView_LayoutParams(Context context) {
        super(context);
    }

    public CustomView_LayoutParams(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView_LayoutParams(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);

                break;
        }
        return true;
    }
}

package com.example.myexperiment.DragFloatingActionButton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DragFloatingActionButton extends FloatingActionButton {

    private int screenWidth;
    private int screenHeight;
    private int statusHeight;


    public DragFloatingActionButton (Context context) {
        super(context);
        init();
    }

    public DragFloatingActionButton (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public DragFloatingActionButton (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs, defStyleAttr);
        init();
    }

    private void init() {
        screenWidth = ScreenUtils.getScreenWidth(getContext());
        screenHeight = ScreenUtils.getScreenHeight(getContext());
        statusHeight = ScreenUtils.getStatusHeight(getContext());
    }

    private int lastX;
    private int lastY;

    private boolean isDrag;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int rawX = (int) ev.getRawX();//点击位置距整个屏幕左侧的距离
        int rawY = (int) ev.getRawY();//点击位置距整个屏幕上侧的距离

        switch (ev.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                getParent().requestDisallowInterceptTouchEvent(true);//取消父组件对触摸时间的拦截
                lastX = rawX;
                lastY = rawY;//记录位置
                break;

            case MotionEvent.ACTION_MOVE:
                isDrag = true;
                int dx = rawX - lastX;//x方向上的位移
                int dy = rawY - lastY;//y方向上的位移
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance < 3) { //如果移动距离过小，没有超过组件大小，不进行拖动
                    isDrag = false;
                    break;
                }

                //计算移动后在View内的位置坐标
                float x = getX() + dx;//getX()点击位置距View左侧的距离
                float y = getY() + dy;//getY()点击位置距View右侧的距离

                if (x < 0) //如果超出了View左侧边界，规定移动到左侧边界处
                    x = 0;
                if (x > screenWidth - getWidth())
                    x = screenWidth - getWidth();

                if (y < 0)
                    y = 0;
                if (y > screenHeight - statusHeight - getHeight())
                    y = screenHeight - statusHeight - getHeight();

                setX(x);
                setY(y);

                lastX = rawX;
                lastY = rawY;//记录位置
                break;
            case MotionEvent.ACTION_UP:
                if(isDrag)
                    setPressed(false);//设置取消按压
                break;
        }
        return isDrag || super.onTouchEvent(ev);
    }
}
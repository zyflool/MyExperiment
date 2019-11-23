package com.example.myexperiment.FloatingViewTest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.myexperiment.R;


public class FloatingViewTestActivity extends AppCompatActivity{

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;

    private int mCount = 0;

    private CustomView_layout layout_view;
    private CustomView_offset offset_view;
    private CustomView_LayoutParams layoutparams_view;
    private View anim_view;
    private CustomView_scroll scroll_view;
    private CustomView_scroller scroller_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_view_test);
        layout_view = findViewById(R.id.customview_layout);
        offset_view = findViewById(R.id.customview_offset);
        layoutparams_view = findViewById(R.id.customview_layoutparams);
        anim_view = findViewById(R.id.customview_anim);
        scroll_view = findViewById(R.id.customview_scroll);
        scroller_view = findViewById(R.id.customview_scroller);

        layout_view.setVisibility(View.GONE);
        offset_view.setVisibility(View.GONE);
        layoutparams_view.setVisibility(View.GONE);
        anim_view.setVisibility(View.VISIBLE);
        scroller_view.setVisibility(View.GONE);
        scroll_view.setVisibility(View.GONE);

        //最小滑动距离
        //Log.d("TouchSlop", ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop()+"");

        /*
        //属性动画 能够改变View的位置参数
        ObjectAnimator.ofFloat(anim_view, "translationX", 0,300).setDuration(1000).start();
        //View动画 不能改变View的位置参数
        anim_view.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));
        */

        //scroller_view.smoothScrollTo(-400, 0);

        //TODO 用动画模仿scroller移动，没有变化？？？？？
        final int startX = 0;
        final int deltaX = 100;
        ValueAnimator animator = ValueAnimator.ofFloat(0,1).setDuration(1);
        animator.setTarget(anim_view);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animator.getAnimatedFraction();
                Log.d("TAG",fraction+"");
                anim_view.scrollTo(startX+(int)(deltaX*fraction),0);
            }
        });
        animator.start();



    }

}

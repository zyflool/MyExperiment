package com.example.myexperiment.Animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myexperiment.MainActivity;
import com.example.myexperiment.R;

public class AnimationActivity extends AppCompatActivity {

    private View view;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        view = findViewById(R.id.view_anim);
        mButton = findViewById(R.id.btn_animation);
        work();
    }

    private void work () {
        mButton.setOnClickListener(v -> {
            /*
            ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(view,"rotation", 0,180,0);
            mObjectAnimator.setDuration(2000);
            mObjectAnimator.start();
            */

            /* xml添加动画
            Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale);
            animator.setTarget(view);
            animator.start();
            */

            /*
            ValueAnimator mValueAnimator  = ValueAnimator.ofFloat(0, 800);
            mValueAnimator.setTarget(view);
            mValueAnimator.setDuration(1000).start();
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Float mFloat = (Float) animation.getAnimatedValue();
                    Log.d("TAG",mFloat+"");
                }
            });
            * /

            /*组合动画 AnimatorSet
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationX", 0.0f, 200.0f, 0.0f);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 2.0f,1.0f);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "rotationX", 0.0f, 90.0f, 0.0F);
            AnimatorSet set = new AnimatorSet();
            set.setDuration(1000);
            set.play(animator1).with(animator2).after(animator3);
            set.start();
            */

            /*组合动画 PropertyValuesHolder
            PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("scaleX",1.0f, 1.5f,1.0f);
            PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("rotationX",0.0f, 90.0f, 0.0F);
            PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("alpha",1.0f, 3.0f, 1.0F);
            PropertyValuesHolder valuesHolder4 = PropertyValuesHolder.ofFloat("translationX",0.0f, 200.0f, 0.0f);
            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, valuesHolder1, valuesHolder2, valuesHolder3,valuesHolder4);
            objectAnimator.setDuration(2000);
            objectAnimator.start();
            */
        });
    }
}

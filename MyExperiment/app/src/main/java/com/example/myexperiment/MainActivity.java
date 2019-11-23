package com.example.myexperiment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myexperiment.Animation.AnimationActivity;
import com.example.myexperiment.CardView.CardViewActivity;
import com.example.myexperiment.CircleImageView.CircleImageViewActivity;
import com.example.myexperiment.DragFloatingActionButton.DragFloatingActionButtonActivity;
import com.example.myexperiment.FloatingViewTest.FloatingViewTestActivity;
import com.example.myexperiment.FragmentUseTest.FragmentUsingTestActivity;
import com.example.myexperiment.GridLayout.GridLayoutActivity;
import com.example.myexperiment.Notification.NotificationActivity;
import com.example.myexperiment.RecyclerView.RecyclerViewActivity;
import com.example.myexperiment.RecyclerViewBackgroundTest.RecyclerViewBackgroundActivity;
import com.example.myexperiment.RxJavaTest.RxJavaTestActivity;
import com.example.myexperiment.Spinner.SpinnerActivity;
import com.example.myexperiment.TitleBar.TitleBarActivity;
import com.example.myexperiment.getCompoundDrawables.GetCompoundDrawablesActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDragFloatingActionButton;
    private Button mRecyclerView;
    private Button mCardView;
    private Button mGridLayout;
    private Button mTextView;
    private Button mNotification;
    private Button mCircleImageView;
    private Button mRxJavaTest;
    private Button mFragmentUsingTest;
    private Button mFloatingViewTest;
    private Button mAnimation;
    private Button mRecyclerViewBackground;
    private Button mCalculator;
    private Button mTitleBar;
    private Button mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView ( ) {
        mDragFloatingActionButton = findViewById(R.id.drag_floating_action_button);
        mRecyclerView = findViewById(R.id.recycler_view);
        mCardView = findViewById(R.id.card_view);
        mGridLayout = findViewById(R.id.grid_layout_add_view);
        mTextView = findViewById(R.id.get_compound_drawables);
        mNotification = findViewById(R.id.notification);
        mCircleImageView = findViewById(R.id.circleImageView);
        mRxJavaTest = findViewById(R.id.RxJavaTest);
        mFragmentUsingTest = findViewById(R.id.FragmentUsingTest);
        mFloatingViewTest = findViewById(R.id.FloatingViewTest);
        mAnimation = findViewById(R.id.Animation);
        mRecyclerViewBackground = findViewById(R.id.RecyclerViewBackground);
        mCalculator = findViewById(R.id.calculator);
        mTitleBar = findViewById(R.id.title_bar);
        mSpinner = findViewById(R.id.spinner);

        mDragFloatingActionButton.setOnClickListener(this);
        mRecyclerView.setOnClickListener(this);
        mCardView.setOnClickListener(this);
        mGridLayout.setOnClickListener(this);
        mTextView.setOnClickListener(this);
        mNotification.setOnClickListener(this);
        mCircleImageView.setOnClickListener(this);
        mRxJavaTest.setOnClickListener(this);
        mFragmentUsingTest.setOnClickListener(this);
        mFloatingViewTest.setOnClickListener(this);
        mAnimation.setOnClickListener(this);
        mRecyclerViewBackground.setOnClickListener(this);
        mCalculator.setOnClickListener(this);
        mSpinner.setOnClickListener(this);
        mTitleBar.setOnClickListener(this);
    }

    public void onClick( View view ) {
        Intent intent;
        switch (view.getId()) {
            case R.id.drag_floating_action_button :
                intent = new Intent(MainActivity.this, DragFloatingActionButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.recycler_view :
                intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.card_view :
                intent = new Intent(MainActivity.this, CardViewActivity.class);
                startActivity(intent);
                break;
            case R.id.grid_layout_add_view:
                intent = new Intent(MainActivity.this, GridLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.get_compound_drawables:
                intent = new Intent(MainActivity.this, GetCompoundDrawablesActivity.class);
                startActivity(intent);
                break;
            case R.id.notification:
                intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.circleImageView:
                intent = new Intent(MainActivity.this, CircleImageViewActivity.class);
                startActivity(intent);
                break;
            case R.id.RxJavaTest:
                intent = new Intent(MainActivity.this, RxJavaTestActivity.class);
                startActivity(intent);
                break;
            case R.id.FragmentUsingTest:
                intent = new Intent(MainActivity.this, FragmentUsingTestActivity.class);
                startActivity(intent);
                break;
            case R.id.FloatingViewTest:
                intent = new Intent(MainActivity.this, FloatingViewTestActivity.class);
                startActivity(intent);
                break;
            case R.id.Animation:
                intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.RecyclerViewBackground:
                intent = new Intent(MainActivity.this, RecyclerViewBackgroundActivity.class);
                startActivity(intent);
                break;
            case R.id.calculator:
                intent = new Intent(MainActivity.this, com.example.myexperiment.calculater.MainActivity.class);
                startActivity(intent);
                break;
            case R.id.title_bar:
                intent = new Intent(MainActivity.this, TitleBarActivity.class);
                startActivity(intent);
                break;
            case R.id.spinner:
                intent = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivity(intent);
                break;
        }

    }

}
package com.example.myexperiment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myexperiment.CardView.CardViewActivity;
import com.example.myexperiment.DragFloatingActionButton.DragFloatingActionButtonActivity;
import com.example.myexperiment.GridLayout.GridLayoutActivity;
import com.example.myexperiment.Notification.NotificationActivity;
import com.example.myexperiment.RecyclerView.RecyclerViewActivity;
import com.example.myexperiment.getCompoundDrawables.GetCompoundDrawablesActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDragFloatingActionButton;
    private Button mRecyclerView;
    private Button mCardView;
    private Button mGridLayout;
    private Button mTextView;
    private Button mNotification;

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

        mDragFloatingActionButton.setOnClickListener(this);
        mRecyclerView.setOnClickListener(this);
        mCardView.setOnClickListener(this);
        mGridLayout.setOnClickListener(this);
        mTextView.setOnClickListener(this);
        mNotification.setOnClickListener(this);
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
            case R.id.notification:
                intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
        }

    }

}
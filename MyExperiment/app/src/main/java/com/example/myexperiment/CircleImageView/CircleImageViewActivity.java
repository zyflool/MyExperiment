package com.example.myexperiment.CircleImageView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myexperiment.R;

public class CircleImageViewActivity extends AppCompatActivity {

    private CircleImageView mCircleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_view);

        mCircleImageView = findViewById(R.id.civ);
    }
}

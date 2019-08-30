package com.example.myexperiment.DragFloatingActionButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myexperiment.DragFloatingActionButton.DragFloatingActionButton;
import com.example.myexperiment.R;

public class DragFloatingActionButtonActivity extends AppCompatActivity{

    private DragFloatingActionButton dfab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_floating_action_button);
        dfab = findViewById(R.id.dfab);
        dfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DragFloatingActionButtonActivity.this,"点击了可拖拽按钮的点击事件",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
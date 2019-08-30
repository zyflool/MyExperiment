package com.example.myexperiment.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myexperiment.R;

public class GridLayoutActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        gridLayout = findViewById(R.id.grid_layout);

        //      gridLayout.setColumnCount(30);
        //    gridLayout.setRowCount(30);
/*
        Button newbutton = new Button(this);
        newbutton.setText("what");
        newbutton.setBackgroundColor(Color.parseColor("#333333"));

        GridLayout.Spec rowSpec = GridLayout.spec(0,29);
        GridLayout.Spec columnSpec = GridLayout.spec(0,1);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
        params.setGravity(Gravity.FILL);
        gridLayout.addView(newbutton,params);

        Button anewbutton = new Button(this);
        anewbutton.setText("why");
        anewbutton.setBackgroundColor(Color.parseColor("#FFFFFF"));


        rowSpec = GridLayout.spec(25,1);
        columnSpec = GridLayout.spec(28,1);
        params = new GridLayout.LayoutParams(rowSpec,columnSpec);
        //params.setGravity(Gravity.AXIS_X_SHIFT);
        gridLayout.addView(anewbutton,params);
*/
/*
        GridLayout.Spec rowSpec = GridLayout.spec(0);
        GridLayout.Spec columnSpec = GridLayout.spec(0);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
        gridLayout.addView(newbutton, params);

        GridLayout.Spec rowSpec = GridLayout.spec(8,2); // 设置它的行和列
        GridLayout.Spec columnSpec = GridLayout.spec(4,5);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
        gridLayout.addView(newbutton,params);*/
        AddView();
        //add();

    }

    private void AddView() {
        gridLayout.setRowCount(5);
        gridLayout.setColumnCount(5);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                TextView textView = new TextView(this);
                textView.setBackgroundColor(Color.parseColor("#B32920"));
                textView.setText("嘻哈");
                //设置它的行 和 权重 有了权重才能水平均匀分布
                //由于方法重载，注意这个地方的1.0f 必须是float，
                GridLayout.Spec rowSpec = GridLayout.spec(i, 1.0f);
                GridLayout.Spec columnSpec = GridLayout.spec(j, 1.0f);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                gridLayout.addView(textView, params);
            }
        }
    }


    private void add () {
        int count = 1;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 5; j++) {
                System.out.println("count:" + count);
                Button btn = new Button(this);
                btn.setWidth(4);
                btn.setText(String.valueOf(count));
                count++;
                GridLayout.Spec rowSpec = GridLayout.spec(i,1.0f);     //设置它的行和列
                GridLayout.Spec columnSpec = GridLayout.spec(j,1.0f);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.setGravity(Gravity.LEFT);
                gridLayout.addView(btn);
            }
    }

}
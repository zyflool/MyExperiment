package com.example.myexperiment.TitleBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.myexperiment.R;

public class TitleBarActivity extends AppCompatActivity {

    private ProgressTitleBar mTitleBar;
    private MyTitleBar myTitleBar;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_bar);
        initViewProgress();
        initViewMy();
        initView();
    }

    private void initViewProgress() {
        mTitleBar = findViewById(R.id.progress_title_bar);
        mTitleBar.setOptionSp(this);
        mTitleBar.setOptionSelectListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTitleBar.adapter.setSelectedPosition(position);
                switch (position){
                    case 0:
                        Toast.makeText(TitleBarActivity.this,  "select all", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(TitleBarActivity.this,  "select product", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(TitleBarActivity.this,  "select design", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(TitleBarActivity.this,  "select frontend", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(TitleBarActivity.this,  "select android", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(TitleBarActivity.this,  "select backend", Toast.LENGTH_LONG).show();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mTitleBar.setOptionIbListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleBar.showSpinner();
            }
        });

        mTitleBar.setAddListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleBarActivity.this,"wow, you can really dance",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initViewMy( ) {
        myTitleBar = findViewById(R.id.my_title_bar);
        myTitleBar.setOptionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               myTitleBar.showPopupMenu(v, TitleBarActivity.this);
            }
        });

        myTitleBar.setAddClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleBarActivity.this,"wow, you can really dance",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView() {
        titleBar = findViewById(R.id.title_bar_popup_window);
        titleBar.setOptionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleBar.showPopupWindow(TitleBarActivity.this);
            }
        });

        titleBar.setAddListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TitleBarActivity.this,"wow, you can really dance",Toast.LENGTH_LONG).show();
            }
        });
    }
}
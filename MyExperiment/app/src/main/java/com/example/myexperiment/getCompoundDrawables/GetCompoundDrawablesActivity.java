package com.example.myexperiment.getCompoundDrawables;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myexperiment.R;

/**
 * Demo描述:
 * 测试getCompoundDrawables()方法.
 * Returns drawables for the left, top, right, and bottom borders.
 * 该方法返回包含控件左,上,右,下四个位置的Drawable的数组
 */
public class GetCompoundDrawablesActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_compound_drawables);
        init();
    }
    private void init(){
        mTextView= findViewById(R.id.textView);
        Drawable [] drawables=mTextView.getCompoundDrawables();
        Log.d("??????????????????",""+drawables.length);
        for (int i = 0; i < drawables.length; i++) {
            Drawable drawable=drawables[i];
            if ( drawable == null)
                continue;
            System.out.println("第"+i+"张图片 width="+drawable.getBounds().width()+
                    ",height="+drawable.getBounds().height());
        }
    }


}
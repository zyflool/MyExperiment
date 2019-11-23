package com.example.myexperiment.TitleBar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myexperiment.R;

public class MyTitleBar extends ConstraintLayout {

    private TextView OptionTv, TitleTv;
    private ImageButton OptionIb, AddIb;
    private View LineV;

    public MyTitleBar(Context context) {
        super(context);
        initView(context);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.title_bar_my, this, true);
        OptionTv = findViewById(R.id.tv_my_title_bar_option);
        OptionIb = findViewById(R.id.ib_my_title_bar_option);
        TitleTv = findViewById(R.id.tv_my_title_bar_title);
        AddIb = findViewById(R.id.ib_my_title_bar_add);
        LineV = findViewById(R.id.view_my_title_bar_line);
    }

    public void setOptionClickListener(OnClickListener onClickListener) {
        OptionTv.setOnClickListener(onClickListener);
    }

    public void setAddClickListener(OnClickListener onClickListener) {
        AddIb.setOnClickListener(onClickListener);
    }

    public void showPopupMenu(View view, Context context) {
        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(context, view);
        // 获取布局文件
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_my_title_bar_option, popupMenu.getMenu());
        popupMenu.show();
        // 通过上面这几行代码，就可以把控件显示出来了
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_all:
                        OptionTv.setText(R.string.all);
                        return true;
                    case R.id.item_product:
                        OptionTv.setText(R.string.product);
                        return true;
                    case R.id.item_design:
                        OptionTv.setText(R.string.design);
                        return true;
                    case R.id.item_frontend:
                        OptionTv.setText(R.string.frontend);
                        return true;
                    case R.id.item_android:
                        OptionTv.setText(R.string.android);
                        return true;
                    case R.id.item_backend:
                        OptionTv.setText(R.string.backend);
                        return true;
                }
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                // 控件消失时的事件
            }
        });
    }
}

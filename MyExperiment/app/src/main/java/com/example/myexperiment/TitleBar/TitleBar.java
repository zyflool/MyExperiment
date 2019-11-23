package com.example.myexperiment.TitleBar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myexperiment.R;

public class TitleBar extends ConstraintLayout implements View.OnClickListener {

    private ImageButton OptionIb, AddIb;
    private TextView OptionTv, TitleTv;
    private PopupWindow popWnd;

    public TitleBar(Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.title_bar_popup_window, this, true);
        OptionTv = findViewById(R.id.tv_title_bar_popup_window_option);
        OptionIb = findViewById(R.id.ib_title_bar_popup_window_option);
        TitleTv = findViewById(R.id.tv_title_bar_popup_window_title);
        AddIb = findViewById(R.id.ib_title_bar_popup_window_add);
    }

    public void setAddListener(OnClickListener onClickListener) {
        AddIb.setOnClickListener(onClickListener);
    }

    public void setOptionClickListener(OnClickListener onClickListener) {
        OptionTv.setOnClickListener(onClickListener);
        OptionIb.setOnClickListener(onClickListener);
    }

    public void showPopupWindow(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_window_title_bar, null);
        popWnd = new PopupWindow(context);
        popWnd.setContentView(contentView);
        popWnd.setWidth(OptionTv.getWidth());
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.showAsDropDown(OptionTv,0,0,Gravity.RIGHT);
        popWnd.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        // 设置PopupWindow是否能响应外部点击事件
        popWnd.setOutsideTouchable(true);
        popWnd.setTouchable(true);
        popWnd.setFocusable(true);
        TextView AllTv = contentView.findViewById(R.id.tv_all_popup_window);
        TextView ProductTv = contentView.findViewById(R.id.tv_product_popup_window);
        TextView AndroidTv = contentView.findViewById(R.id.tv_android_popup_window);
        TextView FrontendTv = contentView.findViewById(R.id.tv_frontend_popup_window);
        TextView BackendTv = contentView.findViewById(R.id.tv_backend_popup_window);
        TextView DesignTv = contentView.findViewById(R.id.tv_design_popup_window);
        AllTv.setOnClickListener(this);
        ProductTv.setOnClickListener(this);
        DesignTv.setOnClickListener(this);
        FrontendTv.setOnClickListener(this);
        AndroidTv.setOnClickListener(this);
        BackendTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all_popup_window:
                OptionTv.setText(R.string.all);
                popWnd.dismiss();
                break;
            case R.id.tv_android_popup_window:
                OptionTv.setText(R.string.android);
                popWnd.dismiss();
                break;
            case R.id.tv_product_popup_window:
                OptionTv.setText(R.string.product);
                popWnd.dismiss();
                break;
            case R.id.tv_backend_popup_window:
                OptionTv.setText(R.string.backend);
                popWnd.dismiss();
                break;
            case R.id.tv_design_popup_window:
                OptionTv.setText(R.string.design);
                popWnd.dismiss();
                break;
            case R.id.tv_frontend_popup_window:
                OptionTv.setText(R.string.frontend);
                popWnd.dismiss();
                break;
            default:
                popWnd.dismiss();
                break;
        }
    }
}

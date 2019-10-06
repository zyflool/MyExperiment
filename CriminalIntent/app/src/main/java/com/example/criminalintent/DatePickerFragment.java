package com.example.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";

    private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;

    //要传递crime日期给 DatePickerFragment ,需将它保存在 DatePickerFragment 的argument bundle中。
    // 这样, DatePickerFragment 就能直接获取它。
    //创建和设置fragment argument通常是在 newInstance() 方法中完成的(代替fragment构造方法)。
    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);


        //DatePickerFragment 使用 Date 中的信息来初始化 DatePicker 对象。
        // DatePicker 对象的初始化需整数形式的月、日、年。 Date 是时间戳,无法直接提供整数。
        //必须首先创建一个 Calendar 对象,然后用 Date 对象配置它,再从 Calendar 对象中取回所需信息。
        //在 onCreateDialog(Bundle) 方法内,从argument中获取 Date 对象,然后用它和 Calendar对象初始化 DatePicker
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                /*
                用户点击对话框中的positive按钮时,需要从DatePicker中获取日期并回传给CrimeFragment 。
                在onCreateDialog(...)方法中,替换掉setPositiveButton(...)的null参数值,实现DialogInterface.OnClickListener 监听器接口。
                在监听器接口的onClick(...)方法中,获取日期并调用sendResult(...)方法。
                 */
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = mDatePicker.getYear();
                                int month = mDatePicker.getMonth();
                                int day = mDatePicker.getDayOfMonth();
                                Date date = new GregorianCalendar(year, month, day).getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .create();
    }

    /*
    新建 sendResult(...) 私有方法,创建intent并将日期数据作为extra附加到intent上。
    最后调用 CrimeFragment.onActivityResult(...) 方法。
    */
    private void sendResult(int resultCode, Date date) {
        if ( getTargetFragment() == null )
            return;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment().
                onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}

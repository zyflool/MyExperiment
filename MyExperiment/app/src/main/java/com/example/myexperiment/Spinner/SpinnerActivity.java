package com.example.myexperiment.Spinner;

import androidx.annotation.ArrayRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myexperiment.R;

public class SpinnerActivity extends AppCompatActivity {

    private AppCompatSpinner mAppCompatSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        initView();
    }

    private void initView ( ) {
        mAppCompatSpinner = findViewById(R.id.appcompatspinner);
        SpinnerAdapter adapter = SpinnerAdapter.createFromResource(this, R.array.screening,R.layout.spinner_progress_layout);
        adapter.setDropDownViewResource(R.layout.spinner_progress_dropdown);
        mAppCompatSpinner.setBackgroundColor(0x0);
        mAppCompatSpinner.setAdapter(adapter);
        mAppCompatSpinner.setDropDownVerticalOffset(Math.round(getResources().getDisplayMetrics().density * 25));

        mAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedPostion(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public static class SpinnerAdapter<T> extends ArrayAdapter<T> {

        private int selectedPostion;

        public void setSelectedPostion(int selectedPostion) {
            this.selectedPostion = selectedPostion;
        }

        public SpinnerAdapter(@NonNull Context context, int resource, @NonNull T[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView textView = (TextView)view;
            if(selectedPostion == position){
                textView.setTextColor(0xff373741);
                textView.getPaint().setFakeBoldText(true);
            }
            else{
                textView.setTextColor(0xff6d6d6d);
                textView.getPaint().setFakeBoldText(false);
            }
            return view;
        }

        public static @NonNull SpinnerAdapter<CharSequence> createFromResource(
                @NonNull Context context, @ArrayRes int textArrayResId, @LayoutRes int textViewResId) {
            final CharSequence[] strings = context.getResources().getTextArray(textArrayResId);
            return new SpinnerAdapter<>(context, textViewResId, strings);
        }
    }
}

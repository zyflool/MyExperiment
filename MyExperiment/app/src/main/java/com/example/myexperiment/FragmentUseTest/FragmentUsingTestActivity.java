package com.example.myexperiment.FragmentUseTest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myexperiment.R;

import java.util.ArrayList;

public class FragmentUsingTestActivity extends AppCompatActivity implements LeftListFragment.OnListItemSelectedListener {

    RightViewFragment rightFrag;
    LeftListFragment leftFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_using_test);

        rightFrag = RightViewFragment.newInstance();
        ArrayList<String> ids = new ArrayList<>();
        ids.add("1"); ids.add("2"); ids.add("3");
        ids.add("4"); ids.add("5"); ids.add("6");
        ids.add("7"); ids.add("8"); ids.add("9");
        ids.add("10"); ids.add("11"); ids.add("12");
        leftFrag = LeftListFragment.newInstance(ids);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_left_list,leftFrag,"ONE");
        ft.add(R.id.fl_right_view, rightFrag,"TWO");
        ft.commit();
    }

    @Override
    public void onItemSeleted(String s) {
        RightViewFragment rightViewFragment = RightViewFragment.newInstance();
        Bundle args = new Bundle();
        args.putString(RightViewFragment.ARG_STRING, s);
        rightViewFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_right_view,rightViewFragment).commit();
    }

}

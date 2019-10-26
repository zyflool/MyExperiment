package com.example.myexperiment.FragmentUseTest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myexperiment.R;

public class RightViewFragment extends Fragment {

    public static String ARG_STRING = "RightViewFragment";

    private String s = "meiyou";

    private TextView mTextView;

    public static RightViewFragment newInstance() {
        RightViewFragment rightViewFragment = new RightViewFragment();
        return rightViewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            s = getArguments().getString(ARG_STRING);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_right_view, container, false);

        mTextView = v.findViewById(R.id.tv_fragment);
        mTextView.setText(s);

        return v;
    }

}

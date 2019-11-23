package com.example.myexperiment.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myexperiment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 1 react long click to delete with RecyclerView Delete item animation
 * 2 update following 20 items when move to the bottom
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private HomeAdapter mHomeAdaper;
    private List<List<String>> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initData();
        initView();
    }

    private void initData() {
        int a = 1;
        for ( int x = 0 ; x < 10 ; x++ ) {
            List<String> temp = new ArrayList<>();
            mList.add(temp);
            for ( int y = 0 ; y < 20 ; y++ ) {
                mList.get(x).add(a+"");
                a++;
            }
        }
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.id_recyclerview);

        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置item增加和删除时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(RecyclerViewActivity.this, DividerItemDecoration.VERTICAL_LIST));

        mHomeAdaper = new HomeAdapter(this,mList);
        mHomeAdaper.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this,"CLICK"+(position+1), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                new AlertDialog.Builder(RecyclerViewActivity.this)
                        .setTitle("Delete?")
                        .setNegativeButton("cancel",null)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mHomeAdaper.removeData(position);
                            }
                        })
                        .show();
            }
        });
        mRecyclerView.setAdapter(mHomeAdaper);
    }
}

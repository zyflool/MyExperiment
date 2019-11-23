package com.example.myexperiment.RecyclerViewBackgroundTest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myexperiment.R;

import java.util.List;

public class RecyclerViewBackgroundActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_background);

        mRecyclerView = findViewById(R.id.recyclerview_background);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new MyAdapter(this));
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        Context mContext;

        public MyAdapter(Context context) {
            mContext = context;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_background, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.mTextView1.setText("aaaaaaaaaaaaaaaaaa");
            holder.mTextView2.setText("111111111111111111");
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView mTextView1, mTextView2;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mTextView1 = itemView.findViewById(R.id.tv1_recyclerview_background);
                mTextView2 = itemView.findViewById(R.id.tv2_recyclerview_background);
            }
        }
    }
}

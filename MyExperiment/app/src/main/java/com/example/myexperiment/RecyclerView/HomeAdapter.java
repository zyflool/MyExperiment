package com.example.myexperiment.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myexperiment.R;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<List<String>> mList;
    private List<String> curList = new ArrayList<>();
    private Context mContext;
    private int i = 0;
    private OnItemClickListener mOnItemClickListener;

    public HomeAdapter (Context mContext, List<List<String>> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void removeData (int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false));
        } else {
            return new MoreViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_more, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if ( position == curList.size() )
            return 1;
        else return 0;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder mholder = (MyViewHolder)holder;
            if ( curList.size() > 0 )
                mholder.tv.setText(curList.get(position));
            if (mOnItemClickListener != null) {
                mholder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(mholder.tv, pos);
                    }
                });
                mholder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(mholder.tv, pos);
                        return false;
                    }
                });
            }

        } else if (holder instanceof MoreViewHolder) {
            MoreViewHolder moreViewHolder = (MoreViewHolder)holder;
            moreViewHolder.moreTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( i < 10 ) {
                        curList.addAll(mList.get(i));
                        notifyItemRangeInserted(i*20,20);
                        i++;
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return curList.size()+1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.tv_item_of_recycler_view);
        }
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {
        TextView moreTv;
        public MoreViewHolder(View view) {
            super(view);
            moreTv = view.findViewById(R.id.tv_rv_more);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

}

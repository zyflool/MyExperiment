package com.example.myexperiment.FragmentUseTest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myexperiment.R;

import java.util.ArrayList;
import java.util.List;

public class LeftListFragment extends Fragment {

    OnListItemSelectedListener mCallback;

    public static String LIST_ID = "LeftListFragment";

    private List<String> IDs;

    private RecyclerView mRecyclerView;

    public interface OnListItemSelectedListener {
        public void onItemSeleted(String s);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnListItemSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException ( context.toString() + "must implement OnListItemSelectedListener");
        }

    }

    public static LeftListFragment newInstance(ArrayList<String> IDs) {
        Bundle args = new Bundle();
        args.putStringArrayList(LIST_ID, IDs);

        LeftListFragment fragment = new LeftListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IDs = getArguments().getStringArrayList(LIST_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_left_list, container, false);

        mRecyclerView = v.findViewById(R.id.rv_fragment_left);

        ListAdapter adapter = new ListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        return v;
    }

    private class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

        @NonNull
        @Override
        public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ListViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ListAdapter.ListViewHolder holder, int position) {
            String s = IDs.get(position);
            holder.bind(s);
        }

        @Override
        public int getItemCount() {
            return IDs.size();
        }

        private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView mTextView;

            public ListViewHolder (LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.item_recycler, parent, false));
                itemView.setOnClickListener(this);
                mTextView = itemView.findViewById(R.id.tv_item_of_recycler_view);
            }

            public void bind (String s) {
                mTextView.setText(s);
            }

            @Override
            public void onClick(View v) {
                mCallback.onItemSeleted(mTextView.getText().toString());
            }
        }
    }


}

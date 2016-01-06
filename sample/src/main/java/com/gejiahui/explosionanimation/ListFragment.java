package com.gejiahui.explosionanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gejiahui on 2016/1/6.
 */
public class ListFragment extends Fragment {
    private RecyclerView list;
    private List<String> mDatas;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.list_fragment,container,false);
        list = (RecyclerView)view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        Adapter adapter = new Adapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.v("gjh","click"+position);
            }
        });
        list.setAdapter(adapter);

        return view;
    }

    private void initData(){
        mDatas = new ArrayList<String>();
        for(int i = 0 ; i < 20 ; i++){
            mDatas.add("this is "+ i + " item");
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


        private OnItemClickListener mOnItemClickListener;

        public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
        {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.txt.setText(mDatas.get(position));
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView txt;

            public MyViewHolder(View view)
            {
                super(view);
                txt = (TextView) view.findViewById(R.id.txt);
            }
        }
    }


}

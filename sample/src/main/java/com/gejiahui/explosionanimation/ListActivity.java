package com.gejiahui.explosionanimation;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gejiahui.library.explosionanimation.ExplosionField;
import com.gejiahui.library.explosionanimation.particle.LeftParticle;
import com.gejiahui.library.explosionanimation.particle.RightParticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gejiahui on 2016/1/6.
 */
public class ListActivity extends Activity {
    private ListView list;
    private List<String> mDatas;
    private ExplosionField mExplosionField;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        list = (ListView)findViewById(R.id.list);
        mExplosionField = ExplosionField.attach2Window(this);
        initData();
        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }



    private void initData(){
        mDatas = new ArrayList<String>();
        for(int i = 0 ; i < 20 ; i++){

            mDatas.add("This is "+ i +" item ");
        }
    }

    class MyAdapter extends BaseAdapter {

        public MyAdapter() {
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView != null){
                view = convertView;
            }else{
                view = LayoutInflater.from(ListActivity.this).inflate(R.layout.item,parent,false);
            }
            TextView txt = (TextView)view.findViewById(R.id.txt);
            txt.setText(mDatas.get(position));
            txt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.v("gjh",event.getX()+" : "+ event.getY());
                    mExplosionField.explode(event,new LeftParticle());
                    return false;
                }
            });
            return view;
        }
    }


}

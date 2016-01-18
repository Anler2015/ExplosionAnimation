package com.gejiahui.explosionanimation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gejiahui.library.explosionanimation.ExplosionField;
import com.gejiahui.library.explosionanimation.ExplosionLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gejiahui on 2016/1/6.
 */
public class ListActivity extends Activity {
    private ListView list;
    private List<String> mDatas;
    private List<Integer> mColor;
    private ExplosionField mExplosionField;
    Random random = new Random();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        list = (ListView)findViewById(R.id.list);
        //   mExplosionField = ExplosionField.attach2Window(this);
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
        mColor = new ArrayList<Integer>();
        for(int i =1; i <20 ; i++){
            mDatas.add("This is " + i +" item ");
            mColor.add(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView != null){
                view = convertView;
            }else{
                view = LayoutInflater.from(ListActivity.this).inflate(R.layout.item,parent,false);
            }
            TextView txt = (TextView)view.findViewById(R.id.txt);
            final ExplosionLayout layout = (ExplosionLayout)view.findViewById(R.id.layout);
            layout.setOnDeleteListener(new ExplosionLayout.OnDeleteListener() {
                @Override
                public void onDelete() {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
                    alphaAnimation.setDuration(500);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1,0,1,0,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                    scaleAnimation.setDuration(500);

                    AnimationSet as = new AnimationSet(true);
                    as.addAnimation(alphaAnimation);
                    as.addAnimation(scaleAnimation);
                    as.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mDatas.remove(position);
                            mColor.remove(position);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    layout.startAnimation(as);

                }
            });
            txt.setText(mDatas.get(position));
            txt.setBackgroundColor(mColor.get(position));
            return view;
        }
    }


}

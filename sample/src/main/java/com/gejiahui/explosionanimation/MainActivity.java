package com.gejiahui.explosionanimation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.gejiahui.library.explosionanimation.ExplosionField;
import com.gejiahui.library.explosionanimation.particle.FlyAwayParticle;


import java.util.ArrayList;

/**
 * Created by gejiahui on 2015/12/28.
 */
public class MainActivity extends AppCompatActivity {

    private ExplosionField mExplosionField;
    private ViewPager viewPager;
    private ArrayList<Fragment> list = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mExplosionField = ExplosionField.attach2Window(this);
        viewPager = (ViewPager) findViewById(R.id.view_page);


        ExplodeViewFragment explodeViewFragment = new ExplodeViewFragment();
        list.add(explodeViewFragment);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);

    }

    private void addListener(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                addListener(parent.getChildAt(i));
            }
        } else {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExplosionField.explode(v, new FlyAwayParticle());
                    v.setOnClickListener(null);
                }
            });
        }
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//      mExplosionField.explode(event, new LeftParticle());
//        return true;
//    }


}

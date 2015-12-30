package com.gejiahui.explosionanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gejiahui.library.explosionanimation.ExplosionField;
import com.gejiahui.library.explosionanimation.particle.ExplodeParticle;
import com.gejiahui.library.explosionanimation.particle.FlyAwayParticle;
import com.gejiahui.library.explosionanimation.particle.RightParticle;

/**
 * Created by gejiahui on 2015/12/28.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private ExplosionField mExplosionField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mExplosionField = ExplosionField.attach2Window(this);
        addListener(findViewById(R.id.root));

        ImageView img1 = (ImageView)findViewById(R.id.img1);
        ImageView img2 = (ImageView)findViewById(R.id.img2);
        ImageView img3 = (ImageView)findViewById(R.id.img3);

//        img1.setOnClickListener(this);
//        img2.setOnClickListener(this);
//        img3.setOnClickListener(this);



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


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//      mExplosionField.explode(event, new RightParticle());
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.img1:
                mExplosionField.explode(v, new RightParticle());
                break;
            case R.id.img2:
                mExplosionField.explode(v, new FlyAwayParticle());
                break;
            case R.id.img3:
                mExplosionField.explode(v, new ExplodeParticle());
                break;
        }
    }
}

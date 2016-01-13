package com.gejiahui.explosionanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gejiahui.library.explosionanimation.ExplosionField;
import com.gejiahui.library.explosionanimation.particle.ExplodeParticle;
import com.gejiahui.library.explosionanimation.particle.FlyAwayParticle;
import com.gejiahui.library.explosionanimation.particle.RightParticle;


import java.util.ArrayList;

import static com.gejiahui.library.explosionanimation.Utils.createBitmapFromViewGroup;

/**
 * Created by gejiahui on 2015/12/28.
 */
public class MainActivity extends AppCompatActivity {

    private ExplosionField mExplosionField;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    private ArrayList<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mExplosionField = ExplosionField.attach2Window(this);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        mExplosionField = ExplosionField.attach2Window(this);
        img1.setOnClickListener(clickListener);
        img2.setOnClickListener(clickListener);
        img3.setOnClickListener(clickListener);
        layout.setOnClickListener(clickListener);

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



    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.img1:
                    mExplosionField.explode(v, new RightParticle());
                    break;
                case R.id.img2:
                    mExplosionField.explode(v, new FlyAwayParticle());
                    break;
                case R.id.img3:
                    mExplosionField.explode(v, new ExplodeParticle());
                    break;
                case R.id.layout:
                    mExplosionField.explode1(MainActivity.this,(ViewGroup) v, new ExplodeParticle());
                    img1.setImageBitmap(createBitmapFromViewGroup(MainActivity.this,(ViewGroup) v));
                    break;
            }
            ValueAnimator valueAnimator = new ValueAnimator().ofFloat(1).setDuration(1700);
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    v.animate().alpha(1).scaleY(1).scaleX(1).setDuration(150).start();
                }
            });
            valueAnimator.start();
        }


    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,ListActivity.class);
            startActivity(intent);


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

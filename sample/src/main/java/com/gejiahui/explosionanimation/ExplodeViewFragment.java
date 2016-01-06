package com.gejiahui.explosionanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gejiahui.library.explosionanimation.ExplosionField;
import com.gejiahui.library.explosionanimation.particle.ExplodeParticle;
import com.gejiahui.library.explosionanimation.particle.FlyAwayParticle;
import com.gejiahui.library.explosionanimation.particle.RightParticle;

/**
 * Created by gejiahui on 2016/1/5.
 */
public class ExplodeViewFragment extends Fragment {
    private ExplosionField mExplosionField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.exlpode_view_fragment,container,false);
        ImageView img1 = (ImageView)view.findViewById(R.id.img1);
        ImageView img2 = (ImageView)view.findViewById(R.id.img2);
        ImageView img3 = (ImageView)view.findViewById(R.id.img3);
        mExplosionField = ExplosionField.attach2Window(getActivity());
        img1.setOnClickListener(clickListener);
        img2.setOnClickListener(clickListener);
        img3.setOnClickListener(clickListener);
        return view;
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

}

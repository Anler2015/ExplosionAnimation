package com.gejiahui.library.explosionanimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by gejiahui on 2015/12/28.
 */
public class ExplodeFiled extends View {
    Random random = new Random();

    public ExplodeFiled(Context context) {
        super(context);
    }

    public ExplodeFiled(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExplodeFiled(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    public void explode(final View view){

        ValueAnimator animator = ValueAnimator.ofFloat(0,1).setDuration(150);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((random.nextFloat()-0.5f)*view.getWidth()*0.05f);
                view.setTranslationY((random.nextFloat()-0.5f)*view.getHeight()*0.05f);
            }
        });

        animator.start();
        view.animate().alpha(0).scaleX(0).scaleY(0).setDuration(150).setStartDelay(150).start();
        //接下来爆炸
    }
}

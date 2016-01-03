package com.gejiahui.library.explosionanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.gejiahui.library.explosionanimation.particle.Particle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gejiahui on 2015/12/28.
 */
public class ExplosionField extends View {
    Random random = new Random();
    private List<ExplosionAnimator> mExplosions = new ArrayList<>();
    public ExplosionField(Context context) {
        super(context);
    }

    public ExplosionField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExplosionField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void explode(final View view,Particle particle){
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);

        int contentTop = ((ViewGroup)getParent()).getTop();
        rect.offset(0, -contentTop );//去掉状态栏高度和标题栏高度

        ValueAnimator animator = ValueAnimator.ofFloat(0,1).setDuration(150);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationX((random.nextFloat() - 0.5f) * view.getWidth() * 0.05f);
                view.setTranslationY((random.nextFloat() - 0.5f) * view.getHeight() * 0.05f);
            }
        });
        animator.start();
        view.animate().alpha(0).scaleY(0).scaleX(0).setStartDelay(100).setDuration(150).start();
        //接下来爆炸
        explode(Utils.createBitmapFromView(view), rect, 100, ExplosionAnimator.DEFAULT_DURATION, particle);
    }

    public void explode(Bitmap bitmap, Rect bound, long startDelay, long duration,Particle particle) {
        final ExplosionAnimator explosion = new ExplosionAnimator(this, bitmap, bound,particle);
        explosion.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mExplosions.remove(animation);
            }
        });
        explosion.setStartDelay(startDelay);
        explosion.setDuration(duration);
        mExplosions.add(explosion);
        explosion.start();
    }


    public void explode(MotionEvent event,Particle particle){
        int x = (int)event.getX();
        int y = (int)event.getY();
        int rectRaduis = 20;
        Rect rect = new Rect(x-200,y-200,x+200,y+200);
        explode(Utils.createBitmapFromView(this), rect, 100, ExplosionAnimator.DEFAULT_DURATION, particle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (ExplosionAnimator explosion : mExplosions) {
            explosion.draw(canvas);
        }

    }

    public static ExplosionField attach2Window(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        ExplosionField explosionField = new ExplosionField(activity);
        rootView.addView(explosionField, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return explosionField;
    }
}

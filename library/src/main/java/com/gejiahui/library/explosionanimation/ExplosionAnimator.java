package com.gejiahui.library.explosionanimation;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import com.gejiahui.library.explosionanimation.particle.Particle;

import java.util.Random;

/**
 * Created by gejiahui on 2015/12/29.
 */
public class ExplosionAnimator extends ValueAnimator {

    static long DEFAULT_DURATION = 1200;
    private static final Interpolator DEFAULT_INTERPOLATOR = new AccelerateInterpolator(0.6f);
    private static final float END_VALUE = 1.4f;
    private static final float X = Utils.dp2px(5);
    private static final float Y = Utils.dp2px(20);
    private static final float V = Utils.dp2px(2);
    private static final float W = Utils.dp2px(1);
    private int partLen =15;
    private int partLenForTouch =2;
    private Paint mPaint;
    private Particle[][] mParticles ;
    private View mContainer;
    private Rect mBound;

    public ExplosionAnimator(View container, Bitmap bitmap, Rect bound, Particle particle,int mode) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mContainer = container;
        mBound = new Rect(bound);
        Random random = new Random(System.currentTimeMillis());
        generateParticles(bitmap,random,particle,mode);
        setDuration(DEFAULT_DURATION);
        setInterpolator(DEFAULT_INTERPOLATOR);
        setFloatValues(0f,1.4f);
    }


    public void  generateParticles(Bitmap bitmap ,Random random,Particle particle,int mode){
        int w = 0,h = 0,parts = 0;
        if(mode == 0){
            parts = partLen;
        }
        else{
           parts = partLenForTouch;
        }
        w = mBound.width() / (parts+2);
        h = mBound.height() / (parts+2) ;
        mParticles = new Particle[parts][parts];
        for (int i = 0; i < parts; i++) {
            for (int j = 0; j < parts; j++) {
                if(mode == 0){
                    mParticles[i][j] = generateParticle( bitmap.getPixel((i+1)*w,(j+1)*h),random, particle);
                }
                else{
                    int color = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
                    mParticles[i][j] = generateParticle( color,random, particle);
                }

            }
        }

    }



    public Particle generateParticle(int color,Random random,Particle p){
        Particle particle = p.newInstance();
        particle.color = color;
        particle.radius = V;
        if (random.nextFloat() < 0.2f) {
            particle.baseRadius = V + ((X - V) * random.nextFloat());
        } else {
            particle.baseRadius = W + ((V - W) * random.nextFloat());
        }
        float nextFloat = random.nextFloat();
        particle.top = mBound.height() * ((0.18f * random.nextFloat()) + 0.2f);
        particle.top = nextFloat < 0.2f ? particle.top : particle.top + ((particle.top * 0.2f) * random.nextFloat());
        particle.bottom = (mBound.height() * (random.nextFloat() - 0.5f)) * 1.8f;
        float f = nextFloat < 0.2f ? particle.bottom : nextFloat < 0.8f ? particle.bottom * 0.6f : particle.bottom * 0.3f;
        particle.bottom = f;
        particle.mag = 4.0f * particle.top / particle.bottom;
        particle.neg = (-particle.mag) / particle.bottom;
        f = mBound.centerX() + (Y * (random.nextFloat() - 0.5f));
        particle.baseCx = f;
        particle.cx = f;
        f = mBound.centerY() + (Y * (random.nextFloat() - 0.5f));
        particle.baseCy = f;
        particle.cy = f;
        particle.life = END_VALUE / 10 * random.nextFloat();
        particle.overflow = 0.4f * random.nextFloat();
        particle.alpha = 1f;
        return particle;
    }



    public void draw(Canvas canvas){
        if (!isStarted()) {
            return ;
        }
        for (Particle[] particles : mParticles) {
            for (Particle p : particles) {

                p.advance((float) getAnimatedValue());
                if (p.alpha > 0f) {
                    mPaint.setColor(p.color);
                    mPaint.setAlpha((int) (Color.alpha(p.color) * p.alpha));
                    canvas.drawCircle(p.cx, p.cy, p.radius, mPaint);
                }
            }
        }

       mContainer.invalidate();
    }

    @Override
    public void start() {
        super.start();
        mContainer.invalidate(mBound);
    }
}

package com.gejiahui.library.explosionanimation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gejiahui.library.explosionanimation.particle.LeftParticle;

/**
 * Created by gejiahui on 2016/1/11.
 */
public class ExplosionLayout extends FrameLayout {
    private int mX,mY;
    private int firstX;
    private int scrollX;
    private ViewGroup backLayout;
    private boolean wantToSwap = false;
    private OnDeleteListener mDeleteListener;
    private Context mContext;
    public interface OnDeleteListener{
        void onDelete();
    }

    private ExplosionField mExplosionField;
    public ExplosionLayout(Context context) {
        this(context, null);
    }

    public ExplosionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExplosionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mExplosionField = ExplosionField.attach2Window((Activity)context);
        mContext = context;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(getChildCount()!=1){
            throw new IllegalArgumentException(" this layout require only one child view");
        }
        backLayout = (ViewGroup) getChildAt(0);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mX = (int)event.getX();
        mY = (int)event.getY();
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                if(mX > backLayout.getWidth()*4/5){
                    firstX = mX;
                    wantToSwap = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                scrollX = firstX - mX;
                if(wantToSwap == true && scrollX >0){
                    if(mX >= 0 && mY >= 0 && mY <= backLayout.getHeight()) {
                        mExplosionField.explode(mContext,backLayout ,(int) event.getRawX(), (int) event.getRawY(), new LeftParticle());
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                //want to delete
                if(firstX - mX >= backLayout.getWidth()/2){
                    if(mDeleteListener != null){
                        mDeleteListener.onDelete();
                    }
                }
                scrollX = 0;
                wantToSwap = false;

                break;
        }

        if (wantToSwap) {
            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.clipRect(0, 0, backLayout.getWidth() - scrollX, getHeight());
        invalidate();
        super.dispatchDraw(canvas);
    }

    public void setOnDeleteListener(OnDeleteListener mDeleteListener){
        this.mDeleteListener = mDeleteListener;
    }
}

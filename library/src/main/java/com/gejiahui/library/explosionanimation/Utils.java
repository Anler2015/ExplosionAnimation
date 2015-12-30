package com.gejiahui.library.explosionanimation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by gejiahui on 2015/12/28.
 */
public class Utils {


    private final static Canvas mCanvas = new Canvas();


    public static float dp2px(float dp) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static Bitmap createBitmapFromView(View view){
        Bitmap bitmap;
        if(view instanceof ImageView){
            Drawable drawable =  ((ImageView) view) .getDrawable();
            if(drawable != null && drawable instanceof BitmapDrawable){
                bitmap = ((BitmapDrawable) drawable).getBitmap();
                return bitmap;
            }
        }
        view.clearFocus();
        bitmap = createBitmapSafely(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888,1);
        if(bitmap == null) {
            synchronized (mCanvas){
                Canvas canvas = mCanvas;
                canvas.setBitmap(bitmap);
                view.draw(canvas);
                canvas.setBitmap(null);
            }
        }
        return bitmap;
    }

    public static Bitmap createBitmapSafely(int width,int height,Bitmap.Config config,int retryCount){
        try{
            return Bitmap.createBitmap(width,height,config);

        }catch (OutOfMemoryError e){
            if(retryCount > 0){
                System.gc();
                return createBitmapSafely(width, height, config, retryCount - 1);
            }
            return null;
        }

    }
}

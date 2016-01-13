package com.gejiahui.library.explosionanimation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by gejiahui on 2015/12/28.
 */
public class Utils {

    public static float dp2px(float dp) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }


    public static Bitmap createBitmapFromView(Context context, View view ){
        Bitmap bitmap;
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);

        View decorView = ((Activity)context).getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap background = decorView.getDrawingCache();
        bitmap = Bitmap.createBitmap(background,rect.left,rect.top,rect.width(),rect.height());
        decorView.destroyDrawingCache();
        decorView.setDrawingCacheEnabled(false);
        background.recycle();
        return bitmap;
    }

}

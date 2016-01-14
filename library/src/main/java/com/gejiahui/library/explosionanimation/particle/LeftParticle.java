package com.gejiahui.library.explosionanimation.particle;

import android.graphics.Paint;

import com.gejiahui.library.explosionanimation.Utils;

import java.util.Random;

/**
 * Created by gejiahui on 2015/12/30.
 */
public class LeftParticle extends Particle {
    private final float V = Utils.dp2px(2);
    Random random = new Random(System.currentTimeMillis());
    int stand ;
    @Override
    public void advance(float fraction) {
        float f = 0f;
        float normalization = fraction / 1.4f;
        if (normalization < life || normalization > 1f - overflow) {
            alpha = 0f;
            return;
        }
        normalization = (normalization - life) / (1f - life - overflow);
        float f2 = normalization * 1.4f;
        if (normalization >= 0.7f) {
            f = (normalization - 0.7f) / 0.3f;
        }
        alpha = 1f - f;
        radius = V + (baseRadius - V) * f2;
        f = bottom * f2;
        cx = baseCx - stand * f2;
        cy = (float) (baseCy - this.neg * Math.pow(f, 2.0)) ;

    }

    @Override
    public Particle newInstance() {
        LeftParticle particle =  new LeftParticle();
        particle.stand = random.nextInt(300);
        return particle;
    }
}

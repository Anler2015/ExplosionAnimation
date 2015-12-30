package com.gejiahui.library.explosionanimation.particle;

import com.gejiahui.library.explosionanimation.Utils;

/**
 * Created by gejiahui on 2015/12/29.
 */
public class ExplodeParticle extends Particle {
    private static final float V = Utils.dp2px(2);

    public ExplodeParticle() {
    }

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
        f = bottom * f2;
        cx = baseCx + f;
        cy = (float) (baseCy - this.neg * Math.pow(f, 2.0)) - f * mag;
        radius = V + (baseRadius - V) * f2;
    }

    @Override
    public Particle newInstance() {
        return new ExplodeParticle();
    }
}

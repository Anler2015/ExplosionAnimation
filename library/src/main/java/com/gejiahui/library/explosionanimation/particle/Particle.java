package com.gejiahui.library.explosionanimation.particle;

/**
 * Created by gejiahui on 2015/12/29.
 */
public abstract class Particle {

    public float alpha;
    public int   color;
    public float cx;
    public float cy;
    public float radius;
    public float baseCx;
    public float baseCy;
    public float baseRadius;
    public float top; //正数
    public float bottom;//正负 随机
    public float mag;//正负的个位数
    public float neg;//小数
    public float life;
    public float overflow;


    public abstract void advance(float fraction);

    public abstract Particle newInstance();

}

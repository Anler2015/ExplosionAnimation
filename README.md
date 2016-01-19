# ExplosionAnimation
An Amazing Explode Animation 

This is an explode animation. When I first saw the repository  [ExplosionField](https://github.com/tyrantgit/ExplosionField), I was so
surprised and wanted to write it by myself. So I did it.</br> 
What else ?
I refactored the project, and you can implement your particles easily. And I also made an ExplosionLayout. 

---
### Demo
![](https://github.com/Anler2015/ExplosionAnimation/blob/master/outputs/1.gif)

![](https://github.com/Anler2015/ExplosionAnimation/blob/master/outputs/2.gif)

[Download Demo](https://github.com/Anler2015/ExplosionAnimation/blob/master/outputs/sample-debug.apk) 

---
### usage

##### Explode View

###### Step 1

Use it in your activity 'onCreat()':
```java
    ExplosionField mExplosionField = ExplosionField.attach2Window(this);
```	
###### Step 2
Then use the 'explode(Context context, View view,Particle particle)' to explode your View.
```java
    mExplosionField.explode(MainActivity.this, view, new ExplodeParticle());
```	
And you can explode all of views even the groupviews.
I made some particles like 
`ExplodeParticle`
`FlyAwayParticle`
`RightParticle`
`LeftParticle`.</br> 
And you also can make your own particles by defining their moving.
##### Make your own particles
###### Step 1
Extends the class `Particle`
```java
   public class ExplodeParticle extends Particle {
   
   }
```	
###### Step 2
And you need to implement 'advance(float fraction)' and 'newInstance()'.
```java
    public void advance(float fraction) {
      ...
    }

    @Override
    public Particle newInstance() {
        return new ExplodeParticle();
    }
```	

##### Use ExplosionLayout
There are some things you should be known, when you are creating 'ExplosionLayout'.</br> 
1. There must be one childview.</br> 
2. You need to use 'ExplosionLayout' in you listview item xml.</br> 
###### Step 1
Create a 'ExplosionLayout'.
```xml
<com.gejiahui.library.explosionanimation.ExplosionLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FFFFFF"
    android:id="@+id/layout">

    <!-- What you want to show-->

</com.gejiahui.library.explosionanimation.ExplosionLayout>
```	

###### Step 1
Implement 'ExplosionLayout.OnDeleteListener()' in your listview adapter.
like this:
```Java
 Viewgroup.setOnDeleteListener(new ExplosionLayout.OnDeleteListener() {
                @Override
                public void onDelete() {
                       mDatas.remove(position);
                       mColor.remove(position);
                       notifyDataSetChanged();
            });
```	

I know my English is poor , and think you to watch this README.md. You can see more details by loaddown the code.

---
### About me

I am an anroid developer in China.













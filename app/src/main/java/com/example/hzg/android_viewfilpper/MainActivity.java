package com.example.hzg.android_viewfilpper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper flipper;
    private float startX;
    private int[] resId = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));
        }
        //设置自动切换效果
//        flipper.setInAnimation(this,R.anim.left_in);
//        flipper.setOutAnimation(this, R.anim.left_out);
//        flipper.setFlipInterval(3000);
//        flipper.startFlipping();
    }

    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(resId);
        return image;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指落下
            case MotionEvent.ACTION_DOWN:{
                //记录手机按下的X开始坐标
                startX=event.getX();
                break;
            }

            //手指滑动
            case MotionEvent.ACTION_MOVE: {
                //通过判断手指向左向右滑动的距离来判断是否向左向右滑动
                //向右滑
                if (event.getX()-startX>100) {
                    //左边淡入淡出
                    flipper.setInAnimation(this,R.anim.left_in);
                    flipper.setOutAnimation(this, R.anim.left_out);
                    //看前一页
                    flipper.showPrevious();
                }
                if (startX-event.getX()>100) {
                    //右边淡入淡出
                    flipper.setInAnimation(this,R.anim.right_in);
                    flipper.setOutAnimation(this, R.anim.right_out);
                    //看下一页
                    flipper.showNext();
                }
                break;
            }
            //手指移开
            case MotionEvent.ACTION_UP:{
                break;
            }
        }
        return super.onTouchEvent(event);
    }
}

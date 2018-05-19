package com.example.aeon.simpledrawing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;



public class PaintingActivity extends Activity {

    private Button btn_save;
    private Button btn_re;
    private Button btn_clear;
    MySurfaceView surfaceView;
    private View lastSelectPen=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.painting);
        surfaceView=(MySurfaceView)findViewById(R.id.surfaceview);

        Intent intent = getIntent();
        int imageId = intent.getIntExtra("image",0);
        surfaceView.setImageId(imageId);

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("image",imageId);
        editor.apply();

        lastSelectPen=findViewById(R.id.ibtn_black);
        lastSelectPen.startAnimation(AnimationUtils.loadAnimation(PaintingActivity.this,
                R.anim.scale_zoom_out_anim));
        btn_clear=findViewById(R.id.btn_clear);
    }

    private void playAnim(View v) {
        if (lastSelectPen != null) {
            lastSelectPen.startAnimation(AnimationUtils.loadAnimation(PaintingActivity.this, R.anim.scale_zoom_in_anim));
            v.startAnimation(AnimationUtils.loadAnimation(PaintingActivity.this,R.anim.scale_zoom_out_anim));
        }
        lastSelectPen=v;
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ibtn_black:
                playAnim(v);
                surfaceView.setBlackPaint();
                break;
            case R.id.ibtn_yellow:
                playAnim(v);
                surfaceView.setYellowPaint();
                break;
            case R.id.btn_clear:
                surfaceView.clean();
                break;
            case R.id.ibtn_red:
                playAnim(v);
                surfaceView.setRedPaint();
                break;
            case R.id.ibtn_bule:
                playAnim(v);
                surfaceView.setBulePaint();
                break;
            case R.id.ibtn_green:
                playAnim(v);
                surfaceView.setGreenPaint();
                break;
        }
    }
}

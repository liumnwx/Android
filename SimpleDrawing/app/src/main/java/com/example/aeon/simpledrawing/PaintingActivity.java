package com.example.aeon.simpledrawing;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class PaintingActivity extends AppCompatActivity {

    private Button play;
    private Button pause;
    private Button stop;
    private Button btn_clear;
    MySurfaceView surfaceView;
    private View lastSelectPen=null;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.painting);
        btn_clear=findViewById(R.id.btn_clear);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
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
        if (ContextCompat.checkSelfPermission(PaintingActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PaintingActivity.this,
                    new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            initMediaPlayer(); // 初始化MediaPlayer
        }
    }
    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Like It.mp3");
            mediaPlayer.setDataSource(file.getPath()); // 指定音频文件的路径
            mediaPlayer.prepare(); // 让MediaPlayer进入到准备状态
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void playAnim(View v) {
        if (lastSelectPen != null) {
            lastSelectPen.startAnimation(AnimationUtils.loadAnimation(PaintingActivity.this,
                    R.anim.scale_zoom_in_anim));
            v.startAnimation(AnimationUtils.loadAnimation(PaintingActivity.this,
                    R.anim.scale_zoom_out_anim));
        }
        lastSelectPen=v;
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start(); // 开始播放
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause(); // 暂停播放
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset(); // 停止播放
                    initMediaPlayer();
                }
                break;
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
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

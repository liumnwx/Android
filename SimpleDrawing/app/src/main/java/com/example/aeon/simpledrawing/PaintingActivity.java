package com.example.aeon.simpledrawing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static android.util.Base64.DEFAULT;


public class PaintingActivity extends AppCompatActivity {
    ImageView imageView;
    Button btn_save;
    Button btn_re;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.painting);
        imageView = findViewById(R.id.painting_iv);
        final Intent intent = getIntent();
        setTitle(intent.getStringExtra("title"));
        imageView.setImageResource(intent.getIntExtra("image", 1));
        btn_save = findViewById(R.id.btn_save);
        btn_re= findViewById(R.id.btn_re);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBitmapToSharePreferenves();
            }
        });
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBitmapFromSharePreferences();
            }
        });
    }

    private void getBitmapFromSharePreferences() {
        SharedPreferences sharedPreferences=getSharedPreferences("data",Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString=sharedPreferences.getString("image","");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray=Base64.decode(imageString, DEFAULT);
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
        //第三步:利用ByteArrayInputStream生成Bitmap
        Bitmap bitmap=BitmapFactory.decodeStream(byteArrayInputStream);
        imageView.setImageBitmap(bitmap);
    }

    private void saveBitmapToSharePreferenves(){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray=byteArrayOutputStream.toByteArray();
        String imageString= Base64.encodeToString(byteArray, DEFAULT);
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences=getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.apply();
    }
}

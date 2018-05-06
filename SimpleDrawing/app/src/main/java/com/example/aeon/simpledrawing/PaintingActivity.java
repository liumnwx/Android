package com.example.aeon.simpledrawing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class PaintingActivity extends AppCompatActivity {
    ImageView imageView;
    Button btn_save;
    Button btn_re;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.painting);
        imageView=(ImageView)findViewById(R.id.painting_iv);
        Intent intent = getIntent();
        final int imageId = intent.getIntExtra("image",0);
        imageView.setImageResource(imageId);
        btn_save = findViewById(R.id.btn_save);
        btn_re=findViewById(R.id.btn_re);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("image",imageId);
                editor.apply();
            }
        });
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("data",Context.MODE_PRIVATE);
                int old = sharedPreferences.getInt("image",imageId);
                imageView.setImageResource(old);
            }
        });
    }
}

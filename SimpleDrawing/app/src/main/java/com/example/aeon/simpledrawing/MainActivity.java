package com.example.aeon.simpledrawing;

import android.app.Activity;
import android.util.Log;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.net.*;

public class MainActivity extends AppCompatActivity {

    private List<Pics> picsList = new ArrayList<Pics>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=getSharedPreferences("data",0);
        int imageId=sharedPreferences.getInt("image",0);
        if(imageId!=0){
            Intent intent=new Intent(MainActivity.this,PaintingActivity.class);
            intent.putExtra("image",imageId);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);
        initPics();
        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv);
        GridLayoutManager  layoutManager= new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        PicsAdapter adapter = new PicsAdapter(picsList);
        recyclerView.setAdapter(adapter);

    }

    private void initPics(){
        for(int i=0;i<2;i++){
            Pics banana = new Pics(this.getString(R.string.banana),R.drawable.banana);
            picsList.add(banana);
            Pics bee = new Pics(this.getString(R.string.bee),R.drawable.bee);
            picsList.add(bee);
            Pics cake = new Pics(this.getString(R.string.cake),R.drawable.cake);
            picsList.add(cake);
            Pics car = new Pics(this.getString(R.string.car),R.drawable.car);
            picsList.add(car);
            Pics crocodilia = new Pics(this.getString(R.string.crocodilia),R.drawable.crocodilia);
            picsList.add(crocodilia);
            Pics duck = new Pics(this.getString(R.string.duck),R.drawable.duck);
            picsList.add(duck);
            Pics grape = new Pics(this.getString(R.string.grape),R.drawable.grape);
            picsList.add(grape);
            Pics pineapple = new Pics(this.getString(R.string.pineapple),R.drawable.pineapple);
            picsList.add(pineapple);
            Pics rabbit = new Pics(this.getString(R.string.rabbit),R.drawable.rabbit);
            picsList.add(rabbit);
            Pics turtle = new Pics(this.getString(R.string.turtle),R.drawable.turtle);
            picsList.add(turtle);
            Pics watermelon = new Pics(this.getString(R.string.watermelon),R.drawable.watermelon);
            picsList.add(watermelon);
        }
    }
}


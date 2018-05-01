package com.example.aeon.simpledrawing;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.net.*;
import java.util.ServiceConfigurationError;

public class PicsAdapter extends RecyclerView.Adapter<PicsAdapter.ViewHolder>
{
    private List<Pics> mPicsList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View picsView;
        ImageView pics_image;
        TextView pics_name;
        String name;

        public ViewHolder(View view){
            super(view);
            picsView=view;
            pics_name=(TextView)view.findViewById(R.id.pics_name);
            pics_image=(ImageView)view.findViewById(R.id.pics_image);
        }
    }
    public PicsAdapter(List<Pics> picsList){
        this.mPicsList=picsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pics_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.picsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Pics pics =mPicsList.get(position);
                Intent intent=new Intent(parent.getContext(),PaintingActivity.class);
                intent.putExtra("image",pics.getImageId());
                intent.putExtra("name",pics.getName());
                parent.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "you clicked view " + pics.getName(), Toast.LENGTH_SHORT).show();

            }
        });
        holder.pics_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position= holder.getAdapterPosition();
                Pics pics=mPicsList.get(position);
                Intent intent=new Intent(parent.getContext(),PaintingActivity.class);
                intent.putExtra("image",pics.getImageId());
                intent.putExtra("name",pics.getName());
                parent.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "you clicked image " + pics.getName(), Toast.LENGTH_SHORT).show();

            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pics pics = mPicsList.get(position);
        holder.pics_image.setImageResource(pics.getImageId());
        holder.pics_name.setText(pics.getName());
    }
    public int getItemCount(){
        return mPicsList.size();
    }
}

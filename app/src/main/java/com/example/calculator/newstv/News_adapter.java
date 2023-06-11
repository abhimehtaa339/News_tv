package com.example.calculator.newstv;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class News_adapter extends RecyclerView.Adapter<News_adapter.viewHolder> {
    private Context context;
    private ArrayList<Newsmodal>list;

    public News_adapter(Context context, ArrayList<Newsmodal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.news_card , parent , false);
       return new News_adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Newsmodal mode = list.get(position);
        holder.heading.setText(mode.getHeading());
        holder.subheading.setText(mode.getSubheading());
        Picasso.get().load(mode.getImg()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , News_screen.class);
                intent.putExtra("img" , mode.getImg());
                intent.putExtra("title" , mode.getHeading());
                intent.putExtra("descp" , mode.getDescp());
                intent.putExtra("content" , mode.getSubheading());
                intent.putExtra("url" , mode.getUrl());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView heading , subheading;
        ImageView img;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            heading = itemView.findViewById(R.id.imagebelowtext);
            subheading = itemView.findViewById(R.id.subtitletext);
            img= itemView.findViewById(R.id.newsimage);
        }
    }
}

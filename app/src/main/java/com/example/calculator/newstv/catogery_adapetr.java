package com.example.calculator.newstv;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class catogery_adapetr extends RecyclerView.Adapter<catogery_adapetr.viewHolder> {

    private Context context;
    private ArrayList<catogeries_modal> list;

    private onlclick onClick;

    public catogery_adapetr(Context context, ArrayList<catogeries_modal> list , onlclick onclicked) {
        this.context = context;
        this.list = list;
        this.onClick = onclicked;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.catogery_card , parent , false);
       return new catogery_adapetr.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        catogeries_modal modal = list.get(position);
        holder.catogery.setText(modal.getCatogery());
        Picasso.get().load(modal.getImg_url()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onCatogeryclick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onlclick{
        void onCatogeryclick(int position);

    }

    public class viewHolder extends RecyclerView.ViewHolder  {

       TextView catogery;
       ImageView image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            catogery = itemView.findViewById(R.id.catogery_text);
            image = itemView.findViewById(R.id.catogery_image);

        }

    }
}

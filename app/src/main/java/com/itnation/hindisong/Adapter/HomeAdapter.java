package com.itnation.hindisong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itnation.hindisong.Model.HomeModel;
import com.itnation.hindisong.R;
import com.itnation.hindisong.VideoPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    ArrayList<HomeModel> homeModelArrayList;

    public HomeAdapter(Context context, ArrayList<HomeModel> homeModelArrayList) {
        this.context = context;
        this.homeModelArrayList = homeModelArrayList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        HomeModel homeModel = homeModelArrayList.get(position);

        String itemName = homeModel.getItemName();
        String videoId = homeModel.getVideoId();
        String tittle = homeModel.getTittle();

        holder.tittle.setText(tittle);



        String thumLink = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
        Picasso.get().load(thumLink).
                placeholder(R.drawable.load).
                into(holder.child_image);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (videoId != null){

                    VideoPlayerActivity.video_id= videoId;
                    VideoPlayerActivity.repo=itemName;
                    VideoPlayerActivity.tittle = tittle;
                    Intent intent = new Intent(context, VideoPlayerActivity.class);
                    context.startActivity(intent);

                }else {

                    Toast.makeText(context,"Item not fond",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return homeModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView child_image;
        TextView tittle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            child_image=itemView.findViewById(R.id.child_image);
            tittle=itemView.findViewById(R.id.tittle);




        }
    }
}

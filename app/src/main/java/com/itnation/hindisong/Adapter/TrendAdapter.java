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

import com.itnation.hindisong.Model.TrendModel;
import com.itnation.hindisong.R;
import com.itnation.hindisong.VideoPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrendAdapter extends RecyclerView.Adapter<TrendAdapter.ViewHolder> {

    Context context;
    ArrayList<TrendModel>trendModelArrayList;

    public TrendAdapter(Context context, ArrayList<TrendModel> trendModelArrayList) {
        this.context = context;
        this.trendModelArrayList = trendModelArrayList;
    }

    @NonNull
    @Override
    public TrendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendAdapter.ViewHolder holder, int position) {

        TrendModel trendModel = trendModelArrayList.get(position);




        String itemName = trendModel.getItemName();
        String videoId = trendModel.getVideoId();
        String tittle = trendModel.getTittle();

        holder.tittle.setText(trendModel.getTittle());

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
        return trendModelArrayList.size();
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

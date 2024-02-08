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
import com.itnation.hindisong.Model.PlayerItemModel;
import com.itnation.hindisong.R;
import com.itnation.hindisong.VideoPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayerItemAdapter extends RecyclerView.Adapter<PlayerItemAdapter.ViewHolder> {

    Context context;
    ArrayList<PlayerItemModel>playerItemModelArrayList;

    public PlayerItemAdapter(Context context, ArrayList<PlayerItemModel> playerItemModelArrayList) {
        this.context = context;
        this.playerItemModelArrayList = playerItemModelArrayList;
    }

    @NonNull
    @Override
    public PlayerItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerItemAdapter.ViewHolder holder, int position) {

        PlayerItemModel playerItemModel = playerItemModelArrayList.get(position);

        String itemName = playerItemModel.getItemName();
        String videoId = playerItemModel.getVideoId();
        String tittle = playerItemModel.getTittle();

        holder.tittle.setText(playerItemModel.getTittle());

        String thumLink = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
        Picasso.get().load(thumLink).
                placeholder(R.drawable.load).
                into(holder.child_image);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (videoId != null){

                    VideoPlayerActivity.video_id= videoId;
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
        return playerItemModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView child_image;
        public TextView tittle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            child_image=itemView.findViewById(R.id.child_image);
            tittle=itemView.findViewById(R.id.tittle);


        }
    }
}

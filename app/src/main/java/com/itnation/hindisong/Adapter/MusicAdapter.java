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

import com.itnation.hindisong.Model.MusicModel;
import com.itnation.hindisong.R;
import com.itnation.hindisong.VideoPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    Context context;
    ArrayList<MusicModel>musicModelArrayList;

    public MusicAdapter(Context context, ArrayList<MusicModel> musicModelArrayList) {
        this.context = context;
        this.musicModelArrayList = musicModelArrayList;
    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {

        MusicModel musicModel = musicModelArrayList.get(position);
        String itemName = musicModel.getItemName();
        String videoId = musicModel.getVideoId();
        String tittle= musicModel.getTittle();

        holder.tittle.setText(musicModel.getTittle());

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
        return musicModelArrayList.size();
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

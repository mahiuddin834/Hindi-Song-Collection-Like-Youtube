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

import com.itnation.hindisong.Model.SingerModel;
import com.itnation.hindisong.MusicActivity;
import com.itnation.hindisong.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {

    Context context;
    ArrayList<SingerModel>singerModelArrayList;

    public SingerAdapter(Context context, ArrayList<SingerModel> singerModelArrayList) {
        this.context = context;
        this.singerModelArrayList = singerModelArrayList;
    }

    @NonNull
    @Override
    public SingerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingerAdapter.ViewHolder holder, int position) {

        SingerModel singerModel = singerModelArrayList.get(position);

        String singerName = singerModel.getSingerName();
        String singerImg = singerModel.getSingerImg();

        holder.singerName.setText(singerName);


        Picasso.get().load(singerImg).
                placeholder(R.drawable.load).
                into(holder.singerImg);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (singerName != null){

                    MusicActivity.categoryName=singerName;
                    MusicActivity.libraryName=singerName;
                    Intent intent = new Intent(context, MusicActivity.class);
                    context.startActivity(intent);



                }else {

                    Toast.makeText(context,"Item not fond",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return singerModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView singerName;
        ImageView singerImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            singerImg=itemView.findViewById(R.id.singerImg);
            singerName=itemView.findViewById(R.id.singerName);



        }
    }
}

package com.itnation.hindisong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itnation.hindisong.Model.CategoryModel;
import com.itnation.hindisong.MusicActivity;
import com.itnation.hindisong.R;
import com.itnation.hindisong.VideoPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoryModel>categoryModelArrayList;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModelArrayList) {
        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.library_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        CategoryModel categoryModel= categoryModelArrayList.get(position);

        String catName = categoryModel.getCatName();
        String catImg = categoryModel.getCatImgUrl();

        holder.catName.setText(catName);


        Picasso.get().load(catImg).
                placeholder(R.drawable.load).
                into(holder.catImg);


        /*Random random = new Random();
        int color= Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        holder.backColor.setBackgroundColor(color);

         */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (catName != null){

                    MusicActivity.categoryName=catName;
                    MusicActivity.libraryName=catName;
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
        return categoryModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView catName;
        ImageView catImg;
        RelativeLayout backColor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catImg= itemView.findViewById(R.id.catImg);
            catName= itemView.findViewById(R.id.catName);
            backColor= itemView.findViewById(R.id.backColor);
        }
    }

    private void generateColor(){




    }
}

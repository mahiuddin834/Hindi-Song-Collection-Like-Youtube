package com.itnation.hindisong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.InterstitialAd;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.hindisong.Adapter.HomeAdapter;
import com.itnation.hindisong.Adapter.PlayerItemAdapter;
import com.itnation.hindisong.Model.HomeModel;
import com.itnation.hindisong.Model.PlayerItemModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoPlayerActivity extends AppCompatActivity {



    TextView headerTittle;
    YouTubePlayerView youTubePlayerView;
    RecyclerView playerRV;
    LinearLayout backHomebtn;
    PlayerItemAdapter playerItemAdapter;
    ArrayList<PlayerItemModel> playerItemModelArrayList;


    boolean isFullScreen = false;

    public static String video_id = "";

    DatabaseReference databaseReference;

    public static String repo ="";
    public static String tittle ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);


        youTubePlayerView = findViewById(R.id.youtube_player_view);
        playerRV = findViewById(R.id.playerRV);
        backHomebtn = findViewById(R.id.backHomebtn);
        headerTittle = findViewById(R.id.headerTittle);


        getLifecycle().addObserver(youTubePlayerView);

        backHomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(VideoPlayerActivity.this, MainActivity.class));
                finish();

            }
        });

        headerTittle.setText(tittle);






// full screen mode ----------------



      /*  if (isFullScreen) {
            youTubePlayerView.wrapContent();
        } else {
            youTubePlayerView.matchParent();
        }
        isFullScreen = !isFullScreen;

       */

        if (video_id != null){

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = video_id;
                    youTubePlayer.loadVideo(videoId, 0);


                }
            });


        }








        homeItem();




    }//------------------------

    private void homeItem() {


        databaseReference = FirebaseDatabase.getInstance().getReference(repo);
        playerRV.setLayoutManager(new LinearLayoutManager(this));

        playerItemModelArrayList = new ArrayList<>();

        playerItemAdapter = new PlayerItemAdapter(this,playerItemModelArrayList);
        playerRV.setAdapter(playerItemAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    PlayerItemModel playerItemModel = dataSnapshot.getValue(PlayerItemModel.class);
                    playerItemModelArrayList.add(playerItemModel);

                    //animationView.setVisibility(View.GONE);

                }

                playerItemAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(VideoPlayerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VideoPlayerActivity.this, MainActivity.class));
        finish();

    }


    //======================================
}
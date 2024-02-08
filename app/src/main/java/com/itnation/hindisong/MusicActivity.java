package com.itnation.hindisong;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.hindisong.Adapter.HomeAdapter;
import com.itnation.hindisong.Adapter.MusicAdapter;
import com.itnation.hindisong.Model.HomeModel;
import com.itnation.hindisong.Model.MusicModel;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {


    public static String categoryName= "";
    public static String libraryName= "";
    TextView libName;
    RecyclerView musicRV;
    MusicAdapter musicAdapter;
    ArrayList<MusicModel>musicModelArrayList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        libName = findViewById(R.id.libName);
        musicRV = findViewById(R.id.musicRV);

        libName.setText(libraryName);



        databaseReference = FirebaseDatabase.getInstance().getReference(categoryName);
        musicRV.setLayoutManager(new LinearLayoutManager(MusicActivity.this));

        musicModelArrayList = new ArrayList<>();

        musicAdapter= new MusicAdapter(MusicActivity.this, musicModelArrayList);
        musicRV.setAdapter(musicAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    MusicModel musicModel = dataSnapshot.getValue(MusicModel.class);
                    musicModelArrayList.add(musicModel);

                    //animationView.setVisibility(View.GONE);

                }

                musicAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MusicActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
}
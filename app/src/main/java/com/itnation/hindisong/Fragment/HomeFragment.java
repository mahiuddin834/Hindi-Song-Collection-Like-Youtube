package com.itnation.hindisong.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.hindisong.Adapter.HomeAdapter;
import com.itnation.hindisong.Model.HomeModel;
import com.itnation.hindisong.R;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {

    RecyclerView homeRv;

    HomeAdapter homeAdapter;
    ArrayList<HomeModel>homeModelArrayList;

    DatabaseReference databaseReference;

    LottieAnimationView animationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);
        homeRv=v.findViewById(R.id.homeRv);
        animationView =v.findViewById(R.id.animationView);



        homeItem();






        return v;
    }//---------------------------

    private void homeItem(){





        databaseReference = FirebaseDatabase.getInstance().getReference("home");
        homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        homeModelArrayList = new ArrayList<>();

        
        homeAdapter= new HomeAdapter(getContext(),homeModelArrayList);


        homeRv.setAdapter(homeAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    HomeModel homeModel = dataSnapshot.getValue(HomeModel.class);
                    homeModelArrayList.add(homeModel);

                    animationView.setVisibility(View.GONE);

                }

                homeAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



    }

}
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itnation.hindisong.Adapter.HomeAdapter;
import com.itnation.hindisong.Adapter.TrendAdapter;
import com.itnation.hindisong.Model.HomeModel;
import com.itnation.hindisong.Model.TrendModel;
import com.itnation.hindisong.R;

import java.util.ArrayList;

public class TrendFragment extends Fragment {

   RecyclerView trndRV;

   DatabaseReference databaseReference;
   TrendAdapter trendAdapter;
   ArrayList<TrendModel>trendModelArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_trend, container, false);

        trndRV = v.findViewById(R.id.trndRV);

        trendItem();


        return v;
    }

    private void trendItem(){



        databaseReference = FirebaseDatabase.getInstance().getReference("Trends");
        trndRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        trendModelArrayList = new ArrayList<>();

        trendAdapter= new TrendAdapter(getContext(),trendModelArrayList);
        trndRV.setAdapter(trendAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    TrendModel trendModel = dataSnapshot.getValue(TrendModel.class);
                    trendModelArrayList.add(trendModel);

                    //animationView.setVisibility(View.GONE);

                }

                trendAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });




    }

}
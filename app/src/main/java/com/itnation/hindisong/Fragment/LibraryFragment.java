package com.itnation.hindisong.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.itnation.hindisong.Adapter.CategoryAdapter;
import com.itnation.hindisong.Adapter.HomeAdapter;
import com.itnation.hindisong.Adapter.SingerAdapter;
import com.itnation.hindisong.Model.CategoryModel;
import com.itnation.hindisong.Model.HomeModel;
import com.itnation.hindisong.Model.SingerModel;
import com.itnation.hindisong.R;

import java.util.ArrayList;

public class LibraryFragment extends Fragment {

    RecyclerView catRV, singerRV;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryModel>categoryModelArrayList;

    SingerAdapter singerAdapter;
    ArrayList<SingerModel>singerModelArrayList;

    DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_library, container, false);

       catRV = view.findViewById(R.id.catRV);
       singerRV = view.findViewById(R.id.singerRV);



       singerList();

       catItem();



        return view;
    }//-----------------------------


    private void singerList(){


        databaseReference = FirebaseDatabase.getInstance().getReference("Singer List");
        singerRV.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));

        singerModelArrayList = new ArrayList<>();

        singerAdapter= new SingerAdapter(getContext(),singerModelArrayList);
        singerRV.setAdapter(singerAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    SingerModel singerModel = dataSnapshot.getValue(SingerModel.class);
                    singerModelArrayList.add(singerModel);

                    //animationView.setVisibility(View.GONE);

                }

                singerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });




    }//-------------------------

    private  void catItem(){


        databaseReference = FirebaseDatabase.getInstance().getReference("Browse All");
        catRV.setLayoutManager(new GridLayoutManager(getContext(), 2));

        categoryModelArrayList = new ArrayList<>();

        categoryAdapter= new CategoryAdapter(getContext(),categoryModelArrayList);
        catRV.setAdapter(categoryAdapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot :snapshot.getChildren()){


                    CategoryModel categoryModel = dataSnapshot.getValue(CategoryModel.class);
                    categoryModelArrayList.add(categoryModel);

                    //animationView.setVisibility(View.GONE);

                }

                categoryAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



    }
}
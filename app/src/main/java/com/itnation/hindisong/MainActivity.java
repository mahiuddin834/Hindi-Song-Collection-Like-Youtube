package com.itnation.hindisong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.itnation.hindisong.Fragment.HomeFragment;
import com.itnation.hindisong.Fragment.LibraryFragment;
import com.itnation.hindisong.Fragment.TrendFragment;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {


    SmoothBottomBar bottomBar;
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomBar= findViewById(R.id.bottomBar);
        frameLayout= findViewById(R.id.frameLayout);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());
        fragmentTransaction.commit();

        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (i){

                    case 0:

                        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());

                        break;
                    case 1:

                        fragmentTransaction.replace(R.id.frameLayout, new TrendFragment());

                        break;
                    case 2:

                        fragmentTransaction.replace(R.id.frameLayout, new LibraryFragment());




                        break;

                }
                fragmentTransaction.commit();
                return false;
            }
        });

    }
}
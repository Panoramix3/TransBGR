package com.example.transbgr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private RecyclerView recyclerView;
    private ArtistesAdapter mAdapter;
    List<Artiste> artistes;

    // STEP 1 : make a reference to the database...
    private FirebaseDatabase mFireDataBase;
    private DatabaseReference mArtistesDatabaseReference;

    //STEP 4: child event lister.
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add fragment

        adapter.AddFragment(new Artiste_list(),"Artistes");
        adapter.AddFragment(new Artiste_detail(),"Maps");
        //adapter.AddFragment (new Artiste_maps(),"3");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //tablayout.getTabAt(0);
        //tablayout.getTabAt(1);
        //tablayout.getTabAt(2);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setElevation(0);

      //getSupportActionBar().hide();
      Artiste_list mArtiste_list = new Artiste_list();
      loadFragment(mArtiste_list);
    }

    public void loadFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layoutmain, fragment);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }




}

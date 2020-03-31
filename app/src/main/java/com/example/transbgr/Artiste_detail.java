package com.example.transbgr;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Artiste_detail extends Fragment {



    public static Artiste_detail newInstance() {
        return new Artiste_detail();
    }

    View view;
    private RecyclerView recyclerView;
    private ArtistesAdapter mAdapter;
    List<Artiste> artistes;

    // STEP 1 : make a reference to the database...
    private FirebaseDatabase mFireDataBase;
    private DatabaseReference mArtistesDatabaseReference;

    //STEP 4: child event lister.
    private ChildEventListener mChildEventListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.artiste_detail_fragment, container, false);
        return view;


    }
}

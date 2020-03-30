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

        // STEP 2 : access the DB...
        mFireDataBase = FirebaseDatabase.getInstance();

        // STEP 2.1: and from the DB, get a reference on the child node "artistes"
        mArtistesDatabaseReference = mFireDataBase.getReference();

        // STEP 2.2: get the recycler view
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // STEP 2.3: create and set the adapter
        artistes = new ArrayList<>();
        mAdapter = new ArtistesAdapter(getContext(), artistes, this);
        recyclerView.setAdapter(mAdapter);

        // STEP 3: enable adding a artiste to Firebase
        activateAddingArtiste();

        // STEP 4: listen to any change on the DB
        enableUpdatesFromDB();

        // STEP 5: Enable removing a artiste
        activateRemovingArtiste();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    public void onArtisteSpotifySelected(Artiste artiste) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(artiste.getFields().getSpotify()));
        intent.putExtra(Intent.EXTRA_REFERRER,
                Uri.parse("android-app://" + getContext().getPackageName()));
        startActivity(intent);

        //loadFragment(new Artiste_detail());
    }

}

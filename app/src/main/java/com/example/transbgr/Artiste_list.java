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

import android.util.Log;
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

public class Artiste_list extends Fragment implements ArtistesAdapter.ArtistesAdapterListener {

    public static Artiste_list newInstance() {
        return new Artiste_list();
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
        view = inflater.inflate(R.layout.artiste_list_fragment, container, false);

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


    // STEP 3: enable adding a artiste to Firebase
    public void activateAddingArtiste() {
       /* FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //STEP 3.1 : create a artiste
                Artiste artiste = new Artiste("Jacky", "https://s.yimg.com/ny/api/res/1.2/VLsXGJZMY_L.Rlgs4eHu5w--~A/YXBwaWQ9aGlnaGxhbmRlcjtzbT0xO3c9ODAw/http://media.zenfs.com/en-SG/homerun/ybrand.cinema.com.my/b4dc114cce3aaf62cc056b26bf1ce1ce", "+186 9");
                //STEP 3.2: and sync it using FireBase. push() creates a unique ID, and setValue serializes the object to sync it with the server
                mArtistesDatabaseReference.push().setValue(artiste);
                // no need to update the recyclerview, it will be updated by Firebase updates
            }
        });*/
    }

    // STEP 4: listen to any change on the DB
    public void enableUpdatesFromDB() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Artiste artiste = dataSnapshot.getValue(Artiste.class);
                    Log.v("CHILDADDED", artiste.toString());
                    // don't forget to set the key to identify the Artiste!
                    artiste.setUid(dataSnapshot.getKey());
                    artistes.add(artiste);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Artiste artiste = dataSnapshot.getValue(Artiste.class);
                    artiste.setUid(dataSnapshot.getKey());
                    mAdapter.updateArtiste(artiste);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Artiste msg = dataSnapshot.getValue(Artiste.class);
                    //don't forget to set the key to identify the Artiste!
                    mAdapter.removeArtisteWithId(dataSnapshot.getKey());
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            mArtistesDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    // STEP 5: Enable removing a artiste
    public void activateRemovingArtiste() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // STEP 5.1: get a reference to the selected entity
                Artiste remove = mAdapter.artisteListFiltered.get(viewHolder.getAdapterPosition());

                // STEP 5.2: use it's unique ID to remove it from Firebase
                mArtistesDatabaseReference.child(remove.getUid()).removeValue();

                Toast.makeText(getContext(), "Artiste deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void onArtisteSelected(Artiste artiste) {
        /*Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(artiste.getFields().getSpotify()));
        intent.putExtra(Intent.EXTRA_REFERRER,
                Uri.parse("android-app://" + getContext().getPackageName()));
        startActivity(intent);*/

        //loadFragment(new Artiste_detail());
    }

    public void onLike(Artiste artiste){
        // STEP 6.1: Updating the field in the class
        artiste.getFields().setLikes(artiste.getFields().getLikes());

        mArtistesDatabaseReference.child(artiste.getUid()).child("fields").child("likes").setValue(artiste.getFields().getLikes());

        // STEP 6.2: Updating the field on the Firebase DB
        //mContactsDatabaseReference.child(contact.getUid()).child("likes").setValue(contact.getLikes());
    }

    @Override
    public void onSpotify(Artiste artiste) {
        if(artiste.getFields().getSpotify() != null){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(artiste.getFields().getSpotify()));
            intent.putExtra(Intent.EXTRA_REFERRER,
                    Uri.parse("android-app://" + getContext().getPackageName()));
            startActivity(intent);
        }
        else{
            Toast.makeText(getContext(), "Spotify indisponible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDeezer(Artiste artiste) {}

    public void loadFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getChildFragmentManager();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.recycler_view, fragment);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }

}

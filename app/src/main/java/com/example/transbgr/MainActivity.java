package com.example.transbgr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ArtistesAdapter.ArtistesAdapterListener{

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
        getSupportActionBar().hide();

        // STEP 2 : access the DB...
        mFireDataBase = FirebaseDatabase.getInstance();

        // STEP 2.1: and from the DB, get a reference on the child node "artistes"
        mArtistesDatabaseReference = mFireDataBase.getReference().child("artistes");

        // STEP 2.2: get the recycler view
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // STEP 2.3: create and set the adapter
        artistes = new ArrayList<>();
        mAdapter = new ArtistesAdapter(getApplicationContext(), artistes, this);
        recyclerView.setAdapter(mAdapter);

        // STEP 3: enable adding a artiste to Firebase
        activateAddingArtiste();

        // STEP 4: listen to any change on the DB
        enableUpdatesFromDB();

        // STEP 5: Enable removing a artiste
        activateRemovingArtiste();

    }

    // STEP 3: enable adding a artiste to Firebase
    public void activateAddingArtiste() {
        /*FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFAB);
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
                    // don't forget to set the key to identify the Artiste!
                    artiste.setUid(dataSnapshot.getKey());
                    artistes.add(artiste);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Artiste artiste = dataSnapshot.getValue(Artiste.class);
                    artiste.setUid(dataSnapshot.getKey());
                    //mAdapter.(artiste);
                    //mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    //Artiste msg = dataSnapshot.getValue(Artiste.class);
                    // don't forget to set the key to identify the Artiste!
                    //mAdapter.removeArtisteWithId(dataSnapshot.getKey());
                    //mAdapter.notifyDataSetChanged();
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

                Toast.makeText(MainActivity.this, "Artiste deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onArtisteSelected(Artiste artiste) {

    }
}

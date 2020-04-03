package com.example.transbgr;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.Resources;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Artiste_detail extends Fragment implements OnMapReadyCallback {



    public static Artiste_detail newInstance() {
        return new Artiste_detail();
    }

    MapView mapView;
    private GoogleMap mMap;
    List<Artiste> artistes;
    List<ArtisteMapItem> artisteMapItems;
    private ClusterManager<ArtisteMapItem> clusterManager;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.artiste_detail_fragment, container, false);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        MapsInitializer.initialize(getActivity().getApplicationContext());
        mapView.getMapAsync(this);
        mapView.onResume();
        artistes = ((MainActivity)getActivity()).getArtistes();
        return view;


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this.getActivity(), R.raw.mapstyle));



            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-34, 151)));
            setClusterManager();

            if (!success) {
                Log.e("mapReady", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("mapReady", "Can't find style. Error: ", e);
        }
    }

    public void setArtisteMapItems(){
        Log.v("CLUSTER", "CALLED");
        for(Artiste a : artistes){
            if(a.getGeometry() != null){
                ArtisteMapItem artisteMapItem = new ArtisteMapItem(a.getGeometry().getCoordinates().get(1),
                        a.getGeometry().getCoordinates().get(0),
                        a.getFields().getArtistes());
                Log.v("CLUSTER", a.toString());
                Log.v("CLUSTER", a.getGeometry().getCoordinates().get(1)+" ; " + a.getGeometry().getCoordinates().get(0));

                clusterManager.addItem(artisteMapItem);
            }
        }
    }
    public void setClusterManager(){
        clusterManager = new ClusterManager<>(Objects.requireNonNull(getContext()), mMap);

        mMap.setOnCameraIdleListener(clusterManager);
        setArtisteMapItems();
    }
}

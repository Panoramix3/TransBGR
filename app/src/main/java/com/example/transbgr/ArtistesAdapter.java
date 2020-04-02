package com.example.transbgr;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

    public class ArtistesAdapter extends RecyclerView.Adapter<ArtistesAdapter.MyViewHolder>
            implements Filterable {
        private Context context;
        List<Artiste> artisteList;
        List<Artiste> artisteListFiltered;
        private ArtistesAdapterListener listener;
        Dialog mDialog;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            private LinearLayout item_artiste;

            public MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.name);
                item_artiste = (LinearLayout) itemView.findViewById(R.id.detail_item);



                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // send selected artiste in callback
                        listener.onArtisteSelected(artisteList.get(getAdapterPosition()));
                    }
                });

            }
        }


        public ArtistesAdapter(Context context, List<Artiste> artisteList, ArtistesAdapterListener listener) {
            this.context = context;
            this.listener = listener;
            this.artisteList = artisteList;
            this.artisteListFiltered = artisteList;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;

            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.artiste_item, parent, false);

            final MyViewHolder vHolder = new MyViewHolder(itemView);

            // Dialog

            mDialog = new Dialog(context);
            mDialog.setContentView(R.layout.detail_contact);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



            vHolder.item_artiste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("CLICKED", ""+artisteList.get(vHolder.getAdapterPosition()).getFields().get_1ere_salle());
                    Log.v("CLICKED", ""+artisteList.get(vHolder.getAdapterPosition()).getFields().getLikes());
                    


                    TextView name = (TextView) mDialog.findViewById(R.id.detail_name);
                    TextView annee = (TextView) mDialog.findViewById(R.id.detail_annee);
                    TextView pays = (TextView) mDialog.findViewById(R.id.detail_pays);
                    TextView premiere_date = (TextView) mDialog.findViewById(R.id.detail_1_date);
                    Button spotify = (Button) mDialog.findViewById(R.id.spotify);
                    Button like = (Button) mDialog.findViewById(R.id.like);

                    name.setText(artisteList.get(vHolder.getAdapterPosition()).getFields().getArtistes());
                    pays.setText(artisteList.get(vHolder.getAdapterPosition()).getFields().getOrigine_pays1());
                    annee.setText(artisteList.get(vHolder.getAdapterPosition()).getFields().getAnnee());
                    premiere_date.setText(artisteList.get(vHolder.getAdapterPosition()).getFields().get_1ere_date());

                    long counter = artisteList.get(vHolder.getAdapterPosition()).getFields().getLikes();
                    like.setText("Like ("+counter+")");
                    like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            long counter = artisteList.get(vHolder.getAdapterPosition()).getFields().getLikes() + 1;
                            artisteList.get(vHolder.getAdapterPosition()).getFields().setLikes(counter);
                            like.setText("Like ("+counter+")");
                            like.setClickable(false);
                            listener.onLike(artisteList.get(vHolder.getAdapterPosition()));
                        }
                    });

                    spotify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onSpotify(artisteList.get(vHolder.getAdapterPosition()));
                        }
                    });

                    mDialog.show();
                }
            });

            return vHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, final int position) {
            final Artiste artiste = artisteListFiltered.get(position);

            if (artiste.getFields().getArtistes() != null) {
                viewHolder.name.setText(artiste.getFields().getArtistes());

            }

        }

        @Override
        public int getItemCount() {
            return artisteListFiltered.size();
        }

        public void removeArtisteWithId(String uid) {

         artisteListFiltered.removeIf(artiste ->  (artiste.getUid().equals(uid)) );
         artisteList.removeIf(artiste ->  (artiste.getUid().equals(uid)) );
         }


        public void updateArtiste(Artiste updatedArtiste) {

            Artiste oldArtiste = artisteListFiltered.stream()
                    .filter(c -> (updatedArtiste.getUid().equals(c.getUid())))
                    .findFirst()
                    .orElse(null);
            if (oldArtiste != null) {
                artisteListFiltered.set(artisteListFiltered.indexOf(oldArtiste), updatedArtiste);

            }

            oldArtiste = artisteList.stream()
                    .filter(c -> (updatedArtiste.getUid().equals(c.getUid())))
                    .findFirst()
                    .orElse(null);
            if (oldArtiste != null)
                artisteList.set(artisteList.indexOf(oldArtiste),updatedArtiste);

        }


        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        artisteListFiltered = artisteList;
                    } else {
                        List<Artiste> filteredList = new ArrayList<>();
                        for (Artiste row : artisteList) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.getFields().getArtistes().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }

                        artisteListFiltered = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = artisteListFiltered;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    artisteListFiltered = (ArrayList<Artiste>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        public interface ArtistesAdapterListener {
            void onArtisteSelected(Artiste artiste);
            void onLike(Artiste artiste);
            void onSpotify(Artiste artiste);
            void onDeezer(Artiste artiste);
        }
}

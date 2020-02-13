package com.example.transbgr;

public class Artiste {

    // this uid is required if we want to identify which Artistes to remove/update
    // it's value is provided by Firebase
    String uid;
    String artistes;
    String origine_pays;
    String origine_ville;
    int annee;
    String edition;
    String spotify;
    String deezer;
    String date;
    String premiere_date;
    String premiere_salle;
    String deuxieme_date;
    String deuxieme_salle;
    String troisieme_date;
    String troisieme_salle;
    String quatrieme_date;
    String quatrieme_salle;
    String cinquieme_date;
    String cinquieme_salle;
    String sixieme_date;
    String sixieme_salle;
    double geo_point_2D;
    String label_en;

    public Artiste() {

    }

    public Artiste(String name, String image, String phone) {

        this.artistes = artistes;
        this.origine_pays = origine_pays;
        this.origine_ville = origine_ville;
        this.annee = annee;
        this.edition = edition;
        this.spotify = spotify;
        this.deezer = deezer;
        this.date = date;
        this.premiere_date = premiere_date;
        this.premiere_salle = premiere_salle;
        this.deuxieme_date = deuxieme_date;
        this.deuxieme_salle = deuxieme_salle;
        this.troisieme_date = troisieme_date;
        this.troisieme_salle = troisieme_salle;
        this.quatrieme_date = quatrieme_date;
        this.quatrieme_salle = quatrieme_salle;
        this.cinquieme_date = cinquieme_date;
        this.cinquieme_salle = cinquieme_salle;
        this.sixieme_date = sixieme_date;
        this.sixieme_salle = sixieme_salle;
        this.geo_point_2D = geo_point_2D;
        this.label_en = label_en;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getArtistes() {
        return artistes;
    }

    public void setArtistes(String artistes) {
        this.artistes = artistes;
    }

    public String getOrigine_pays() {
        return origine_pays;
    }

    public void setOrigine_pays(String origine_pays) {
        this.origine_pays = origine_pays;
    }

    public String getOrigine_ville() {
        return origine_ville;
    }

    public void setOrigine_ville(String origine_ville) {
        this.origine_ville = origine_ville;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    public String getDeezer() {
        return deezer;
    }

    public void setDeezer(String deezer) {
        this.deezer = deezer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPremiere_date() {
        return premiere_date;
    }

    public void setPremiere_date(String premiere_date) {
        this.premiere_date = premiere_date;
    }

    public String getPremiere_salle() {
        return premiere_salle;
    }

    public void setPremiere_salle(String premiere_salle) {
        this.premiere_salle = premiere_salle;
    }

    public String getDeuxieme_date() {
        return deuxieme_date;
    }

    public void setDeuxieme_date(String deuxieme_date) {
        this.deuxieme_date = deuxieme_date;
    }

    public String getDeuxieme_salle() {
        return deuxieme_salle;
    }

    public void setDeuxieme_salle(String deuxieme_salle) {
        this.deuxieme_salle = deuxieme_salle;
    }

    public String getTroisieme_date() {
        return troisieme_date;
    }

    public void setTroisieme_date(String troisieme_date) {
        this.troisieme_date = troisieme_date;
    }

    public String getTroisieme_salle() {
        return troisieme_salle;
    }

    public void setTroisieme_salle(String troisieme_salle) {
        this.troisieme_salle = troisieme_salle;
    }

    public String getQuatrieme_date() {
        return quatrieme_date;
    }

    public void setQuatrieme_date(String quatrieme_date) {
        this.quatrieme_date = quatrieme_date;
    }

    public String getQuatrieme_salle() {
        return quatrieme_salle;
    }

    public void setQuatrieme_salle(String quatrieme_salle) {
        this.quatrieme_salle = quatrieme_salle;
    }

    public String getCinquieme_date() {
        return cinquieme_date;
    }

    public void setCinquieme_date(String cinquieme_date) {
        this.cinquieme_date = cinquieme_date;
    }

    public String getCinquieme_salle() {
        return cinquieme_salle;
    }

    public void setCinquieme_salle(String cinquieme_salle) {
        this.cinquieme_salle = cinquieme_salle;
    }

    public String getSixieme_date() {
        return sixieme_date;
    }

    public void setSixieme_date(String sixieme_date) {
        this.sixieme_date = sixieme_date;
    }

    public String getSixieme_salle() {
        return sixieme_salle;
    }

    public void setSixieme_salle(String sixieme_salle) {
        this.sixieme_salle = sixieme_salle;
    }

    public double getGeo_point_2D() {
        return geo_point_2D;
    }

    public void setGeo_point_2D(double geo_point_2D) {
        this.geo_point_2D = geo_point_2D;
    }

    public String getLabel_en() {
        return label_en;
    }

    public void setLabel_en(String label_en) {
        this.label_en = label_en;
    }

}

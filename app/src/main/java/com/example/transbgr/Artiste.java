package com.example.transbgr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "datasetid",
        "fields",
        "geometry",
        "record_timestamp",
        "recordid"
})
public class Artiste {

    private String Uid;
    private String datasetid;
    private Fields fields;
    private Geometry geometry;
    private String recordTimestamp;
    private String recordid;

    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getRecordTimestamp() {
        return recordTimestamp;
    }

    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String toString(){
        if (this.fields != null) {
            return "Nom : " + this.fields.getArtistes() +
                    "\nSpotify : " + this.fields.getSpotify() +
                    "\nDeezer : " + this.fields.getDeezer() +
                    "\nAnnée : " + this.fields.getAnnee() +
                    "\nOrigine (pays) : " + this.fields.getOrigine_pays1() +
                    "\nUId : " + this.getUid();
        } else {
            return "Erreur lors de l'affichage des informations concernant l'artiste";
        }
    }
}